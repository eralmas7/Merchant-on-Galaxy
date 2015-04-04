package com.galaxy.merchant.coverter;

import com.galaxy.merchant.datatype.RomanSymbol;
import com.galaxy.merchant.exception.InvalidInputException;
import com.galaxy.merchant.number.ArabicNumber;
import com.galaxy.merchant.number.GalaxyNumber;
import com.galaxy.merchant.utils.GalaxyConstants;
import com.galaxy.merchant.validator.TranslationValidator;

/**
 * Converts Roman number to Arabic number.
 */
public class RomanUnitConverter implements UnitConverter {

    /**
     * Converts Roman number to Arabic number.
     */
    @Override
    public GalaxyNumber convert(final String romanNumber) throws InvalidInputException {
        if (TranslationValidator.isValidRomanNumber(romanNumber)) {
            final double totalArabicResult = convertRomanToArabic(romanNumber);
            final GalaxyNumber convertedValue = new ArabicNumber(totalArabicResult);
            return convertedValue;
        }
        throw new InvalidInputException(GalaxyConstants.ROMAN_RULE_VIOLATED);
    }

    private RomanSymbol getRomanNumber(final char romanChar) {
        final RomanSymbol symbol = RomanSymbol.valueOf(String.valueOf(romanChar));
        return symbol;
    }

    private double convertRomanToArabic(final String romanNumber) {
        double totalArabicResult = 0d;
        int lastArabicNumber = 0;
        RomanSymbol romanSymbol;
        for (int index = romanNumber.length() - 1; index >= 0; index--) {
            romanSymbol = getRomanNumber(romanNumber.charAt(index));
            totalArabicResult = romanSymbol.getArabicEquivalent(lastArabicNumber, totalArabicResult);
            lastArabicNumber = romanSymbol.getValue();
        }
        return totalArabicResult;
    }
}
