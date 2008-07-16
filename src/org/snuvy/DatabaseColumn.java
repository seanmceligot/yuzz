package org.snuvy;

import com.sleepycat.bind.tuple.DoubleBinding;
import com.sleepycat.bind.tuple.FloatBinding;
import com.sleepycat.bind.tuple.IntegerBinding;
import com.sleepycat.bind.tuple.LongBinding;
import com.sleepycat.bind.tuple.StringBinding;
import com.sleepycat.je.DatabaseEntry;

public final class DatabaseColumn {
  private final DatabaseEntry _entry = new DatabaseEntry();
  
  public DatabaseColumn(int value) {
    setInt(value);
  }
  public DatabaseColumn(String value) {
    setString(value);
  }
  public DatabaseColumn(long value) {
    setLong(value);
  }
  public DatabaseColumn(float value) {
    setFloat(value);
  }
  public DatabaseColumn(Money value) {
    setMoney(value);
  }
  public DatabaseColumn() {
  }
  public void setInt(int value) {
    IntegerBinding.intToEntry(value, _entry);
  }
  public void setLong(long value) {
    LongBinding.longToEntry(value, _entry);
  }
  public void setString(String value) {
    StringBinding.stringToEntry(value, _entry);
  }
  public void setMoney(Money value) {
    DoubleBinding.doubleToEntry(value.bd$().doubleValue(), _entry);
  }
  public void setFloat(float value) {
    FloatBinding.floatToEntry(value, _entry);
  }
  public float getFloat() {
    return FloatBinding.entryToFloat(_entry);
  }
	public int getInt() {
		return IntegerBinding.entryToInt(_entry);
	}
	public long getLong() {
		return LongBinding.entryToLong(_entry);
	}
	public String getString() {
		return StringBinding.entryToString(_entry);
	}
  public Money getMoney() {
    return new Money(DoubleBinding.entryToDouble(_entry));
  }
  public DatabaseEntry getEntry() {
    return _entry;
  }
  public String toString() {
    return _entry.toString();
  }
}
