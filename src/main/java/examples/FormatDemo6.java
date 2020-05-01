package examples;
/* FormatDemo6.java */


import java.io.DataInputStream;
import java.util.Vector;

import org.j_paine.formatter.FormatMap;
import org.j_paine.formatter.Formatter;
import org.j_paine.formatter.InputFormatException;
import org.j_paine.formatter.InvalidFormatException;


public class FormatDemo6
{
  public static void main( String[] args )
  {
    try {
      Formatter f = new Formatter( "I3/,F12.4/,E12.4" );

      FormatMap fm = new FD6FormatMap();
      f.setFormatMap( fm );

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


class FD6FormatMap extends FormatMap
{
  public String getMapping( String in )
  {
    if ( in.startsWith("x") )
      return "0";
    else
      return null;
  }
}
