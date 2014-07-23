package org.snuvy;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.yuzz.functor.Fun;
import org.yuzz.functor.Functions;
import org.yuzz.functor.Operators;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.Transaction;

/**
 * dbm -> many databases 
 *  one instance initialized at startup
 *  stored in singleton instance 
 *  closed at shutdown
 * database -> many tables, 
 *  initialized a startup 
 *  stored in singleton instance
 *  synced after write
 *  * closed at shutdown
 * table
 *  opened and closed on read /write
 * @author sean
 *
 */
public class Dbm {
  private EnvironmentConfig _envConfig;
	private Environment _env;
	private TreeMap<String,Database> _dbs = new TreeMap<String, Database>();
	private TreeMap<String,TableSequence> _seqs = new TreeMap<String, TableSequence>();
  private final File _homeDir;
  private static Dbm _singleton = null;
	
  public static void setSingleton(Dbm singleton) {
    _singleton = singleton;
  }
  public Dbm singleton() {
    return _singleton;
  }
  public Dbm() {
    this(new File("."));
  }
	public Dbm(String homeDir) {
    this(new File(homeDir));
  }
  public Dbm(File homeDir) {
    _homeDir = homeDir;
  }
  public void open() throws DatabaseException {
    if (!_homeDir.isDirectory()) {
      _homeDir.mkdirs();
    }
		_envConfig = new EnvironmentConfig();
		_envConfig.setAllowCreate(true);
		//_envConfig.setTransactional(true);
	   _env = new Environment(_homeDir, _envConfig);
	}
  public DbTable getTable(Schema schema) {
    return new DbTable(this, schema);
  }
  public void close() throws DatabaseException {
    Set<Entry<String, Database>> set = _dbs.entrySet();
    for (Entry<String, Database> entry : set) {
      entry.getValue().close();
      set.remove(entry.getKey());
    }
    if (_env != null) {
      _env.close();
    }
  }
  private static String j(final String... strings) {
  	return Functions.reduce(new Operators.Join("."), Arrays.asList(strings));
  }
  
  Database open(boolean allowDups, String...name) throws DatabaseException {
    return open(true, allowDups, name);
  }
  Database openExisting(boolean allowDups, String... name) throws DatabaseException {
    return open(false, allowDups, name);
  }
  Database openOrCreate(boolean allowDups, String... name) throws DatabaseException {
    return open(true, allowDups, name);
  }
	Database open(boolean allowCreate, boolean allowDups, String... nameParts) throws DatabaseException {
		String name = j(nameParts);
		if (_env == null) {
			open();
		}
		Database db = _dbs.get(name);
		if (db == null) {
			DatabaseConfig dbConfig = new DatabaseConfig();
			dbConfig.setAllowCreate(allowCreate);
      dbConfig.setSortedDuplicates(allowDups);
			//dbConfig.setTransactional(true);
			db = _env.openDatabase(null, name, dbConfig);
			_dbs.put(name, db);
		}
		return db;
	}
	
	void close(String name) throws DatabaseException {
		Database db = _dbs.remove(name);
		if (db != null) {
			db.close();
		}
	}
  public void sync() throws DatabaseException {
    Set<Entry<String, Database>> set = _dbs.entrySet();
    for (Entry<String, Database> entry : set) {
      entry.getValue().sync();
    }
  }
  public <R> R withTable(Schema sc, Fun.F<DbTable, R> f) {
    DbTable table = getTable(sc);
    return f.f(table);
  }
	public TableSequence getSequence(String name) throws DatabaseException {
		TableSequence seq = _seqs.get(name);
		if (seq == null) {
			seq = new TableSequence(this, name);
			_seqs.put(name, seq);
			try {
				seq.open();
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException("unlikely",e);
			}
		}
		return seq;
	}
	public Transaction startTransaction() throws DatabaseException {
        Transaction txn = _env.beginTransaction(null, null);
		return txn;
	}
	public static void log(String message) {
		System.out.println(message);
	}
}
