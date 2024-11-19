package output_mapper;

import utils.KubectlUtils;

public class PodNotExistsOutputMapper implements OutputMapper {
    @Override
    public boolean getState() throws InterruptedException {
        return KubectlUtils.getPodStatus("test-pod", "");
    }
}
