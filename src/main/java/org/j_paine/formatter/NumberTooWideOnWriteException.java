package org.j_paine.formatter;

/**
 * This exception is thrown if formatted output detects a number that won't fit
 * in its format, e.g. trying to output 1234 against an I3 element.
 */
public class NumberTooWideOnWriteException extends OutputFormatException {
	
	/**
	 * auto-generated UID for serialization
	 */
	private static final long serialVersionUID = -6283244011303823487L;

	public NumberTooWideOnWriteException(Number n, int vecptr, String format) {
		this("Number too wide while writing formatted data:\n" + "  Number = \"" + n + "\"\n" + "  Index  = " + vecptr
				+ "\n" + "  Format = " + format + " .");
	}

	public NumberTooWideOnWriteException(String s) {
		super(s);
	}

	public NumberTooWideOnWriteException() {
		super();
	}
}
