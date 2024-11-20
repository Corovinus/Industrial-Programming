package org.example.texteditor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.Scanner;
public class HelloController {
    @FXML private TextArea mainTextArea;
    @FXML private TextField mainTextField;
    @FXML private Button mainButton;
    @FXML private Button openButton;
    @FXML private Button saveButton;
    @FXML private Button clearButton;

    @FXML
    protected void onHelloButtonClick() {
        if(mainTextArea.getText().equals("")) {
            mainTextArea.setText(mainTextField.getText());
            mainTextField.setText("");
        }
        else {
            mainTextArea.setText(mainTextArea.getText() + "\n" + mainTextField.getText());
            mainTextField.setText("");
        }
    }
    @FXML
    protected void setMainTextFieldEnter() {
        if(mainTextArea.getText().equals("")) {
            mainTextArea.setText(mainTextField.getText());
            mainTextField.setText("");
        }
        else {
            mainTextArea.setText(mainTextArea.getText() + "\n" + mainTextField.getText());
            mainTextField.setText("");
        }
    }
    @FXML
    protected void openButtonClick() throws FileNotFoundException {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("C:\\Users\\GIGABYTE\\Industrial-Programming\\"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        chooser.setFileFilter(filter);
        mainTextArea.setText("");

        int ret = chooser.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                if (mainTextArea.getText().equals("")) {
                    mainTextArea.setText(scanner.nextLine());
                }
                else {
                    mainTextArea.setText(mainTextArea.getText() + "\n" + scanner.nextLine());
                }
            }
        }
    }
    @FXML
    protected void clearButtonClick() {
        mainTextArea.setText("");
        mainTextField.setText("");
    }
    @FXML
    protected void saveButtonClick() throws IOException {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("C:\\Users\\GIGABYTE\\Industrial-Programming\\"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        chooser.setFileFilter(filter);
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        chooser.setDialogTitle("Сохранить файл");
        int ret = chooser.showSaveDialog(null);
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (!file.getName().endsWith("txt")) {
                file = new File(file.getAbsolutePath() + ".txt");
            }
            System.out.println("Файл для сохранения " + file.getAbsolutePath());
            try {
                if (file.createNewFile()) {
                    System.out.println("Файл создан");
                } else {
                    System.out.println("Файл уже существует");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Writer writer = new FileWriter(file,false);
            String text = mainTextArea.getText();
            writer.write(text);
            writer.close();
        }
    }
}