package com.cyprien.kickunofficialapp;

public class KickString {
    // Return if "compare" starts with an item of the array
    public static boolean ArrayItemIsStartsOf(String[] array, String compare) {
        for (String item: array)
            if (compare.startsWith(item))
                return true;
        return false;
    }
}
