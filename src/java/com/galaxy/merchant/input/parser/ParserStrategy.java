package com.galaxy.merchant.input.parser;

import com.galaxy.merchant.exception.InvalidInputException;

/**
 * Strategy to parse the input from Merchant.
 */
public interface ParserStrategy {

    /**
     * Parse the input and return the trading items from input.
     * 
     * @param inputByMerchant
     * @return trading items from merchant's input
     * @throws InvalidInputException
     */
    public String[] getTradingDetailsFromInputString(String inputByMerchant) throws InvalidInputException;
}
