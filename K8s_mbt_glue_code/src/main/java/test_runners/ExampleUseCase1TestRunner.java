package test_runners;

import utils.JsonTestSuiteProcessor;
import utils.TestSuite;
import utils.Utils;

public class ExampleUseCase1TestRunner {

    public static void main(String[] args) throws Exception {
        TestSuite testSuite = JsonTestSuiteProcessor.loadTestSuite(Utils.getResourceFilePath("test_suites/Example_Use_Case1-test_suite.json")).testSuite;
        TestRunner testRunner = new TestRunner();
        testRunner.runTestSuite(testSuite);
    }
}