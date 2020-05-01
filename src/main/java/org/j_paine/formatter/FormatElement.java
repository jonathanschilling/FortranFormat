package org.j_paine.formatter;

import java.io.PrintStream;

/**
 * This class represents a single format element such as F12.5, I2, or X.
 */
public abstract class FormatElement extends FormatUniv {
	
	/**
	 * This method will be defined differently by each subclass.
	 */
	public abstract void write(FormatOutputList vp, PrintStream out) throws OutputFormatException;
}