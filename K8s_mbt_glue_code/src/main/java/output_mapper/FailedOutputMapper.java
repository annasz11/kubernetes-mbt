package output_mapper;

import utils.KubectlUtils;

public class FailedOutputMapper implements OutputMapper {
    @Override
    public boolean getState() throws InterruptedException {
        return KubectlUtils.getPodStatus("test-pod", "ContainerCannotRun")
                && KubectlUtils.getPodStatusPhase("test-pod", "Failed");
    }
}
