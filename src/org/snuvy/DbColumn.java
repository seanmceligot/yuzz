package org.snuvy;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseException;

public class DbColumn {
	private String _dbName;
	private final Dbm _dbm;
	private Database _db;

	public DbColumn(Dbm dbm, String dbname) {
		_dbm = dbm;
		_dbName = dbname;
	}
	public String getName() {
		return _dbName;
	}

	protected Database getdb() throws DatabaseException {
		_db = _dbm.open(false, _dbName);
	    return _db;
	}

    public void close() throws DatabaseException {
       _dbm.close(_dbName);
	}

}