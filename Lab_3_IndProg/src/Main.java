import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.io.BufferedReader;
public class Main {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("input.txt");
        File outputFile = new File("output.txt");

        List<String> lines = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();

        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Введите позицию буквы для замены (k): ");
        int k = Integer.valueOf(inputReader.readLine());

        System.out.print("Введите строку для замены: ");
        String replaceString = inputReader.readLine();

        List<String> endLines = new ArrayList<>();

        for (String l : lines) {
            String[] words = l.split("\\s+");
            for (int i = 0; i < words.length; i++) {
                if (words[i].length() >= k) {
                    String modifiedWord = words[i].substring(0, k - 1) + replaceString + words[i].substring(k);
                    words[i] = modifiedWord;
                }
            }
            endLines.add(String.join(" ", words));
        }

        for (String endLine : endLines) {
            writer.write(endLine);
            writer.newLine();
        }

        writer.close();
        System.out.println("Результат записан в файл: " + outputFile);
    }
}
