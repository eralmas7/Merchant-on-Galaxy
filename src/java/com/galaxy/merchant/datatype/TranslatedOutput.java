package com.galaxy.merchant.datatype;

/**
 * Output to Merchant is wrapped in this object
 */
public class TranslatedOutput {

    private final String translatedOutput;

    public TranslatedOutput(final String translatedOutput) {
        this.translatedOutput = translatedOutput;
    }

    public String getTranslatedOutput() {
        return translatedOutput;
    }

    @Override
    public String toString() {
        return translatedOutput;
    }
}
