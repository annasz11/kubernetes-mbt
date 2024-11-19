import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TestSuite {

    private final String name;
    private final String id;
    private final String method;
    private final List<String> inputList;
    private final List<String> outputList;

    @JsonCreator
    public TestSuite(
            @JsonProperty("name") String name,
            @JsonProperty("id") String id,
            @JsonProperty("method") String method,
            @JsonProperty("input_list") List<String> inputList,
            @JsonProperty("output_list") List<String> outputList) {
        this.name = name;
        this.id = id;
        this.method = method;
        this.inputList = inputList;
        this.outputList = outputList;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getMethod() {
        return method;
    }

    public List<String> getInputList() {
        return inputList;
    }

    public List<String> getOutputList() {
        return outputList;
    }

}
