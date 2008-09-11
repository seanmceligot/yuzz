package test.snuvy;

import org.snuvy.ColType;
import org.snuvy.DatabaseColumn;
import org.snuvy.DbIndex;
import org.snuvy.DbRow;
import org.snuvy.DbTable;
import org.snuvy.Dbm;
import org.snuvy.FunctionalDbm;
import org.snuvy.Schema;
import org.snuvy.TableIterator;
import org.snuvy.TableSequence;

import java.io.IOException;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.yuzz.functor.Procedure;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseException;

public class DbTest extends TestCase {
	private static final String HELLO_WORLD = "Hello World!";
  private static final String HELLO = "Hello";
  private static final String TEXT = "text";
  private static final String SUBJECT = "subject";
  //private static final String ID = "id";
	
	public final void testLongPkTable() throws DatabaseException {
  	Dbm dbm = new Dbm();
  	dbm.open();
    long rowid = -1L;
  	try {
  		Schema schema = createSchema("primary");
  		DbTable table = dbm.getTable(schema);
  		{
        table.saveRow(createRow(table, "first", "first"));
  		DbRow row = createRow(table, HELLO, HELLO_WORLD);
  		table.saveRow(row);
      rowid = row.getRowId();
      table.saveRow(createRow(table, "last", "last"));
  		}
  		{
  		DbRow row = table.getRow(rowid);
  		String subject = row.getString(SUBJECT);
  		String text = row.getString(TEXT);
      Assert.assertEquals(rowid, row.getRowId());
      Assert.assertEquals(HELLO, subject);
      Assert.assertEquals(HELLO_WORLD, text);
  		}
      {
        TableIterator tit = table.iterator();
        for(DbRow row = tit.next(); row != null; row = tit.next()) {
          String subject = row.getString(SUBJECT);
          String text = row.getString(TEXT);
          System.out.println("it: "+row.getRowId()+":"+subject+":"+text);
        }
      }
  	} finally {
  		try {dbm.close();} catch (Throwable th){}
  	}
  }


  private DbRow createRow(DbTable table, String subject, String msg) {
    DbRow row = table.newRow();
    row.setString(SUBJECT, subject);
    row.setString(TEXT, msg);
    return row;
  }


  /**
   * @throws DatabaseException
   * */
  public final void testSecondaryPkTable() throws DatabaseException {
    Dbm dbm = new Dbm();
    dbm.open();
    long rowid = -1L;
    try {
      Schema schema = createSchema("secondary");
      DbIndex bySubject = new DbIndex(SUBJECT, ColType.STRING);
      schema.addIndex(bySubject);

      DbTable table = dbm.getTable(schema);
      {
        table.saveRow(createRow(table, "first", "first"));
        DbRow row = createRow(table, HELLO, HELLO_WORLD);
        table.saveRow(row);
        rowid = row.getRowId();
        table.saveRow(createRow(table, "last", "last"));
      }
      {
      DbRow row = table.getRow(bySubject, new DatabaseColumn(HELLO));
      String subject = row.getString(SUBJECT);
      String text = row.getString(TEXT);
      System.out.println("get row by subject: "+row.getRowId()+":"+row.getString(SUBJECT));
      Assert.assertEquals(rowid, row.getRowId());
      Assert.assertEquals(HELLO, subject);
      Assert.assertEquals(HELLO_WORLD, text);
      }
    } finally {
      dbm.close();
    }
	}


  private Schema createSchema(String tableName) {
    Schema schema = new Schema(tableName);
    schema.addColumn(SUBJECT, ColType.STRING);
    schema.addColumn(TEXT, ColType.STRING);
    return schema;
  }
  class PrintRow extends Procedure.Proc1<DbRow> {
    public void f(DbRow row) {
      long rowId = row.getRowId();
      String subject = row.getString(SUBJECT);
      String text = row.getString(TEXT);      
      System.out.println(rowId+": "+subject+": "+text);
    }
  }
  public final void testFunctors() throws DatabaseException {
    Dbm dbm = new Dbm();
    dbm.open();
    long rowid = -1L;
    try {
      Schema schema = createSchema("secondary");
      DbIndex bySubject = new DbIndex(SUBJECT, ColType.STRING);
      schema.addIndex(bySubject);
      DbTable table = dbm.getTable(schema);
      {
        table.saveRow(createRow(table, "first", "first"));
        DbRow row = createRow(table, HELLO, HELLO_WORLD);
        table.saveRow(row);
        rowid = row.getRowId();
        table.saveRow(createRow(table, "xxzz", "xxzz"));
        table.saveRow(createRow(table, "xxyy", "xxyy 1"));
        table.saveRow(createRow(table, "xxyy", "xxyy 2"));
        table.saveRow(createRow(table, "zzzz", "zzzz"));
        table.saveRow(createRow(table, "last", "last"));
      }
      /*System.out.println("FunctionalDbm.firstToLast");
      FunctionalDbm.firstToLast(table, table.byPk(), new PrintRow());
      System.out.println("FunctionalDbm.lastToFirst");
      FunctionalDbm.lastToFirst(table, table.byPk(), new PrintRow());
      System.out.println("FunctionalDbm.firstToLast subject");
      FunctionalDbm.firstToLast(table, bySubject, new PrintRow());
      System.out.println("FunctionalDbm.lastToFirst subject");
      FunctionalDbm.lastToFirst(table, bySubject, new PrintRow());
      
      System.out.println("FunctionalDbm.findMatching xx");
      FunctionalDbm.findMatching(table, bySubject, new DatabaseColumn("xx"), new PrintRow());
      //matches all xxyy, xxyzz
      
      System.out.println("FunctionalDbm.findMatching xxyy");
      FunctionalDbm.findMatching(table, bySubject, new DatabaseColumn("xxyy"), new PrintRow());
      //matches all xxyy
      */
    } finally {
      try {dbm.close();} catch (Throwable th){}
    }
  }
  public static void main(String[] args) throws DatabaseException {
  }
  }
