package com.galaxy.merchant.suite;

import com.galaxy.merchant.coverter.RomanUnitConverterTest;
import com.galaxy.merchant.input.evaluator.AssignmentQuestionEvaluatorTest;
import com.galaxy.merchant.input.evaluator.CreditQuestionEvaluatorTest;
import com.galaxy.merchant.input.evaluator.HowManyQuestionEvaluatorTest;
import com.galaxy.merchant.input.evaluator.HowMuchQuestionEvaluatorTest;
import com.galaxy.merchant.input.evaluator.factory.EvaluatorTypeProducerTest;
import com.galaxy.merchant.input.evaluator.factory.InputTypeEvaluatorFactoryTest;
import com.galaxy.merchant.input.evaluator.factory.OutputTypeEvaluatorFactoryTest;
import com.galaxy.merchant.input.parser.InputParseStrategyTest;
import com.galaxy.merchant.input.parser.QueryParseStrategyTest;
import com.galaxy.merchant.input.type.factory.InputStatementTypeFactoryTest;
import com.galaxy.merchant.translator.NumberTranslatorTest;
import com.galaxy.merchant.utils.GalaxyUtilsTest;
import com.galaxy.merchant.validator.TranslationValidatorTest;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class GalaxyMerchantTestSuite {

    public static Test suite() {
        final TestSuite testSuite = new TestSuite();
        testSuite.addTestSuite(RomanUnitConverterTest.class);
        testSuite.addTestSuite(NumberTranslatorTest.class);
        testSuite.addTestSuite(GalaxyUtilsTest.class);
        testSuite.addTestSuite(TranslationValidatorTest.class);
        testSuite.addTestSuite(InputStatementTypeFactoryTest.class);
        testSuite.addTestSuite(InputParseStrategyTest.class);
        testSuite.addTestSuite(QueryParseStrategyTest.class);
        testSuite.addTestSuite(InputTypeEvaluatorFactoryTest.class);
        testSuite.addTestSuite(OutputTypeEvaluatorFactoryTest.class);
        testSuite.addTestSuite(EvaluatorTypeProducerTest.class);
        testSuite.addTestSuite(AssignmentQuestionEvaluatorTest.class);
        testSuite.addTestSuite(CreditQuestionEvaluatorTest.class);
        testSuite.addTestSuite(HowManyQuestionEvaluatorTest.class);
        testSuite.addTestSuite(HowMuchQuestionEvaluatorTest.class);
        return testSuite;
    }

    /**
     * Runs the test suite using the textual runner.
     */
    public static void main(String[] args) {
        TestRunner.run(suite());
    }
}
