package org.j_paine.formatter;
/* FormatTester9.java */


import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;


public class FormatTester9
{
  public static void main( String[] args )
  {
    try {
      Formatter f;
      f = new Formatter( "A10'abc',A2'xy',A4" );
      System.out.println( f.toString() );

      Vector v = new Vector();

      DataInputStream in =
        new DataInputStream( new FileInputStream("FormatTester9.dat") );

      f.read( v, in );
      System.out.println( v );
    }
    catch ( InvalidFormatException e ) {
      System.out.println( e );
    }
    catch ( IOException e ) {
      System.out.println( e );
    }
    catch ( InputFormatException e ) {
      System.out.println( e );
    }
  }

}
