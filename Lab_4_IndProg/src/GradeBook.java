import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.PrintWriter;
import java.util.*;
public class GradeBook {
    ///////////////////////////////////Поля
    private String name;
    private String surname;
    private String patronymic;
    private int course;
    private int group;
    private int studakNumber;
    List<Session> sessions = new ArrayList<>();
    /////////////////////////////Вложенные классы
    public class Session {
        private int sessionNumber;
        private List<Exam> exams = new ArrayList<>();

        public void addExam(String lesson, int grade) {
            exams.add(new Exam(lesson, grade));
        }

        public boolean allExamsAboveNine() {
            for (Exam exam : exams) {
                if (exam.grade < 9) {
                    return false;
                }
            }
            return true;
        }

        public List<Exam> getExams() {
            return exams;
        }
    }

    public class Exam {
        private String lesson;
        private int grade;

        public Exam(String lesson, int grade) {
            this.lesson = lesson;
            this.grade = grade;
        }

        public String getLesson() {
            return lesson;
        }

        public int getGrade() {
            return grade;
        }
    }

    //////////////////////////////Методы
    private Session findSessionByNumber(int sessionNumber) {
        for (Session session : sessions) {
            if (session.sessionNumber == sessionNumber) {
                return session;
            }
        }
        return null;
    }
    private static GradeBook findGradeBookByStudak(List<GradeBook> myList, int studakNumber) {
        for (GradeBook gradeBook : myList) {
            if (gradeBook.studakNumber == studakNumber) {
                return gradeBook;
            }
        }
        return null;
    }
    public static void setStudentFromFile(List<GradeBook> myList, String inputFile) {
        try (Scanner scanner = new Scanner(new File(inputFile))) {
            while (scanner.hasNextLine()) {
                GradeBook gradeBook = new GradeBook();
                try {
                    gradeBook.studakNumber = scanner.nextInt();
                    gradeBook.name = scanner.next();
                    gradeBook.surname = scanner.next();
                    gradeBook.patronymic = scanner.next();
                    gradeBook.course = scanner.nextInt();
                    gradeBook.group = scanner.nextInt();
                    myList.add(gradeBook);
                } catch (InputMismatchException e) {
                    System.out.println("Ошибка в формате данных: " + e.getMessage());
                    scanner.nextLine();
                }
            }
            System.out.println("--------------------------------------------------");
            System.out.println("Студенты с файла " + inputFile + " успешно загружены!");
            System.out.println("--------------------------------------------------");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
    }

    public static void importExamReport(List <GradeBook> myList, String inputFile) {
        try (Scanner scanner = new Scanner(new File(inputFile))) {

            String line = scanner.nextLine();
            String[] s = line.split(" ");
            int semester = Integer.parseInt(s[s.length - 1]);
            StringBuilder subject = new StringBuilder();
            for (int i = 0; i < s.length - 1; i++) {
                subject.append(s[i]);
                if (i < s.length - 2) {
                    subject.append(" ");
                }
            }
            while (scanner.hasNextLine()) {

                int studakNumber = scanner.nextInt();
                int grade = scanner.nextInt();

                //поиск студента по номеру студака
                GradeBook student = findGradeBookByStudak(myList, studakNumber);
                if (student != null) {
                    GradeBook.Session session = student.findSessionByNumber(semester);
                    if (session == null) {
                        session = student.new Session();
                        session.sessionNumber = semester;
                        student.sessions.add(session);
                    }

                    session.addExam(subject.toString(), grade);
                } else {
                    System.out.println("Студент с номером студенческого " + studakNumber + " не найден.");
                }
            }
            System.out.println("Экзамены с файла  " + inputFile + " успешно загружены!");
            System.out.println("--------------------------------------------------");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }

    }

    public static void outStudentList(List<GradeBook> myList) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("outputStudents.txt"))) {
            writer.println("----------------------------------------------------------");
            writer.printf("%-15s %-15s %-15s %-5s %-5s\n", "Имя", "Фамилия", "Отчество", "Курс", "Группа");
            writer.println("----------------------------------------------------------");
            for (GradeBook gradeBook : myList) {
                writer.printf("%-15s %-15s %-15s %-5d %-5d\n",
                        gradeBook.name,
                        gradeBook.surname,
                        gradeBook.patronymic,
                        gradeBook.course,
                        gradeBook.group);

                for (Session session : gradeBook.sessions) {
                    writer.println("\n" + session.sessionNumber + " Сессия");

                    for (Exam exam : session.exams) {
                        writer.printf("%-40s %-10d\n", exam.lesson, exam.grade);
                    }
                    writer.println("----------------------------------------------------------");
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeHonorStudentsToFile(String outputFile, List<GradeBook> gradeBooks) {
        gradeBooks.sort(Comparator.comparing(GradeBook::getSurname));
        try (FileWriter writer = new FileWriter(outputFile)) {
            for (GradeBook gradeBook : gradeBooks) {
                for (Session session : gradeBook.sessions) {
                    if (session.allExamsAboveNine()) {
                        writer.write(gradeBook.surname + " " + gradeBook.name + " " + gradeBook.patronymic + " "
                                + gradeBook.course + " " + gradeBook.group + " Сессия: " + session.sessionNumber + "\n");
                        for (Exam exam : session.getExams()) {
                            writer.write("Предмет: " + exam.getLesson() + " Оценка: " + exam.getGrade() + "\n");
                        }
                        writer.write("\n");
                    }
                }
            }
            System.out.println("Файл с отличниками " + outputFile + " успешно создан!");
        } catch (IOException e) {
            System.out.println("Файл не найден");
        }
    }
    public String getSurname() {
        return surname;
    }
}
