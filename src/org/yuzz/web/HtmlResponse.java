package org.yuzz.web;
import java.io.PrintStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yuzz.xml.Xhtml.DocType;

import static org.yuzz.xml.NodeStatics.*;
import static org.yuzz.xml.Xhtml.*;
import static org.yuzz.functor.Function.*;

public class HtmlResponse implements Response {
	private Page _page;
	public HtmlResponse(Page creator) {
		_page = creator;
	}
	public void send(HttpServletRequest req, HttpServletResponse res) throws SystemException {
		ServletOutputStream out;
		try {
			res.setContentType("text/html");	
			out = res.getOutputStream();
			DocType docType = xhtml11Strict();
			docType.add(_page.process(req, res));
			docType.write(new PrintStream(out));
		} catch (Throwable e) {
			e.printStackTrace();
			throw new SystemException(e);
		}
	}
}
