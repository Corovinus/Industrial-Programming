import java.util.Scanner;
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        List<GradeBook> gradeBooks = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        GradeBook gradeBook = new GradeBook();
        gradeBook.setGradeBookFromFile(sc.nextLine());
        gradeBooks.add(gradeBook);
        GradeBook.writeHonorStudentsToFile("output.txt", gradeBooks);
    }
}