package bookmarks;

import static org.yuzz.xml.NodeStatics.a;
import static org.yuzz.xml.NodeStatics.n;
import static org.yuzz.xml.NodeStatics.t;
import static org.yuzz.xml.NodeStatics.td;
import static org.yuzz.xml.NodeStatics.tr;

import org.snuvy.DbRow;
import org.yuzz.functor.Fun.F;
import org.yuzz.xml.Xhtml.Tr;

public class BookmarkRowToTr extends F<DbRow, Tr> {
	public class RowToTr extends F<DbRow, Tr> {
		@Override
		public Tr f(DbRow row) {
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
	public Tr f(DbRow row) {
		 long rowId = row.getRowId();
	      String name = row.getString(BookmarkManager.NAME);
	      String url = row.getString(BookmarkManager.URL);
	      return tr(
	    		  td(t(Long.toString(rowId))),
	    		  td(t(name)), 
	    		  td( n("a", a("href", url), t(url))));
	}
}