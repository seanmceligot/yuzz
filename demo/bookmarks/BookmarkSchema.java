package bookmarks;

import org.snuvy.ColType;
import org.snuvy.DatabaseColumn;
import org.snuvy.DbIndex;
import org.snuvy.DbRow;
import org.snuvy.DbTable;
import org.snuvy.Schema;

import java.net.MalformedURLException;
import java.net.URL;

import com.sleepycat.je.DatabaseException;

public class BookmarkSchema extends Schema {
	public final DbIndex byUrl;
	public final DbIndex byHost;

	public BookmarkSchema() {
		super("bookmarks");
		byHost = new DbIndex("HOST", ColType.STRING) {
			@Override
			public DatabaseColumn fk(DbTable table, DbRow row)
					throws DatabaseException {
				DatabaseColumn entry = row.getEntry("URL");
				String url = entry.getString();
				String host = hostName(url);
				return new DatabaseColumn(host);
			}
		};
		addColumn(BookmarkManager.NAME, ColType.STRING);
		addColumn(BookmarkManager.URL, ColType.STRING);
		byUrl = new DbIndex(BookmarkManager.URL, ColType.STRING);
		addIndex(byUrl);
		addIndex(byHost);
		
	}
	private String hostName(String url) {
		  try {
			return new URL(url).getHost();
		} catch (MalformedURLException e) {
			return "";
		}
	  }
	
}