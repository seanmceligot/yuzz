package org.yuzz.web;
import javax.servlet.http.HttpServletRequest;

public class AlwaysMatcher implements RequestMatcher {

	public boolean matches(HttpServletRequest req) {
		return true;
	}

}
