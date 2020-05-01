package org.j_paine.formatter;

public class EndOfKeyVectorOnReadException extends InputFormatException {
	
	/**
	 * auto-generated UID for serialization
	 */
	private static final long serialVersionUID = -3899709952055829828L;

	public EndOfKeyVectorOnReadException(int vecptr, String format, String line_error_report) {
		this("End of key vector while reading formatted data:\n" + "  Index  = " + vecptr + "\n" + "  Format = "
				+ format + "\n" + line_error_report + "\n");
	}

	public EndOfKeyVectorOnReadException(String s) {
		super(s);
	}

	public EndOfKeyVectorOnReadException() {
		super();
	}
}
