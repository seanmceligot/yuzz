package bookmarks;

import org.snuvy.DbRow;
import org.snuvy.DbTable;
import org.snuvy.Dbm;
import org.snuvy.FunctionalDbm;
import org.snuvy.FunctionalDbm.WithDbm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yuzz.xml.Xhtml.HtmlTag;

import org.yuzz.web.Page;
import org.yuzz.web.HtmlNoderCreator;

public class AddBookmark implements Page {
	private final String name;
	private final String url;

	public static class Creator implements HtmlNoderCreator { 
	public Page create(HttpServletRequest req, HttpServletResponse res) {
		String name = req.getParameter("name");
		String url = req.getParameter("url");
		return new AddBookmark(name, url);
	}
	}
	public AddBookmark(String name1, String url1) {
		name = name1;
		url = url1;
	}
	
	public void add() throws Throwable {
		final BookmarkManager bookmarkManager = new BookmarkManager();
		FunctionalDbm.withDbm(new WithDbm() {
			public void run(Dbm dbm) throws Throwable {
				DbTable table = bookmarkManager.getTable(dbm);
				DbRow row = bookmarkManager.createRow(table, name, url);
				table.saveRow(row);
			}});
	}

	public HtmlTag process(HttpServletRequest req, HttpServletResponse res)
			throws Throwable {
		add();
		return new BookmarksPage().process(req, res);
	}

}
