import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            FileHandler fileHandler = new FileHandler("input.txt", "output.txt");
            TextProcessor textProcessor = new TextProcessor();

            List<String> lines = fileHandler.readLines();

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Введите позицию буквы для замены (k): ");
            int k = Integer.parseInt(inputReader.readLine());

            System.out.print("Введите строку для замены: ");
            String replaceString = inputReader.readLine();

            List<String> processedLines = textProcessor.replaceCharacters(lines, k, replaceString);

            fileHandler.writeLines(processedLines);

            System.out.println("Результат записан в файл: " + fileHandler.getOutputFilePath());
        } catch (IOException e) {
            System.err.println("Ошибка ввода-вывода: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Неверный формат числа: " + e.getMessage());
        }
    }
}
