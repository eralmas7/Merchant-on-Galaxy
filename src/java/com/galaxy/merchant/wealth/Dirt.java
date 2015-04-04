package com.galaxy.merchant.wealth;

/**
 * Representation of dirt
 */
public class Dirt implements TradingObject {

    private String name;
    private String value;

    public Dirt(final String name, final String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return value;
    }
}
