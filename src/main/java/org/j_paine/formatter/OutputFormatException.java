package org.j_paine.formatter;

/**
 * This exception is a generic one, a superclass of all those thrown to report
 * an error while doing formatted output.
 */
public abstract class OutputFormatException extends Exception {
	
	/**
	 * auto-generated UID for serialization
	 */
	private static final long serialVersionUID = -9150714982509806801L;

	public OutputFormatException(String s) {
		super(s);
	}

	public OutputFormatException() {
		super();
	}
}