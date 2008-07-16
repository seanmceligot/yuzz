package org.snuvy;


import java.util.TreeMap;

public class DbRow {
	private final Schema _schema;
	private TreeMap<String, DatabaseColumn> _data = new TreeMap<String, DatabaseColumn>();

	public DbRow(Schema schema) {
		_schema = schema;
	}
   public DatabaseColumn getEntry(String key) {
    DatabaseColumn entry = _data.get(key);
    return entry;
   }
   public DatabaseColumn getNonNullEntry(String key) {
		DatabaseColumn entry = _data.get(key);
		if (entry == null) {
			entry = new DatabaseColumn();
			_data.put(key, entry);
		}
		return entry;
	}
	public DatabaseColumn setInt(String key, int value) {
		assert(_schema.getType(key) == ColType.INT);
        DatabaseColumn entry = getNonNullEntry(key);
		entry.setInt(value);
		return entry;
	}
	public DatabaseColumn setLong(String key, long value) {
		assert(_schema.getType(key) == ColType.LONG);
		DatabaseColumn entry = getNonNullEntry(key);
		entry.setLong(value);
		return entry;
	}
	public DatabaseColumn setString(String key, String value) {
		assert(_schema.getType(key) == ColType.STRING);
		DatabaseColumn entry = getNonNullEntry(key);
		entry.setString(value);
		return entry;
	}
	public int getInt(String key) {
		return getEntry(key).getInt();
	}
	public long getLong(String key) {
    return getEntry(key).getLong();
	}
	public String getString(String key) {
    return getEntry(key).getString();
	}
	public void setEntry(String name, DatabaseColumn value) {
		_data.put(name, value);
	}
	public DatabaseColumn setFloat(String key, float value) {
		assert(_schema.getType(key) == ColType.FLOAT);
		DatabaseColumn entry = getNonNullEntry(key);
		entry.setFloat(value);
		return entry;
	}
	public float getFloat(String key) {
		return getEntry(key).getFloat();
	}
  public Money getMoney(String key) {
    return getEntry(key).getMoney();
  }
  public DatabaseColumn setMoney(String key, Money value) {
    assert(_schema.getType(key) == ColType.FLOAT);
    DatabaseColumn entry = getNonNullEntry(key);
    entry.setMoney(value);
    return entry;
  }
  public long getRowId() {
    return getEntry(Schema.ROWID).getLong();
  }
  public DatabaseColumn getRowIdEntry() {
    return getEntry(Schema.ROWID);
  }
	public String toString(String name) {
		ColType ct = _schema.getType(name);
		switch(ct.type()) {
			case INT_COLUMN:
				return Integer.toString(getInt(name));
			case LONG_COLUMN:
			case AUTOLONG:
				return Long.toString(getLong(name));
			case STRING_COLUMN:
				return getString(name);
			case FLOAT_COLUMN:
				return Float.toString(getFloat(name));
			case LMONEY:
				return getMoney(name).toString();
		}
		return "";
	}
  /*
  public String toString(String key) {
    switch(_schema.getType(key)) {
      
    }
  }*/
}
