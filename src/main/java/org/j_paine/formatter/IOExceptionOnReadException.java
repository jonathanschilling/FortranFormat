package org.j_paine.formatter;

public class IOExceptionOnReadException extends InputFormatException {
	
	/**
	 * auto-generated UID for serialization
	 */
	private static final long serialVersionUID = 6026238076477791681L;

	public IOExceptionOnReadException(String line, int line_number, String IOMessage) {
		this("IOException while reading formatted data:\n" + "Last line was number " + line_number + ":\n" + line + "\n"
				+ IOMessage);
	}

	public IOExceptionOnReadException(String s) {
		super(s);
	}

	public IOExceptionOnReadException() {
		super();
	}
}