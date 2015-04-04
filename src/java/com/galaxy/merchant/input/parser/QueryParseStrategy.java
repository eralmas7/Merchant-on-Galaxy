package com.galaxy.merchant.input.parser;

import com.galaxy.merchant.exception.InvalidInputException;
import com.galaxy.merchant.utils.GalaxyConstants;

/**
 * A Parser to parse the trader's query.
 */
public class QueryParseStrategy implements ParserStrategy {

    @Override
    public String[] getTradingDetailsFromInputString(String inputByMerchant) throws InvalidInputException {
        final String[] patitionedInputByMerchant = inputByMerchant.split(GalaxyConstants.SPACE_IS_SPACE_REGEX);
        if (patitionedInputByMerchant.length != 2) {
            throw new InvalidInputException(GalaxyConstants.NO_IDEA_STRING);
        }
        final String tradingObjectsToEvaluate = patitionedInputByMerchant[1].replace(GalaxyConstants.QUESTION_MARK, GalaxyConstants.EMPTY_STRING).trim();
        final String tradingItems[] = tradingObjectsToEvaluate.split(GalaxyConstants.SPACE_REGEX);
        return tradingItems;
    }
}
