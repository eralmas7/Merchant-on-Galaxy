package com.galaxy.merchant.input.parser;

import org.junit.Before;
import org.junit.Test;
import junit.framework.Assert;
import junit.framework.TestCase;

public class InputParseStrategyTest extends TestCase {

    private ParserStrategy inputParseStrategy;

    @Before
    public void setUp() {
        inputParseStrategy = new InputParseStrategy();
    }

    @Test
    public void testParserWithValidInput() {
        final String[] inputs = new String[2];
        inputs[0] = "glob is I";
        inputs[1] = "glob glob Silver is 34 Credits";
        for (int count = 0; count < inputs.length; count++) {
            try {
                inputParseStrategy.getTradingDetailsFromInputString(inputs[count]);
            } catch (Exception e) {
                Assert.fail("Did not expected any exception");
            }
        }
    }

    @Test
    public void testParserWithInvalidInput() {
        String input = "what Credits ";
        try {
            inputParseStrategy.getTradingDetailsFromInputString(input);
            Assert.fail("Did not expected to be here as i was expecting an exception");
        } catch (Exception e) {
        }
    }
}
