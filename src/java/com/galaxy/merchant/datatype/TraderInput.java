package com.galaxy.merchant.datatype;

/**
 * Input by Merchant is wrapped in this object.
 */
public class TraderInput {

    private final String inputByMerchant;

    public TraderInput(final String inputByMerchant) {
        this.inputByMerchant = inputByMerchant;
    }

    public String getInputByMerchant() {
        return inputByMerchant;
    }

    @Override
    public String toString() {
        return inputByMerchant;
    }
}
