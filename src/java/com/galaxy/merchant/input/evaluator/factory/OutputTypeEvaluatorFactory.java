package com.galaxy.merchant.input.evaluator.factory;

import java.util.Map;
import com.galaxy.merchant.coverter.UnitConverter;
import com.galaxy.merchant.datatype.InputStatementType;
import com.galaxy.merchant.input.evaluator.HowManyQuestionEvaluator;
import com.galaxy.merchant.input.evaluator.HowMuchQuestionEvaluator;
import com.galaxy.merchant.input.evaluator.InputTypeEvaluator;
import com.galaxy.merchant.input.evaluator.OutputTypeEvaluator;
import com.galaxy.merchant.input.parser.QueryParseStrategy;
import com.galaxy.merchant.wealth.TradingObject;

/**
 * Factory to produce output evaluator which will evaluate input queries from merchant.
 */
public class OutputTypeEvaluatorFactory implements EvaluatorFactory {

    private final UnitConverter unitConverter;
    private final Map<String, TradingObject> tradingObjectNameValueMap;

    public OutputTypeEvaluatorFactory(final Map<String, TradingObject> tradingObjectNameValueMap, final UnitConverter unitConverter) {
        this.tradingObjectNameValueMap = tradingObjectNameValueMap;
        this.unitConverter = unitConverter;
    }

    @Override
    public OutputTypeEvaluator getOutputEvaluator(final InputStatementType inputStatementType) {
        OutputTypeEvaluator outputTypeEvaluator = null;
        switch (inputStatementType) {
            case QUESTION_HOW_MANY:
                outputTypeEvaluator = new HowManyQuestionEvaluator(unitConverter, tradingObjectNameValueMap, new QueryParseStrategy());
                break;
            case QUESTION_HOW_MUCH:
                outputTypeEvaluator = new HowMuchQuestionEvaluator(unitConverter, tradingObjectNameValueMap, new QueryParseStrategy());
                break;
        }
        return outputTypeEvaluator;
    }

    @Override
    public InputTypeEvaluator getInputEvaluator(final InputStatementType inputStatementType) {
        throw new UnsupportedOperationException("Input evaluation in output evaluator is not supported");
    }
}
