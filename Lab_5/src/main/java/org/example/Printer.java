package org.example;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.Writer;
import com.pddstudio.phrase.java.Phrase;
public class Printer {
    String text;
    String[] convertedText;

    void readFile(String filename) throws IOException {
        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNextLine()) {
            text += scanner.nextLine();
        }

        scanner.close();
    }

    void writeFile(String filename) throws IOException {
        Writer writer = new FileWriter(new File(filename));
        for (int i = 0; i < convertedText.length; i++) {
            writer.write(convertedText[i]);
        }
        writer.close();
    }

    void convertText(int length) {
        String[] words = text.split("\s+");
        Phrase[] phrases = new Phrase[words.length];

    }
}
