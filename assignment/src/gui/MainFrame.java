package gui;

import models.Student;
import utils.DataManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * MainFrame - Main application window
 * Demonstrates JFrame, JTabbedPane, JMenuBar, and event handling
 */
public class MainFrame extends JFrame {
    private List<Student> students;
    private JTabbedPane tabbedPane;
    private StudentPanel studentPanel;
    private GradePanel gradePanel;
    private StatisticsPanel statisticsPanel;

    /**
     * Constructor - sets up the main window
     */
    public MainFrame() {
        // Load data
        DataManager.initializeFiles();
        students = DataManager.loadAll();

        // Set up frame
        setTitle("Student Management System - Java Swing");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen

        // Create menu bar
        createMenuBar();

        // Create tabbed pane with panels
        tabbedPane = new JTabbedPane();

        studentPanel = new StudentPanel(students);
        gradePanel = new GradePanel(students);
        statisticsPanel = new StatisticsPanel(students);

        tabbedPane.addTab("Students", new ImageIcon(), studentPanel, "Manage students");
        tabbedPane.addTab("Grades", new ImageIcon(), gradePanel, "Manage grades");
        tabbedPane.addTab("Statistics", new ImageIcon(), statisticsPanel, "View statistics");

        // Add tabbed pane to frame
        add(tabbedPane, BorderLayout.CENTER);

        // Add status bar
        JLabel statusBar = new JLabel(" Ready | Students: " + students.size());
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        add(statusBar, BorderLayout.SOUTH);

        // Window closing event - save data
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveData();
            }
        });

        // Tab change listener - refresh statistics
        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedIndex() == 2) {
                statisticsPanel.refreshStatistics();
            }
            statusBar.setText(" Ready | Students: " + students.size());
        });
    }

    /**
     * Create menu bar with File, Edit, and Help menus
     * Demonstrates JMenuBar, JMenu, JMenuItem
     */
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem saveItem = new JMenuItem("Save", KeyEvent.VK_S);
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        saveItem.addActionListener(e -> {
            saveData();
            JOptionPane.showMessageDialog(this, "Data saved successfully!",
                "Save", JOptionPane.INFORMATION_MESSAGE);
        });

        JMenuItem loadItem = new JMenuItem("Reload", KeyEvent.VK_R);
        loadItem.addActionListener(e -> {
            students.clear();
            students.addAll(DataManager.loadAll());
            refreshAllPanels();
            JOptionPane.showMessageDialog(this, "Data reloaded!",
                "Reload", JOptionPane.INFORMATION_MESSAGE);
        });

        JMenuItem exitItem = new JMenuItem("Exit", KeyEvent.VK_X);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        exitItem.addActionListener(e -> {
            saveData();
            System.exit(0);
        });

        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Edit Menu
        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);

        JMenuItem addStudentItem = new JMenuItem("Add Student", KeyEvent.VK_A);
        addStudentItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        addStudentItem.addActionListener(e -> {
            tabbedPane.setSelectedIndex(0);
            studentPanel.showAddDialog();
        });

        JMenuItem refreshItem = new JMenuItem("Refresh", KeyEvent.VK_R);
        refreshItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        refreshItem.addActionListener(e -> refreshAllPanels());

        editMenu.add(addStudentItem);
        editMenu.add(refreshItem);

        // Help Menu
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        JMenuItem aboutItem = new JMenuItem("About", KeyEvent.VK_A);
        aboutItem.addActionListener(e -> showAboutDialog());

        JMenuItem helpItem = new JMenuItem("Help", KeyEvent.VK_H);
        helpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        helpItem.addActionListener(e -> showHelpDialog());

        helpMenu.add(helpItem);
        helpMenu.add(aboutItem);

        // Add menus to menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }

    /**
     * Save data to files
     */
    private void saveData() {
        DataManager.saveAll(students);
    }

    /**
     * Refresh all panels
     */
    private void refreshAllPanels() {
        studentPanel.refreshTable();
        gradePanel.refreshData();
        statisticsPanel.refreshStatistics();
    }

    /**
     * Show about dialog
     */
    private void showAboutDialog() {
        String message = "Student Management System\n\n" +
                        "Version: 1.0\n" +
                        "Built with: Java Swing\n\n" +
                        "A GUI application for managing students and grades.\n\n" +
                        "© 2024 OOP Course Assignment";

        JOptionPane.showMessageDialog(this, message, "About",
            JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Show help dialog
     */
    private void showHelpDialog() {
        String help = "Student Management System - Help\n\n" +
                     "STUDENTS TAB:\n" +
                     "- Add new students\n" +
                     "- Edit student information\n" +
                     "- Delete students\n" +
                     "- Search students by name or ID\n\n" +
                     "GRADES TAB:\n" +
                     "- Select a student\n" +
                     "- Add grades for subjects\n" +
                     "- Edit or delete grades\n\n" +
                     "STATISTICS TAB:\n" +
                     "- View overall statistics\n" +
                     "- See pass/fail rates\n" +
                     "- View top performers\n\n" +
                     "KEYBOARD SHORTCUTS:\n" +
                     "Ctrl+S: Save data\n" +
                     "Ctrl+N: Add new student\n" +
                     "Ctrl+Q: Exit\n" +
                     "F5: Refresh\n" +
                     "F1: Help";

        JOptionPane.showMessageDialog(this, help, "Help",
            JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Main method - entry point
     */
    public static void main(String[] args) {
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create and show GUI on EDT
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
