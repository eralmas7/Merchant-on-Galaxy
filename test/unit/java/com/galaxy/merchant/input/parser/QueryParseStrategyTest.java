package com.galaxy.merchant.input.parser;

import org.junit.Before;
import org.junit.Test;
import junit.framework.Assert;
import junit.framework.TestCase;

public class QueryParseStrategyTest extends TestCase {

    private ParserStrategy queryParseStrategy;

    @Before
    public void setUp() {
        queryParseStrategy = new QueryParseStrategy();
    }

    @Test
    public void testParserWithValidInput() {
        final String[] inputs = new String[2];
        inputs[0] = "how much is pish tegj glob glob ?";
        inputs[1] = "how many Credits is glob prok Silver ?";
        for (int count = 0; count < inputs.length; count++) {
            try {
                queryParseStrategy.getTradingDetailsFromInputString(inputs[count]);
            } catch (Exception e) {
                Assert.fail("Did not expected any exception");
            }
        }
    }

    @Test
    public void testParserWithInvalidInput() {
        String input = "what Credits do you think would be glob";
        try {
            queryParseStrategy.getTradingDetailsFromInputString(input);
            Assert.fail("Did not expected to be here as i was expecting an exception");
        } catch (Exception e) {
        }
    }
}
