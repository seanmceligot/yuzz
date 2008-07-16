package org.yuzz.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yuzz.xml.Xhtml.*;

public interface Page {
	//public HtmlTag node() throws Throwable;

	public HtmlTag process(HttpServletRequest req, HttpServletResponse res) throws Throwable;

}
