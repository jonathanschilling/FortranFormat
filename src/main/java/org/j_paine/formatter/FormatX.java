package org.j_paine.formatter;

import java.io.PrintStream;

/**
 * This class represents an X format element.
 */
public class FormatX extends FormatElement {
	
	public void write(FormatOutputList vp, PrintStream out) {
		out.print(" ");
	}

	public void read(FormatInputList vp, InputStreamAndBuffer in, FormatMap format_map) {
		in.advance(1);
	}

	public String toString() {
		return "X";
	}
}
