package com.galaxy.merchant.input.evaluator;

import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import com.galaxy.merchant.coverter.RomanUnitConverter;
import com.galaxy.merchant.coverter.UnitConverter;
import com.galaxy.merchant.datatype.TranslatedOutput;
import com.galaxy.merchant.input.parser.ParserStrategy;
import com.galaxy.merchant.input.parser.QueryParseStrategy;
import com.galaxy.merchant.utils.GalaxyConstants;
import com.galaxy.merchant.wealth.Dirt;
import com.galaxy.merchant.wealth.TradingObject;
import junit.framework.Assert;
import junit.framework.TestCase;

public class HowMuchQuestionEvaluatorTest extends TestCase {

    private OutputTypeEvaluator howMuchQuestionEvaluator;

    @Before
    public void setUp() {
        ParserStrategy parserStrategy = new QueryParseStrategy();
        UnitConverter unitConverter = new RomanUnitConverter();
        Map<String, TradingObject> tradingObjectsMap = new HashMap<String, TradingObject>();
        tradingObjectsMap.put("glob", new Dirt("glob", "I"));
        tradingObjectsMap.put("pish", new Dirt("pish", "V"));
        howMuchQuestionEvaluator = new HowMuchQuestionEvaluator(unitConverter, tradingObjectsMap, parserStrategy);
    }

    @Test
    public void testHowMuchQuestionEvaluationWithValidInput() {
        final String inputByMerchant = "how much is pish glob glob ?";
        try {
            final TranslatedOutput translatedOutput = howMuchQuestionEvaluator.evaluateTradingQuery(inputByMerchant);
            Assert.assertEquals(translatedOutput.getTranslatedOutput(), "pish glob glob is 7");
        } catch (Exception e) {
            Assert.fail("Didnt expected exception as it's a valid input " + inputByMerchant);
        }
    }

    @Test
    public void testHowMuchQuestionEvaluationWithInvalidInput() {
        final String inputByMerchant = "how much wood could a woodchuck chuck if a woodchuck could chuck wood";
        try {
            final TranslatedOutput translatedOutput = howMuchQuestionEvaluator.evaluateTradingQuery(inputByMerchant);
            Assert.assertEquals(GalaxyConstants.NO_IDEA_STRING, translatedOutput.getTranslatedOutput());
        } catch (Exception e) {
        }
    }
}
