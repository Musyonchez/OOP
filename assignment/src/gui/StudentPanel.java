package gui;

import models.Student;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * StudentPanel - Panel for managing students
 * Demonstrates JTable, JButton, JTextField, JDialog, and layout managers
 */
public class StudentPanel extends JPanel {
    private List<Student> students;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    /**
     * Constructor - sets up the student management panel
     */
    public StudentPanel(List<Student> students) {
        this.students = students;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create top panel with search and add button
        JPanel topPanel = createTopPanel();
        add(topPanel, BorderLayout.NORTH);

        // Create table
        createTable();
        JScrollPane scrollPane = new JScrollPane(studentTable);
        add(scrollPane, BorderLayout.CENTER);

        // Create button panel
        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.EAST);
    }

    /**
     * Create top panel with search functionality
     */
    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));

        JLabel titleLabel = new JLabel("Student Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel, BorderLayout.WEST);

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel searchLabel = new JLabel("Search:");
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        searchButton.addActionListener(e -> searchStudents());
        searchField.addActionListener(e -> searchStudents());

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            searchField.setText("");
            refreshTable();
        });

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(clearButton);

        panel.add(searchPanel, BorderLayout.EAST);

        return panel;
    }

    /**
     * Create student table
     */
    private void createTable() {
        String[] columnNames = {"Student ID", "Name", "Email", "Major", "Average", "Grade", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };

        studentTable = new JTable(tableModel);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentTable.setRowHeight(25);
        studentTable.getTableHeader().setReorderingAllowed(false);

        refreshTable();
    }

    /**
     * Refresh table with current student data
     */
    public void refreshTable() {
        tableModel.setRowCount(0); // Clear table

        for (Student student : students) {
            Object[] row = {
                student.getStudentId(),
                student.getName(),
                student.getEmail(),
                student.getMajor(),
                String.format("%.2f", student.calculateAverage()),
                student.getLetterGrade(),
                student.getStatus()
            };
            tableModel.addRow(row);
        }
    }

    /**
     * Create button panel with action buttons
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 5, 5));

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(e -> showAddDialog());

        JButton editButton = new JButton("Edit Student");
        editButton.addActionListener(e -> showEditDialog());

        JButton deleteButton = new JButton("Delete Student");
        deleteButton.addActionListener(e -> deleteStudent());

        JButton viewButton = new JButton("View Details");
        viewButton.addActionListener(e -> viewStudentDetails());

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> refreshTable());

        panel.add(addButton);
        panel.add(editButton);
        panel.add(deleteButton);
        panel.add(viewButton);
        panel.add(refreshButton);

        return panel;
    }

    /**
     * Show dialog to add new student
     */
    public void showAddDialog() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Add Student", true);
        dialog.setLayout(new GridLayout(5, 2, 10, 10));
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(this);

        // Create fields
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField majorField = new JTextField();

        dialog.add(new JLabel("Student ID:"));
        dialog.add(idField);
        dialog.add(new JLabel("Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Email:"));
        dialog.add(emailField);
        dialog.add(new JLabel("Major:"));
        dialog.add(majorField);

        // Buttons
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        saveButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String major = majorField.getText().trim();

            if (id.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "ID and Name are required!",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check for duplicate ID
            for (Student s : students) {
                if (s.getStudentId().equals(id)) {
                    JOptionPane.showMessageDialog(dialog, "Student ID already exists!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            Student student = new Student(id, name, email, major);
            students.add(student);
            refreshTable();
            dialog.dispose();

            JOptionPane.showMessageDialog(this, "Student added successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.add(saveButton);
        dialog.add(cancelButton);

        dialog.setVisible(true);
    }

    /**
     * Show dialog to edit selected student
     */
    private void showEditDialog() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to edit.",
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Student student = students.get(selectedRow);

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Edit Student", true);
        dialog.setLayout(new GridLayout(5, 2, 10, 10));
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(this);

        // Create fields with current values
        JTextField idField = new JTextField(student.getStudentId());
        idField.setEditable(false); // Don't allow ID change

        JTextField nameField = new JTextField(student.getName());
        JTextField emailField = new JTextField(student.getEmail());
        JTextField majorField = new JTextField(student.getMajor());

        dialog.add(new JLabel("Student ID:"));
        dialog.add(idField);
        dialog.add(new JLabel("Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Email:"));
        dialog.add(emailField);
        dialog.add(new JLabel("Major:"));
        dialog.add(majorField);

        // Buttons
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        saveButton.addActionListener(e -> {
            student.setName(nameField.getText().trim());
            student.setEmail(emailField.getText().trim());
            student.setMajor(majorField.getText().trim());
            refreshTable();
            dialog.dispose();

            JOptionPane.showMessageDialog(this, "Student updated successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.add(saveButton);
        dialog.add(cancelButton);

        dialog.setVisible(true);
    }

    /**
     * Delete selected student
     */
    private void deleteStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to delete.",
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Student student = students.get(selectedRow);

        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete " + student.getName() + "?",
            "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            students.remove(selectedRow);
            refreshTable();
            JOptionPane.showMessageDialog(this, "Student deleted successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * View detailed information about selected student
     */
    private void viewStudentDetails() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to view.",
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Student student = students.get(selectedRow);

        String details = "Student Details\n\n" +
                        "ID: " + student.getStudentId() + "\n" +
                        "Name: " + student.getName() + "\n" +
                        "Email: " + student.getEmail() + "\n" +
                        "Major: " + student.getMajor() + "\n\n" +
                        "Academic Performance:\n" +
                        "Courses Enrolled: " + student.getGrades().size() + "\n" +
                        "Average Score: " + String.format("%.2f", student.calculateAverage()) + "\n" +
                        "Letter Grade: " + student.getLetterGrade() + "\n" +
                        "Status: " + student.getStatus();

        JOptionPane.showMessageDialog(this, details, "Student Details",
            JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Search students by name or ID
     */
    private void searchStudents() {
        String searchTerm = searchField.getText().trim().toLowerCase();

        if (searchTerm.isEmpty()) {
            refreshTable();
            return;
        }

        tableModel.setRowCount(0);

        for (Student student : students) {
            if (student.getName().toLowerCase().contains(searchTerm) ||
                student.getStudentId().toLowerCase().contains(searchTerm)) {
                Object[] row = {
                    student.getStudentId(),
                    student.getName(),
                    student.getEmail(),
                    student.getMajor(),
                    String.format("%.2f", student.calculateAverage()),
                    student.getLetterGrade(),
                    student.getStatus()
                };
                tableModel.addRow(row);
            }
        }
    }
}
