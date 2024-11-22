package test_runners;

import input_executors.*;
import output_mapper.*;
import utils.KubectlUtils;
import utils.TestSuite;
import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestRunner {
    private static final Map<String, InputExecutor> inputFunctionMap = new HashMap<>();
    private static final Map<String, OutputMapper> outputMap = new HashMap<>();

    static {
        // Map input commands to their respective InputExecutor implementations
        inputFunctionMap.put("create_pod", new PodCreateInputExecutor());
        inputFunctionMap.put("image_pull_success", new ImagePullSuccessInputExecutor());
        inputFunctionMap.put("delete_pod", new DeletePodInputExecutor());
        inputFunctionMap.put("clean_up", new CleanUpInputExecutor());
        inputFunctionMap.put("image_pull_error", new ImagePullErrorInputExecutor());
        inputFunctionMap.put("containers_ready", new ContainersReadyInputExecutor());
        inputFunctionMap.put("rediness_probe_success", new ReadinessProbeSuccessInputExecutor());
        inputFunctionMap.put("rediness_probe_failure", new ReadinessProbeFailureInputExecutor());
        inputFunctionMap.put("one_container_failed", new OneContainerFailedInputExecutor());
        inputFunctionMap.put("container_terminated", new ContainerTerminatedInputExecutor());
        inputFunctionMap.put("timeout", new TimeoutInputExecutor());
        inputFunctionMap.put("containers_exited_succ", new ContainersExitedSuccessInputExecutor());
        inputFunctionMap.put("check_status", new CheckStatusInputExecutor());

        // Map output states to their respective OutputMapper implementations
        outputMap.put("ContainerCreating", new ContainerCreatingOutputMapper());
        outputMap.put("CrashLoopBackOff", new CrashLoopBackOffOutputMapper());
        outputMap.put("Failed", new FailedOutputMapper());
        outputMap.put("ImagePullBackOff", new ImagePullBackOffOutputMapper());
        outputMap.put("Pending", new PendingOutputMapper());
        outputMap.put("PodNotExists", new PodNotExistsOutputMapper());
        outputMap.put("Running_NotReady", new RunningNotReadyOutputMapper());
        outputMap.put("Running_Ready", new RunningReadyOutputMapper());
        outputMap.put("Succeeded", new SucceededOutputMapper());
        outputMap.put("Terminating", new TerminatingOutputMapper());
        outputMap.put("", new PodNotExistsOutputMapper());
    }

    public void runTestSuite(TestSuite testSuite) throws FileNotFoundException, URISyntaxException, InterruptedException {
        long startTime = System.currentTimeMillis();
        boolean allTestPassed = true;
        boolean currentResult;

        KubectlUtils.deletePod("test-pod"); // fresh start

        List<String> inputs = testSuite.getInputList();
        List<String> expectedOutputs = testSuite.getOutputList();
        int testSuiteLength = inputs.size();

        // Iterate through all test cases
        for (int i = 0; i < testSuiteLength; i++) {
            String input = inputs.get(i);
            String expectedOutput = expectedOutputs.get(i);

            // Execute the corresponding InputExecutor for the given input
            if (inputFunctionMap.containsKey(input) && outputMap.containsKey(expectedOutput)) {
                System.out.println(STR."Running test for input: \{input}");
                System.out.println(STR."Expected output: \{expectedOutput}");
                currentResult = Utils.processInput(inputFunctionMap.get(input), outputMap.get(expectedOutput));
            } else {
                throw new IllegalArgumentException(STR."Unknown input: \{input}");
            }

            if (currentResult) {
                System.out.println("--------------------------------------------------");
                System.out.println(STR."Test (\{i+1}/\{testSuiteLength}) passed for input: \{input}");
                System.out.println("--------------------------------------------------");
            } else {
                System.out.println("--------------------------------------------------");
                System.out.println(STR."Test (\{i+1}/\{testSuiteLength}) failed for input: \{input}");
                System.out.println(STR."Expected: \{expectedOutput}");
                System.out.println("--------------------------------------------------");
            }

            allTestPassed = allTestPassed && currentResult;
        }

        long endTime = System.currentTimeMillis();
        System.out.println("\n");
        System.out.println(STR."Elapsed time: \{(endTime-startTime)/1000} s");
        if (allTestPassed) {
            System.out.println("All test passed!\n\n");
        } else {
            System.out.println("There were failures...\n\n");
        }
    }
}
