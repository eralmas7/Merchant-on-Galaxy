package com.galaxy.merchant.validator;

import java.util.Arrays;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;
import com.galaxy.merchant.datatype.RomanSymbol;

public class TranslationValidatorTest extends TestCase {

    private void testValuationWhenSymbolTranslates(final RomanSymbol[] allowedRomanSymbols, final RomanSymbol symbolInTest) {
        boolean isValidRomanNumber;
        for (RomanSymbol romanSymbol : RomanSymbol.values()) {
            isValidRomanNumber = TranslationValidator.isValidRomanNumber(symbolInTest.name() + romanSymbol.name());
            if (Arrays.binarySearch(allowedRomanSymbols, romanSymbol) >= 0) {
                Assert.assertTrue(isValidRomanNumber);
            } else {
                Assert.assertFalse(isValidRomanNumber);
            }
        }
    }

    @Test
    public void testValuationWhenISymbolSubtractsWithOthers() {
        final RomanSymbol[] allowedRomanSymbols = {RomanSymbol.I, RomanSymbol.V, RomanSymbol.X};
        testValuationWhenSymbolTranslates(allowedRomanSymbols, RomanSymbol.I);
    }

    @Test
    public void testValuationWhenXSymbolSubtractsWithOthers() {
        final RomanSymbol[] allowedRomanSymbols = {RomanSymbol.I, RomanSymbol.V, RomanSymbol.X, RomanSymbol.L, RomanSymbol.C};
        testValuationWhenSymbolTranslates(allowedRomanSymbols, RomanSymbol.X);
    }

    @Test
    public void testValuationWhenCSymbolSubtractsWithOthers() {
        final RomanSymbol[] allowedRomanSymbols = RomanSymbol.values();
        testValuationWhenSymbolTranslates(allowedRomanSymbols, RomanSymbol.C);
    }

    @Test
    public void testValuationWhenVSymbolSubtractsWithOthers() {
        final RomanSymbol[] allowedRomanSymbols = {RomanSymbol.I};
        testValuationWhenSymbolTranslates(allowedRomanSymbols, RomanSymbol.V);
    }

    @Test
    public void testValuationWhenLSymbolSubtractsWithOthers() {
        final RomanSymbol[] allowedRomanSymbols = {RomanSymbol.I, RomanSymbol.V, RomanSymbol.X};
        testValuationWhenSymbolTranslates(allowedRomanSymbols, RomanSymbol.L);
    }

    @Test
    public void testValuationWhenDSymbolSubtractsWithOthers() {
        final RomanSymbol[] allowedRomanSymbols = {RomanSymbol.I, RomanSymbol.V, RomanSymbol.X, RomanSymbol.L, RomanSymbol.C};
        testValuationWhenSymbolTranslates(allowedRomanSymbols, RomanSymbol.D);
    }

    @Test
    public void testValuationWhenSymbolIsRepeatedFourTimes() {
        boolean isValidRomanNumber;
        for (RomanSymbol romanSymbol : RomanSymbol.values()) {
            isValidRomanNumber = TranslationValidator.isValidRomanNumber(romanSymbol.name() + romanSymbol.name() + romanSymbol.name() + romanSymbol.name());
            Assert.assertFalse(isValidRomanNumber);
        }
    }

    @Test
    public void testValuationWhenSymbolIsRepeatedThrice() {
        boolean isValidRomanNumber;
        final RomanSymbol[] allowedRomanSymbols = {RomanSymbol.I, RomanSymbol.X, RomanSymbol.C, RomanSymbol.M};
        for (RomanSymbol romanSymbol : RomanSymbol.values()) {
            isValidRomanNumber = TranslationValidator.isValidRomanNumber(romanSymbol.name() + romanSymbol.name() + romanSymbol.name());
            if (Arrays.binarySearch(allowedRomanSymbols, romanSymbol) >= 0) {
                Assert.assertTrue(isValidRomanNumber);
            } else {
                Assert.assertFalse(isValidRomanNumber);
            }
        }
    }
}
