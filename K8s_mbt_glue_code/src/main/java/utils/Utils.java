package utils;

import input_executors.InputExecutor;
import output_mapper.OutputMapper;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Objects;

public class Utils {

    // Access resource file from the resources folder
    public static String getResourceFilePath(String fileName) throws URISyntaxException {
        URL file = Objects.requireNonNull(Utils.class.getClassLoader().getResource(fileName));
        return Path.of(new URI(file.toString())).toString();
    }

    public static boolean processInput(InputExecutor inputExecutor, OutputMapper expectedOutput) throws FileNotFoundException, URISyntaxException, InterruptedException {
        return inputExecutor.execute(expectedOutput);
    }

    public static String executeKubectlCommand(String... command) {
        StringBuilder output = new StringBuilder();
        try {
            // Initialize the process builder with the kubectl command and arguments
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true); // Merge error and output streams

            // Start the process
            Process process = processBuilder.start();
            process.waitFor(); // Wait for the command to complete

            // Read and append the output of the command
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }

            if (process.exitValue() != 0) {
                return "Error executing kubectl command.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return STR."Error executing kubectl command: \{String.join(" ", command)}";
        }

        return output.toString().trim();
    }

}
