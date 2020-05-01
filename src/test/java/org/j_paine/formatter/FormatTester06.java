package org.j_paine.formatter;

import java.io.DataInputStream;
import java.util.Vector;

public class FormatTester06 {
	
	public static void main(String[] args) {
		try {
			Formatter f;
			f = new Formatter("5(5I5/)");
			System.out.println(f.toString());

			Vector v = new Vector();

			DataInputStream in = new DataInputStream(FormatTester06.class.getResourceAsStream("/FormatTester6.dat"));

			f.read(v, in);
			System.out.println(v);
		} catch (InvalidFormatException e) {
			System.out.println(e);
		} catch (InputFormatException e) {
			System.out.println(e);
		}
	}

}
