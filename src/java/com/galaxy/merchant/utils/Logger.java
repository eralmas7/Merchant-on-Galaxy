package com.galaxy.merchant.utils;

/**
 * Utility to log events
 */
public class Logger {

    /**
     * Log information on console.
     * 
     * @param applicationMessage
     * @param exceptionMessage
     */
    public static void info(final String applicationMessage, final Object exceptionMessage) {
        System.out.println(applicationMessage + exceptionMessage);
    }

    /**
     * Log error.
     * 
     * @param statement
     */
    public static void error(final String statement, final Object exceptionMessage) {
        System.err.println(statement + exceptionMessage);
    }
}
