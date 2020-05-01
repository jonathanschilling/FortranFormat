package examples;

import java.util.Vector;

import org.j_paine.formatter.Formatter;
import org.j_paine.formatter.InvalidFormatException;
import org.j_paine.formatter.OutputFormatException;

public class FormatDemo1 {
	
	public static void main(String[] args) {
		try {
			Formatter f = new Formatter("I3, F15.7, E15.7");
			Vector v = new Vector();
			v.addElement(new Integer(111));
			v.addElement(new Integer(222));
			v.addElement(new Integer(333));
			f.write(v, System.out);
			System.out.println();
		} catch (InvalidFormatException e) {
			System.out.println(e);
		} catch (OutputFormatException e) {
			System.out.println(e);
		}
	}
}
