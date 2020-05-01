package examples;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;

import org.j_paine.formatter.Formatter;
import org.j_paine.formatter.InputFormatException;
import org.j_paine.formatter.InvalidFormatException;

public class FormatDemo8 {
	
	public static void main(String[] args) {
		try {
			Formatter f;
			f = new Formatter("5I5");
			System.out.println(f.toString());

			Vector v = new Vector();

			/** TODO: Where is FormatDemo8.dat ? */
			DataInputStream in = new DataInputStream(new FileInputStream("FormatDemo8.dat"));

			while (in.available() > 0) {
				f.read(v, in);
				System.out.println(v);
			}
		} catch (InvalidFormatException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} catch (InputFormatException e) {
			System.out.println(e);
		}
	}

}
