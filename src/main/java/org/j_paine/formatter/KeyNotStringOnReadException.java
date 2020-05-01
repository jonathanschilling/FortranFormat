package org.j_paine.formatter;

public class KeyNotStringOnReadException extends InputFormatException {
	
	/**
	 * auto-generated UID for serialization
	 */
	private static final long serialVersionUID = -8047131686249447642L;

	public KeyNotStringOnReadException(Object key, int vecptr, String format, String line_error_report) {
		this("Key not string while reading formatted data:\n" + "  Key    = \"" + vecptr + "\"\n" + "  Index  = "
				+ vecptr + "\n" + "  Format = " + format + "\n" + line_error_report + "\n");
	}

	public KeyNotStringOnReadException(String s) {
		super(s);
	}

	public KeyNotStringOnReadException() {
		super();
	}
}
