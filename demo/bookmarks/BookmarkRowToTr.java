package bookmarks;

import org.snuvy.DbRow;
import static org.yuzz.xml.NodeStatics.*;
import static org.yuzz.xml.Xhtml.*;
import static org.yuzz.functor.Function.*;

public class BookmarkRowToTr extends Fun1<Tr, DbRow> {
	public class RowToTr extends Fun1<Tr, DbRow> {
		@Override
		public Tr apply(DbRow row) {
			 long rowId = row.getRowId();
		      String name = row.getString(BookmarkManager.NAME);
		      String url = row.getString(BookmarkManager.URL);
		      return tr(
		    		  td(t(Long.toString(rowId))),
		    		  td(t(name)), 
		    		  td( n("a", a("href", url), t(url))));
		}
	}

	@Override
	public Tr apply(DbRow row) {
		 long rowId = row.getRowId();
	      String name = row.getString(BookmarkManager.NAME);
	      String url = row.getString(BookmarkManager.URL);
	      return tr(
	    		  td(t(Long.toString(rowId))),
	    		  td(t(name)), 
	    		  td( n("a", a("href", url), t(url))));
	}
}