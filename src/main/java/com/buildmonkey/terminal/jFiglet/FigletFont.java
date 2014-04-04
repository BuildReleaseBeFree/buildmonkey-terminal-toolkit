package com.buildmonkey.terminal.jFiglet;

import com.buildmonkey.Classpath;
import com.buildmonkey.types.typeHelpers;

import java.util.*;
import java.io.*;


/**
 * FigletFont implementation. A single static method call will create the ascii art in a mulitilne String.
 * FigletFont format is specified at: https://github.com/lalyos/jfiglet/blob/master/figfont.txt
 * 
 * <pre>
 * <code>String asciiArt = FigletFont.getBanner("hello");</code>
 * </pre>
 * 
 * Originally found at: http://www.rigaut.com/benoit/CERN/FigletJava/. Moved to
 * <a href="http://lalyos.github.io/jfiglet/">github.com</a>.
 * 
 * @author Benoit Rigaut CERN July 96
 * www.rigaut.com benoit@rigaut.com
 * released with GPL the 13th of november 2000 (my birthday!)
 * FigletFont is now jFiglet
 *
 */
public class FigletFont {
    public char hardblank;
    public int height = -1;
    public int heightWithoutDescenders = -1;
    public int maxLine = -1;
    public int smushMode = -1;
    public char font[][][] = null;
    public String fontName = null;
    final public static int MAX_CHARS = 1024;

    /**
     * Returns all character from this Font. Each character is defined as
     * char[][]. So the whole font is a char[][][].
     *
     * @return The representation of all characters.
     */
    public char[][][] getFont() {
        return font;
    }

    /**
     * Return a single character represented as char[][].
     *
     * @param c The numerical id of the character.
     * @return The definition of a single character.
     */
    public char[][] getChar(int c) {
        return font[c];
    }

    /**
     * Selects a single line from a character.
     *
     * @param c Character id
     * @param l Line number
     * @return The selected line from the character
     */
    public String getCharLineString( int c, int l ) {
        if ( font[ c ][ l ] == null )
            return null;
        else {
            String ret = new String( font[ c ][ l ] );
            return ret.substring( 1 );
        }
    }

    /**
     * Creates a FigletFont as specified at: https://github.com/lalyos/jfiglet/blob/master/figfont.txt
     *
     * @param stream
     */
    public FigletFont(InputStream stream) {
        font = new char[ MAX_CHARS ][][];
        DataInputStream data;
        String dummyS;
        char dummyC;
        int dummyI;
        int charCode;
        String codeTag;
        try {
            data = new DataInputStream( new BufferedInputStream( stream ) );
            dummyS = data.readLine();
            StringTokenizer st = new StringTokenizer( dummyS, " " );
            String s = st.nextToken();
            hardblank = s.charAt( s.length() - 1 );
            height = Integer.parseInt( st.nextToken() );
            heightWithoutDescenders = Integer.parseInt( st.nextToken() );
            maxLine = Integer.parseInt( st.nextToken() );
            smushMode = Integer.parseInt( st.nextToken() );
            dummyI = Integer.parseInt( st.nextToken() );

			/*
			 * try to read the font name as the first word of the first comment
			 * line, but this is not standardized !
			 */
            st = new StringTokenizer( data.readLine(), " " );
            if ( st.hasMoreElements() )
                fontName = st.nextToken();
            else
                fontName = "";

            for ( int i = 0; i < dummyI - 1; i++ ) // skip the comments
                dummyS = data.readLine();
            charCode = 31;
            while ( dummyS != null ) {  // for all the characters
                charCode++;
                for ( int h = 0; h < height; h++ ) {
                    dummyS = data.readLine();
                    if ( dummyS != null ) {
                        int iNormal = charCode;
                        boolean abnormal = true;
                        if ( h == 0 ) {
                            try {
                                codeTag = dummyS.concat( " " ).split( " " )[ 0 ];
                                if ( codeTag.length() > 2 && "x".equals( codeTag.substring( 1, 2 ) ) ) {
                                    charCode = Integer.parseInt( codeTag.substring( 2 ), 16 );
                                } else {
                                    charCode = Integer.parseInt( codeTag );
                                }
                            } catch ( NumberFormatException e ) {
                                abnormal = false;
                            }
                            if ( abnormal )
                                dummyS = data.readLine();
                            else
                                charCode = iNormal;
                        }
                        if ( h == 0 )
                            font[ charCode ] = new char[ height ][];
                        int t = dummyS.length() - 1 - ( ( h == height - 1 ) ? 1 : 0 );
                        if ( height == 1 )
                            t++;
                        font[ charCode ][ h ] = new char[ t ];
                        for ( int l = 0; l < t; l++ ) {
                            char a = dummyS.charAt( l );
                            font[ charCode ][ h ][ l ] = ( a == hardblank ) ? ' ' : a;
                        }
                    }
                }
            }
        } catch ( IOException e ) {
            System.out.println( "IO Error: " + e.getMessage() );
        }
    }

    public static String listAllFonts() throws Exception {
        // Get an array of fonts
        String[] fonts = Classpath.getClasspathEntitysWithExtension( "flf" );
        // Sort the list
        typeHelpers.sortStringArrayOfStringsCaseInsensitiveOrder( fonts );
        String listToReturn = "";
        for ( String font: fonts ) {
            listToReturn += font+"\n";
        }
        return listToReturn;
    }


    public static String getBanner( String bannerText ) {
        return getBannerAsFont( "standard", bannerText );
    }

    public static String getBannerAsFont( String fontName, String message ) {
        String result = "";
        FigletFont figletFont;
        try {
            InputStream stream = FigletFont.class.getClassLoader().getResourceAsStream( "fonts/"+fontName+".flf" );
            figletFont = new FigletFont( stream );
            for ( int l = 0; l < figletFont.height; l++ ) {
                // for each line
                for ( int c = 0; c < message.length(); c++ )
                    // for each char
                    result += figletFont.getCharLineString( (int) message.charAt( c ), l );
                result += '\n';
            }

        } catch ( Exception e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public static String getBannerMaxWidth( String bannerText, int maxWidth ) {
        return getBannerAsFontMaxWidth( "standard", maxWidth , bannerText);
    }

    public static String getBannerAsFontMaxWidth( String font, int maxWidth, String bannerText ) {
        // Render the Ascii Art and assign to a scanner
        String generatedBanner = FigletFont.getBannerAsFont( font, bannerText );
        Scanner bannerScanner;
        String bannerTextToDo = "";
        String formattedBannerToReturn = "";
        // In case we are dealing with something that is wider than our required maxWidth lets test if that is the case
        while ( typeHelpers.getLengthOfLongestLineOfString( generatedBanner ) > maxWidth ){
            // Inner while loop to pop characters onto bannerTextToDo until we have the desired width
            while ( typeHelpers.getLengthOfLongestLineOfString( generatedBanner ) > maxWidth ){
                // We are over the desired width, so lets pop off chars and test until we are under it
                // Stick last char from bannerText onto bannerTextToDo
                bannerTextToDo = Character.toString( bannerText.charAt( bannerText.length() - 1 ) )+bannerTextToDo;
                // And now pop it off the end of of bannerText
                bannerText = bannerText.substring( 0, bannerText.length() - 1 );
                // Now we have popped onto our stack, lets re-generate our banner...
                generatedBanner = FigletFont.getBannerAsFont( font, bannerText );
            }
            bannerScanner = new Scanner( generatedBanner );
            // We have a line of banner that is less than the required width, so lets output it
            while ( bannerScanner.hasNextLine() ) {
                formattedBannerToReturn += bannerScanner.nextLine()+"\n";
            }
            // And now we have flushed our output to the formattedBannerToReturn String, lets re-assign so we can cont.
            bannerText = bannerTextToDo;
            bannerTextToDo = "";
            // In case our looping has left us to start a line with spaces at the start of our banner
            bannerText = bannerText.replaceAll( "^\\s+", "" );  // Lets only .trim() the start of the line
            generatedBanner = FigletFont.getBannerAsFont( font, bannerText );
        }
        generatedBanner = FigletFont.getBannerAsFont( font, bannerText );
        bannerScanner = new Scanner( generatedBanner );
        // And, now lets deal with the leftovers...
        while ( bannerScanner.hasNextLine() ) {
            formattedBannerToReturn += bannerScanner.nextLine()+"\n";
        }
        bannerScanner.close();
        return formattedBannerToReturn;
    }

    /**
     * This is the main method which enables command-line usage
     * <p/>
     * <pre>
     * java -jar jfiglet.jar "hello"
     * </pre>
     *
     * @param args the first argument will be converted to ascii art
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //String textHeading = "BuildMonkey Terminal Toolkit";
        //String textSubHeading = "Featuring Figlet style Banners";
        //String textInlineHeading = "BuildMonkey Term Tools";
        //System.out.println(getBannerAsFont("chunky", textHeading));
        //System.out.println(getBannerAsFont("small", textSubHeading));
        //System.out.println(getBannerAsFont("rectangles", textInlineHeading));

        String headingText = "Some Message to Test Some Message to Test Some meSSage To Test...";
        String longSample = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //String headingText = "Some Message to Test";
        int textWidth = 180;
        //String font = "linux";
        String font = "3d_diagonal";
        //String font = "funfaces";
        //String font = "standard";
        //String font = "standard";
        //String font = "bear";
        //String font = "funface";
        //System.out.println(getBannerMaxWidth(headingText, textWidth));
        //System.out.println( getBannerAsFontMaxWidth( font, textWidth, headingText ) );
        System.out.println( getBannerAsFontMaxWidth( font, textWidth, longSample ) );
    }
}



