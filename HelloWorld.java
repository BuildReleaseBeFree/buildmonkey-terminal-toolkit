import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.buildmonkey.terminal.jFiglet.FigletFont;




public class HelloWorld {
	

	    
	private static int readInputStream( final InputStream is ) throws IOException {
	    final byte[] buf = new byte[ 8192 ];
	    int read = 0;
	    int cntRead;
	    while ( ( cntRead = is.read( buf, 0, buf.length ) ) >=0  )
	    {
	        read += cntRead;
	    }
	    return read;
	}
	
	public static String convertOneLineAsFont(String fontFile, String message)  {
		String result = "";

		FigletFont figletFont;
		try {
			InputStream stream = FigletFont.class.getClassLoader().getResourceAsStream( fontFile + ".flf" );
			figletFont = new FigletFont(stream);
			for (int l = 0; l < figletFont.height; l++) { // for each line
				for (int c = 0; c < message.length(); c++)
					// for each char
					result += figletFont.getCharLineString((int) message.charAt(c), l);
				result += '\n';
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
    public static void main(String[] args) throws IOException { 
    	String find = "standard";
    	
        System.out.println(Thread.currentThread().getContextClassLoader().getResource( "terminalFontPack001.zip"));
        
    	final ZipFile file = new ZipFile( Thread.currentThread().getContextClassLoader().getResource( "terminalFontPack001.zip").getFile() );
    	try
    	{
    	    final Enumeration<? extends ZipEntry> entries = file.entries();
    	    while ( entries.hasMoreElements() )
    	    {
    	        final ZipEntry entry = entries.nextElement();
    	        System.out.println( entry.getName() );
    	        //use entry input stream:
    	        readInputStream( file.getInputStream( entry ) );
    	        if (entry.getName().endsWith(find+".flf")){
    	        	  System.out.println(entry.toString()+" Found It!");
    	        	  InputStream streamedFont = file.getInputStream( entry );
    	        	  figletFont = new FigletFont(streamedFont);
    	        }
    	    }
    	}
    	finally
    	{
    	    file.close();
    	}
        
        
    }
}
