package output_mapper;

import utils.KubectlUtils;

public class SucceededOutputMapper implements OutputMapper {
    @Override
    public boolean getState() throws InterruptedException {
        return KubectlUtils.getPodStatus("test-pod", "Completed")
                && KubectlUtils.getPodStatusPhase("test-pod", "Succeeded");
    }
}
