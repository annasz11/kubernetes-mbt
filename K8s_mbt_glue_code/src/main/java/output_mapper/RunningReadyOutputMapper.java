package output_mapper;

import utils.KubectlUtils;

public class RunningReadyOutputMapper implements OutputMapper {
    @Override
    public boolean getState() throws InterruptedException {
        return KubectlUtils.getPodStatus("test-pod", "Running")
                && KubectlUtils.getPodReady("test-pod", "true");
    }
}
