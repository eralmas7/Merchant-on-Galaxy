package com.galaxy.merchant.exception;

/**
 * An exception which will be used to throw whenever merchant tries to ask translator to translate
 * invalid queries.
 */
public class InvalidInputException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a InvalidInputException with the specified detail message.
     * 
     * @param message the detail message.
     */
    public InvalidInputException(final String message) {
        super(message);
    }

    /**
     * Constructs a InvalidInputException with the specified detail message and cause of the same.
     * 
     * @param message - detail exception message
     * @param throwable - cause of exception
     */
    public InvalidInputException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
