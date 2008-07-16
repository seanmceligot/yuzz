package bookmarks;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yuzz.web.AlwaysMatcher;
import org.yuzz.web.HtmlResponse;
import org.yuzz.web.PathEndMatcher;
import org.yuzz.web.RequestHandler;
import org.yuzz.web.XmlResponse;



public class BookmarksServlet extends HttpServlet {
	
	private static final long serialVersionUID = -7601516215697972093L;
	private RequestHandler handlers[];
	public BookmarksServlet() {
		RequestHandler home = new RequestHandler(new AlwaysMatcher(), new HtmlResponse(new BookmarksPage()));
		handlers = new RequestHandler[] {home};
		
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		for (RequestHandler handler : handlers) {
			if (handler.getMatcher().matches(req)) {
				try {
					handler.getResponse().send(req, res);
				} catch (RuntimeException re) {
					re.printStackTrace();
				}
				return;
			}
		}
		res.sendError(404, req.getContextPath());
	}

}
