import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    private FileHandler fileHandler;
    private final String inputFilePath = "testInput.txt";
    private final String outputFilePath = "testOutput.txt";

    @BeforeEach
    void setup() throws IOException {
        List<String> testInputData = Arrays.asList("Hello world", "This is a test file");
        Files.write(Path.of(inputFilePath), testInputData);

        fileHandler = new FileHandler(inputFilePath, outputFilePath);
    }

    @Test
    void testReadLines() throws IOException {
        List<String> lines = fileHandler.readLines();

        assertEquals(2, lines.size());
        assertEquals("Hello world", lines.get(0));
        assertEquals("This is a test file", lines.get(1));
    }

    @Test
    void testWriteLines() throws IOException {
        List<String> outputData = Arrays.asList("Test line 1", "Test line 2");
        fileHandler.writeLines(outputData);

        List<String> writtenLines = Files.readAllLines(Path.of(outputFilePath));

        assertEquals(outputData.size(), writtenLines.size());
        assertEquals("Test line 1", writtenLines.get(0));
        assertEquals("Test line 2", writtenLines.get(1));
    }
}
