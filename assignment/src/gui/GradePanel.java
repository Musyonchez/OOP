package gui;

import models.Student;
import models.Grade;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * GradePanel - Panel for managing student grades
 * Demonstrates JComboBox, JTable, and complex layouts
 */
public class GradePanel extends JPanel {
    private List<Student> students;
    private JComboBox<String> studentComboBox;
    private JTable gradeTable;
    private DefaultTableModel tableModel;
    private Student currentStudent;

    /**
     * Constructor - sets up the grade management panel
     */
    public GradePanel(List<Student> students) {
        this.students = students;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create top panel with student selection
        JPanel topPanel = createTopPanel();
        add(topPanel, BorderLayout.NORTH);

        // Create grade table
        createTable();
        JScrollPane scrollPane = new JScrollPane(gradeTable);
        add(scrollPane, BorderLayout.CENTER);

        // Create button panel
        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.EAST);

        // Create summary panel
        JPanel summaryPanel = createSummaryPanel();
        add(summaryPanel, BorderLayout.SOUTH);
    }

    /**
     * Create top panel with student selection
     */
    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Grade Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel, BorderLayout.WEST);

        // Student selection panel
        JPanel selectionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel selectLabel = new JLabel("Select Student:");

        studentComboBox = new JComboBox<>();
        studentComboBox.setPreferredSize(new Dimension(300, 25));
        updateStudentComboBox();

        studentComboBox.addActionListener(e -> {
            String selected = (String) studentComboBox.getSelectedItem();
            if (selected != null && !selected.equals("-- Select Student --")) {
                String studentId = selected.split(" - ")[0];
                loadStudentGrades(studentId);
            } else {
                currentStudent = null;
                tableModel.setRowCount(0);
            }
        });

        selectionPanel.add(selectLabel);
        selectionPanel.add(studentComboBox);

        panel.add(selectionPanel, BorderLayout.EAST);

        return panel;
    }

    /**
     * Update student combo box with current students
     */
    private void updateStudentComboBox() {
        studentComboBox.removeAllItems();
        studentComboBox.addItem("-- Select Student --");

        for (Student student : students) {
            String item = student.getStudentId() + " - " + student.getName();
            studentComboBox.addItem(item);
        }
    }

    /**
     * Load grades for selected student
     */
    private void loadStudentGrades(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                currentStudent = student;
                refreshGradeTable();
                return;
            }
        }
    }

    /**
     * Create grade table
     */
    private void createTable() {
        String[] columnNames = {"Subject", "Score", "Letter Grade", "Semester", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        gradeTable = new JTable(tableModel);
        gradeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gradeTable.setRowHeight(25);
        gradeTable.getTableHeader().setReorderingAllowed(false);
    }

    /**
     * Refresh grade table for current student
     */
    private void refreshGradeTable() {
        tableModel.setRowCount(0);

        if (currentStudent == null) {
            return;
        }

        for (Grade grade : currentStudent.getGrades()) {
            Object[] row = {
                grade.getSubject(),
                String.format("%.2f", grade.getScore()),
                grade.getLetterGrade(),
                grade.getSemester(),
                grade.isPassing() ? "Pass" : "Fail"
            };
            tableModel.addRow(row);
        }
    }

    /**
     * Create button panel
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 5, 5));

        JButton addButton = new JButton("Add Grade");
        addButton.addActionListener(e -> showAddGradeDialog());

        JButton editButton = new JButton("Edit Grade");
        editButton.addActionListener(e -> showEditGradeDialog());

        JButton deleteButton = new JButton("Delete Grade");
        deleteButton.addActionListener(e -> deleteGrade());

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> refreshGradeTable());

        panel.add(addButton);
        panel.add(editButton);
        panel.add(deleteButton);
        panel.add(refreshButton);

        return panel;
    }

    /**
     * Create summary panel showing student average
     */
    private JPanel createSummaryPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder("Student Summary"));
        panel.setPreferredSize(new Dimension(0, 60));

        JLabel summaryLabel = new JLabel("Select a student to view grade summary");
        summaryLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(summaryLabel);

        // Update summary when table changes
        gradeTable.getModel().addTableModelListener(e -> {
            if (currentStudent != null) {
                String summary = String.format(
                    "Total Courses: %d  |  Average: %.2f  |  Letter Grade: %s  |  Status: %s",
                    currentStudent.getGrades().size(),
                    currentStudent.calculateAverage(),
                    currentStudent.getLetterGrade(),
                    currentStudent.getStatus()
                );
                summaryLabel.setText(summary);
            }
        });

        return panel;
    }

    /**
     * Show dialog to add new grade
     */
    private void showAddGradeDialog() {
        if (currentStudent == null) {
            JOptionPane.showMessageDialog(this, "Please select a student first.",
                "No Student Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Add Grade", true);
        dialog.setLayout(new GridLayout(4, 2, 10, 10));
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(this);

        // Create fields
        JTextField subjectField = new JTextField();
        JTextField scoreField = new JTextField();
        JTextField semesterField = new JTextField("Fall 2024");

        dialog.add(new JLabel("Subject:"));
        dialog.add(subjectField);
        dialog.add(new JLabel("Score (0-100):"));
        dialog.add(scoreField);
        dialog.add(new JLabel("Semester:"));
        dialog.add(semesterField);

        // Buttons
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        saveButton.addActionListener(e -> {
            String subject = subjectField.getText().trim();
            String scoreText = scoreField.getText().trim();
            String semester = semesterField.getText().trim();

            if (subject.isEmpty() || scoreText.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Subject and Score are required!",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double score = Double.parseDouble(scoreText);

                if (score < 0 || score > 100) {
                    JOptionPane.showMessageDialog(dialog, "Score must be between 0 and 100!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Grade grade = new Grade(subject, score, semester);
                currentStudent.addGrade(grade);
                refreshGradeTable();
                dialog.dispose();

                JOptionPane.showMessageDialog(this, "Grade added successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid score! Please enter a number.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.add(saveButton);
        dialog.add(cancelButton);

        dialog.setVisible(true);
    }

    /**
     * Show dialog to edit selected grade
     */
    private void showEditGradeDialog() {
        if (currentStudent == null) {
            JOptionPane.showMessageDialog(this, "Please select a student first.",
                "No Student Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int selectedRow = gradeTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a grade to edit.",
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Grade grade = currentStudent.getGrades().get(selectedRow);

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Edit Grade", true);
        dialog.setLayout(new GridLayout(4, 2, 10, 10));
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(this);

        // Create fields with current values
        JTextField subjectField = new JTextField(grade.getSubject());
        JTextField scoreField = new JTextField(String.valueOf(grade.getScore()));
        JTextField semesterField = new JTextField(grade.getSemester());

        dialog.add(new JLabel("Subject:"));
        dialog.add(subjectField);
        dialog.add(new JLabel("Score (0-100):"));
        dialog.add(scoreField);
        dialog.add(new JLabel("Semester:"));
        dialog.add(semesterField);

        // Buttons
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        saveButton.addActionListener(e -> {
            try {
                double score = Double.parseDouble(scoreField.getText().trim());

                if (score < 0 || score > 100) {
                    JOptionPane.showMessageDialog(dialog, "Score must be between 0 and 100!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                grade.setSubject(subjectField.getText().trim());
                grade.setScore(score);
                grade.setSemester(semesterField.getText().trim());
                refreshGradeTable();
                dialog.dispose();

                JOptionPane.showMessageDialog(this, "Grade updated successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid score!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.add(saveButton);
        dialog.add(cancelButton);

        dialog.setVisible(true);
    }

    /**
     * Delete selected grade
     */
    private void deleteGrade() {
        if (currentStudent == null) {
            JOptionPane.showMessageDialog(this, "Please select a student first.",
                "No Student Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int selectedRow = gradeTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a grade to delete.",
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Grade grade = currentStudent.getGrades().get(selectedRow);

        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete the grade for " + grade.getSubject() + "?",
            "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            currentStudent.removeGrade(grade.getSubject());
            refreshGradeTable();
            JOptionPane.showMessageDialog(this, "Grade deleted successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Refresh data (called when student list changes)
     */
    public void refreshData() {
        updateStudentComboBox();
        currentStudent = null;
        tableModel.setRowCount(0);
    }
}
