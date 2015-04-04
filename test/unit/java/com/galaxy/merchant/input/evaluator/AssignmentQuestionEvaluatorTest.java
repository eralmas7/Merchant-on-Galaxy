package com.galaxy.merchant.input.evaluator;

import org.junit.Before;
import org.junit.Test;
import com.galaxy.merchant.input.parser.InputParseStrategy;
import com.galaxy.merchant.input.parser.ParserStrategy;
import com.galaxy.merchant.wealth.TradingObject;
import junit.framework.Assert;
import junit.framework.TestCase;

public class AssignmentQuestionEvaluatorTest extends TestCase {

    private InputTypeEvaluator assignmentQuestionEvaluator;

    @Before
    public void setUp() {
        ParserStrategy parserStrategy = new InputParseStrategy();// good to mock
        // here!
        assignmentQuestionEvaluator = new AssignmentQuestionEvaluator(parserStrategy);
    }

    @Test
    public void testAssignmentQuestionEvaluationWithValidInput() {
        final String inputByMerchant = "glob is I";
        try {
            final TradingObject tradingObject = assignmentQuestionEvaluator.evaluateTradingObject(inputByMerchant);
            Assert.assertEquals(tradingObject.getName(), "glob");
            Assert.assertEquals(tradingObject.getValue(), "I");
        } catch (Exception e) {
            Assert.fail("Didnt expected exception as it's a valid input " + inputByMerchant);
        }
    }

    @Test
    public void testAssignmentQuestionEvaluationWithInvalidInput() {
        final String inputByMerchant = "what's credits";
        try {
            assignmentQuestionEvaluator.evaluateTradingObject(inputByMerchant);
            Assert.fail("Expected an exception as it's an invalid input " + inputByMerchant);
        } catch (Exception e) {
        }
    }
}
