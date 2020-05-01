package org.j_paine.formatter;

public interface FormatOutputList {
	
	public boolean hasCurrentElement();

	public void checkCurrentElementForWrite(FormatElement format_element) throws EndOfVectorOnWriteException;

	public Object getCurrentElement();

	public Object getCurrentElementAndAdvance();

	/*
	 * Returns the current pointer. Used only in generating error messages.
	 */
	public int getPtr();
}