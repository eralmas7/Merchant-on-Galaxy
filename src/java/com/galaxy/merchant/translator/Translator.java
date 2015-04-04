package com.galaxy.merchant.translator;

import java.util.List;
import com.galaxy.merchant.datatype.TraderInput;
import com.galaxy.merchant.datatype.TranslatedOutput;

public interface Translator {

    /**
     * Translates trade input into output information which merchant can use to trade with on
     * Galaxy.
     * 
     * @param traderInputs
     * @return
     */
    public List<TranslatedOutput> translate(final List<TraderInput> traderInputs);
}
