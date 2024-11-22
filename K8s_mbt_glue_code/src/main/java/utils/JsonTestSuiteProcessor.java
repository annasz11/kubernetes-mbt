package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class JsonTestSuiteProcessor {

    public static TestSuiteWrapper loadTestSuite(String filePath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), TestSuiteWrapper.class);
    }
}