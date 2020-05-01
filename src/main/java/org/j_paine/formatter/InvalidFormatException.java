package org.j_paine.formatter;

/**
 * This exception is thrown when a syntax error is detected while parsing a
 * format string.
 */
public class InvalidFormatException extends Exception {
	
	/**
	 * auto-generated UID for serialization
	 */
	private static final long serialVersionUID = -5735718033703224048L;

	public InvalidFormatException(String parser_message) {
		super(parser_message);
	}

	public InvalidFormatException() {
		super();
	}
}