package com.buildmonkey.terminal.display;

/**
 * Created by v-jborkowski on 04/04/2014.
 */
public class displayUtils {
    public static void main(String[] args) throws Exception {


    }

    public static String returnLineOfStrings( int repeatStringOnLineCount, String stringToRepeat ) {
        String toReturn = "";
        // Print out a line of *'s the width that we will use to wrap the output of our banner
        while ( repeatStringOnLineCount > 0 ) {
            toReturn += stringToRepeat+"\n";
            repeatStringOnLineCount--;
        }
        return toReturn;

    }


    public static void printLineOfStrings( int repeatStringOnLineCount, String stringToRepeat ) {
        // Print out a line of *'s the width that we will use to wrap the output of our banner
        while ( repeatStringOnLineCount > 0 ) {
            System.out.print( stringToRepeat );
            repeatStringOnLineCount--;
        }
        System.out.println();
    }

    public static void printLineOfStringsWithTitle( int repeatStringOnLineCount, String stringToRepeat,
                                                    String titleOfLine ) {
        // Print out a line of *'s the width that we will use to wrap the output of our banner
        String stringToReturn = "";
        while ( repeatStringOnLineCount > 0 ) {
            stringToReturn += stringToRepeat;
            repeatStringOnLineCount--;
        }
        System.out.println( stringToRepeat );

    }




}
