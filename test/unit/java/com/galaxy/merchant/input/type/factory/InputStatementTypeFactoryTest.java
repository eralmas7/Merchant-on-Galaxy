package com.galaxy.merchant.input.type.factory;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;
import com.galaxy.merchant.datatype.InputStatementType;

public class InputStatementTypeFactoryTest extends TestCase {

    private final InputStatementTypeFactory inputStatementTypeFactory = new InputStatementTypeFactory();

    @Test
    public void testFactoryWithValidInput() {
        final String[] inputs = new String[4];
        inputs[0] = "glob is I";
        inputs[1] = "glob glob Silver is 34 Credits";
        inputs[2] = "how much is pish tegj glob glob ?";
        inputs[3] = "how many Credits is glob prok Silver ?";
        InputStatementType[] expectedInputStatementTypes = {InputStatementType.ASSIGNED, InputStatementType.CREDITS, InputStatementType.QUESTION_HOW_MUCH, InputStatementType.QUESTION_HOW_MANY};
        for (int count = 0; count < inputs.length; count++) {
            try {
                Assert.assertEquals(expectedInputStatementTypes[count], inputStatementTypeFactory.getInputStatementType(inputs[count]));
            } catch (Exception exception) {
                Assert.fail("Did not expected exception");
            }
        }
    }

    @Test
    public void testFactoryWithInValidInput() {
        String input = "what Credit is Silver to glob prok ?";
        try {
            inputStatementTypeFactory.getInputStatementType(input);
            Assert.fail("Did not expected to be here as i expected exception ");
        } catch (Exception exception) {
        }
    }
}
