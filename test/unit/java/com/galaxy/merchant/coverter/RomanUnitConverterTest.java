package com.galaxy.merchant.coverter;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import com.galaxy.merchant.exception.InvalidInputException;

public class RomanUnitConverterTest extends TestCase {

    private UnitConverter unitConverter;

    @Before
    public void setUp() throws Exception {
        unitConverter = new RomanUnitConverter();
    }

    @Test
    public void testConverterWithValidRomanNumber() {
        try {
            Assert.assertEquals(unitConverter.convert("VI").getValue(), 6.0d);
        } catch (InvalidInputException invalidInputException) {
            Assert.fail("Did not expected invalid input Exception");
        }
    }

    @Test
    public void testConverterWithInvalidRomanNumber() {
        try {
            unitConverter.convert("VV");
            Assert.fail("Expected invalid input exception but haven't got one");
        } catch (InvalidInputException invalidInputException) {
        }
    }

    @Test
    public void testConverterWithSubtractionOfRomanNumber() {
        try {
            Assert.assertEquals(unitConverter.convert("CM").getValue(), 900.0d);
        } catch (InvalidInputException invalidInputException) {
            Assert.fail("Did not expected invalid input Exception");
        }
    }
}
