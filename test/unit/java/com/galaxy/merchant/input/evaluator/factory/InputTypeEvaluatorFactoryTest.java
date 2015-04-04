package com.galaxy.merchant.input.evaluator.factory;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import com.galaxy.merchant.datatype.InputStatementType;
import com.galaxy.merchant.input.evaluator.AssignmentQuestionEvaluator;
import com.galaxy.merchant.input.evaluator.CreditQuestionEvaluator;

public class InputTypeEvaluatorFactoryTest extends TestCase {

    private InputTypeEvaluatorFactory inputTypeEvaluatorFactory;

    @Before
    public void setUp() {
        inputTypeEvaluatorFactory = new InputTypeEvaluatorFactory(null, null);
    }

    @Test
    public void testInputFactoryWithValidInput() {
        try {
            Assert.assertTrue(inputTypeEvaluatorFactory.getInputEvaluator(InputStatementType.ASSIGNED) instanceof AssignmentQuestionEvaluator);
            Assert.assertTrue(inputTypeEvaluatorFactory.getInputEvaluator(InputStatementType.CREDITS) instanceof CreditQuestionEvaluator);
        } catch (Exception e) {
            Assert.fail("Did not expected any exception");
        }
    }

    @Test
    public void testInputFactoryWithInvalidInput() {
        try {
            Assert.assertNull(inputTypeEvaluatorFactory.getInputEvaluator(InputStatementType.QUESTION_HOW_MANY));
        } catch (Exception e) {
            Assert.fail("Did not expected any exception");
        }
    }

    @Test
    public void testInputFactoryWithOutputEvaluatorRequest() {
        try {
            inputTypeEvaluatorFactory.getOutputEvaluator(InputStatementType.QUESTION_HOW_MANY);
            Assert.fail("Expected an exception here");
        } catch (UnsupportedOperationException e) {
        } catch (Exception e) {
            Assert.fail("Did not expected any exception");
        }
    }
}
