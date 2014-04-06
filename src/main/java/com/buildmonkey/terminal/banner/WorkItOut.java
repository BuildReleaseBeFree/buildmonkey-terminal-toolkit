package com.buildmonkey.terminal.banner;



import com.buildmonkey.terminal.display.Column;

/**
 * Created by v-jborkowski on 26/03/2014.
 */
public class WorkItOut {

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
/*        for (String one: fonts) {
            System.out.println(one);
        }*/
        // List all fonts in a multi-column table

        Column.renderStringArrayAsColumns(4, fonts);
        System.out.println("\n\nCurrently " + fonts.length + " fonts available to select from...\n");
        //System.out.print("The largest text field is: "+largestTextField);


        System.out.println(" a test ".trim());


        //Map<String, String> a = new Map<String, String>();







    }
}
