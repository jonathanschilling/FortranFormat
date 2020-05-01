package org.j_paine.formatter;

/**
 * This class represents a mapping from input data. We use it to specify, for
 * example, that on input, an "X" should be replaced by a "0" before being
 * interpreted by the formatted input routines. The user must provide an
 * instance of this class, with getMapping defined. getMapping should return
 * either null, if the input string is to be left as it is, or a replacement
 * string.
 */
public abstract class FormatMap {
	public abstract String getMapping(String in);
}