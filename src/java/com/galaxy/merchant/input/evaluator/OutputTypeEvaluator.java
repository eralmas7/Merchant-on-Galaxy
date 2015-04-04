package com.galaxy.merchant.input.evaluator;

import com.galaxy.merchant.datatype.TranslatedOutput;
import com.galaxy.merchant.exception.InvalidInputException;
import com.galaxy.merchant.input.parser.ParserStrategy;
import com.galaxy.merchant.utils.GalaxyConstants;
import com.galaxy.merchant.utils.GalaxyUtils;
import com.galaxy.merchant.wealth.TradingObject;

/**
 * Merchants output evaluator. It will understand merchant's query and will evaluate accordingly.
 */
public abstract class OutputTypeEvaluator {

    private final ParserStrategy inputParser;

    public OutputTypeEvaluator(final ParserStrategy inputParser) {
        this.inputParser = inputParser;
    }

    /**
     * Based on Merchant's input, evaluate the input.
     * 
     * @param inputByMerchant
     * @return TranslatedOutput - Output that merchant is going to recieve.
     * @throws InvalidInputException
     */
    public TranslatedOutput evaluateTradingQuery(final String inputByMerchant) {
        String tradingItems[];
        try {
            tradingItems = inputParser.getTradingDetailsFromInputString(inputByMerchant);
            final String creditOutputResult = doTradingItemsValuation(tradingItems);
            return getTranslatedOutput(creditOutputResult);
        } catch (InvalidInputException invalidInputException) {
            return getTranslatedOutput(invalidInputException.getMessage());
        }
    }

    /**
     * This method will do final aggregation of all the items that are being asked for by the
     * Merchant.
     * 
     * @param creditValueOfMetals
     * @param romanEquivalent
     * @param tradingObjectsToEvaluate
     * @return
     * @throws InvalidInputException
     */
    public abstract String getAggregatedValueOfTradingObjects(final double creditValueOfMetals, final String romanEquivalent, final String tradingObjectsToEvaluate) throws InvalidInputException;

    /**
     * This method will do individual item valuation.
     * 
     * @param tradingItems
     * @return
     * @throws InvalidInputException
     */
    public abstract String doTradingItemsValuation(final String tradingItems[]) throws InvalidInputException;

    /**
     * The result string that Merchant would see for his query.
     * 
     * @param creditValueOfMetals
     * @param creditOutputResult
     * @param romanEquivalent
     * @param tradingItems
     * @return
     * @throws InvalidInputException
     */
    protected String getCreditOutputResult(final double creditValueOfMetals, String creditOutputResult, final String romanEquivalent, final String[] tradingItems) throws InvalidInputException {
        if (creditOutputResult == null) {
            final String tradingObjectsToEvaluate = GalaxyUtils.concatenate(GalaxyConstants.SPACE, tradingItems);
            creditOutputResult = getAggregatedValueOfTradingObjects(creditValueOfMetals, romanEquivalent, tradingObjectsToEvaluate);
        }
        return creditOutputResult;
    }

    /**
     * is Trading object parameter null?
     * 
     * @param tradingObject
     * @return
     */
    protected boolean isTradingObjectPresent(final TradingObject tradingObject) {
        return tradingObject != null;
    }

    /*
     * Prepare the final Translated output for Merchant.
     * 
     * @param creditOutputResult
     * 
     * @return
     */
    private TranslatedOutput getTranslatedOutput(final String creditOutputResult) {
        final TranslatedOutput translatedOutput = new TranslatedOutput(creditOutputResult);
        return translatedOutput;
    }
}
