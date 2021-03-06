package bookmarks;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.snuvy.DbRow;
import org.snuvy.DbTable;
import org.snuvy.Dbm;
import org.snuvy.FunctionalDbm;
import org.yuzz.functor.Fun.F;
import org.yuzz.web.XmlNoder;
import org.yuzz.web.XmlNoderCreator;
import org.yuzz.xml.Node;
import org.yuzz.xml.NodeStatics;
import org.yuzz.xml.Xhtml.Table;

import rss.Rss;
import rss.Rss.RssItem;

public class RssBookmarks implements XmlNoder{
	public static class Creator implements XmlNoderCreator { 
		public XmlNoder create(HttpServletRequest req, HttpServletResponse res) {
			return new RssBookmarks();
		}
		}
	class RowToItem extends F<DbRow, RssItem> {
		@Override
		public RssItem f(DbRow row) {
		      String name = row.getString(BookmarkManager.NAME);
		      String url = row.getString(BookmarkManager.URL);
		      return Rss.item(name, url, name);
		}
	}
	public Node node() throws Throwable {
		final BookmarkManager bookmarkManager = new BookmarkManager();
		final LinkedList<RssItem> items = new LinkedList();
		final Table byHostTable = NodeStatics.table();
		FunctionalDbm.withDbm(new FunctionalDbm.WithDbm() {
			public void run(Dbm dbm) throws Throwable {
				DbTable bookmarks = bookmarkManager.getTable(dbm);
				// retrieve all rows using index byHost into List<Tr> using RowToTr
				RowToItem rowToTr = new RowToItem();
				List<RssItem> results = bookmarks.map(rowToTr, BookmarkManager.BOOKMARK_SCHEMA.byHost);
				items.addAll(results);
			}
		});
		final RssItem[] rssItems = new RssItem[items.size()];
		items.toArray(rssItems);
		return Rss.rss(rssItems);
	}
}
