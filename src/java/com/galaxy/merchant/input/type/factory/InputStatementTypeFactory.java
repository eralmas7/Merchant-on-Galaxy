package com.galaxy.merchant.input.type.factory;

import com.galaxy.merchant.datatype.InputStatementType;
import com.galaxy.merchant.exception.InvalidInputException;
import com.galaxy.merchant.utils.GalaxyConstants;

public class InputStatementTypeFactory {

    /**
     * Based on input from merchant, identify statement type and return its equivalent
     * 
     * @param inputByMerchant
     * @return
     * @throws InvalidInputException
     */
    public InputStatementType getInputStatementType(final String inputByMerchant) throws InvalidInputException {
        for (InputStatementType inputStatementType : InputStatementType.values()) {
            if (inputByMerchant.matches(inputStatementType.getPatternType())) {
                return inputStatementType;
            }
        }
        throw new InvalidInputException(GalaxyConstants.NO_IDEA_STRING);
    }
}
