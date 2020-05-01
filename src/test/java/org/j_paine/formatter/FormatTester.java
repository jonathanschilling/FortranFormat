package org.j_paine.formatter;
/* FormatTester.java */


import java.io.DataInputStream;
import java.io.StringBufferInputStream;
import java.util.Vector;


public class FormatTester
{
  public static void main( String[] args )
  {
    try {
      Formatter f;
      f = new Formatter( "1X,2I4,X/,I5/,"+
                         "F12.5,E12.5"
                       );
      System.out.println( f.toString() );

      f = new Formatter( "1X,2I4,X/,'x'I5'y'/,'a'F12.5'b','c'E12.5'd'" );
      System.out.println( f.toString() );
      Vector v = new Vector();
      v.addElement( new Integer(111) );
      v.addElement( new Integer(222) );
      v.addElement( new Integer(333) );
      v.addElement( new Float(444.44) );
      v.addElement( new Double(555.55) );
      f.write( v, System.out );
      System.out.println();

      testOut( "'['I3']'/", new Integer(1) );
      testOut( "'['I3']'/", new Integer(12) );
      testOut( "'['I3']'/", new Integer(123) );
      testOut( "'['I3']'/", new Integer(1234) );

      testOut( "'['I3']'/", new Integer(-1) );
      testOut( "'['I3']'/", new Integer(-12) );
      testOut( "'['I3']'/", new Integer(-123) );
      testOut( "'['I3']'/", new Integer(-1234) );

      testOut( "'['F7.3']'/", new Float(12.345) );
      testOut( "'['F7.3']'/", new Float(123.456) );
      testOut( "'['F7.3']'/", new Float(1234.567) );
      testOut( "'['F7.3']'/", new Float(12345.678) );

      testOut( "'['F7.3']'/", new Float(-12.345) );
      testOut( "'['F7.3']'/", new Float(-123.456) );
      testOut( "'['F7.3']'/", new Float(-1234.567) );
      testOut( "'['F7.3']'/", new Float(-12345.678) );

      testOut( "'['E12.3']'/", new Float(12.345) );
      testOut( "'['E12.3']'/", new Float(123.456) );
      testOut( "'['E12.3']'/", new Float(1234.567) );
      testOut( "'['E12.3']'/", new Float(12345.678) );

      testOut( "'['E12.3']'/", new Float(-12.345) );
      testOut( "'['E12.3']'/", new Float(-123.456) );
      testOut( "'['E12.3']'/", new Float(-1234.567) );
      testOut( "'['E12.3']'/", new Float(-12345.678) );

      testIn( "I3", "123" );

      testIn( "'['I3']'", "x" );
      testIn( "'['I3']'", "[1" );
      testIn( "'['I3']'", "[123" );
      testIn( "'['I3']'", "[123]" );
      testIn( "'['I3']'", "[-12]" );
      testIn( "'['I3']'", "[  1]" );
      testIn( "'['I3']'", "[ 4 ]" );
      testIn( "'['I3']'", "[123x" );
      testIn( "'['I3']'", "[yyy]" );
    }
    catch ( InvalidFormatException e ) {
      System.out.println( e );
    }
    catch ( OutputFormatException e ) {
      System.out.println( e );
    }
  }

  static void testOut( String format, Object o )
  {
    System.out.println( "\nOutputting " + o + " against " + format + " :" );
    try {
      Formatter f = new Formatter( format );
      f.write( o, System.out );
    }
    catch ( InvalidFormatException e ) {
      System.out.println( "SYNTAX ERROR\n" + e );
    }
    catch ( OutputFormatException e ) {
      System.out.println( "FORMATTING ERROR\n" + e );
    }
  }


  static void testIn( String format, String data )
  {
    System.out.println( "\nInputting " + data + " against " + format + " :" );
    try {
      Formatter f = new Formatter( format );
      DataInputStream in =
        new DataInputStream( new StringBufferInputStream(data) );
      Object o = f.read( in );
      System.out.println( "  Read " + o );
    }
    catch ( InvalidFormatException e ) {
      System.out.println( "SYNTAX ERROR\n" + e );
    }
    catch ( InputFormatException e ) {
      System.out.println( "FORMATTING ERROR\n" + e );
    }
  }
}
