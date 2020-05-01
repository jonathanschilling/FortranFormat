package org.j_paine.formatter;

import java.io.ByteArrayInputStream;

/**
 * This class represents an Ew.d format element. Numbers should be output as
 * s0.dd...ddEsdd where s is a sign.
 */
public class FormatE extends FormatIOElement {
	
	private int d;

	public FormatE(int w, int d) {
		setWidth(w);
		this.d = d;
	}

	public String convertToString(Object o, int vecptr) throws IllegalObjectOnWriteException, NumberTooWideOnWriteException {
		String s;

		/* Convert the number to a string. */
		if (o instanceof Integer || o instanceof Long || o instanceof Float || o instanceof Double) {
			CJFormat cjf = new CJFormat();
			cjf.setWidth(getWidth());
			cjf.setPrecision(this.d);
			cjf.setPre("");
			cjf.setPost("");
			cjf.setLeadingZeroes(false);
			cjf.setShowPlus(false);
			cjf.setAlternate(false);
			cjf.setShowSpace(false);
			cjf.setLeftAlign(false);
			cjf.setFmt('E');
			s = cjf.form(((Number) o).doubleValue());

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
			int start = np.Float();
			Double d = new Double(s.substring(start));
			return d;
		} catch (ParseException e) {
			throw new InvalidNumberOnReadException(s, vp.getPtr(), this.toString(), in.getLineErrorReport(),
					e.getMessage());
		} catch (TokenMgrError e) {
			throw new InvalidNumberOnReadException(s, vp.getPtr(), this.toString(), in.getLineErrorReport(),
					e.getMessage());
		}
	}

	public String toString() {
		return "E" + getWidth() + "." + this.d;
	}
}