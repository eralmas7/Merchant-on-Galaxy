package com.galaxy.merchant.input.parser;

import com.galaxy.merchant.exception.InvalidInputException;
import com.galaxy.merchant.utils.GalaxyConstants;

/**
 * Parser which will parse assignment type of input from Merchant
 */
public class InputParseStrategy implements ParserStrategy {

    @Override
    public String[] getTradingDetailsFromInputString(String inputByMerchant) throws InvalidInputException {
        final String formattedInputOfMerchant = inputByMerchant.replaceAll(GalaxyConstants.CREDITS_REGEX, GalaxyConstants.EMPTY_STRING).trim();
        final String[] tradingDetails = formattedInputOfMerchant.split(GalaxyConstants.SPACE_REGEX);
        if (tradingDetails.length < 2) {
            throw new InvalidInputException(GalaxyConstants.NO_IDEA_STRING);
        }
        return tradingDetails;
    }
}
