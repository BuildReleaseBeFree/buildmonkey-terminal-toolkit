package com.buildmonkey.terminal.display;

import com.buildmonkey.types.typeHelpers;

import java.util.ArrayList;

/**
 * Created by v-jborkowski on 01/04/2014.
 */
public class Column {

    public static void renderStringArrayAsFixedWidthColumns(int columns, String[] list, int columnWidth) {
        // Lets start by converting our given String[] into an ArrayList that we can modify the object on the fly
        ArrayList<String> wordList = typeHelpers.getArrayListFromStringArray( list );
        // Now, lets pad up the remaining cells so we can render a full table of columns without Null Pointer Exceptions
        int numberOfColumns = columns;
        int numberOfRows = ( int ) Math.ceil( wordList.size() / ( double ) numberOfColumns );
        int remainder = wordList.size() % numberOfColumns;
        int emptyCellCount = numberOfColumns - remainder;
        while ( emptyCellCount > 0 ) {
            wordList.add( "" );
            emptyCellCount--;
        }
        // Lets draw our table...
        int row, column;
        for ( row = 1; row <= numberOfRows; row++ ) {
            for ( column = 0; column < numberOfColumns; column++ ) {
                // Lets have a cell
                System.out.printf( "%-"+columnWidth+"s", wordList.get(( column * numberOfRows ) + row - 1 ));
            }
            // And a line feed
            System.out.println();
        }
    }
    public static void renderStringArrayAsColumns( int columns, String[] list ) {
        int columnSpacing = 2;
        int columnWidth = typeHelpers.getLengthOfLongestStringInStringArrayOfStrings( list ) + columnSpacing;
        renderStringArrayAsFixedWidthColumns( columns, list, columnWidth );
    }

    public static void renderStringArrayAsColumnsOnFixedWidthTerminal( int termWidth, String[] list ) {
        int columnSpacing = 2;
        int columnWidth = typeHelpers.getLengthOfLongestStringInStringArrayOfStrings( list ) + columnSpacing;
        int maxColumns = ( int ) Math.ceil( termWidth / columnWidth );
        renderStringArrayAsFixedWidthColumns( maxColumns, list, columnWidth );
    }

    public static void main(String[] args) throws Exception {

        String[] fonts = { "3-d", "3d_diagonal", "3x5", "5lineoblique", "B1FF", "DANC4", "acrobatic", "alligator",
                "alligator2", "alpha", "amc3line", "amc3liv1", "amcaaa01", "amcneko", "amcrazo2", "amcrazor",
                "amcslash", "amcslder", "amcthin", "amctubes", "amcun1", "arrows", "avatar", "banner3-D", "banner4",
                "barbwire", "basic", "bear", "bell", "benjamin", "big", "bigchief", "bigfig", "block", "blocks",
                "bubble", "bulbhead", "calgphy2", "caligraphy", "cards", "catwalk", "chiseled", "chunky", "coinstak",
                "cola", "computer", "contessa", "contrast", "cosmic", "cosmike", "crawford", "crazy", "cricket",
                "cursive", "cyberlarge", "cybermedium", "cybersmall", "cygnet", "dancingfont", "defleppard", "diamond",
                "dietcola", "digital", "doh", "doom", "dosrebel", "dotmatrix", "double", "doubleshorts", "drpepper",
                "eftifont", "eftirobot", "eftitalic", "eftiwall", "eftiwater", "epic", "fender", "flipped",
                "flowerpower", "fourtops", "fraktur", "funface", "funfaces", "fuzzy", "georgi16", "ghost", "ghoulish",
                "glenyn", "goofy", "gothic", "graffiti", "henry3d", "hieroglyphs", "hollywood", "horizontalleft",
                "horizontalright", "impossible", "invita", "isometric1", "isometric2", "isometric3", "isometric4",
                "italic", "jerusalem", "katakana", "kban", "keyboard", "knob", "konto", "kontoslant", "larry3d", "lcd",
                "lean", "lildevil", "lineblocks", "linux", "lockergnome", "madrid", "marquee", "merlin1", "merlin2",
                "mike", "mini", "mirror", "morse", "morse2", "moscow", "nancyj", "nancyj-fancy", "nancyj-improved",
                "nancyj-underlined", "nipples", "nscript", "ntgreek", "nvscript", "ogre", "oldbanner", "os2", "pawp",
                "peaks", "peaksslant", "pebbles", "pepper", "poison", "puffy", "puzzle", "rammstein", "rectangles",
                "red_phoenix", "relief", "relief2", "rev", "reverse", "roman", "rotated", "rounded", "rowancap",
                "santaclara", "sblood", "script", "serifcap", "shadow", "shimrod", "short", "slant", "slide",
                "slscript", "small", "smallcaps", "smisome1", "smkeyboard", "smpoison", "smscript", "smshadow",
                "smslant", "smtengwar", "soft", "speed", "spliff", "stacey", "stampate", "stampatello", "standard",
                "starstrips", "starwars", "stellar", "stop", "straight", "sub-zero", "swampland", "swan", "tanja",
                "test1", "ticks", "ticksslant", "tiles", "tombstone", "train", "trek", "tsalagi", "tubular", "twisted",
                "twopoint", "varsity", "wavy", "weird", "wetletter", "whimsy", "wow" };

        // List all fonts in a multi-column table
        // typeHelpers.printStringArrayOfStrings( fonts ); // Straight up list
        typeHelpers.sortStringArrayOfStringsCaseInsensitiveOrder( fonts );
        Column.renderStringArrayAsColumns( 6, fonts );
        System.out.println( "\nCurrently "+fonts.length+" fonts available to select from..." );
        System.out.println( "The largest text field is: "+
                typeHelpers.getLengthOfLongestStringInStringArrayOfStrings( fonts )+"\n" );
        renderStringArrayAsColumnsOnFixedWidthTerminal(180, fonts);



    }

}
