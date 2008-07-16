package org.yuzz.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HtmlNoderCreator {
	public Page create(HttpServletRequest req, HttpServletResponse res);
}
