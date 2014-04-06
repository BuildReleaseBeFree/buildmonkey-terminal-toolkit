package com.buildmonkey.terminal.banner;

import com.buildmonkey.types.typeHelpers;

import java.util.Properties;
import java.util.Scanner;

/**
 * Created by v-jborkowski on 25/03/2014.
 */
public class TestClassJB {
    public static void main(String[] args) {

        System.out.println(FigletFont.getBannerAsFont("fonts/big", "Hi there"));

        String terminalColumns = System.getenv("COLUMNS");
        String termCol = System.getProperty("COLUMNS");
        Properties envList = System.getProperties();
        System.out.println("and now.. " + envList.getProperty("COLUMNS"));
        System.out.println("Terminal has: " + terminalColumns + " columns.");
        System.out.println("Terminal has: " + termCol + " columns.");
        String headingText = "abcdefghijklmnopqrstuvwxyz";
        int textWidth = 80;

        // Render the Ascii Art and assign to a scanner
        String asciiArt = FigletFont.getBanner(headingText);
        System.out.println(asciiArt);
        Scanner scanner = new Scanner( asciiArt );
        String headingTextToDo = "";
        // In case we are dealing with something that is wider than our required
        // textWidth lets test if that is the case
        while ( typeHelpers.getLengthOfLongestLineOfString( asciiArt ) > textWidth ){
            while ( typeHelpers.getLengthOfLongestLineOfString( asciiArt ) > textWidth ){
                // It is, so lets pop off chars until we have meet the required width
                headingTextToDo=Character.toString(headingText.charAt(headingText.length()-1))+headingTextToDo;
                headingText=headingText.substring(0, headingText.length()-1);
                asciiArt = FigletFont.getBanner(headingText);
                scanner = new Scanner( asciiArt );
            }
            // We have a line of banner that is less than the required width, so lets output it
            while (scanner.hasNextLine()) {
                System.out.println("" + scanner.nextLine());
            }
            //System.out.println(scanner);
            headingText=headingTextToDo;
            headingTextToDo="";
            asciiArt = FigletFont.getBanner(headingText);
            scanner = new Scanner( asciiArt );
        }
        // And the leftovers... :-)
        //System.out.println(scanner.toString());
        while (scanner.hasNextLine()) {
            System.out.println("" + scanner.nextLine());
        }
        scanner.close();


    }

}
