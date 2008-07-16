package org.snuvy;

import java.io.IOException;


import com.sleepycat.bind.tuple.LongBinding;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.DatabaseException;

public class AutoNumber  {
  public DatabaseEntry next(DbTable table, String name, DbRow row) throws DatabaseException {
    TableSequence seq = table.getSequence(name); 
    long l = seq.next();
    DatabaseEntry en = new DatabaseEntry();
    LongBinding.longToEntry(l, en);
    return en;
  }

}
