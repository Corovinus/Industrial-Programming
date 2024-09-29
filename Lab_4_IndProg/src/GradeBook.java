import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GradeBook {
    private String name;
    private String surname;
    private String patronymic;
    private int course;
    private int group;
    private List<Session> sessions = new ArrayList<>();

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

    class Exam {
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

    private Session findSessionByNumber(int sessionNumber) {
        for (Session session : sessions) {
            if (session.sessionNumber == sessionNumber) {
                return session;
            }
        }
        return null;
    }

    public void setGradeBookFromFile(String inputFile) {
        try (Scanner scanner = new Scanner(new File(inputFile))) {
            name = scanner.next();
            surname = scanner.next();
            patronymic = scanner.next();
            course = scanner.nextInt();
            group = scanner.nextInt();

            while (scanner.hasNext()) {
                int sessionNumber = scanner.nextInt();
                String lesson = scanner.next();
                int grade = scanner.nextInt();

                Session session = findSessionByNumber(sessionNumber);

                if (session == null) {
                    session = new Session();
                    session.sessionNumber = sessionNumber;
                    sessions.add(session);
                }
                session.addExam(lesson, grade);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static void writeHonorStudentsToFile(String outputFile, List<GradeBook> gradeBooks) {
        try (FileWriter writer = new FileWriter(outputFile)) {
            for (GradeBook gradeBook : gradeBooks) {
                for (Session session : gradeBook.sessions) {
                    if (session.allExamsAboveNine()) {
                        writer.write(gradeBook.surname + " " + gradeBook.name + " " + gradeBook.patronymic + " "
                                + gradeBook.course + " " + gradeBook.group + " Сессия: " + session.sessionNumber + "\n");
                        for (Exam exam : session.getExams()) {
                            writer.write("Предмет: " + exam.getLesson() + " Оценка: " + exam.getGrade() + "\n");
                        }
                    }
                    writer.write("\n");
                }
            }
            System.out.println("File successfully written to " + outputFile);
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

}
