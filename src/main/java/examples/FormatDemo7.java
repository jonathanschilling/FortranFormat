package examples;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;

import org.j_paine.formatter.EndOfFileWhenStartingReadException;
import org.j_paine.formatter.Formatter;
import org.j_paine.formatter.InputFormatException;
import org.j_paine.formatter.InvalidFormatException;

public class FormatDemo7 {
	
	public static void main(String[] args) {
		try {
			Formatter f;
			f = new Formatter("5I5");
			System.out.println(f.toString());

			Vector v = new Vector();

			/** TODO: Where is FormatDemo7.dat ? */
			DataInputStream in = new DataInputStream(new FileInputStream("FormatDemo7.dat"));

			try {
				while (true) {
					f.read(v, in);
					System.out.println(v);
				}
			} catch (EndOfFileWhenStartingReadException e) {
				System.out.println(e);
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
