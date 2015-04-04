package com.galaxy.merchant.utils;

/**
 * Series of constants to be used in the application.
 */
public class GalaxyConstants {

    public static final String VALID_ROMAN_NUMBER_REGEX = "M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
    public static final String NO_IDEA_STRING = "I have no idea what you are talking about";
    public static final String ROMAN_RULE_VIOLATED = "Violated roman number format/operation";
    public static final String SPACE_REGEX = "\\s+";
    public static final String CREDITS_REGEX = "(is\\s+)|([c|C]redits\\s*)";
    public static final String EMPTY_STRING = "";
    public static final char EMPTY_CHAR = '\u0000';
    public static final String SPACE_IS_SPACE_REGEX = "(\\sis\\s)";
    public static final String QUESTION_MARK = "?";
    public static final String IS_STRING_WITH_SPACE = " is ";
    public static final String CREDITS_STRING = " Credits";
    public static final String DOT_STRING = ".";
    public static final char SPACE = ' ';
    public static final String SPACE_STRING = " ";
    public static final String ASSIGNED_INPUT_PATTERN = "^([A-Za-z]+) is ([I|V|X|L|C|D|M])$";
    public static final String CREDIT_INPUT_PATTERN = "^([A-Za-z]+)([A-Za-z\\s]*) is ([0-9]+) ([c|C]redits)$";
    public static final String HOW_MUCH_INPUT_PATTERN = "^how much (([A-Za-z\\s])+)\\?$";
    public static final String HOW_MANY_INPUT_PATTERN = "^how many [c|C]redits is (([A-Za-z\\s])+)\\?$";
}
