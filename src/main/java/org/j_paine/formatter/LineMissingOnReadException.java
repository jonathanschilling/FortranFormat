package org.j_paine.formatter;

public class LineMissingOnReadException extends InputFormatException {
	
	/**
	 * auto-generated UID for serialization
	 */
	private static final long serialVersionUID = -2331131892165679854L;

	public LineMissingOnReadException(int vecptr, String format, String line, int line_number) {
		this("End of file while reading formatted data:\n" + "  Index  = " + vecptr + "\n" + "  Format = " + format
				+ "\n" + "Last line was number " + line_number + ":\n" + line);
	}

	public LineMissingOnReadException(String s) {
		super(s);
	}

	public LineMissingOnReadException() {
		super();
	}
}