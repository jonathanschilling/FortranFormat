package org.j_paine.formatter;

/**
 * This exception is thrown if formatted output detects an object that's the
 * wrong type for a format element, e.g. a real when outputting against an Iw
 * element.
 */
public class IllegalObjectOnWriteException extends OutputFormatException {
	
	/**
	 * auto-generated UID for serialization
	 */
	private static final long serialVersionUID = -7578674197866562157L;

	public IllegalObjectOnWriteException(Object o, int vecptr, String format) {
		this("Illegal object while writing formatted data:\n" + "  Object = \"" + o + "\"\n" + "  Index  = " + vecptr
				+ "\n" + "  Format = " + format + " .");
	}

	public IllegalObjectOnWriteException(String s) {
		super(s);
	}

	public IllegalObjectOnWriteException() {
		super();
	}
}