package com.galaxy.merchant.utils;

/**
 * Utilities needed for
 */
public class GalaxyUtils {

    /**
     * Given array of strings, it will return you all the strings combined and separated by with
     * space
     *
     * @param seperator separator to separate each string with while concatenating String.
     * @param strings String array to be concatenated and separated by separated.
     * @return
     */
    public static String concatenate(final char seperator, final String... strings) {
        if (strings == null || strings.length == 0) {
            return GalaxyConstants.EMPTY_STRING;
        } else {
            final String[] stringArray = getStringArray(seperator, strings);
            return concatenate(stringArray);
        }
    }

    private static String[] getStringArray(final char seperator, final String... strings) {
        final int arrayLength = strings.length * 2 - 1;
        final String[] stringArray = new String[arrayLength];
        int index = 0;
        final String seperatorString = new String(new char[] {seperator});
        for (int count = 0; count < strings.length - 1; count++) {
            stringArray[index++] = strings[count];
            stringArray[index++] = seperatorString;
        }
        stringArray[index] = strings[strings.length - 1];
        return stringArray;
    }

    private static int getStringLength(final String... strings) {
        int count = 0;
        for (int index = 0; index < strings.length; index++) {
            count += strings[index].length();
        }
        return count;
    }

    /**
     * Given array of strings, it will return you all the strings combined.
     * 
     * @param strings
     * @return
     */
    public static String concatenate(final String... strings) {
        if (strings == null || strings.length == 0) {
            return GalaxyConstants.EMPTY_STRING;
        } else {
            final StringBuilder stringBuilder = new StringBuilder(getStringLength(strings));
            for (int index = 0; index < strings.length; index++) {
                stringBuilder.append(strings[index]);
            }
            return stringBuilder.toString();
        }
    }
}
