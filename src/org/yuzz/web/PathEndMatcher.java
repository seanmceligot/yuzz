package org.yuzz.web;
import javax.servlet.http.HttpServletRequest;

public class PathEndMatcher implements RequestMatcher {
	private final String _end;
	
	public PathEndMatcher(String suffix) {
		_end = suffix;
	}
	public boolean matches(HttpServletRequest req) {
		final String path = req.getPathInfo();
		if (( path == null) && ( (_end != null) || (_end.length() != 0) )) {
		  return false;
		}
		System.out.println("path: "+path);
		return path.endsWith(_end);
	}

}
