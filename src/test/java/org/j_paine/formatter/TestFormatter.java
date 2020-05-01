package org.j_paine.formatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

import org.junit.jupiter.api.Test;

public class TestFormatter {
	
	public static final String formatIntoString(Formatter f, Object o) throws UnsupportedEncodingException, OutputFormatException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    final String utf8 = StandardCharsets.UTF_8.name();
	    try (PrintStream ps = new PrintStream(baos, true, utf8)) {
	    	f.write(o, ps);
	    }
	    return baos.toString(utf8);
	}
	
	public static final Object unformatFromString(Formatter f, String s) throws InputFormatException {
		DataInputStream in = new DataInputStream(new ByteArrayInputStream(s.getBytes()));
		return f.read(in);
	}
	
	@Test
	public void testToString() throws InvalidFormatException {
		Formatter f = new Formatter("1X,2I4,X/,I5/," + "F12.5,E12.5");
		assertEquals("[Formatter X, 2(I4), X, /, I5, /, F12.5, E12.5]", f.toString());
	}
	
	@Test
	public void testMultiOutput() throws InvalidFormatException, OutputFormatException, UnsupportedEncodingException {
		Formatter f = new Formatter("1X,2I4,X/,'x'I5'y'/,'a'F12.5'b','c'E12.5'd'");
		assertEquals("[Formatter X, 2(I4), X, /, 'x', I5, 'y', /, 'a', F12.5, 'b', 'c', E12.5, 'd']", f.toString());

		Vector<Number> v = new Vector<>();
		v.addElement(new Integer(111));
		v.addElement(new Integer(222));
		v.addElement(new Integer(333));
		v.addElement(new Float(444.44));
		v.addElement(new Double(555.55));
		
		String formatted = formatIntoString(f, v);
		assertEquals("  111 222 \nx  333y\na   444.44000bc 5.55550E+02d", formatted);
	}
	
	@Test
	public void testI3InBrackets() throws InvalidFormatException, UnsupportedEncodingException, OutputFormatException {
		Formatter f = new Formatter("'['I3']'/");
		
		assertEquals("[  1]\n", formatIntoString(f, new Integer(1)));
		assertEquals("[ 12]\n", formatIntoString(f, new Integer(12)));
		assertEquals("[123]\n", formatIntoString(f, new Integer(123)));
		assertThrows(NumberTooWideOnWriteException.class, () -> {
			formatIntoString(f, new Integer(1234));
		});
		
		assertEquals("[ -1]\n", formatIntoString(f, new Integer(-1)));
		assertEquals("[-12]\n", formatIntoString(f, new Integer(-12)));
		assertThrows(NumberTooWideOnWriteException.class, () -> {
			formatIntoString(f, new Integer(-123));
		});
		assertThrows(NumberTooWideOnWriteException.class, () -> {
			formatIntoString(f, new Integer(-1234));
		});
	}
	
	@Test
	public void testF7p3InBrackets() throws InvalidFormatException, UnsupportedEncodingException, OutputFormatException {
		Formatter f = new Formatter("'['F7.3']'/");
		
		assertEquals("[ 12.345]\n", formatIntoString(f, new Float(12.345)));
		assertEquals("[123.456]\n", formatIntoString(f, new Float(123.456)));
		assertThrows(NumberTooWideOnWriteException.class, () -> {
			formatIntoString(f, new Float(1234.567));
		});
		assertThrows(NumberTooWideOnWriteException.class, () -> {
			formatIntoString(f, new Float(12345.678));
		});

		assertEquals("[-12.345]\n", formatIntoString(f, new Float(-12.345)));
		assertThrows(NumberTooWideOnWriteException.class, () -> {
			formatIntoString(f, new Float(-123.456));
		});
		assertThrows(NumberTooWideOnWriteException.class, () -> {
			formatIntoString(f, new Float(-1234.567));
		});
		assertThrows(NumberTooWideOnWriteException.class, () -> {
			formatIntoString(f, new Float(-12345.678));
		});
	}
	
	@Test
	public void testE12p3InBrackets() throws InvalidFormatException, UnsupportedEncodingException, OutputFormatException {
		Formatter f = new Formatter("'['E12.3']'/");
		
		assertEquals("[   1.235E+01]\n", formatIntoString(f, new Float(12.345)));
		assertEquals("[   1.235E+02]\n", formatIntoString(f, new Float(123.456)));
		assertEquals("[   1.235E+03]\n", formatIntoString(f, new Float(1234.567)));
		assertEquals("[   1.235E+04]\n", formatIntoString(f, new Float(12345.678)));

		assertEquals("[  -1.235E+01]\n", formatIntoString(f, new Float(-12.345)));
		assertEquals("[  -1.235E+02]\n", formatIntoString(f, new Float(-123.456)));
		assertEquals("[  -1.235E+03]\n", formatIntoString(f, new Float(-1234.567)));
		assertEquals("[  -1.235E+04]\n", formatIntoString(f, new Float(-12345.678)));
	}
	
	@Test
	public void testI3Input() throws InvalidFormatException, InputFormatException {
		Formatter f = new Formatter("I3");
		
		assertEquals(new Long(123), unformatFromString(f, "123"));
	}
	
	@Test
	public void testI3InBracketsInput() throws InvalidFormatException, InputFormatException {
		Formatter f = new Formatter("'['I3']'");
		
		assertThrows(UnmatchedStringOnReadException.class, () -> {
			unformatFromString(f, "x");
		});
		assertThrows(DataMissingOnReadException.class, () -> {
			unformatFromString(f, "[1");
		});
		assertThrows(DataMissingOnReadException.class, () -> {
			unformatFromString(f, "[123");
		});
		assertEquals(new Long(123), unformatFromString(f, "[123]"));
		assertEquals(new Long(-12), unformatFromString(f, "[-12]"));
		assertEquals(new Long(1), unformatFromString(f, "[  1]"));
		assertThrows(InvalidNumberOnReadException.class, () -> {
			unformatFromString(f, "[ 4 ]");
		});
		assertThrows(UnmatchedStringOnReadException.class, () -> {
			unformatFromString(f, "[123x");
		});
		assertThrows(InvalidNumberOnReadException.class, () -> {
			unformatFromString(f, "[yyy]");
		});
	}
	
	@Test
	public void testFiveIntegers() throws InvalidFormatException, InputFormatException {
		Formatter f = new Formatter("5I5");
		
		assertEquals("[Formatter 5(I5)]", f.toString());
		
		Vector v = new Vector();
		DataInputStream in = new DataInputStream(TestFormatter.class.getResourceAsStream("/FormatTester2.dat"));
		f.read(v, in);
		
		Vector expected = new Vector();
		expected.add(new Long(12345));
		expected.add(new Long(67890));
		expected.add(new Long(34567));
		expected.add(new Long(98765));
		expected.add(new Long(67890));
		
		assertEquals(expected.size(), v.size());
		for (int i=0; i<expected.size(); ++i) {
			assertEquals(expected.get(i), v.get(i));
		}
	}
	
	@Test
	public void testNewline() throws InvalidFormatException {
		Formatter f = new Formatter("/");
		assertEquals("[Formatter /]", f.toString());

		Formatter f2 = new Formatter(",/,");
		assertEquals("[Formatter /]", f2.toString());
	}
	
	@Test
	public void testEndOfLineInData() throws InvalidFormatException {
		Formatter f = new Formatter("5I5");
		
		assertEquals("[Formatter 5(I5)]", f.toString());
		
		Vector v = new Vector();
		DataInputStream in = new DataInputStream(FormatTester04.class.getResourceAsStream("/FormatTester4.dat"));

		assertThrows(DataMissingOnReadException.class, () -> {
			f.read(v, in);
		});
	}
	
	
	
	
	
	
	
}
