package org.j_paine.formatter;

/**
 * This exception is thrown if formatted output runs off the end of the vector
 * being output before it has completed the format.
 */
public class EndOfVectorOnWriteException extends OutputFormatException {
	
	/**
	 * auto-generated UID for serialization
	 */
	private static final long serialVersionUID = -6274136375439995070L;

	public EndOfVectorOnWriteException(int vecptr, String format) {
		this("End of vector while writing formatted data:\n" + "  Index  = " + vecptr + "\n" + "  Format = " + format
				+ " .");
	}

	public EndOfVectorOnWriteException(String s) {
		super(s);
	}

	public EndOfVectorOnWriteException() {
		super();
	}
}