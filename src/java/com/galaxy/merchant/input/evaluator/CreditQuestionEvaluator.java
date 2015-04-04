package com.galaxy.merchant.input.evaluator;

import java.util.Map;
import com.galaxy.merchant.coverter.UnitConverter;
import com.galaxy.merchant.exception.InvalidInputException;
import com.galaxy.merchant.input.parser.ParserStrategy;
import com.galaxy.merchant.number.GalaxyNumber;
import com.galaxy.merchant.wealth.Metal;
import com.galaxy.merchant.wealth.TradingObject;

/**
 * Evaluates merchants input query like glob glob Silver is 34 Credits ?
 */
public class CreditQuestionEvaluator implements InputTypeEvaluator {

    private final UnitConverter unitConverter;
    private final Map<String, TradingObject> tradingObjectNameValueMap;
    private final ParserStrategy inputParser;

    public CreditQuestionEvaluator(final UnitConverter unitConverter, final Map<String, TradingObject> tradingObjectNameValueMap, final ParserStrategy inputParser) {
        this.unitConverter = unitConverter;
        this.tradingObjectNameValueMap = tradingObjectNameValueMap;
        this.inputParser = inputParser;
    }

    @Override
    public TradingObject evaluateTradingObject(final String inputByMerchant) throws InvalidInputException {
        final String[] tradingDetails = inputParser.getTradingDetailsFromInputString(inputByMerchant);
        final String traderObjectName = tradingDetails[tradingDetails.length - 2];
        final double traderObjectValue = Double.parseDouble(tradingDetails[tradingDetails.length - 1]);
        final GalaxyNumber arabicNumber = unitConverter.convert(getRomanEquivalentOfDirts(tradingDetails));
        final double arabicEquivalentofMetal = (double) (traderObjectValue / arabicNumber.getValue());
        final TradingObject tradingObject = new Metal(traderObjectName, arabicEquivalentofMetal);
        return tradingObject;
    }

    private String getRomanEquivalentOfDirts(final String[] dirts) {
        StringBuilder romanEquivalentOfDirts = new StringBuilder();
        TradingObject tradingObject;
        for (int tradeObjectIndex = 0; tradeObjectIndex < dirts.length - 2; tradeObjectIndex++) {
            tradingObject = tradingObjectNameValueMap.get(dirts[tradeObjectIndex]);
            romanEquivalentOfDirts.append(tradingObject.getValue());
        }
        return romanEquivalentOfDirts.toString();
    }
}
