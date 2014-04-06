package com.buildmonkey.terminal.jFiglet;

import com.buildmonkey.Classpath;
import com.buildmonkey.types.typeHelpers;
import com.buildmonkey.terminal.display.Column;

import java.lang.management.ManagementFactory;
import java.util.*;

/**
 * Created by v-jborkowski on 24/03/2014.
 */
public class CommandLineLauncher {
    //Command line argument: -addNumbers adds all the number arguments after it
    public static void main(String[] args) throws Exception {
        // String textHeading = "BuildMonkey Terminal Toolkit";
        // String textSubHeading = "Featuring Figlet style Banners";
        int maxWidth = 160;
        String textInlineHeading = "BuildMonkey Term Tools";
        String longSample = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shortSample = "abcdefghijklmnopqrstuvwxyz";
        // System.out.println( FigletFont.getBannerAsFont( "chunky",         textHeading         ) );
        // System.out.println( FigletFont.getBannerAsFont( "small",          textSubHeading      ) );

        // Our header...
        System.out.println( FigletFont.getBannerAsFontMaxWidth("rectangles", maxWidth, textInlineHeading) );
        String usage = "Usage:\n" +
                "\n" +
                "   -h  (or) --help (or) -?                 Display this usage screen\n" +
                "   -w  (or) --width                        Set the display width in characters\n" +
                "                                           before starting a new line\n" +
                "   -l  (or) --list                         List all fonts available\n" +
                "   -a  (or) --display-all                  Display ALL fonts with sample\n" +
                "   -s  (or) --sample-font [FONT]           Sample particular [FONT]\n" +
                "   -S  (or) --sample-font-as [FONT] [TEXT] Sample [FONT] with [TEXT]\n" +
                "   -t  (or) --sample-text [TEXT]           Sample [TEXT] with all fonts\n" +
                "   -el (or) --exp-list [TEXT]              Sample [TEXT] with all fonts\n";

        //Check to see if we have any args to work with
        if ( args.length == 0 )
        {
            System.out.println( "\nThere were no command-line arguments given..." );
            // Show usage screen...
            System.out.println( usage );
        }
        else
        {
            // Get an array of fonts
            String[] fonts = Classpath.getClasspathEntitysWithExtension( "flf" );
            // Sort the list
            typeHelpers.sortStringArrayOfStringsCaseInsensitiveOrder( fonts );

            // Iterate through each of our Arguments
            // REMEMBER!: Any arguments before the matching one will be ignored.
            for( int i = 0; i < args.length; i++ ) {
                String argument = args[ i ];

                // Look for the -h or --help or -? parameter
                if ( argument.equals( "-h" ) || argument.equals( "--help" ) || argument.equals( "-?" ) ) {
                    // Show usage screen...
                    System.out.println( usage );
                }

                //Look for the -l or --list parameter
                else if ( argument.equals( "-l" ) || argument.equals( "--list" ) ) {
                    System.out.println( "Fonts Available for Selection:\n" );
                    // Lets remove the 'font.' from the start that we get as its in a fonts directory
                    fonts = typeHelpers.replaceFirstStringInEachStringInStringArrayOfStrings(fonts, "fonts.", "");
                    // List all fonts in a 4 column table
                    Column.renderStringArrayAsColumnsOnFixedWidthTerminal( 180, fonts );
                    // Lets make an ArrayList full of the fonts in our StringArray so that we can add to it
                    System.out.println( "\nCurrently "+fonts.length+" fonts available to select from..."   );
                }

                //Look for the -el or --experemental-list parameter
                else if ( argument.equals( "-el" ) || argument.equals( "--exp-list" ) ) {
                    System.out.println( "Fonts Available for Selection:\n" );
                    // Lets remove the 'font.' from the start that we get as its in a fonts directory
                    fonts = typeHelpers.replaceFirstStringInEachStringInStringArrayOfStrings( fonts, "fonts.", "");
                    // List all fonts in a fixed width Table of Columns
                    Column.renderStringArrayAsColumnsOnFixedWidthTerminal( 80, fonts );
                    // Lets make an ArrayList full of the fonts in our StringArray so that we can add to it
                    System.out.println( "\nCurrently "+fonts.length+" fonts available to select from..."   );
                }

                //Look for the -l or --list parameter
                else if (argument.equals("-L") || argument.equals("--long-list")) {
                    System.out.println("Fonts Available for Selection:\n");
                    // Lets remove the 'font.' from the start that we get as its in a fonts directory
                    fonts = typeHelpers.replaceFirstStringInEachStringInStringArrayOfStrings( fonts, "fonts.", "");
                    typeHelpers.printStringArrayOfStrings( fonts );
                    System.out.println( "\nCurrently "+fonts.length+" fonts available to select from..." );
                }

                //Look for the -a or --display-all parameter
                else if (argument.equals( "-a" ) || argument.equals( "--display-all" ) ) {
                    System.out.println( "\n" );
                    // Lets remove the 'font.' from the start that we get as its in a fonts directory
                    fonts = typeHelpers.replaceFirstStringInEachStringInStringArrayOfStrings( fonts, "fonts.", "");
                    // Now, for each font, lets show our sample
                    for ( String fontNameToSample : fonts ) {
                        System.out.println( fontNameToSample+":" );
                        System.out.println( FigletFont.getBannerAsFontMaxWidth(fontNameToSample, maxWidth, longSample) );
                    }
                }

                //Look for the -s or --sample-font parameter
                else if ( argument.equals( "-s" ) || argument.equals( "--sample-font" ) ) {
                    String fontNameToSample = args[ i + 1 ];
                    System.out.println( "\n"+fontNameToSample+":" );
                    System.out.println( FigletFont.getBannerAsFontMaxWidth(fontNameToSample, maxWidth, longSample) );
                }

                //Look for the -S or --sample-font-as parameter
                else if ( argument.equals( "-S" ) || argument.equals( "--sample-font-as" ) ) {
                    String fontNameToSample = args[ i + 1 ];
                    String fontTextToSample = args[ i + 2 ];
                    System.out.println( "\n"+fontNameToSample+":" );
                    System.out.println( FigletFont.getBannerAsFontMaxWidth( fontNameToSample, maxWidth, fontTextToSample) );
                }

                //Look for the -D or --debug parameter
                else if ( argument.equals( "-D" ) || argument.equals( "--debug" ) ) {
                    String terminalColumns = System.getenv("COLUMNS");
                    String termCol = System.getProperty("COLUMNS");
                    Properties envList = System.getProperties();
                    System.out.println("and now.. " + envList.getProperty("COLUMNS"));
                    System.out.println("Terminal has: " + terminalColumns + " columns.");
                    System.out.println("Terminal has: " + termCol + " columns.");
                    System.out.println(System.getenv("JAVA_HOME"));
                    System.out.println(System.getenv("JAVA_HOME"));
                    System.out.println(System.getenv("COLUMNS"));


                    String runtime = ManagementFactory.getRuntimeMXBean().getName();
                    System.out.println( runtime );


                    Map<String, String> env = System.getenv();
                    for (String key : env.keySet())
                    {
                        if (key.equalsIgnoreCase("COLUMNS")) {
                            System.out.println("columns is set to: " + env.get(key));
                        }
                        //  System.out.println(key + ":" + env.get(key));
                        System.out.println(key + ":" + env.get(key).replaceFirst("\\[",""));
                    }
         //           int terminalWidth = jline.TerminalFactory.get().getWidth();
         //           System.out.println("My terminal is: " + terminalWidth + "columns wide!");
                }

                //Look for the -S or --sample-font-as parameter
                else if ( argument.equals( "-t" ) || argument.equals( "--sample-text" ) ) {
                    System.out.println();
                    // Lets remove the 'font.' from the start that we get as its in a fonts directory
                    for( int j = 0; j < fonts.length; j++ ) {
                        fonts[ j ] = fonts[ j ].toString().replaceFirst( "fonts.", "" );
                    }
                    String fontTextToSample = args[ i + 1 ];
                    for ( String name : fonts ) {
                        String filenameToRender = name;
                        System.out.println( name + ":" );
                        System.out.println( FigletFont.getBannerAsFontMaxWidth(filenameToRender, maxWidth,
                                fontTextToSample) );
                    }
                }
            }
        }
    }
}