package org.j_paine.formatter;

public class InvalidNumberOnReadException extends InputFormatException {
	
	/**
	 * auto-generated UID for serialization
	 */
	private static final long serialVersionUID = 2680787215042874431L;

	public InvalidNumberOnReadException(String number, int vecptr, String format, String line_error_report,
			String parser_message) {
		this("Invalid number while reading formatted data:\n" + "  Number = \"" + number + "\"\n" + "  Index  = "
				+ vecptr + "\n" + "  Format = " + format + "\n" + line_error_report + "\n" + parser_message);
	}

	public InvalidNumberOnReadException(String s) {
		super(s);
	}

	public InvalidNumberOnReadException() {
		super();
	}
}