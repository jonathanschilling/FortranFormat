package org.j_paine.formatter;

import java.io.ByteArrayInputStream;

/**
 * This class represents an Iw format element.
 */
public class FormatI extends FormatIOElement {
	
	public FormatI(int w) {
		setWidth(w);
	}

	public String convertToString(Object o, int vecptr) throws IllegalObjectOnWriteException, NumberTooWideOnWriteException {
		String s;

		/* Convert the number to a string. */
		if (o instanceof Integer || o instanceof Long) {
			CJFormat cjf = new CJFormat();
			cjf.setWidth(getWidth());
			cjf.setPre("");
			cjf.setPost("");
			cjf.setLeadingZeroes(false);
			cjf.setShowPlus(false);
			cjf.setAlternate(false);
			cjf.setShowSpace(false);
			cjf.setLeftAlign(false);
			cjf.setFmt('i');
			s = cjf.form(((Number) o).longValue());

			/* Throw an exception if the string won't fit. */
			if (s.length() > getWidth())
				throw new NumberTooWideOnWriteException((Number) o, vecptr, this.toString());
			else
				return s;
		} else
			throw new IllegalObjectOnWriteException(o, vecptr, this.toString());
	}

	/**
	 * vp and in are used only in generating error messages.
	 */
	public Object convertFromString(String s, FormatInputList vp, InputStreamAndBuffer in)
			throws InvalidNumberOnReadException {
		/*
		 * Parse the string to check it's a valid number, and convert if so.
		 */
		NumberParser np = new NumberParser(new ByteArrayInputStream(s.getBytes()));
		try {
			int start = np.Integer();
			Long l = new Long(s.substring(start));
			return l;
		} catch (ParseException e) {
			throw new InvalidNumberOnReadException(s, vp.getPtr(), this.toString(), in.getLineErrorReport(),
					e.getMessage());
		} catch (TokenMgrError e) {
			throw new InvalidNumberOnReadException(s, vp.getPtr(), this.toString(), in.getLineErrorReport(),
					e.getMessage());
		}
	}

	public String toString() {
		return "I" + getWidth();
	}
}