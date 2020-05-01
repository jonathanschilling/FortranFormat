package org.j_paine.formatter;

import java.io.DataInputStream;
import java.util.Vector;

public class FormatTester09 {
	
	public static void main(String[] args) {
		try {
			Formatter f;
			f = new Formatter("A10'abc',A2'xy',A4");
			System.out.println(f.toString());

			Vector v = new Vector();

			DataInputStream in = new DataInputStream(FormatTester09.class.getResourceAsStream("/FormatTester9.dat"));

			f.read(v, in);
			System.out.println(v);
		} catch (InvalidFormatException e) {
			System.out.println(e);
		} catch (InputFormatException e) {
			System.out.println(e);
		}
	}

}
