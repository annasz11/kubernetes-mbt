package input_executors;

import output_mapper.OutputMapper;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public interface InputExecutor {
    boolean execute(OutputMapper expectedOutput) throws FileNotFoundException, URISyntaxException, InterruptedException;
}
