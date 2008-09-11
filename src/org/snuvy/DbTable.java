package org.snuvy;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.yuzz.functor.Tuples;
import org.yuzz.functor.Fun.F;

import com.sleepycat.je.Cursor;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.OperationStatus;

public class DbTable {

	private final Dbm _dbm;
	private final Schema _schema;
  
	public DbTable(Dbm dbm, Schema schema) {
		_dbm = dbm;
		_schema = schema;
	}

	public DbRow newRow() {
		return new DbRow(_schema);
	}
	public void saveRow(DbRow row) throws DatabaseException {
		for (String name : _schema) {
      ColType ct = _schema.getType(name);
      DatabaseColumn value;
      if (ct == ColType.AUTOLONG) {
        value = new DatabaseColumn();
        TableSequence sequence = getSequence(name);
        long next = sequence.next();
        value.setLong(next);
        row.setEntry(name, value);
      } else {
        value = row.getEntry(name);
      }
      DatabaseColumn pk = row.getRowIdEntry();
			Database db = getDb(true, false, name);
			OperationStatus status = db.put(null, pk.getEntry(), value.getEntry());
			if (status != OperationStatus.SUCCESS) {
				Dbm.log("status: "+status);
			}
      _dbm.close(name);
		}
    indexRow(row);
	}
  private Database getDb(boolean allowCreate, boolean allowDups, String colname) throws DatabaseException {
    Database db = _dbm.open(allowCreate, allowDups, _schema.getName(), colname);
    return db;
  }
  private Database getIndexDb(String ixname) throws DatabaseException {
    Database db = getDb(true, true, "IX_"+ixname);
    return db;
  }
	private void indexRow(DbRow row) throws DatabaseException {
    DatabaseColumn pk = row.getRowIdEntry();
    for (Iterator<String> ixnames = _schema.indexes(); ixnames.hasNext();) {
      String ixname = ixnames.next();
      DbIndex index = _schema.getIndex(ixname);
      DatabaseColumn fk = index.fk(this, row);
      Database db = getIndexDb(ixname);
      OperationStatus status = db.put(null, fk.getEntry(), pk.getEntry());
      if (status != OperationStatus.SUCCESS) {
        Dbm.log("status: "+status);
      }
    }
      
    
  }

  public DbRow getRow(DatabaseColumn pk) throws DatabaseException {
		DbRow row = new DbRow(_schema);
		String tableName = _schema.getName();
		for (String name : _schema) {
			DatabaseColumn value = new DatabaseColumn();
			Database db = getDb(false, false, name);
			OperationStatus status = db.get(null, pk.getEntry(), value.getEntry(), null);
			if (status != OperationStatus.SUCCESS) {
				Dbm.log("status: "+status);
			} else {
			  row.setEntry(name, value);
      }
		}
		return row;
	}
	public Cursor createCursor() throws DatabaseException {
		Database db = getIndexDb(Schema.ROWID);
		Cursor cursor = db.openCursor(null, null);
		return cursor;
	}
  public Cursor createCursor(String indexName) throws DatabaseException {
    Database db = getIndexDb(indexName);
    Cursor cursor = db.openCursor(null, null);
    return cursor;
  }
  public DbRow searchRow(DatabaseColumn pk, Cursor cursor) throws DatabaseException {
    DatabaseColumn value = new DatabaseColumn();
        if (cursor.getSearchKey(pk.getEntry(), value.getEntry(), LockMode.DEFAULT) ==
            OperationStatus.SUCCESS) {
          DbRow row = getRow(pk);
          return row;
        }
        return null;
  }

	public DbRow nextRow(DatabaseColumn pk, Cursor cursor) throws DatabaseException {
		DatabaseColumn value = new DatabaseColumn();
        if (cursor.getNext(pk.getEntry(), value.getEntry(), LockMode.DEFAULT) ==
            OperationStatus.SUCCESS) {
        	DbRow row = getRow(pk);
        	return row;
        }
        return null;
	}

  public TableSequence getSequence(String name) throws DatabaseException {
    return _dbm.getSequence(_schema.getName()+"."+name);
  }

  public DbRow getRow(long rowid) throws DatabaseException {
    return getRow(new DatabaseColumn(rowid));
  }
  
  public DbRow getRow(DbIndex index, DatabaseColumn fk) throws DatabaseException {
    Database db = getIndexDb(index.getName());
    DatabaseColumn pk = new DatabaseColumn();
    OperationStatus status = db.get(null, fk.getEntry(), pk.getEntry(), null);
    if (status != OperationStatus.SUCCESS) {
      Dbm.log("status: "+status);
    }
    return getRow(pk);
  }
  public TableIterator iterator() throws DatabaseException {
    return new TableIterator(this);
  }
  public DbIndex byPk() {
    return new DbIndex(Schema.ROWID, ColType.LONG); 
  }
  public <OneRow> void queue(F<DbRow, OneRow> rowToTuple,DbIndex index, BlockingQueue<OneRow> q) throws DatabaseException {
	    Cursor cursor = createCursor(index.getName());
	    DatabaseColumn key = new DatabaseColumn();
	    try {
	    	queue(rowToTuple , new Tuples.Tuple3<DbTable, Cursor, DatabaseColumn>(this, cursor, key), q) ;
	    } finally {
	    	cursor.close();
	    }
	}
	private static <OneRow> void queue(F<DbRow, OneRow> fun, Tuples.Tuple3<DbTable, Cursor, DatabaseColumn> tuple,
			BlockingQueue<OneRow> rows) throws DatabaseException {

		DbTable table = tuple._1();
		Cursor cursor = tuple._2();
		DatabaseColumn key = tuple._3(); 
	    DatabaseColumn val = new DatabaseColumn();
	   OperationStatus status = cursor.getNext(key.getEntry(), val.getEntry(), LockMode.DEFAULT);      
	    if (status != OperationStatus.SUCCESS) {
	    	//cursor.close();
	    	return;
	    } 
	     DbRow row = table.getRow(val);
	     rows.offer(fun.f(row));
		
		queue(fun, tuple, rows);
	}

  public <OneRow> List<OneRow> map(F<DbRow, OneRow> rowToTuple,DbIndex index) throws DatabaseException {
	    Cursor cursor = createCursor(index.getName());
	    DatabaseColumn key = new DatabaseColumn();
	    try {
	    	return map(rowToTuple , new Tuples.Tuple3<DbTable, Cursor, DatabaseColumn>(this, cursor, key), new LinkedList<OneRow>()) ;
	    } finally {
	    	cursor.close();
	    }
	}

	private static <OneRow> List<OneRow> map(F<DbRow, OneRow> fun, Tuples.Tuple3<DbTable, Cursor, DatabaseColumn> tuple,
			LinkedList<OneRow> rows) throws DatabaseException {

		DbTable table = tuple._1();
		Cursor cursor = tuple._2();
		DatabaseColumn key = tuple._3(); 
	    DatabaseColumn val = new DatabaseColumn();
	   OperationStatus status = cursor.getNext(key.getEntry(), val.getEntry(), LockMode.DEFAULT);      
	    if (status != OperationStatus.SUCCESS) {
	    	//cursor.close();
	    	return rows;
	    } 
	     DbRow row = table.getRow(val);
	     rows.add(fun.f(row));
		
		return map(fun, tuple, rows);
	}
	public Schema getSchema() {
		return _schema;
	}
}
