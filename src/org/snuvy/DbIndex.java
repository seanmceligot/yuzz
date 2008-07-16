/**
 * 
 */
package org.snuvy;

import com.sleepycat.je.DatabaseException;

public class DbIndex  {
  private String _name;
  private ColType _type;
  public DbIndex(String name, ColType type) {_name=name;_type=type;};
  public DatabaseColumn fk(DbTable table, DbRow row) throws DatabaseException{
    return row.getEntry(_name);
  }
  public String getName() {
    return _name;
  }
  public ColType getType() {
    return _type;
  }
}