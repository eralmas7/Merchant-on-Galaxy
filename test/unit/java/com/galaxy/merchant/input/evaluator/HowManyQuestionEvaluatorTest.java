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
import com.galaxy.merchant.wealth.Metal;
import com.galaxy.merchant.wealth.TradingObject;
import junit.framework.Assert;
import junit.framework.TestCase;

public class HowManyQuestionEvaluatorTest extends TestCase {

    private OutputTypeEvaluator howManyQuestionEvaluator;

    @Before
    public void setUp() {
        ParserStrategy parserStrategy = new QueryParseStrategy();
        UnitConverter unitConverter = new RomanUnitConverter();
        Map<String, TradingObject> tradingObjectsMap = new HashMap<String, TradingObject>();
        tradingObjectsMap.put("glob", new Dirt("glob", "I"));
        tradingObjectsMap.put("Gold", new Metal("Gold", 17.0d));
        howManyQuestionEvaluator = new HowManyQuestionEvaluator(unitConverter, tradingObjectsMap, parserStrategy);
    }

    @Test
    public void testHowManyQuestionEvaluationWithValidInput() {
        final String inputByMerchant = "how many Credits is glob glob Gold ?";
        try {
            final TranslatedOutput translatedOutput = howManyQuestionEvaluator.evaluateTradingQuery(inputByMerchant);
            Assert.assertEquals(translatedOutput.getTranslatedOutput(), "glob glob Gold is 34 Credits");
        } catch (Exception e) {
            Assert.fail("Didnt expected exception as it's a valid input " + inputByMerchant);
        }
    }

    @Test
    public void testHowManyQuestionEvaluationWithInvalidInput() {
        final String inputByMerchant = "what's credits";
        try {
            final TranslatedOutput translatedOutput = howManyQuestionEvaluator.evaluateTradingQuery(inputByMerchant);
            Assert.assertEquals(GalaxyConstants.NO_IDEA_STRING, translatedOutput.getTranslatedOutput());
        } catch (Exception e) {
        }
    }
}
