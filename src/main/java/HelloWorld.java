import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;




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
	
	
	
    public static void main(String[] args) throws IOException { 
        System.out.println("Hello, World");
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
    	        System.out.println(entry.toString());
    	    }
    	}
    	finally
    	{
    	    file.close();
    	}
        
        
    }
}
