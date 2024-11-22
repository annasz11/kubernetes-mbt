package utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import utils.TestSuite;

public class TestSuiteWrapper {
    @JsonProperty("test_suite")
    public TestSuite testSuite;
}