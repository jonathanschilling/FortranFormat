package org.j_paine.formatter;

/**
 * This exception is a generic one, a superclass of all those thrown to report
 * an error while doing formatted input.
 */
public abstract class InputFormatException extends Exception {

	/**
	 * auto-generated UID for serialization
	 */
	private static final long serialVersionUID = -5539245253217000778L;

	public InputFormatException(String s) {
		super(s);
	}

	public InputFormatException() {
		super();
	}

}