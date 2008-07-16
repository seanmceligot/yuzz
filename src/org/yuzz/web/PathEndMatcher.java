package org.yuzz.web;
import javax.servlet.http.HttpServletRequest;

public class PathEndMatcher implements RequestMatcher {
	private final String _end;
	
	public PathEndMatcher(String prefix) {
		_end = prefix;
	}
	public boolean matches(HttpServletRequest req) {
		final String path = req.getPathInfo();
		System.out.println("path: "+path);
		return path.endsWith(_end);
	}

}
