package input_executors;

import output_mapper.OutputMapper;
import utils.KubectlUtils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class ReadinessProbeFailureInputExecutor implements InputExecutor {
    @Override
    public boolean execute(OutputMapper expectedOutput) throws FileNotFoundException, URISyntaxException, InterruptedException {
        System.out.println(KubectlUtils.deletePod("test-pod"));
        System.out.println(KubectlUtils.applyPodDefinition("test-pod-readiness-probe-failure.yaml"));
        return expectedOutput.getState();
    }
}
