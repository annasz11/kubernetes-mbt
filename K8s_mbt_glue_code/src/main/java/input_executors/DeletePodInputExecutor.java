package input_executors;

import output_mapper.OutputMapper;
import utils.KubectlUtils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;


public class DeletePodInputExecutor implements InputExecutor {
    @Override
    public boolean execute(OutputMapper expectedOutput) throws FileNotFoundException, URISyntaxException, InterruptedException {
        System.out.println(KubectlUtils.deletePod("test-pod", "--wait=false"));
        return expectedOutput.getState();
    }
}
