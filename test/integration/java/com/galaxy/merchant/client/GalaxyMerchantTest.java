package com.galaxy.merchant.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import com.galaxy.merchant.client.GalaxyMerchant;
import com.galaxy.merchant.client.Merchant;
import com.galaxy.merchant.coverter.RomanUnitConverter;
import com.galaxy.merchant.coverter.UnitConverter;
import com.galaxy.merchant.datatype.RomanSymbol;
import com.galaxy.merchant.datatype.TraderInput;
import com.galaxy.merchant.datatype.TranslatedOutput;
import com.galaxy.merchant.input.type.factory.InputStatementTypeFactory;
import com.galaxy.merchant.translator.NumberTranslator;
import com.galaxy.merchant.translator.Translator;
import com.galaxy.merchant.utils.GalaxyConstants;

public class GalaxyMerchantTest extends TestCase {

    private Merchant merchant;

    @Before
    public void setUp() throws Exception {
        final InputStatementTypeFactory factory = new InputStatementTypeFactory();
        final UnitConverter unitConverter = new RomanUnitConverter();
        final Translator numberTranslator = new NumberTranslator(factory, unitConverter);
        merchant = new GalaxyMerchant(numberTranslator);
    }

    private List<TraderInput> getTraderInputs(final List<String> traderRawInputs) {
        List<TraderInput> traderInputList = new ArrayList<TraderInput>(traderRawInputs.size());
        for (String traderRawInput : traderRawInputs) {
            traderInputList.add(new TraderInput(traderRawInput));
        }
        return traderInputList;
    }

    @Test
    public void testGalaxyMerchantSuccessfully() {
        final List<String> list = new ArrayList<String>(12);
        list.add("glob is I");
        list.add("prok is V");
        list.add("pish is X");
        list.add("tegj is L");
        list.add("glob glob Silver is 34 Credits");
        list.add("glob prok Gold is 57800 Credits");
        list.add("pish pish Iron is 3910 Credits");
        list.add("how much is pish tegj glob glob ?");
        list.add("how many Credits is glob prok Silver ?");
        list.add("how many Credits is glob prok Gold ?");
        list.add("how many Credits is glob prok Iron ?");
        list.add("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
        final List<TranslatedOutput> output = merchant.translateInput(getTraderInputs(list));
        Assert.assertEquals(output.size(), 5);
        Assert.assertEquals(output.get(0).toString(), "pish tegj glob glob is 42");
        Assert.assertEquals(output.get(1).toString(), "glob prok Silver is 68 Credits");
        Assert.assertEquals(output.get(2).toString(), "glob prok Gold is 57800 Credits");
        Assert.assertEquals(output.get(3).toString(), "glob prok Iron is 782 Credits");
        Assert.assertEquals(output.get(4).toString(), GalaxyConstants.NO_IDEA_STRING);
    }

    @Test
    public void testAddingDirtAfterCredits() {
        final List<String> list = new ArrayList<String>(12);
        list.add("glob is I");
        list.add("prok is V");
        list.add("pish is X");
        list.add("glob glob Silver is 34 Credits");
        list.add("tegj is L");
        list.add("how much is pish tegj glob glob ?");
        list.add("glob prok Gold is 57800 Credits");
        list.add("pish pish Iron is 3910 Credits");
        list.add("how many Credits is glob prok Silver ?");
        list.add("how many Credits is glob prok Gold ?");
        list.add("how many Credits is glob prok Iron ?");
        list.add("how much is wood could a woodchuck chuck if a woodchuck could chuck wood ?");
        final List<TranslatedOutput> output = merchant.translateInput(getTraderInputs(list));
        Assert.assertEquals(output.size(), 5);
        Assert.assertEquals(output.get(0).toString(), "pish tegj glob glob is 42");
        Assert.assertEquals(output.get(1).toString(), "glob prok Silver is 68 Credits");
        Assert.assertEquals(output.get(2).toString(), "glob prok Gold is 57800 Credits");
        Assert.assertEquals(output.get(3).toString(), "glob prok Iron is 782 Credits");
        Assert.assertEquals(output.get(4).toString(), GalaxyConstants.NO_IDEA_STRING);
    }

    @Test
    public void testAddingDirtAfterValuation() {
        final List<String> list = new ArrayList<String>(3);
        list.add("how much is glob ?");
        list.add("glob is I");
        list.add("how much is glob ?");
        final List<TranslatedOutput> output = merchant.translateInput(getTraderInputs(list));
        Assert.assertEquals(output.size(), 2);
        Assert.assertEquals(output.get(0).toString(), GalaxyConstants.NO_IDEA_STRING);
        Assert.assertEquals(output.get(1).toString(), "glob is 1");
    }

    @Test
    public void testValuationByAddingDuplicateDirt() {
        final List<String> list = new ArrayList<String>(4);
        list.add("glob is I");
        list.add("glob is V");
        list.add("glob is X");
        list.add("how much is glob glob?");
        final List<TranslatedOutput> output = merchant.translateInput(getTraderInputs(list));
        Assert.assertEquals(output.size(), 1);
        Assert.assertEquals(output.get(0).toString(), "glob glob is 20");
    }

    @Test
    public void testValuationForMultipleMetals() {
        final List<String> list = new ArrayList<String>(5);
        list.add("glob is I");
        list.add("prok is V");
        list.add("glob glob Silver is 34 Credits");
        list.add("glob prok Gold is 57800 Credits");
        list.add("how many Credits is Silver Gold ?");
        final List<TranslatedOutput> output = merchant.translateInput(getTraderInputs(list));
        Assert.assertEquals(output.size(), 1);
        Assert.assertEquals(output.get(0).toString(), "Silver Gold is 245650 Credits");
    }

    @Test
    public void testValuationForMultipleDirts() {
        final List<String> list = new ArrayList<String>(3);
        list.add("glob is I");
        list.add("prok is V");
        list.add("how much is glob prok ?");
        final List<TranslatedOutput> output = merchant.translateInput(getTraderInputs(list));
        Assert.assertEquals(output.size(), 1);
        Assert.assertEquals(output.get(0).toString(), "glob prok is 4");
    }

    @Test
    public void testValuationWhenInvalidSymbol() {
        final List<String> list = new ArrayList<String>(3);
        list.add("glob is I");
        list.add("blob is P");
        list.add("how much is glob blob ?");
        final List<TranslatedOutput> output = merchant.translateInput(getTraderInputs(list));
        Assert.assertEquals(output.size(), 1);
        Assert.assertEquals(output.get(0).toString(), GalaxyConstants.NO_IDEA_STRING);
    }

    @Test
    public void testValuationWhenInvalidAssignment() {
        final List<String> list = new ArrayList<String>(3);
        list.add("glob is I");
        list.add("blob is going to be V");
        list.add("how much is glob blob ?");
        final List<TranslatedOutput> output = merchant.translateInput(getTraderInputs(list));
        Assert.assertEquals(output.size(), 1);
        Assert.assertEquals(output.get(0).toString(), GalaxyConstants.NO_IDEA_STRING);
    }

    @Test
    public void testValuationWhenEmptyInput() {
        final List<String> list = new ArrayList<String>(3);
        list.add("glob is I");
        list.add("");
        list.add("how much is glob glob ?");
        final List<TranslatedOutput> output = merchant.translateInput(getTraderInputs(list));
        Assert.assertEquals(output.size(), 1);
        Assert.assertEquals(output.get(0).toString(), "glob glob is 2");
    }

    @Test
    public void testValuationWhenInvalidInput() {
        final List<String> list = new ArrayList<String>(3);
        list.add("glob is I");
        list.add("glob and plob are Z");
        list.add("how much is glob glob ?");
        final List<TranslatedOutput> output = merchant.translateInput(getTraderInputs(list));
        Assert.assertEquals(output.size(), 1);
        Assert.assertEquals(output.get(0).toString(), "glob glob is 2");
    }

    @Test
    public void testValuationWhenInvalidEvaluateQuestion() {
        final List<String> list = new ArrayList<String>(3);
        list.add("glob is I");
        list.add("plob is V");
        list.add("how much are glob glob ?");
        final List<TranslatedOutput> output = merchant.translateInput(getTraderInputs(list));
        Assert.assertEquals(output.size(), 1);
        Assert.assertEquals(output.get(0).toString(), GalaxyConstants.NO_IDEA_STRING);
    }

    private void testValuationWhenSymbolTranslates(final RomanSymbol[] allowedRomanSymbols, final RomanSymbol symbolInTest, final String evaluationString) {
        List<String> merchantInputList;
        final String inputMerchantString = "glob is " + symbolInTest;
        for (RomanSymbol romanSymbol : RomanSymbol.values()) {
            merchantInputList = new ArrayList<String>(3);
            merchantInputList.add(inputMerchantString);
            merchantInputList.add("blob is " + romanSymbol.name());
            merchantInputList.add(evaluationString);
            final List<TranslatedOutput> output = merchant.translateInput(getTraderInputs(merchantInputList));
            Assert.assertEquals(output.size(), 1);
            if (Arrays.binarySearch(allowedRomanSymbols, romanSymbol) >= 0) {
                Assert.assertNotSame(output.get(0).toString(), GalaxyConstants.ROMAN_RULE_VIOLATED);
            } else {
                Assert.assertEquals(output.get(0).toString(), GalaxyConstants.ROMAN_RULE_VIOLATED);
            }
        }
    }

    @Test
    public void testValuationWhenISymbolSubtractsWithOthers() {
        final RomanSymbol[] allowedRomanSymbols = {RomanSymbol.I, RomanSymbol.V, RomanSymbol.X};
        testValuationWhenSymbolTranslates(allowedRomanSymbols, RomanSymbol.I, "how much is glob blob ?");
    }

    @Test
    public void testValuationWhenXSymbolSubtractsWithOthers() {
        final RomanSymbol[] allowedRomanSymbols = {RomanSymbol.I, RomanSymbol.V, RomanSymbol.X, RomanSymbol.L, RomanSymbol.C};
        testValuationWhenSymbolTranslates(allowedRomanSymbols, RomanSymbol.X, "how much is glob blob ?");
    }

    @Test
    public void testValuationWhenCSymbolSubtractsWithOthers() {
        final RomanSymbol[] allowedRomanSymbols = RomanSymbol.values();
        testValuationWhenSymbolTranslates(allowedRomanSymbols, RomanSymbol.C, "how much is glob blob ?");
    }

    @Test
    public void testValuationWhenVSymbolSubtractsWithOthers() {
        final RomanSymbol[] allowedRomanSymbols = {RomanSymbol.I};
        testValuationWhenSymbolTranslates(allowedRomanSymbols, RomanSymbol.V, "how much is glob blob ?");
    }

    @Test
    public void testValuationWhenLSymbolSubtractsWithOthers() {
        final RomanSymbol[] allowedRomanSymbols = {RomanSymbol.I, RomanSymbol.V, RomanSymbol.X};
        testValuationWhenSymbolTranslates(allowedRomanSymbols, RomanSymbol.L, "how much is glob blob ?");
    }

    @Test
    public void testValuationWhenDSymbolSubtractsWithOthers() {
        final RomanSymbol[] allowedRomanSymbols = {RomanSymbol.I, RomanSymbol.V, RomanSymbol.X, RomanSymbol.L, RomanSymbol.C};
        testValuationWhenSymbolTranslates(allowedRomanSymbols, RomanSymbol.D, "how much is glob blob ?");
    }

    @Test
    public void testValuationWhenSymbolIsRepeatedFourTimes() {
        final RomanSymbol[] allowedRomanSymbols = {};
        final String evaluationString = "how much is glob glob glob glob ?";
        for (RomanSymbol romanSymbol : RomanSymbol.values()) {
            testValuationWhenSymbolTranslates(allowedRomanSymbols, romanSymbol, evaluationString);
        }
    }

    @Test
    public void testValuationWhenSymbolIsRepeatedThrice() {
        final RomanSymbol[] allowedRomanSymbols = {RomanSymbol.I, RomanSymbol.X, RomanSymbol.C, RomanSymbol.M};
        final String evaluationString = "how much is blob blob blob ?";
        testValuationWhenSymbolTranslates(allowedRomanSymbols, allowedRomanSymbols[0], evaluationString);
    }
}
