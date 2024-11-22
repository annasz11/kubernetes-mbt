package test_runners;

import utils.JsonTestSuiteProcessor;
import utils.TestSuite;
import utils.Utils;

public class ASTestRunner {

    public static void main(String[] args) throws Exception {
        TestSuite testSuite = JsonTestSuiteProcessor.loadTestSuite(Utils.getResourceFilePath("test_suites/Kubernetes_Pod_Lifecycle-AS-test_suite.json")).testSuite;
        TestRunner testRunner = new TestRunner();
        testRunner.runTestSuite(testSuite);
    }
}