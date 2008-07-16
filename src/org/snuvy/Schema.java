package org.snuvy;

import java.util.Iterator;


public class Schema implements Iterable<String>{
  public static final String ROWID = "ROWID";
  private final OrderedMap<String,ColType> _map = new OrderedMap<String,ColType>();
  private final OrderedMap<String,DbIndex> _indexes = new OrderedMap<String,DbIndex>();
  private final String _name;
	
  public static class Column {
		final String name;
		final ColType type;
		public Column(ColType ptype, String pname) {
			name = pname;
			type = ptype;
		}
	}
	public static Column c(ColType type,String name) {
		return new Column(type, name);
	}
	public Schema(String name) {
		_name =name;
    // Internal index
    addIndex(new DbIndex(ROWID, ColType.AUTOLONG));
    addColumn(ROWID, ColType.AUTOLONG);
	}
	public Schema(String name, Column[]cols, DbIndex[] indexes) {
		this(name);
    for(DbIndex ii: indexes) {
      addIndex(ii);
    }
		for (Column c : cols) {
			addColumn(c.name, c.type);
		}
	}
  public void addIndex(DbIndex ii) {
    _indexes.put(ii.getName(), ii);
  }
	public void init(Object[]...objects) {
		for(int i =0; i < objects.length;i+=2) {
			Object o1 = objects[i];
			String name =(String) o1;
			Object o2 = objects[i+1];
			ColType type =(ColType) o2;
			addColumn(name, type); 
		}
	}
	public void addColumn(String name, ColType coltype) {
		_map.put(name, coltype);
	}
	public ColType getType(String name) {
		return _map.get(name);
	}
	public Iterator<String> getColumnNames() {
		return _map.keys();
	}
	public Iterator<String> iterator() {
		return getColumnNames();
	}
	public String getName() {
		return _name;
	}
  public DbIndex getPk() {
    return _indexes.first();
  }
  public Iterator<String> indexes() {
    return _indexes.keys();
  }
  public DbIndex getIndex(String name) {
    return _indexes.get(name);
  }
}
