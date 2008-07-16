package org.snuvy;

import java.util.LinkedList;
import java.util.List;

import org.yuzz.functor.Function.Fun1;
import org.yuzz.functor.Procedure;
import org.yuzz.functor.Tuples;

import com.sleepycat.je.Cursor;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.OperationStatus;


public class FunctionalDbm {

  //public static final Logger log = Logger.getLogger(Dbm.class.getName());
  public static interface WithDbm {
  	public void run(Dbm dbm) throws Throwable;
  }

  public static interface WithDb {
  	public void run(String name, Dbm db) throws Throwable;
  }

  public static interface WithSequence {
  	public void run(TableSequence db) throws Throwable;
  }

  public static void withDbm(WithDbm withdbm) throws Throwable {
  	Dbm dbm = new Dbm();
  	dbm.open();
  	try {
  		withdbm.run(dbm);
  	} finally {
  		dbm.close();
  	}
  }

  public void withDb(Dbm dbm, WithDb withdb, String name) throws Throwable {		
  	try {
  		withdb.run(name,dbm);
  	} finally {
  		dbm.close();
  	}
  }

  public void withSequence(Dbm dbm, WithSequence withseq, String name) throws Throwable {		
  	TableSequence sequence = new TableSequence(dbm, name);
  	sequence.open();
  	try {
  		withseq.run(sequence);
  	} finally {
  		sequence.close();
  	}
  }
  public static void firstToLast(DbTable table,DbIndex index, Procedure.Proc1<DbRow> fn) throws DatabaseException {
    DatabaseColumn key = new DatabaseColumn();
    DatabaseColumn val = new DatabaseColumn();

    Cursor cursor = table.createCursor(index.getName());
    try {
      OperationStatus status;
      while( (status = cursor.getNext(key.getEntry(), val.getEntry(), LockMode.DEFAULT)) == OperationStatus.SUCCESS) {
        // pk is  in val
        DbRow row = table.getRow(val);
        fn.call(row);
      }
    } finally {
      cursor.close();
   }    
  }
  public static List<List> mapList(Fun1<List, DbRow> rowToList, DbTable table, DbIndex index) throws DatabaseException {
	    Cursor cursor = table.createCursor(index.getName());
	    DatabaseColumn key = new DatabaseColumn();
		return mapList(rowToList , new Tuples.Tuple3<DbTable, Cursor, DatabaseColumn>(table, cursor, key), new LinkedList<List>()) ;
	}

	private static List<List> mapList(Fun1<List, DbRow> fun, Tuples.Tuple3<DbTable, Cursor, DatabaseColumn> tuple,
			LinkedList<List> linkedList) throws DatabaseException {

		DbTable table = tuple._1();
		Cursor cursor = tuple._2();
		DatabaseColumn key = tuple._3(); 
	    DatabaseColumn val = new DatabaseColumn();
	   OperationStatus status = cursor.getNext(key.getEntry(), val.getEntry(), LockMode.DEFAULT);      
	    if (status != OperationStatus.SUCCESS) {
	    	return linkedList;
	    } 
	     DbRow row = table.getRow(val);
	     linkedList.add(fun.apply(row));
		
		return mapList(fun, tuple, linkedList);
	}
  public static void lastToFirst(DbTable table,DbIndex index, Procedure.Proc1<DbRow> fn) throws DatabaseException {
    DatabaseColumn key = new DatabaseColumn();
    DatabaseColumn val = new DatabaseColumn();

    Cursor cursor = table.createCursor(index.getName());
    try {
      OperationStatus status;
      while( (status = cursor.getPrev(key.getEntry(), val.getEntry(), LockMode.DEFAULT)) == OperationStatus.SUCCESS) {
        // pk is  in val
        DbRow row = table.getRow(val);
        fn.call(row);
      }
    } finally {
      cursor.close();
   }    
  }
  
  public static void findMatching(DbTable table,DbIndex index, DatabaseColumn key, Procedure.Proc1<DbRow> fn) throws DatabaseException {
    DatabaseColumn val = new DatabaseColumn();

    Cursor cursor = table.createCursor(index.getName());
    try {
      OperationStatus status = cursor.getSearchKeyRange(key.getEntry(), val.getEntry(), LockMode.DEFAULT);      
      while(status == OperationStatus.SUCCESS) {
        DbRow row = table.getRow(val);
        fn.call(row);
        status = cursor.getNextDup(key.getEntry(), val.getEntry(), LockMode.DEFAULT);
        
      }
    } finally {
      cursor.close();
   }    
  }

  public static <OneRow> List<OneRow> map(Fun1<OneRow, DbRow> rowToTuple, DbTable table, DbIndex index) throws DatabaseException {
	    Cursor cursor = table.createCursor(index.getName());
	    DatabaseColumn key = new DatabaseColumn();
		return map(rowToTuple , new Tuples.Tuple3<DbTable, Cursor, DatabaseColumn>(table, cursor, key), new LinkedList<OneRow>()) ;
	}

	private static <OneRow> List<OneRow> map(Fun1<OneRow, DbRow> fun, Tuples.Tuple3<DbTable, Cursor, DatabaseColumn> tuple,
			LinkedList<OneRow> rows) throws DatabaseException {

		DbTable table = tuple._1();
		Cursor cursor = tuple._2();
		DatabaseColumn key = tuple._3(); 
	    DatabaseColumn val = new DatabaseColumn();
	   OperationStatus status = cursor.getNext(key.getEntry(), val.getEntry(), LockMode.DEFAULT);      
	    if (status != OperationStatus.SUCCESS) {
	    	cursor.close();
	    	return rows;
	    } 
	     DbRow row = table.getRow(val);
	     rows.add(fun.apply(row));
		
		return map(fun, tuple, rows);
	}

}
