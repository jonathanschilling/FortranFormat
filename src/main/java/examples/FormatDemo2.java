package examples;
/* FormatDemo2.java */

import org.j_paine.formatter.Formatter;
import org.j_paine.formatter.InvalidFormatException;
import org.j_paine.formatter.OutputFormatException;

public class FormatDemo2
{
  public static void main( String[] args )
  {
    try {
      Formatter f = new Formatter( "I3" );
      f.write( 111, System.out );
      System.out.println();
    }
    catch ( InvalidFormatException e ) {
      System.out.println( e );
    }
    catch ( OutputFormatException e ) {
      System.out.println( e );
    }
  }
}
