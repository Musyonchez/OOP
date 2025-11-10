package utils;

import models.Student;
import models.Grade;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DataManager - handles file I/O for students and grades
 * Demonstrates file operations in Java
 */
public class DataManager {
    private static final String STUDENTS_FILE = "../data/students.txt";
    private static final String GRADES_FILE = "../data/grades.txt";

    /**
     * Save all students to file
     */
    public static void saveStudents(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENTS_FILE))) {
            for (Student student : students) {
                writer.write(student.toString());
                writer.newLine();
            }
            System.out.println("Students saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving students: " + e.getMessage());
        }
    }

    /**
     * Load all students from file
     */
    public static List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();

        File file = new File(STUDENTS_FILE);
        if (!file.exists()) {
            System.out.println("No students file found. Starting fresh.");
            return students;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split("\\|");
                if (parts.length >= 4) {
                    Student student = new Student(parts[0], parts[1], parts[2], parts[3]);
                    students.add(student);
                }
            }
            System.out.println("Loaded " + students.size() + " students.");
        } catch (IOException e) {
            System.err.println("Error loading students: " + e.getMessage());
        }

        return students;
    }

    /**
     * Save all grades to file
     * Format: StudentID|Subject|Score|Semester
     */
    public static void saveGrades(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(GRADES_FILE))) {
            for (Student student : students) {
                for (Grade grade : student.getGrades()) {
                    writer.write(student.getStudentId() + "|" + grade.toString());
                    writer.newLine();
                }
            }
            System.out.println("Grades saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving grades: " + e.getMessage());
        }
    }

    /**
     * Load all grades and associate with students
     */
    public static void loadGrades(List<Student> students) {
        File file = new File(GRADES_FILE);
        if (!file.exists()) {
            System.out.println("No grades file found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(GRADES_FILE))) {
            String line;
            int gradeCount = 0;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split("\\|");
                if (parts.length >= 4) {
                    String studentId = parts[0];
                    String subject = parts[1];
                    double score = Double.parseDouble(parts[2]);
                    String semester = parts[3];

                    // Find student and add grade
                    for (Student student : students) {
                        if (student.getStudentId().equals(studentId)) {
                            student.addGrade(new Grade(subject, score, semester));
                            gradeCount++;
                            break;
                        }
                    }
                }
            }
            System.out.println("Loaded " + gradeCount + " grades.");
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading grades: " + e.getMessage());
        }
    }

    /**
     * Initialize data files if they don't exist
     */
    public static void initializeFiles() {
        try {
            File dataDir = new File("../data");
            if (!dataDir.exists()) {
                dataDir.mkdirs();
            }

            File studentsFile = new File(STUDENTS_FILE);
            if (!studentsFile.exists()) {
                studentsFile.createNewFile();
            }

            File gradesFile = new File(GRADES_FILE);
            if (!gradesFile.exists()) {
                gradesFile.createNewFile();
            }

            System.out.println("Data files initialized.");
        } catch (IOException e) {
            System.err.println("Error initializing files: " + e.getMessage());
        }
    }

    /**
     * Save all data (students and grades)
     */
    public static void saveAll(List<Student> students) {
        saveStudents(students);
        saveGrades(students);
    }

    /**
     * Load all data (students with their grades)
     */
    public static List<Student> loadAll() {
        List<Student> students = loadStudents();
        loadGrades(students);
        return students;
    }
}
