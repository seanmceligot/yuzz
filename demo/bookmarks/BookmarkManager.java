package bookmarks;

import org.snuvy.DbRow;
import org.snuvy.DbTable;
import org.snuvy.Dbm;


public class BookmarkManager {

	public static final BookmarkSchema BOOKMARK_SCHEMA = new BookmarkSchema();
	static final String NAME = "NAME";
	static final String URL = "URL";
	  
	  public DbRow createRow(DbTable table, String name, String url) {
	    DbRow row = table.newRow();
	    row.setString(BookmarkManager.NAME, name);
	    row.setString(BookmarkManager.URL, url);
	    return row;
	  }
	  public DbTable getTable(Dbm dbm) {
			return dbm.getTable(BOOKMARK_SCHEMA);
	  }

}
