package org.tont.exceptions;

public class ConfigParseException extends Exception {

	private static final long serialVersionUID = 1079159475180632917L;

	public ConfigParseException() {
		super();
	}
	
	public ConfigParseException(String message) {
		super(message);
	}
}
