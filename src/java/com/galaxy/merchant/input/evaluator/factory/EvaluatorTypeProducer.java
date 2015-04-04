package com.galaxy.merchant.input.evaluator.factory;

import java.util.Map;
import com.galaxy.merchant.coverter.UnitConverter;
import com.galaxy.merchant.datatype.InputStatementType;
import com.galaxy.merchant.exception.InvalidInputException;
import com.galaxy.merchant.wealth.TradingObject;

/**
 * Evaluator producer which will produce abstract Evaluator factory.
 */
public class EvaluatorTypeProducer {

    private static UnitConverter unitConverter;
    private static Map<String, TradingObject> tradingObjectNameValueMap;

    /**
     * Based on input statement by merchant, this abstract factory provides the appropriate factory
     * to client.
     * 
     * @param inputStatementType
     * @return
     * @throws InvalidInputException
     */
    public static EvaluatorFactory getEvaluatorFactory(final InputStatementType inputStatementType) {
        if (inputStatementType.isLineInputbyMerchant()) {
            return new InputTypeEvaluatorFactory(tradingObjectNameValueMap, unitConverter);
        } else {
            return new OutputTypeEvaluatorFactory(tradingObjectNameValueMap, unitConverter);
        }
    }

    /**
     * sets the unit converter which will convert trader's Roman number to Arabic
     * 
     * @param unitConverter
     */
    public static void setUnitConverter(UnitConverter unitConverter) {
        EvaluatorTypeProducer.unitConverter = unitConverter;
    }

    /**
     * Sets the trading object map.
     * 
     * @param tradingObjectNameValueMap
     */
    public static void setTradingObjectNameValueMap(Map<String, TradingObject> tradingObjectNameValueMap) {
        EvaluatorTypeProducer.tradingObjectNameValueMap = tradingObjectNameValueMap;
    }
}
