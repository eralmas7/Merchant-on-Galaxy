package com.galaxy.merchant.translator;

import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import com.galaxy.merchant.coverter.RomanUnitConverter;
import com.galaxy.merchant.coverter.UnitConverter;
import com.galaxy.merchant.datatype.TraderInput;
import com.galaxy.merchant.datatype.TranslatedOutput;
import com.galaxy.merchant.input.type.factory.InputStatementTypeFactory;
import com.galaxy.merchant.utils.GalaxyConstants;

public class NumberTranslatorTest extends TestCase {

    private NumberTranslator numberTranslator;

    @Before
    public void setUp() throws Exception {
        final InputStatementTypeFactory inputStatementTypeFactory = new InputStatementTypeFactory();
        final UnitConverter unitConverter = new RomanUnitConverter();
        numberTranslator = new NumberTranslator(inputStatementTypeFactory, unitConverter);
    }

    @Test
    public void testValidTranslationOfInput() {
        final List<TraderInput> traderInputs = new ArrayList<TraderInput>(4);
        traderInputs.add(new TraderInput("glob is I"));
        traderInputs.add(new TraderInput("glob glob Iron is 34 Credits"));
        traderInputs.add(new TraderInput("how much is glob glob ?"));
        traderInputs.add(new TraderInput("how many Credits is glob Iron ?"));
        final List<TranslatedOutput> translatedOutputs = numberTranslator.translate(traderInputs);
        Assert.assertNotNull(translatedOutputs);
        Assert.assertEquals(translatedOutputs.size(), 2);
        Assert.assertEquals(translatedOutputs.get(0).getTranslatedOutput(), "glob glob is 2");
        Assert.assertEquals(translatedOutputs.get(1).getTranslatedOutput(), "glob Iron is 17 Credits");
    }

    @Test
    public void testTranslationWithoutValuation() {
        final List<TraderInput> traderInput = new ArrayList<TraderInput>(1);
        traderInput.add(new TraderInput("glob is I"));
        final List<TranslatedOutput> translatedOutputs = numberTranslator.translate(traderInput);
        Assert.assertNotNull(translatedOutputs);
        Assert.assertEquals(translatedOutputs.size(), 0);
    }

    @Test
    public void testTranslationOfInvalidInput() {
        final List<TraderInput> traderInput = new ArrayList<TraderInput>(2);
        traderInput.add(new TraderInput("glob is is I"));
        traderInput.add(new TraderInput("how much is glob glob ?"));
        final List<TranslatedOutput> translatedOutputs = numberTranslator.translate(traderInput);
        Assert.assertNotNull(translatedOutputs);
        Assert.assertEquals(translatedOutputs.size(), 1);
        Assert.assertEquals(translatedOutputs.get(0).getTranslatedOutput(), GalaxyConstants.NO_IDEA_STRING);
    }

    @Test
    public void testTranslationWithSpaceBeforeInput() {
        final List<TraderInput> traderInput = new ArrayList<TraderInput>(2);
        traderInput.add(new TraderInput("glob is I"));
        traderInput.add(new TraderInput(" how much is glob glob ?"));
        final List<TranslatedOutput> translatedOutputs = numberTranslator.translate(traderInput);
        Assert.assertNotNull(translatedOutputs);
        Assert.assertEquals(translatedOutputs.size(), 1);
        Assert.assertEquals(translatedOutputs.get(0).getTranslatedOutput(), "glob glob is 2");
    }
}
