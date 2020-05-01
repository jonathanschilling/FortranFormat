package org.j_paine.formatter;

/**
 * This exception is thrown if formatted output detects a string that won't fit
 * in its format, e.g. trying to output abcde against an A4 element.
 */
public class StringTooWideOnWriteException extends OutputFormatException {

	/**
	 * auto-generated UID for serialization
	 */
	private static final long serialVersionUID = -4330612801357383088L;

	public StringTooWideOnWriteException(String s, int vecptr, String format) {
		this("String too wide while writing formatted data:\n" + "  String = \"" + s + "\"\n" + "  Index  = " + vecptr
				+ "\n" + "  Format = " + format + " .");
	}

	public StringTooWideOnWriteException(String s) {
		super(s);
	}

	public StringTooWideOnWriteException() {
		super();
	}
}
