import utils.Utils;

public class RandomWalkTestRunner {

    public static void main(String[] args) throws Exception {
        TestSuite testSuite = JsonTestSuiteProcessor.loadTestSuite(Utils.getResourceFilePath("test_suites/Kubernetes_Pod_Lifecycle-Random-transition-100-test_suite.json")).testSuite;
        TestRunner testRunner = new TestRunner();
        testRunner.runTestSuite(testSuite);
    }
}