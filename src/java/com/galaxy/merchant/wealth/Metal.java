package com.galaxy.merchant.wealth;

/**
 * Representation of Metal.
 */
public class Metal implements TradingObject {

    private String name;
    private double value;

    public Metal(final String name, final double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return Double.toString(value);
    }
}
