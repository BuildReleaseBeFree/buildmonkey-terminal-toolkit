package com.buildmonkey.terminal.jFiglet;

/**
 * Created by v-jborkowski on 24/03/2014.
 */
public class CommandLineLauncher {

    //Command line argument: -addNumbers adds all the number arguments after it
    public static void main(String[] args) throws Exception {

        //Check to see if the String array is empty
        //Display a message to the user if it is
        if (args.length == 0)
        {
            System.out.println("No command-line arguments have been entered!");
        }
        else
        {
            // Get an array of fonts
            String[] fonts = Classpath.getMatchingExtensionFileNames("flf");
            // Sort the list
            for(int j=0; j<fonts.length;j++) {
                for (int i = j + 1; i < fonts.length; i++) {
                    if (fonts[i].compareTo(fonts[j]) < 0) {
                        String temp = fonts[j];
                        fonts[j] = fonts[i];
                        fonts[i] = temp;
                    }
                }
            }

            //boolean validNumbers = false;
            boolean addNumbers = false;
            //double total = 0;

            //Iterate through each string in the String array.
            for(String argument: args) {
                //Look for the -l or --list parameter
                //Any arguments before this will be ignored.
                if (argument.equals("-l") || argument.equals("--list")) {
                    // List all fonts
                    for (String name : fonts) {
                        String filenameToRender = name.replace(".", "/");
                        System.out.println(name);
                        // System.out.println(convertOneLineAsFont(filenameToRender , text));
                        // System.out.println(FigletFont.convertOneLineAsFont(filenameToRender , "abcdefghijklmnopqrstuvwxyz"));
                    }
                }

                //Look for the -a or --display-all parameter
                //Any arguments before this will be ignored.
                else if (argument.equals("-a") || argument.equals("--display-all")) {
                    // List all fonts
                    for (String name : fonts) {
                        String filenameToRender = name.replace(".", "/");
                        System.out.println(name);
                        // System.out.println(convertOneLineAsFont(filenameToRender , text));
                        System.out.println(FigletFont.convertOneLineAsFont(filenameToRender, "abcdefghijklmnopqrstuvwxyz"));
                    }
                }


//                else if (addNumbers) //addNumbers will be true once the -addNumbers has been found
//                {
//                    try {
//                        //Use the double class to be able to handle ints and doubles.
//                        total = total + Double.parseDouble(argument);
//                        validNumbers = true;
//
//                        //Catch any NumberFormatExceptions and display a message to the user
//                    } catch (NumberFormatException e) {
//                        System.out.println("The arguments you have entered are not all numbers.");
//                        validNumbers = false;
//                        addNumbers = false;
//                    }
//
//                }
//            }
//            if (validNumbers) //will be false if one of the arguments is not a number
//            {
//                System.out.println("The total value of the arguments is: " + total);
//            }
            }
        }
    }
}