package com.galaxy.merchant.input.evaluator;

import com.galaxy.merchant.exception.InvalidInputException;
import com.galaxy.merchant.input.parser.ParserStrategy;
import com.galaxy.merchant.wealth.Dirt;
import com.galaxy.merchant.wealth.TradingObject;

/**
 * Evaluates merchants input query like A is V.
 */
public class AssignmentQuestionEvaluator implements InputTypeEvaluator {

    private final ParserStrategy inputParser;

    public AssignmentQuestionEvaluator(final ParserStrategy inputParser) {
        this.inputParser = inputParser;
    }

    @Override
    public TradingObject evaluateTradingObject(final String inputByMerchant) throws InvalidInputException {
        final String[] tradingDetails = inputParser.getTradingDetailsFromInputString(inputByMerchant);
        final TradingObject tradingObject = new Dirt(tradingDetails[0], tradingDetails[1]);
        return tradingObject;
    }
}
