package com.galaxy.merchant.input.evaluator;

import com.galaxy.merchant.exception.InvalidInputException;
import com.galaxy.merchant.wealth.TradingObject;

/**
 * Merchants input evaluator. It will understand merchant's input and will evaluate accordingly.
 */
public interface InputTypeEvaluator {

    /**
     * Based on Merchant's input, evaluate the input.
     * 
     * @param inputByMerchant
     * @return
     * @throws InvalidInputException
     */
    public TradingObject evaluateTradingObject(String inputByMerchant) throws InvalidInputException;
}
