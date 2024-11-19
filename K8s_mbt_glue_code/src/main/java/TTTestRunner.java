import utils.Utils;

public class TTTestRunner {

    public static void main(String[] args) throws Exception {
        TestSuite testSuite = JsonTestSuiteProcessor.loadTestSuite(Utils.getResourceFilePath("test_suites/Kubernetes_Pod_Lifecycle-TT-test_suite.json")).testSuite;
        TestRunner testRunner = new TestRunner();
        testRunner.runTestSuite(testSuite);
    }
}