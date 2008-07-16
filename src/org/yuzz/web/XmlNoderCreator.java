package org.yuzz.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface XmlNoderCreator {
	public XmlNoder create(HttpServletRequest req, HttpServletResponse res);

}
