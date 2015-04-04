package com.galaxy.merchant.input.evaluator;

import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import com.galaxy.merchant.coverter.RomanUnitConverter;
import com.galaxy.merchant.coverter.UnitConverter;
import com.galaxy.merchant.input.parser.InputParseStrategy;
import com.galaxy.merchant.input.parser.ParserStrategy;
import com.galaxy.merchant.wealth.Dirt;
import com.galaxy.merchant.wealth.TradingObject;
import junit.framework.Assert;
import junit.framework.TestCase;

public class CreditQuestionEvaluatorTest extends TestCase {

    private InputTypeEvaluator creditQuestionEvaluator;

    @Before
    public void setUp() {
        ParserStrategy parserStrategy = new InputParseStrategy();
        UnitConverter unitConverter = new RomanUnitConverter();// can be mocked
        Map<String, TradingObject> tradingObjectsMap = new HashMap<String, TradingObject>();
        tradingObjectsMap.put("glob", new Dirt("glob", "I"));
        creditQuestionEvaluator = new CreditQuestionEvaluator(unitConverter, tradingObjectsMap, parserStrategy);
    }

    @Test
    public void testCreditQuestionEvaluationWithValidInput() {
        final String inputByMerchant = "glob glob Silver is 34 Credits";
        try {
            final TradingObject tradingObject = creditQuestionEvaluator.evaluateTradingObject(inputByMerchant);
            Assert.assertEquals(tradingObject.getName(), "Silver");
            Assert.assertEquals(tradingObject.getValue(), "17.0");
        } catch (Exception e) {
            Assert.fail("Didnt expected exception as it's a valid input " + inputByMerchant);
        }
    }

    @Test
    public void testCreditQuestionEvaluationWithInvalidInput() {
        final String inputByMerchant = "what's credits";
        try {
            creditQuestionEvaluator.evaluateTradingObject(inputByMerchant);
            Assert.fail("Expected an exception as it's an invalid input " + inputByMerchant);
        } catch (Exception e) {
        }
    }
}
