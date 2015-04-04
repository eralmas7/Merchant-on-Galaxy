package com.galaxy.merchant.translator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.galaxy.merchant.coverter.UnitConverter;
import com.galaxy.merchant.datatype.InputStatementType;
import com.galaxy.merchant.datatype.TraderInput;
import com.galaxy.merchant.datatype.TranslatedOutput;
import com.galaxy.merchant.exception.InvalidInputException;
import com.galaxy.merchant.input.evaluator.InputTypeEvaluator;
import com.galaxy.merchant.input.evaluator.OutputTypeEvaluator;
import com.galaxy.merchant.input.evaluator.factory.EvaluatorFactory;
import com.galaxy.merchant.input.evaluator.factory.EvaluatorTypeProducer;
import com.galaxy.merchant.input.type.factory.InputStatementTypeFactory;
import com.galaxy.merchant.utils.Logger;
import com.galaxy.merchant.wealth.TradingObject;

/**
 * Used by Galaxy merchant to do calculation by translating number from one unit to the other.
 */
public class NumberTranslator implements Translator {

    private InputStatementTypeFactory inputStatementTypeFactory;
    private final Map<String, TradingObject> tradingObjectNameValueMap = new HashMap<String, TradingObject>();

    public NumberTranslator(final InputStatementTypeFactory inputStatementTypeFactory, final UnitConverter unitConverter) {
        this.inputStatementTypeFactory = inputStatementTypeFactory;
        EvaluatorTypeProducer.setTradingObjectNameValueMap(tradingObjectNameValueMap);
        EvaluatorTypeProducer.setUnitConverter(unitConverter);
    }

    /**
     * Translates trade input into output information which merchant can use to trade with on
     * Galaxy.
     * 
     * @param traderInputs
     * @return
     */
    @Override
    public List<TranslatedOutput> translate(final List<TraderInput> traderInputs) {
        final List<TranslatedOutput> translatedOutputs = new LinkedList<TranslatedOutput>();
        String inputByMerchant = "";
        for (TraderInput traderInput : traderInputs) {
            try {
                inputByMerchant = traderInput.getInputByMerchant();
                translateInputStatementByType(inputByMerchant.trim(), translatedOutputs);
            } catch (InvalidInputException inputException) {
                Logger.info(inputByMerchant + " Statement was not recognized as one of with how much/how many/is/is Credits string, so ignoring it ", inputException.getMessage());
                continue;
            }
        }
        return translatedOutputs;
    }

    private void translateInputStatementByType(final String inputByMerchant, final List<TranslatedOutput> translatedOutputs) throws InvalidInputException {
        final InputStatementType inputStatementType = inputStatementTypeFactory.getInputStatementType(inputByMerchant);
        final EvaluatorFactory evaluatorFactory = EvaluatorTypeProducer.getEvaluatorFactory(inputStatementType);
        if (inputStatementType.isLineInputbyMerchant()) {
            evaluateInputStatementByType(evaluatorFactory, inputStatementType, inputByMerchant);
        } else {
            evaluateOutputStatementByType(evaluatorFactory, inputStatementType, inputByMerchant, translatedOutputs);
        }
    }

    private void evaluateOutputStatementByType(final EvaluatorFactory evaluatorFactory, final InputStatementType inputStatementType, final String inputByMerchant, final List<TranslatedOutput> translatedOutputs) {
        final OutputTypeEvaluator outputTypeEvaluator = evaluatorFactory.getOutputEvaluator(inputStatementType);
        final TranslatedOutput translatedOutput = outputTypeEvaluator.evaluateTradingQuery(inputByMerchant);
        translatedOutputs.add(translatedOutput);
    }

    private void evaluateInputStatementByType(final EvaluatorFactory evaluatorFactory, final InputStatementType inputStatementType, final String inputByMerchant) throws InvalidInputException {
        final InputTypeEvaluator inputTypeEvaluator = evaluatorFactory.getInputEvaluator(inputStatementType);
        final TradingObject tradingObject = inputTypeEvaluator.evaluateTradingObject(inputByMerchant);
        tradingObjectNameValueMap.put(tradingObject.getName(), tradingObject);
    }
}
