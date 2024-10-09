import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class GradeBookTest {
    /*@Test
    void testSetGradeBookFromFile() throws Exception {
        Path tempFileIn = Files.createTempFile("input", ".txt");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFileIn)) {
            writer.write("Имя Фамилия Отчество 2 6\n");
            writer.write("1 Математический_анализ 9\n");
            writer.write("1 Основы_высшей_алгебры 10\n");
            writer.write("2 Математический_анализ 10\n");
            writer.write("2 ОВА 9\n");
        }

        GradeBook gradeBook = new GradeBook();
        gradeBook.setStudentFromFile(tempFileIn.toString());

        assertNotNull(gradeBook);
        assertFalse(gradeBook.sessions.isEmpty(), "Данные о студенте должны быть считаны корректно");

        Files.delete(tempFileIn);
    }

    @Test
    void testWriteHonorStudentsToFile() throws Exception {
        List<GradeBook> gradeBooks = new ArrayList<>();

        Path tempFileIn = Files.createTempFile("input", ".txt");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFileIn)) {
            writer.write("Имя Фамилия Отчество 2 6\n");
            writer.write("1 Математический_анализ 9\n");
            writer.write("1 Основы_высшей_алгебры 10\n");
            writer.write("2 Математический_анализ 10\n");
            writer.write("2 ОВА 9\n");
        }

        GradeBook gradeBook = new GradeBook();
        gradeBook.setStudentFromFile(tempFileIn.toString());
        gradeBooks.add(gradeBook);

        Path tempFileOut = Files.createTempFile("honorStudents", ".txt");


        GradeBook.writeHonorStudentsToFile(tempFileOut.toString(), gradeBooks);

        String content = Files.readString(tempFileOut);

        assertTrue(content.contains("Фамилия Имя Отчество 2 6 Сессия: 1\n" +
                "Предмет: Математический_анализ Оценка: 9\n" +
                "Предмет: Основы_высшей_алгебры Оценка: 10\n" +
                "\n" +
                "Фамилия Имя Отчество 2 6 Сессия: 2\n" +
                "Предмет: Математический_анализ Оценка: 10\n" +
                "Предмет: ОВА Оценка: 9\n" +
                "\n"), "Файл должен содержать информацию о студенте");

        // Удаление временных файлов
        Files.delete(tempFileOut);
        Files.delete(tempFileIn);
    }
    */
}