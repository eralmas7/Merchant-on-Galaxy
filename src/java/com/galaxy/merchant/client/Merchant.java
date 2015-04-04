package com.galaxy.merchant.client;

import java.util.List;
import com.galaxy.merchant.datatype.TraderInput;
import com.galaxy.merchant.datatype.TranslatedOutput;

/**
 * Entry point for the merchants to get equivalent of trade inputs provided by them.
 */
public interface Merchant {

    /**
     * Given the list of inputs by Merchant, return the valuation post conversion.
     * 
     * @param traderInputs
     * @return
     */
    public List<TranslatedOutput> translateInput(final List<TraderInput> traderInputs);
}
