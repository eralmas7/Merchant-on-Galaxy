package com.galaxy.merchant.input.evaluator;

import java.util.Map;
import com.galaxy.merchant.coverter.UnitConverter;
import com.galaxy.merchant.exception.InvalidInputException;
import com.galaxy.merchant.input.parser.ParserStrategy;
import com.galaxy.merchant.number.GalaxyNumber;
import com.galaxy.merchant.utils.GalaxyConstants;
import com.galaxy.merchant.utils.GalaxyUtils;
import com.galaxy.merchant.wealth.TradingObject;

/**
 * Evaluates merchants input query like how much is pish tegj glob glob ?
 */
public class HowMuchQuestionEvaluator extends OutputTypeEvaluator {

    private UnitConverter unitConverter;
    private Map<String, TradingObject> tradingObjectNameValueMap;

    public HowMuchQuestionEvaluator(final UnitConverter unitConverter, final Map<String, TradingObject> tradingObjectNameValueMap, final ParserStrategy inputParser) {
        super(inputParser);
        this.unitConverter = unitConverter;
        this.tradingObjectNameValueMap = tradingObjectNameValueMap;
    }

    @Override
    public String doTradingItemsValuation(final String[] tradingItems) throws InvalidInputException {
        final StringBuilder romanEquivalent = new StringBuilder();
        String creditOutputResult = null;
        TradingObject tradingObject;
        for (String tradingItem : tradingItems) {
            tradingObject = tradingObjectNameValueMap.get(tradingItem);
            if (!isTradingObjectPresent(tradingObject)) {
                creditOutputResult = GalaxyConstants.NO_IDEA_STRING;
                break;
            }
            romanEquivalent.append(tradingObject.getValue());
        }
        creditOutputResult = getCreditOutputResult(0d, creditOutputResult, romanEquivalent.toString(), tradingItems);
        return creditOutputResult;
    }

    @Override
    public String getAggregatedValueOfTradingObjects(final double creditValueOfMetals, final String romanEquivalent, final String tradingObjectsToEvaluate) throws InvalidInputException {
        String creditOutputResult = null;
        final GalaxyNumber arabicNumber = unitConverter.convert(romanEquivalent);
        int creditValuation = (int) arabicNumber.getValue();
        creditOutputResult = GalaxyUtils.concatenate(tradingObjectsToEvaluate, GalaxyConstants.IS_STRING_WITH_SPACE, Integer.toString(creditValuation, 10));
        return creditOutputResult;
    }
}
