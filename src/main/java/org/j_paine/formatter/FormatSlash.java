package org.j_paine.formatter;

import java.io.PrintStream;

/**
 * This class represents an / item.
 */
public class FormatSlash extends FormatElement {
	public void write(FormatOutputList vp, PrintStream out) {
		out.println();
	}

	public void read(FormatInputList vp, InputStreamAndBuffer in, FormatMap format_map) throws InputFormatException {
		in.readLine(vp.getPtr(), this);
	}

	public String toString() {
		return "/";
	}
}