package org.j_paine.formatter;

import java.util.Vector;

public class FormatTester03 {
	
	public static void main(String[] args) {
		try {
			Formatter f;
			Vector v = new Vector();

			f = new Formatter("/");
			System.out.println(f.toString());
			f.write(v, System.out);

			f = new Formatter(",/,");
			System.out.println(f.toString());
			f.write(v, System.out);
		} catch (InvalidFormatException e) {
			System.out.println(e);
		} catch (OutputFormatException e) {
			System.out.println(e);
		}
	}

}
