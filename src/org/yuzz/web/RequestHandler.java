
package org.yuzz.web;
public class RequestHandler {
	private final RequestMatcher _matcher;
	private final Response _response;
	public RequestHandler(RequestMatcher matcher, Response response) {
		_matcher = matcher;
		_response = response;
	}
	public RequestMatcher getMatcher() {
		return _matcher;
	}
	public Response getResponse() {
		return _response;
	}

}
