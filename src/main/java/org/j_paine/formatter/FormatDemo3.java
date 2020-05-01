package org.j_paine.formatter;
/* FormatDemo3.java */


import java.io.DataInputStream;
import java.util.Vector;


public class FormatDemo3
{
  public static void main( String[] args )
  {
    try {
      Formatter f = new Formatter( "I3/,F12.4/,E12.4" );
      Vector v = new Vector();
      f.read( v, new DataInputStream(System.in) );
      System.out.println(v.toString());
    }
    catch ( InvalidFormatException e ) {
      System.out.println( e );
    }
    catch ( InputFormatException e ) {
      System.out.println( e );
    }
  }
}
