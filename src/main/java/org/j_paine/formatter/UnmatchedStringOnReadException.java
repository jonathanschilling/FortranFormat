package org.j_paine.formatter;

public class UnmatchedStringOnReadException extends InputFormatException {
	
	/**
	 * auto-generated UID for serialization
	 */
	private static final long serialVersionUID = 1974963645107770412L;

	public UnmatchedStringOnReadException(String string, int vecptr, String format, String line_error_report) {
		this("Unmatched string while reading formatted data:\n" + "  String = \"" + string + "\"\n" + "  Index  = "
				+ vecptr + "\n" + "  Format = " + format + "\n" + line_error_report + "\n");
	}

	public UnmatchedStringOnReadException(String s) {
		super(s);
	}

	public UnmatchedStringOnReadException() {
		super();
	}
}