package com.galaxy.merchant.datatype;

import com.galaxy.merchant.utils.GalaxyConstants;

public enum InputStatementType {
    /**
     * This represents that input line by Merchant is of Assignment type. e.g.: glob is V
     */
    ASSIGNED(true, GalaxyConstants.ASSIGNED_INPUT_PATTERN),
    /**
     * This represents that input line by Merchant is of Credits type. Ex : glob glob Silver is 34
     * Credits
     */
    CREDITS(true, GalaxyConstants.CREDIT_INPUT_PATTERN),
    /**
     * This represents that input line by Merchant is question asking how much. Ex : how much is
     * pish tegj glob glob ?
     */
    QUESTION_HOW_MUCH(false, GalaxyConstants.HOW_MUCH_INPUT_PATTERN),
    /**
     * This represents that input line by Merchant is question asking how many. Ex: how many Credits
     * is glob prok Iron ?
     */
    QUESTION_HOW_MANY(false, GalaxyConstants.HOW_MANY_INPUT_PATTERN);

    private final boolean isLineInputbyMerchant;
    private final String patternType;

    private InputStatementType(final boolean isLineInputbyMerchant, final String patterType) {
        this.isLineInputbyMerchant = isLineInputbyMerchant;
        this.patternType = patterType;
    }

    public boolean isLineInputbyMerchant() {
        return this.isLineInputbyMerchant;
    }

    public String getPatternType() {
        return patternType;
    }
}
