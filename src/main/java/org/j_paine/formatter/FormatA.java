package org.j_paine.formatter;

/**
 * This class represents an Aw format element.
 */
public class FormatA extends FormatIOElement {
	
	public FormatA(int w) {
		setWidth(w);
	}

	public String convertToString(Object o, int vecptr) throws IllegalObjectOnWriteException, StringTooWideOnWriteException {
		String s;

		if (o instanceof String) {
			/* Throw an exception if the string won't fit. */
			s = (String) o;
			if (s.length() > getWidth()) {
				throw new StringTooWideOnWriteException(s, vecptr, this.toString());
			} else {
				return s;
			}
		} else {
			throw new IllegalObjectOnWriteException(o, vecptr, this.toString());
		}
	}

	/**
	 * vp and in are used only in generating error messages.
	 */
	public Object convertFromString(String s, FormatInputList vp, InputStreamAndBuffer in)
			throws InvalidNumberOnReadException {
		/* We just return the slice read, as a string. */
		return s;
	}

	public String toString() {
		return "A" + getWidth();
	}
}