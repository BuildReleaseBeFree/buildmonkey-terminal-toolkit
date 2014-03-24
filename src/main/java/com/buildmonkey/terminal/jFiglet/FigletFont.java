package com.buildmonkey.terminal.jFiglet;

import net.sf.corn.cps.CPScanner;

import java.util.*;
import java.net.*;
import java.io.*;


/**
 * FigletFont implementation. A single static method call will create the ascii
 * art in a mulitilne String. FigletFont format is specified at: 
 * https://github.com/lalyos/jfiglet/blob/master/figfont.txt
 * 
 * <pre>
 * <code>String asciiArt = FigletFont.convertOneLine("hello");</code>
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
	 * @param c
	 *            The numerical id of the character.
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
	public String getCharLineString(int c, int l) {
		if (font[c][l] == null)
			return null;
		else {
			String ret = new String(font[c][l]);
			return ret.substring(1);
		}
	}

	/**
	 * Creates a FigletFont as specified at: https://github.com/lalyos/jfiglet/blob/master/figfont.txt
	 * 
	 * @param stream
	 */
	public FigletFont(InputStream stream) {
		font = new char[MAX_CHARS][][];
		DataInputStream data;
		String dummyS;
		char dummyC;
		int dummyI;
		int charCode;

		String codeTag;
		try {
			data = new DataInputStream(new BufferedInputStream(stream));

			dummyS = data.readLine();
			StringTokenizer st = new StringTokenizer(dummyS, " ");
			String s = st.nextToken();
			hardblank = s.charAt(s.length() - 1);
			height = Integer.parseInt(st.nextToken());
			heightWithoutDescenders = Integer.parseInt(st.nextToken());
			maxLine = Integer.parseInt(st.nextToken());
			smushMode = Integer.parseInt(st.nextToken());
			dummyI = Integer.parseInt(st.nextToken());

			/*
			 * try to read the font name as the first word of the first comment
			 * line, but this is not standardized !
			 */
			st = new StringTokenizer(data.readLine(), " ");
			if (st.hasMoreElements())
				fontName = st.nextToken();
			else 
				fontName = "";

			for (int i = 0; i < dummyI-1; i++) // skip the comments
				dummyS = data.readLine();
			charCode = 31;
			while (dummyS!=null) {  // for all the characters
				//System.out.print(i+":");
				charCode++;
				for (int h = 0; h < height; h++) {
					dummyS = data.readLine();
					if (dummyS != null){
						//System.out.println(dummyS);
						int iNormal = charCode;
						boolean abnormal = true;
						if (h == 0) {
							try {
								codeTag = dummyS.concat(" ").split(" ")[0];
								if (codeTag.length()>2&&"x".equals(codeTag.substring(1,2))){
									charCode = Integer.parseInt(codeTag.substring(2),16);
								} else {
									charCode = Integer.parseInt(codeTag);
								}
							} catch (NumberFormatException e) {
								abnormal = false;
							}
							if (abnormal)
								dummyS = data.readLine();
							else
								charCode = iNormal;
						}
						if (h == 0)
							font[charCode] = new char[height][];
						int t = dummyS.length() - 1 - ((h == height-1) ? 1 : 0);
						if (height == 1)
							t++;
						font[charCode][h] = new char[t];
						for (int l = 0; l < t; l++) {
							char a = dummyS.charAt(l);
							font[charCode][h][l] = (a == hardblank) ? ' ' : a;
						}
					}
				}
			}
		} catch (IOException e)  {
			System.out.println("IO Error: " + e.getMessage());
		}
	}
	
	public static String listAllFonts()  {
        //Fetch all the environment variables.  
        Map<String,String> enviornmentVariables = System.getenv();  
          
        //Fetch classpath  
        String classPath = enviornmentVariables.get("CLASSPATH");  
          
        //Split based on ";"  
        String[] arrayOfClassPathVariables = classPath.split(";");  
          

        int i=0;  
        //Lopp through array to filter   
        while(i<arrayOfClassPathVariables.length){  
              
            //Use your pattern in this if condition  
            if(arrayOfClassPathVariables[i].matches("f$")){
                //Load it using resource bundle  
            	System.out.println(arrayOfClassPathVariables[i].toString());
            }  
            i++;  
        }  
		
		String result = "";

		FigletFont figletFont;
		try {
			Enumeration<URL> listURLs = FigletFont.class.getClassLoader().getResources( "*flf" );
			//Enumeration<URL> listFILES = ClassLoader.getResources("*flf");
			
			while (listURLs.hasMoreElements()){
				Object element = listURLs.nextElement();
				// process element
				System.out.println(element.toString());

			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
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

	public static String convertOneLine(String message)  {
		String result = "";

		FigletFont figletFont;
		try {
			InputStream stream = FigletFont.class.getClassLoader().getResourceAsStream("standard.flf");
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
	
	public static void findFileOnClassPath() {
		final String classpath = System.getProperty("java.class.path");
		final String pathSeparator = System.getProperty("path.separator");
		final StringTokenizer tokenizer = new StringTokenizer(classpath, pathSeparator);
		while (tokenizer.hasMoreTokens()) {
			final String pathElement = tokenizer.nextToken();
			final File directoryOrJar = new File(pathElement);
			System.out.println(pathElement);

//			final File absoluteDirectoryOrJar = directoryOrJar.getAbsoluteFile();
//			if (absoluteDirectoryOrJar.isFile()) {
//				final File target = new File(absoluteDirectoryOrJar.getParent());
//				if (target.exists()) {
//					return target;
//				} else {
//					target = new File(directoryOrJar, fileName);
//					if (target.exists()) {
//						return target;
//					}
//				} return null;
//			}

		}
		
	}

	/**
	 * This is the main method which enables command-line usage
	 * 
	 * <pre>
	 * java -jar jfiglet.jar "hello"
	 * </pre>
	 * 
	 * @param args
	 *            the first argument will be converted to ascii art
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String text = "JFIGLET";
		// String filename = "standard";
		String filename = "chunky";
		// String filename = "bulbhead";
		//String filename = "mini";
		//String filename = "pepper";
		//String filename = "small";
		//String filename = "thin";



        // List<URL> resources = CPScanner.scanResources(new ResourceFilter().packageName("net.sf.corn.cps.sample").resourceName("*.flf"));
        //List<URL> resources = CPScanner.scanResources(ResourceFilter().packageName("net.sf.corn.cps.sample").resourceName("*.flf"));

     //   List<URL> resources = CPScanner.scanResources(new ResourceFilter().packageName("net.sf.corn.cps.sample").resourceName("*.xml"));

        //findFileOnClassPath();

		if (args.length < 1) {
			System.out.println("Usage: java -jar jfiglet.jar <text-to-convert> (or) java -jar jfiglet.jar <font-flf-file> <text-to-convert>");
		} else if (args.length == 2) {
			filename = args[0];
			text = args[1];
		}
		else {
			text = args[0];
		}

        //InputStream stream = FigletFont.class.getClassLoader().getResourceAsStream("standard.flf");
        //figletFont = new FigletFont(stream);

        System.out.println(convertOneLineAsFont(filename , text));
        System.out.println(convertOneLineAsFont("mini" , text));
        System.out.println(convertOneLineAsFont("test/1row" , text));
        System.out.println(convertOneLineAsFont("test/alpha" , text));
        System.out.println(convertOneLineAsFont("test/arrows" , text));
        System.out.println(convertOneLineAsFont("pepper" , text));

        String[] fonts = Classpath.getMatchingExtensionFileNames("flf");

        for(int j=0; j<fonts.length;j++) {
            for (int i = j + 1; i < fonts.length; i++) {
                if (fonts[i].compareTo(fonts[j]) < 0) {
                    String temp = fonts[j];
                    fonts[j] = fonts[i];
                    fonts[i] = temp;


                }
            }
        }

        for ( String name : fonts ) {
            String filenameToRender = name.replace("." , "/");
            System.out.println(name);
            //System.out.println(convertOneLineAsFont(filenameToRender , text));
            System.out.println(convertOneLineAsFont(filenameToRender , "abcdefghijklmnopqrstuvwxyz"));
        }
        System.out.println("boo");
        System.out.println(listAllFonts());
        System.out.println("boo");

   /*     System.out.println(FigletFont.class.getClassLoader().getResources(""));

        Enumeration<URL> enumeration = FigletFont.class.getClassLoader().getResources("");
        System.out.println(enumeration.nextElement());
        while (enumeration.hasMoreElements()) {
            Object element =  enumeration.nextElement();
            // process element
            System.out.println(element.toString());
        }*/

		//listAllFonts();
		

		
		
		
		
	}


}
