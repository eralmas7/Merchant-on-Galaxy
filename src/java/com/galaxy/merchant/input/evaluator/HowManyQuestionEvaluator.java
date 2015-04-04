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
 * Evaluates merchants input query like how many Credits is glob prok Gold ?
 */
public class HowManyQuestionEvaluator extends OutputTypeEvaluator {

    private final UnitConverter unitConverter;
    private final Map<String, TradingObject> tradingObjectNameValueMap;

    public HowManyQuestionEvaluator(final UnitConverter unitConverter, final Map<String, TradingObject> tradingObjectNameValueMap, final ParserStrategy inputParser) {
        super(inputParser);
        this.unitConverter = unitConverter;
        this.tradingObjectNameValueMap = tradingObjectNameValueMap;
    }

    @Override
    public String doTradingItemsValuation(final String tradingItems[]) throws InvalidInputException {
        final StringBuilder romanEquivalent = new StringBuilder();
        String creditOutputResult = null;
        TradingObject tradingObject;
        double creditValueOfMetals = 1d;
        for (String tradingItem : tradingItems) {
            tradingObject = tradingObjectNameValueMap.get(tradingItem);
            if (isTradingObjectPresent(tradingObject) && isDirtTypeOfObject(tradingObject.getValue())) {
                romanEquivalent.append(tradingObject.getValue());
                continue;
            }
            if (isTradingObjectPresent(tradingObject)) {
                creditValueOfMetals *= Double.parseDouble(tradingObject.getValue());
                continue;
            }
            creditOutputResult = GalaxyConstants.NO_IDEA_STRING;
            break;
        }
        creditOutputResult = getCreditOutputResult(creditValueOfMetals, creditOutputResult, romanEquivalent.toString(), tradingItems);
        return creditOutputResult;
    }

    private boolean isDirtTypeOfObject(final String valueOfTradingObject) {
        return !valueOfTradingObject.contains(GalaxyConstants.DOT_STRING);
    }

    @Override
    public String getAggregatedValueOfTradingObjects(final double creditValueOfMetals, final String romanEquivalent, final String tradingObjectsToEvaluate) throws InvalidInputException {
        int creditValuation = (int) creditValueOfMetals;
        if (romanEquivalent.length() > 0) {
            final GalaxyNumber arabicNumber = unitConverter.convert(romanEquivalent);
            creditValuation = (int) (arabicNumber.getValue() * creditValueOfMetals);
        }
        final String creditOutputResult = GalaxyUtils.concatenate(tradingObjectsToEvaluate, GalaxyConstants.IS_STRING_WITH_SPACE, Integer.toString(creditValuation, 10), GalaxyConstants.CREDITS_STRING);
        return creditOutputResult;
    }
}
