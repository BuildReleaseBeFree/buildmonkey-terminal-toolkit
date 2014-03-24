package com.buildmonkey.terminal.jFiglet;

/**
 * Created by v-jborkowski on 24/03/2014.
 */
public class CommandLineLauncher {

    //Command line argument: -addNumbers adds all the number arguments after it
    public static void main(String[] args) throws Exception {
        String usage = "Usage:\n" +
                "\n" +
                "   -l (or) --list                         List all fonts available\n" +
                "   -a (or) --display-all                  Display ALL fonts with sample\n" +
                "   -s (or) --sample-font [FONT]           Sample particular [FONT]\n" +
                "   -S (or) --sample-font-as [FONT] [TEXT] Sample [FONT] with [TEXT]\n" +
                "   -t (or) --sample-text [TEXT]           Sample [TEXT] with all fonts\n";

        //Check to see if the String array is empty
        //Display a message to the user if it is
        if (args.length == 0)
        {
            System.out.println("\nNo command-line arguments have been given!");
            // Our header
            System.out.println(FigletFont.convertOneLineAsFont("fonts/rectangles" , "BuildMonkey Term Tools"));
            // Show usage screen...
            System.out.println(usage);
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

            //Iterate through each string in the String array.
            //REMEMBER!: Any arguments before the matching one will be ignored.
            for(int i = 0; i < args.length; i++) {
                String argument = args[i];

                //Look for the -h or --help or -? parameter
                if (argument.equals("-h") || argument.equals("--help") || argument.equals("-?")) {
                    // Our header
                    System.out.println(FigletFont.convertOneLineAsFont("fonts/rectangles" , "BuildMonkey Term Tools"));
                    // Show usage screen...
                    System.out.println(usage);
                }

                //Look for the -l or --list parameter
                else if (argument.equals("-l") || argument.equals("--list")) {
                    // Our header
                    System.out.println(FigletFont.convertOneLineAsFont("fonts/rectangles" , "BuildMonkey Term Tools"));
                    System.out.println("Fonts Available for Selection:\n");
                    // Lets remove the 'font.' from the start that we get as its in a fonts directory
                    for(int j=0; j<fonts.length;j++) {
                        fonts[j] = fonts[j].toString().replaceFirst("fonts.", "");
                    }

                    // List all fonts in a 4 column table
                    for (int leftIndex=0; leftIndex<(fonts.length / 4); ++leftIndex)
                    {
                        System.out.printf("%-24s %-24s %-24s %-24s\n", fonts[leftIndex],
                                fonts[leftIndex + (fonts.length / 4)], fonts[leftIndex + ((fonts.length / 4) * 2)], fonts[leftIndex + ((fonts.length / 4) * 3)]);
                    }
                    System.out.println("\nCurrently " + fonts.length + " fonts available to select from...\n"   );

                }

                //Look for the -a or --display-all parameter
                else if (argument.equals("-a") || argument.equals("--display-all")) {
                    System.out.println("\n");
                    // List all fonts
                    for (String name : fonts) {
                        String filenameToRender = name.replace(".", "/");
                        System.out.println(name);
                        System.out.println(FigletFont.convertOneLineAsFont(filenameToRender, "abcdefghijklmnopqrstuvwxyz"));
                    }
                }

                //Look for the -s or --sample-font parameter
                else if (argument.equals("-s") || argument.equals("--sample-font")) {
                    String fontNameToSample = args[i+1];
                    System.out.println("\n" + fontNameToSample + ":");
                    System.out.println(FigletFont.convertOneLineAsFont("fonts/" + fontNameToSample, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")+"\n");
                }

                //Look for the -S or --sample-font-as parameter
                else if (argument.equals("-S") || argument.equals("--sample-font-as")) {
                    String fontNameToSample = args[i+1];
                    String fontTextToSample = args[i+2];
                    System.out.println("\n" + fontNameToSample + ":");
                    System.out.println(FigletFont.convertOneLineAsFont("fonts/" + fontNameToSample, fontTextToSample)+"\n");
                }

                //Look for the -S or --sample-font-as parameter
                else if (argument.equals("-t") || argument.equals("--sample-text")) {
                    System.out.println("\n");
                    // Lets remove the 'font.' from the start that we get as its in a fonts directory
                    for(int j=0; j<fonts.length;j++) {
                        fonts[j] = fonts[j].toString().replaceFirst("fonts.", "");
                    }
                    String fontTextToSample = args[i+1];
                    for (String name : fonts) {
                        String filenameToRender = name;
                        System.out.println(name);
                        System.out.println(FigletFont.convertOneLineAsFont("fonts/" + filenameToRender, fontTextToSample));
                    }
                }
            }
        }
    }
}