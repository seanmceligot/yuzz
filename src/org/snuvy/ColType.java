/**
 * 
 */
package org.snuvy;

public class ColType { 
  
	enum TYPE { INT_COLUMN,
	LONG_COLUMN,
	STRING_COLUMN,
	FLOAT_COLUMN,
	DATETIME_COLUMN,
  LMONEY,
  AUTOLONG
  }
	public static final ColType INT = new ColType(TYPE.INT_COLUMN);
	public static final ColType LONG = new ColType(TYPE.LONG_COLUMN);
	public static final ColType STRING = new ColType(TYPE.STRING_COLUMN);
	public static final ColType FLOAT = new ColType(TYPE.FLOAT_COLUMN);
	public static final ColType DATETIME = new ColType(TYPE.DATETIME_COLUMN);
  public static final ColType LMONEY = new ColType(TYPE.LMONEY);
  public static final ColType AUTOLONG = new ColType(TYPE.AUTOLONG);
	private TYPE _type;
	ColType(TYPE type) {
		_type = type;
		
	}
	public String toString() {
		return _type.toString();
	}
	public TYPE type() {
		return _type;
	}
}
