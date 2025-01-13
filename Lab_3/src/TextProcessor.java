import java.util.ArrayList;
import java.util.List;

public class TextProcessor {
    public List<String> replaceCharacters(List<String> lines, int k, String replaceString) {
        List<String> processedLines = new ArrayList<>();

        for (String line : lines) {
            String[] words = line.split("\\s+");
            for (int i = 0; i < words.length; i++) {
                if (words[i].length() >= k) {
                    String modifiedWord = words[i].substring(0, k - 1) + replaceString + words[i].substring(k);
                    words[i] = modifiedWord;
                }
            }
            processedLines.add(String.join(" ", words));
        }

        return processedLines;
    }
}
