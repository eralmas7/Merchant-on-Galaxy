package com.galaxy.merchant.input.evaluator.factory;

import java.util.Map;
import com.galaxy.merchant.coverter.UnitConverter;
import com.galaxy.merchant.datatype.InputStatementType;
import com.galaxy.merchant.input.evaluator.AssignmentQuestionEvaluator;
import com.galaxy.merchant.input.evaluator.CreditQuestionEvaluator;
import com.galaxy.merchant.input.evaluator.InputTypeEvaluator;
import com.galaxy.merchant.input.evaluator.OutputTypeEvaluator;
import com.galaxy.merchant.input.parser.InputParseStrategy;
import com.galaxy.merchant.wealth.TradingObject;

/**
 * Factory to produce input evaluator which will evaluate input from merchant.
 */
public class InputTypeEvaluatorFactory implements EvaluatorFactory {

    private final UnitConverter unitConverter;
    private final Map<String, TradingObject> tradingObjectNameValueMap;

    public InputTypeEvaluatorFactory(final Map<String, TradingObject> tradingObjectNameValueMap, final UnitConverter unitConverter) {
        this.tradingObjectNameValueMap = tradingObjectNameValueMap;
        this.unitConverter = unitConverter;
    }

    @Override
    public OutputTypeEvaluator getOutputEvaluator(final InputStatementType inputStatementType) {
        throw new UnsupportedOperationException("Output evaluation in input evaluator is not supported");
    }

    @Override
    public InputTypeEvaluator getInputEvaluator(final InputStatementType inputStatementType) {
        InputTypeEvaluator inputTypeEvaluator = null;
        switch (inputStatementType) {
            case ASSIGNED:
                inputTypeEvaluator = new AssignmentQuestionEvaluator(new InputParseStrategy());
                break;
            case CREDITS:
                inputTypeEvaluator = new CreditQuestionEvaluator(unitConverter, tradingObjectNameValueMap, new InputParseStrategy());
                break;
        }
        return inputTypeEvaluator;
    }
}
