package com.galaxy.merchant.coverter;

import com.galaxy.merchant.exception.InvalidInputException;
import com.galaxy.merchant.number.GalaxyNumber;

/**
 * Base converter interface which will be used to convert either Roman to Arabic or vice versa
 */
public interface UnitConverter {

    /**
     * Converts the number from one (Arabic/Roman) to the other form.
     * 
     * @param number
     * @return
     * @throws InvalidInputException
     */
    public GalaxyNumber convert(String number) throws InvalidInputException;
}
