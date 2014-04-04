package com.buildmonkey.types;

import java.util.*;

/**
 * Created by v-jborkowski on 01/04/2014.
 */
public class typeHelpers {

    public static void main(String[] args) {
        String[] a = { "abc", "def", "some long string", "with", "another one"};
        for (String s: a) {
            System.out.println(s);
        }

        for (String s: replaceFirstStringInEachStringInStringArrayOfStrings(a, "a", "ZZZ")) {
            System.out.println(s);
        }




    }

    public static int getLengthOfLongestLineOfString(String text) {
        //Parses multi-line text and returns a character count of the longest line
        Scanner Scanner = new Scanner( text );
        int maxCharacters = 0;
        while ( Scanner.hasNextLine() ) {
            String line = Scanner.nextLine();
            if ( maxCharacters < line.length() ) {
                 maxCharacters = line.length();
            }
        }
        return maxCharacters;
    }

    public static int getLengthOfLongestStringInStringArrayOfStrings(String[] stringArray) {
        // Take a String[] and return the length of the longest String as an int
        int longestLength = 0;
        for (String s: stringArray) {
            if (s.length() > longestLength) {
                longestLength = s.length();
            }
        }
        return longestLength;
    }

    public static ArrayList<String> getArrayListFromStringArray(String[] stringArray) {
        // Take a String[] and return an ArrayList
        ArrayList<String> arrayListToReturn = new ArrayList<String>(stringArray.length);
        for (String s : stringArray) {
            arrayListToReturn.add(s);
        }
        return arrayListToReturn;
    }

    public static String[] sortStringArrayOfStrings(String[] list){
        Arrays.sort(list);
        return list;
    }

    public static String[] sortStringArrayOfStringsBubbleSort(String[] list){
        for( int j = 0; j < list.length; j++ ) {
            for ( int i = j + 1; i < list.length; i++ ) {
                if ( list[ i ].compareTo( list[ j ] ) < 0 ) {
                    String temp = list[ j ];
                    list[ j ] = list[ i ];
                    list[ i ] = temp;
                }
            }
        }
        return list;
    }

    public static String[] sortStringArrayOfStringsCaseInsensitiveOrder(String[] list){
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        return list;
    }

    public static ArrayList<String> sortArrayListOfStrings(ArrayList<String> list){
        Collections.sort(list);
        return list;
    }

    public static ArrayList<String> sortArrayListOfStringsCaseInsensitiveOrder(ArrayList<String> list){
        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
        return list;
    }

    public static void printStringArrayOfStrings(String[] list) {
        for ( String item: list ) {
            System.out.println( item );
        }
    }

    public static void printArrayListOfStrings(ArrayList<String> list) {
        for ( String item: list ) {
            System.out.println( item );
        }
    }

    public static String[] replaceFirstStringInEachStringInStringArrayOfStrings( String[] list,
                                                                             String findString,
                                                                             String replaceString ) {
        for( int pointer = 0; pointer < list.length; pointer++ ) {
            list[ pointer ] = list[ pointer ].toString().replaceFirst( findString, replaceString );
        }
        return list;
    }

    public static ArrayList<String> replaceFirstStringInEachStringInArrayListOfStrings(ArrayList<String> list,
                                                                          String findString,
                                                                          String replaceString ) {
        for( int pointer = 0; pointer < list.size(); pointer++ ) {
            list.set( pointer, list.get( pointer ).replaceFirst( findString, replaceString ) );
        }
        return list;
    }
}