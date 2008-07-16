package org.yuzz.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Response {
	public void send(HttpServletRequest req, HttpServletResponse res);
}
