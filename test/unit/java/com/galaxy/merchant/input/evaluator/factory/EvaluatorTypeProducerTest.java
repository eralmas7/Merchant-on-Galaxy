package com.galaxy.merchant.input.evaluator.factory;

import org.junit.Test;
import com.galaxy.merchant.datatype.InputStatementType;
import junit.framework.Assert;
import junit.framework.TestCase;

public class EvaluatorTypeProducerTest extends TestCase {

    @Test
    public void testEvaluatorTypeProducerWithInputQuery() {
        try {
            assertTrue(EvaluatorTypeProducer.getEvaluatorFactory(InputStatementType.ASSIGNED) instanceof InputTypeEvaluatorFactory);
            assertTrue(EvaluatorTypeProducer.getEvaluatorFactory(InputStatementType.CREDITS) instanceof InputTypeEvaluatorFactory);
        } catch (Exception e) {
            Assert.fail("Did not expected exception here");
        }
    }

    @Test
    public void testEvaluatorTypeProducerWithOutputQuery() {
        try {
            assertTrue(EvaluatorTypeProducer.getEvaluatorFactory(InputStatementType.QUESTION_HOW_MANY) instanceof OutputTypeEvaluatorFactory);
            assertTrue(EvaluatorTypeProducer.getEvaluatorFactory(InputStatementType.QUESTION_HOW_MUCH) instanceof OutputTypeEvaluatorFactory);
        } catch (Exception e) {
            Assert.fail("Did not expected exception here");
        }
    }
}
