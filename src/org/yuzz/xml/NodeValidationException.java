package org.yuzz.xml;
public class NodeValidationException extends RuntimeException {

	public NodeValidationException() {
	}

	public NodeValidationException(String message) {
		super(message);
	}

	public NodeValidationException(Throwable cause) {
		super(cause);
	}

	public NodeValidationException(String message, Throwable cause) {
		super(message, cause);
	}

}
