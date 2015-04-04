package com.galaxy.merchant.client;

import java.util.Collections;
import java.util.List;
import com.galaxy.merchant.datatype.TraderInput;
import com.galaxy.merchant.datatype.TranslatedOutput;
import com.galaxy.merchant.translator.Translator;

/**
 * A merchant who will need to convert the number from one unit to the other in order to trade over
 * the Galaxy has number translator for translation which is being used by Merchant.
 */
public class GalaxyMerchant implements Merchant {

    private Translator translator;

    public GalaxyMerchant(final Translator translator) {
        this.translator = translator;
    }

    /**
     * Given the input to the merchant, Merchant will use translator to: a. Understand the input b.
     * Translate the input from one form to the other.
     * 
     * @param traderInputs
     * @return - Translated output so merchant can trade on galaxy
     */
    public List<TranslatedOutput> translateInput(final List<TraderInput> traderInputs) {
        final List<TranslatedOutput> translatedOutput = translator.translate(traderInputs);
        return Collections.unmodifiableList(translatedOutput);
    }
}
