package org.j_paine.formatter;

public interface FormatInputList {
	
	/**
	 * format_element and in are only for generating error messages.
	 */
	public void checkCurrentElementForRead(FormatElement format_element, InputStreamAndBuffer in) throws InputFormatException;
	// If the list is a VectorAndPointer, it won't throw an exception.
	// If it is a StringsHashtableAndPointer, it will throw a
	// EndOfKeyVectorOnReadException.

	/**
	 * Puts o into the input list and advances its pointer. Must be defined for each
	 * subclass. format_element and in are only for generating error messages.
	 */
	public void putElementAndAdvance(Object o, FormatElement format_element, InputStreamAndBuffer in)
			throws InputFormatException;

	/**
	 * Returns the current pointer. Used only in generating error messages.
	 */
	public int getPtr();
}