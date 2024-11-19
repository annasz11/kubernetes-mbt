package input_executors;

import output_mapper.OutputMapper;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class CheckStatusInputExecutor implements InputExecutor {
    @Override
    public boolean execute(OutputMapper expectedOutput) throws FileNotFoundException, URISyntaxException, InterruptedException {
        return true;
    }
}
