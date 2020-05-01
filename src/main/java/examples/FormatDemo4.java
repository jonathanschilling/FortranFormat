package examples;
/* FormatDemo4.java */

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import org.j_paine.formatter.Formatter;
import org.j_paine.formatter.InputFormatException;
import org.j_paine.formatter.InvalidFormatException;

public class FormatDemo4
{
  public static void main( String[] args )
  {
    try {
      Formatter f = new Formatter( "I3" );
      Object o = f.read( new DataInputStream(new ByteArrayInputStream("123".getBytes())) );
      System.out.println(o);
    }
    catch ( InvalidFormatException e ) {
      System.out.println( e );
    }
    catch ( InputFormatException e ) {
      System.out.println( e );
    }
  }
}
