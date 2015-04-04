package com.galaxy.merchant.wealth;

/**
 * Any object that can be traded by Merchant
 */
public interface TradingObject {

    /**
     * Returns the trading object name.
     * 
     * @return
     */
    public String getName();

    /**
     * Returns the valuation of trading object.
     * 
     * @return
     */
    public String getValue();
}
