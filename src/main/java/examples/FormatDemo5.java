package examples;
/* FormatDemo5.java */


import java.io.DataInputStream;
import java.util.Hashtable;
import java.util.Vector;

import org.j_paine.formatter.Formatter;
import org.j_paine.formatter.InputFormatException;
import org.j_paine.formatter.InvalidFormatException;


public class FormatDemo5
{
  public static void main( String[] args )
  {
    try {
      Formatter f = new Formatter( "I3/,F12.4/,E12.4" );
      String[] keys = { "key1", "key2", "key3" };
      Hashtable ht = new Hashtable();
      f.read( keys, ht, new DataInputStream(System.in) );
      System.out.println(ht.toString());
    }
    catch ( InvalidFormatException e ) {
      System.out.println( e );
    }
    catch ( InputFormatException e ) {
      System.out.println( e );
    }
  }
}
