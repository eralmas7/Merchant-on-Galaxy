package com.galaxy.merchant.datatype;

/**
 * Roman Symbol used by Merchant
 */
public enum RomanSymbol {
    I(1), V(5), X(10), L(50), C(100), D(500), M(1000);

    private final int value;

    private RomanSymbol(final int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public double getArabicEquivalent(final int lastArabicNumber, final double totalArabicResult) {
        if (lastArabicNumber > this.value) {
            return totalArabicResult - this.value;
        } else {
            return totalArabicResult + this.value;
        }
    }
}
