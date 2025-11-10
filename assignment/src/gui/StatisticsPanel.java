package gui;

import models.Student;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * StatisticsPanel - Panel for viewing statistics
 * Demonstrates JLabel, JTextArea, and layout managers
 */
public class StatisticsPanel extends JPanel {
    private List<Student> students;
    private JTextArea statsArea;
    private JLabel totalLabel, passLabel, failLabel, avgLabel;

    /**
     * Constructor - sets up the statistics panel
     */
    public StatisticsPanel(List<Student> students) {
        this.students = students;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Title
        JLabel titleLabel = new JLabel("System Statistics");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Create main content panel
        JPanel contentPanel = new JPanel(new GridLayout(2, 1, 10, 10));

        // Summary cards panel
        JPanel cardsPanel = createSummaryCards();
        contentPanel.add(cardsPanel);

        // Detailed statistics panel
        JPanel detailsPanel = createDetailsPanel();
        contentPanel.add(detailsPanel);

        add(contentPanel, BorderLayout.CENTER);

        // Refresh button
        JButton refreshButton = new JButton("Refresh Statistics");
        refreshButton.addActionListener(e -> refreshStatistics());
        add(refreshButton, BorderLayout.SOUTH);

        refreshStatistics();
    }

    /**
     * Create summary cards showing key metrics
     */
    private JPanel createSummaryCards() {
        JPanel panel = new JPanel(new GridLayout(1, 4, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Overview"));

        // Total students card
        JPanel totalCard = createCard("Total Students", "0", Color.decode("#4CAF50"));
        totalLabel = (JLabel) ((JPanel) totalCard.getComponent(1)).getComponent(0);

        // Pass rate card
        JPanel passCard = createCard("Passing", "0", Color.decode("#2196F3"));
        passLabel = (JLabel) ((JPanel) passCard.getComponent(1)).getComponent(0);

        // Fail rate card
        JPanel failCard = createCard("Failing", "0", Color.decode("#F44336"));
        failLabel = (JLabel) ((JPanel) failCard.getComponent(1)).getComponent(0);

        // Average score card
        JPanel avgCard = createCard("Avg Score", "0.00", Color.decode("#FF9800"));
        avgLabel = (JLabel) ((JPanel) avgCard.getComponent(1)).getComponent(0);

        panel.add(totalCard);
        panel.add(passCard);
        panel.add(failCard);
        panel.add(avgCard);

        return panel;
    }

    /**
     * Create a summary card with title and value
     */
    private JPanel createCard(String title, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout(5, 5));
        card.setBorder(BorderFactory.createLineBorder(color, 2));
        card.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(color);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel valuePanel = new JPanel();
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 24));
        valuePanel.add(valueLabel);

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valuePanel, BorderLayout.CENTER);

        return card;
    }

    /**
     * Create detailed statistics panel
     */
    private JPanel createDetailsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Detailed Statistics"));

        statsArea = new JTextArea();
        statsArea.setEditable(false);
        statsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        statsArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(statsArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Refresh all statistics
     */
    public void refreshStatistics() {
        if (students.isEmpty()) {
            totalLabel.setText("0");
            passLabel.setText("0");
            failLabel.setText("0");
            avgLabel.setText("0.00");
            statsArea.setText("No students in the system.\n\nAdd students to see statistics.");
            return;
        }

        // Calculate statistics
        int totalStudents = students.size();
        int passingStudents = 0;
        int failingStudents = 0;
        double totalAverage = 0.0;
        int studentsWithGrades = 0;

        Student topStudent = null;
        double highestAvg = 0.0;

        for (Student student : students) {
            double avg = student.calculateAverage();

            if (student.getGrades().size() > 0) {
                studentsWithGrades++;
                totalAverage += avg;

                if (student.getStatus().equals("Pass")) {
                    passingStudents++;
                } else {
                    failingStudents++;
                }

                if (topStudent == null || avg > highestAvg) {
                    topStudent = student;
                    highestAvg = avg;
                }
            }
        }

        double systemAverage = studentsWithGrades > 0 ? totalAverage / studentsWithGrades : 0.0;
        double passRate = studentsWithGrades > 0 ? (passingStudents * 100.0 / studentsWithGrades) : 0.0;

        // Update summary cards
        totalLabel.setText(String.valueOf(totalStudents));
        passLabel.setText(String.valueOf(passingStudents));
        failLabel.setText(String.valueOf(failingStudents));
        avgLabel.setText(String.format("%.2f", systemAverage));

        // Build detailed statistics text
        StringBuilder stats = new StringBuilder();
        stats.append("═══════════════════════════════════════════════════════════════\n");
        stats.append("                   SYSTEM STATISTICS REPORT                    \n");
        stats.append("═══════════════════════════════════════════════════════════════\n\n");

        stats.append("OVERALL STATISTICS:\n");
        stats.append("───────────────────────────────────────────────────────────────\n");
        stats.append(String.format("Total Students Registered:        %d\n", totalStudents));
        stats.append(String.format("Students with Grades:             %d\n", studentsWithGrades));
        stats.append(String.format("Students without Grades:          %d\n\n", totalStudents - studentsWithGrades));

        stats.append("ACADEMIC PERFORMANCE:\n");
        stats.append("───────────────────────────────────────────────────────────────\n");
        stats.append(String.format("Passing Students:                 %d\n", passingStudents));
        stats.append(String.format("Failing Students:                 %d\n", failingStudents));
        stats.append(String.format("Pass Rate:                        %.2f%%\n", passRate));
        stats.append(String.format("System Average Score:             %.2f\n\n", systemAverage));

        if (topStudent != null) {
            stats.append("TOP PERFORMER:\n");
            stats.append("───────────────────────────────────────────────────────────────\n");
            stats.append(String.format("Name:                             %s\n", topStudent.getName()));
            stats.append(String.format("Student ID:                       %s\n", topStudent.getStudentId()));
            stats.append(String.format("Average Score:                    %.2f\n", topStudent.calculateAverage()));
            stats.append(String.format("Letter Grade:                     %s\n\n", topStudent.getLetterGrade()));
        }

        // Grade distribution
        int[] gradeDistribution = new int[5]; // A, B, C, D, F
        for (Student student : students) {
            if (student.getGrades().size() > 0) {
                String letterGrade = student.getLetterGrade();
                char firstChar = letterGrade.charAt(0);
                switch (firstChar) {
                    case 'A': gradeDistribution[0]++; break;
                    case 'B': gradeDistribution[1]++; break;
                    case 'C': gradeDistribution[2]++; break;
                    case 'D': gradeDistribution[3]++; break;
                    case 'F': gradeDistribution[4]++; break;
                }
            }
        }

        stats.append("GRADE DISTRIBUTION:\n");
        stats.append("───────────────────────────────────────────────────────────────\n");
        stats.append(String.format("A Grades:                         %d students\n", gradeDistribution[0]));
        stats.append(String.format("B Grades:                         %d students\n", gradeDistribution[1]));
        stats.append(String.format("C Grades:                         %d students\n", gradeDistribution[2]));
        stats.append(String.format("D Grades:                         %d students\n", gradeDistribution[3]));
        stats.append(String.format("F Grades:                         %d students\n\n", gradeDistribution[4]));

        // Major distribution
        stats.append("STUDENTS BY MAJOR:\n");
        stats.append("───────────────────────────────────────────────────────────────\n");
        java.util.Map<String, Integer> majorCount = new java.util.HashMap<>();
        for (Student student : students) {
            String major = student.getMajor().isEmpty() ? "Undeclared" : student.getMajor();
            majorCount.put(major, majorCount.getOrDefault(major, 0) + 1);
        }

        for (java.util.Map.Entry<String, Integer> entry : majorCount.entrySet()) {
            stats.append(String.format("%-30s:    %d students\n", entry.getKey(), entry.getValue()));
        }

        stats.append("\n═══════════════════════════════════════════════════════════════\n");
        stats.append("                     End of Report                             \n");
        stats.append("═══════════════════════════════════════════════════════════════\n");

        statsArea.setText(stats.toString());
        statsArea.setCaretPosition(0); // Scroll to top
    }
}
