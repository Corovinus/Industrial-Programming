import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private File inputFile;
    private File outputFile;

    public FileHandler(String inputFilePath, String outputFilePath) {
        this.inputFile = new File(inputFilePath);
        this.outputFile = new File(outputFilePath);
    }

    public List<String> readLines() throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    public void writeLines(List<String> lines) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public String getOutputFilePath() {
        return outputFile.getAbsolutePath();
    }
}
