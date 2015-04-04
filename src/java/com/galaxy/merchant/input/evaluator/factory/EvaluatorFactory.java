package com.galaxy.merchant.input.evaluator.factory;

import com.galaxy.merchant.datatype.InputStatementType;
import com.galaxy.merchant.input.evaluator.InputTypeEvaluator;
import com.galaxy.merchant.input.evaluator.OutputTypeEvaluator;

/**
 * Evaluator Factory which will assess user input and based on input will return input/output
 * evaluator
 */
public interface EvaluatorFactory {

    /**
     * Return the output query evaluator based on input statement type by Merchant.
     * 
     * @param inputStatementType
     * @return
     */
    public OutputTypeEvaluator getOutputEvaluator(final InputStatementType inputStatementType);

    /**
     * Based on input provided by Merchant, return the inputTypeEvaluator.
     * 
     * @param inputStatementType
     * @return
     */
    public InputTypeEvaluator getInputEvaluator(final InputStatementType inputStatementType);
}
