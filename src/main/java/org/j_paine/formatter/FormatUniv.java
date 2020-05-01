package org.j_paine.formatter;

import java.io.PrintStream;

/**
 * Below, we define various classes for holding complete formats, format
 * elements, and so on. The class FormatUniv is a superclass of them all. This
 * makes it a convenient "universal type" to use to hold any piece of, or a
 * complete, format.
 */
public abstract class FormatUniv {
	public abstract void write(FormatOutputList vp, PrintStream out) throws OutputFormatException;

	public abstract void read(FormatInputList vp, InputStreamAndBuffer in, FormatMap format_map) throws InputFormatException;
}