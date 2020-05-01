package org.j_paine.formatter;

public class DataMissingOnReadException extends InputFormatException {

	/**
	 * auto-generated UID for serialization
	 */
	private static final long serialVersionUID = -3571728877954342595L;

	public DataMissingOnReadException(int vecptr, String format, String line_error_report) {
		this("End of line while reading formatted data:\n" + "  Index  = " + vecptr + "\n" + "  Format = " + format
				+ "\n" + line_error_report);
	}

	public DataMissingOnReadException(String s) {
		super(s);
	}

	public DataMissingOnReadException() {
		super();
	}
}