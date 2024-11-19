package utils;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.stream.Stream;

import static utils.Utils.getResourceFilePath;

public class KubectlUtils {

    public static boolean getPodStatus(String podName, String expectedState) throws InterruptedException {
        String currentStatus1 = "Unknown";
        String currentStatus2 = "Unknown";
        for (int i=0; i<20; i++) {
            currentStatus1 = Utils.executeKubectlCommand("kubectl", "get", "pod", podName, "-o", "custom-columns=\"STATUS:.status.phase\"",  "--no-headers");
            currentStatus2 = Utils.executeKubectlCommand("kubectl", "get", "pod", podName,  "--no-headers");

            System.out.println(STR."Actual status: \{currentStatus1}");
            System.out.println(STR."Pod state: \{currentStatus2}");

            if (currentStatus1.equals(expectedState) || currentStatus2.contains(expectedState)) {
                return true;
            }

            Thread.sleep(1000);
        }
        return false;
    }

    public static boolean getPodReady(String podName, String expectedReady) throws InterruptedException {
        for (int i=0; i<20; i++) {
            String currentStatus = Utils.executeKubectlCommand("kubectl", "get", "pod", podName, "-o", "custom-columns=\"READY:.status.containerStatuses[*].ready\"", "--no-headers");
            System.out.println(STR."Pod ready state: \{currentStatus}");

            if (expectedReady.equals(currentStatus)) {
                return true;
            }

            Thread.sleep(1000);
        }
        return false;
    }

    public static boolean getPodStatusPhase(String podName, String expectedPhase) throws InterruptedException {
        for (int i=0; i<20; i++) {
            String currentPhase = Utils.executeKubectlCommand("kubectl", "get", "pod", podName, "-o", "jsonpath=\"{.status.phase}\"");
            System.out.println(STR."Pod phase: \{currentPhase}");

            if (expectedPhase.equals(currentPhase)) {
                return true;
            }

            Thread.sleep(1000);
        }
        return false;
    }

    public static String applyPodDefinition(String filename) throws URISyntaxException {
        String filePath = getResourceFilePath(STR."\{filename}");
        System.out.println(STR."Running command: kubectl apply -f \{filePath}");
        return Utils.executeKubectlCommand("kubectl", "apply", "-f", filePath);
    }

    public static String deletePod(String podName, String... additionalOptions) {
        String[] command = { "kubectl", "delete", "pod", podName };
        return Utils.executeKubectlCommand(Stream.of(new String[][]{command, additionalOptions})
                .flatMap(Arrays::stream)
                .toArray(String[]::new));
    }
}
