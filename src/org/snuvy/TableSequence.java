package org.snuvy;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Sequence;
import com.sleepycat.je.SequenceConfig;

public class TableSequence {
  private SequenceConfig _seqConfig;
  private Sequence _seq;
  private final String _seqName;
  private Dbm _dbm;
  private String _dbname;

  public TableSequence(Dbm dbm, String name) {
    _dbm = dbm;
    _dbname = name;
    _seqName = name;
  }

  public long next() throws DatabaseException {
    long seqnum = _seq.get(null, 1);
    return seqnum;

  }

  public void close() throws DatabaseException {
    _seq.close();
    _dbm.close();
  }

  public String getName() {
    return _seqName;
  }

  private Database getdb() throws DatabaseException {
    return _dbm.open(false, _dbname);
  }

  void open() throws DatabaseException, UnsupportedEncodingException {
    getdb();

    _seqConfig = new SequenceConfig();
    _seqConfig.setAllowCreate(true);
    DatabaseEntry key = new DatabaseEntry(_seqName.getBytes("UTF-8"));
    _seq = getdb().openSequence(null, key, _seqConfig);
  }

  public static void main(String[] argv) throws DatabaseException, IOException {
    Dbm dbm = new Dbm();
    dbm.open();
    TableSequence app = new TableSequence(dbm, "test");
    app.open();
    System.out.println("Got sequence number: " + app.next());
    app.close();
    dbm.close();
  }

}
