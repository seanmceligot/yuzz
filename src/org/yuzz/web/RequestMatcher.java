package org.yuzz.web;
import javax.servlet.http.HttpServletRequest;

public interface RequestMatcher {
	public boolean matches(HttpServletRequest req);
}
