package com.galaxy.merchant.input.evaluator.factory;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import com.galaxy.merchant.datatype.InputStatementType;
import com.galaxy.merchant.input.evaluator.HowManyQuestionEvaluator;
import com.galaxy.merchant.input.evaluator.HowMuchQuestionEvaluator;

public class OutputTypeEvaluatorFactoryTest extends TestCase {

    private OutputTypeEvaluatorFactory outputTypeEvaluatorFactory;

    @Before
    public void setUp() {
        outputTypeEvaluatorFactory = new OutputTypeEvaluatorFactory(null, null);
    }

    @Test
    public void testOutputFactoryWithValidInput() {
        try {
            Assert.assertTrue(outputTypeEvaluatorFactory.getOutputEvaluator(InputStatementType.QUESTION_HOW_MANY) instanceof HowManyQuestionEvaluator);
            Assert.assertTrue(outputTypeEvaluatorFactory.getOutputEvaluator(InputStatementType.QUESTION_HOW_MUCH) instanceof HowMuchQuestionEvaluator);
        } catch (Exception e) {
            Assert.fail("Did not expected any exception");
        }
    }

    @Test
    public void testOutputFactoryWithInvalidInput() {
        try {
            Assert.assertNull(outputTypeEvaluatorFactory.getOutputEvaluator(InputStatementType.ASSIGNED));
        } catch (Exception e) {
            Assert.fail("Did not expected any exception");
        }
    }

    @Test
    public void testOutputFactoryWithInputEvaluatorRequest() {
        try {
            outputTypeEvaluatorFactory.getInputEvaluator(InputStatementType.ASSIGNED);
            Assert.fail("Did not expected to be here");
        } catch (UnsupportedOperationException e) {
        } catch (Exception e) {
            Assert.fail("Did not expected any exception");
        }
    }
}
