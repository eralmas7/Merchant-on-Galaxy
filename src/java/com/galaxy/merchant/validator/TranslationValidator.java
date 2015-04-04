package com.galaxy.merchant.validator;

import com.galaxy.merchant.utils.GalaxyConstants;

/**
 * Validation of translation logic.
 */
public class TranslationValidator {

    /**
     * Checks if the input number string is a valid Roman number or not.
     * 
     * @param romanNumber
     * @return true - if input Roman number is valid. false - if input Roman number is invalid.
     */
    public static boolean isValidRomanNumber(final String romanNumber) {
        return romanNumber.matches(GalaxyConstants.VALID_ROMAN_NUMBER_REGEX);
    }
}
