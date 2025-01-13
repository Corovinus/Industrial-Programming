import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TextProcessorTest {

    private final TextProcessor textProcessor = new TextProcessor();

    @Test
    void testReplaceCharacters() {
        List<String> lines = Arrays.asList("Hello world", "Java programming");
        int k = 2;
        String replaceString = "X";

        List<String> processedLines = textProcessor.replaceCharacters(lines, k, replaceString);

        assertEquals(2, processedLines.size());
        assertEquals("HXllo wXrld", processedLines.get(0));
        assertEquals("JXva pXogramming", processedLines.get(1));
    }

    @Test
    void testReplaceCharacters_NoReplacement() {
        List<String> lines = Arrays.asList("Hi", "Go");
        int k = 3;
        String replaceString = "Z";
        List<String> processedLines = textProcessor.replaceCharacters(lines, k, replaceString);
        assertEquals(lines, processedLines);
    }

    @Test
    void testReplaceCharacters_EmptyInput() {
        List<String> lines = Arrays.asList();
        int k = 1;
        String replaceString = "Z";

        List<String> processedLines = textProcessor.replaceCharacters(lines, k, replaceString);

        assertTrue(processedLines.isEmpty());
    }
}
