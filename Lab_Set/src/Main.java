import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        File inputFile = new File("input.txt");
        File outputFile = new File("output.txt");

        mySortedSet set = new mySortedSet();
        set.in(inputFile);
        set.out(outputFile);
    }
}

class mySortedSet {
    private SortedSet<String> uniqueWords = new TreeSet<>();

    public void in(File input) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(input));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] words = line.split("\\s+");
            for (String word : words) {
                word = word.replaceAll("[^a-zA-Zа-яА-Я0-9]", "").toLowerCase();
                if (!word.isEmpty()) {
                    uniqueWords.add(word);
                }
            }
        }
        reader.close();
    }

    public void out(File output) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(output));
        for (String word : uniqueWords) {
            writer.write(word + "\n");
        }
        writer.close();
    }
}
