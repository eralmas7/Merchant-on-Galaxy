package com.galaxy.merchant.number;

/**
 * Datatype to hold arabic number.
 */
public final class ArabicNumber implements GalaxyNumber {

    private final double value;

    public ArabicNumber(final double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }
}
