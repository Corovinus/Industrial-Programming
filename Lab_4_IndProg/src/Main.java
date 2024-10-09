import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.*;
import java.io.File;
public class Main {
    public static void main(String[] args) {
        List<GradeBook> gradeBooks = new ArrayList<>();
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("C:\\Users\\GIGABYTE\\Industrial-Programming\\Lab_4_IndProg"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        chooser.setFileFilter(filter);


        int ret = chooser.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            GradeBook.setStudentFromFile(gradeBooks, file.getName());

        }
        chooser.setMultiSelectionEnabled(true);

        ret = chooser.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File[] files = chooser.getSelectedFiles();
            for (File file : files) {
                GradeBook.importExamReport(gradeBooks, file.getName());
            }
        }

        GradeBook.outStudentList(gradeBooks);
        GradeBook.writeHonorStudentsToFile("output.txt", gradeBooks);
    }
}