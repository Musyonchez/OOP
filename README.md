# Object Oriented Programming Lab Work

A comprehensive Java-based Student Grade Management System demonstrating core OOP concepts for university coursework.

## 📁 Project Structure

```
OOP/
├── README.md                    # This file
├── CLAUDE.md                    # AI collaboration documentation
├── .gitignore                   # Git ignore rules
└── labwork/                     # Main project folder
    ├── Person.java              # Parent class
    ├── Student.java             # Child class (extends Person)
    ├── Grade.java               # Grade helper class
    ├── GradeManager.java        # Student collection manager
    ├── InvalidGradeException.java  # Custom exception
    ├── Main.java                # Program entry point
    ├── LAB_REPORT.txt           # Lab report template
    └── SCREENSHOT_GUIDE.txt     # Screenshot instructions
```

## 🎯 Features

- **Student Management**: Add, search, and manage student records
- **Grade Tracking**: Record and calculate subject grades
- **Statistical Analysis**: Class performance statistics
- **Grade Validation**: Exception handling for invalid grades
- **Comprehensive Reporting**: Individual and summary reports

## 🔧 OOP Concepts Demonstrated

### Core Concepts
- ✅ **Classes**: 5 custom classes with clear responsibilities
- ✅ **Objects**: Multiple instances created and manipulated
- ✅ **Variables**: Instance and local variables throughout
- ✅ **Constants**: PASSING_GRADE, MAX_GRADE (static final)
- ✅ **Methods**: 18+ methods with various functionalities

### Advanced OOP
- ✅ **Inheritance**: Student extends Person
- ✅ **Polymorphism**: Method overriding and overloading
- ✅ **Encapsulation**: Private fields with public accessors
- ✅ **Exception Handling**: Custom exception with try-catch blocks

### Control Structures
- ✅ **Loops**: for, for-each, while, do-while
- ✅ **Selection**: if-else, if-else-if chains, switch statements

## 🚀 Quick Start

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Terminal/Command Prompt

### Compilation
```bash
cd labwork
javac *.java
```

### Execution
```bash
java Main
```

### Expected Output
The program will:
1. Create three students with grades
2. Display individual student reports
3. Show class statistics
4. Demonstrate exception handling
5. Show polymorphism examples

## 📊 Sample Output

```
Student Alice Johnson added successfully.

==================================================
Student Report: Alice Johnson (ID: S001)
Major: Computer Science | Age: 20
==================================================

Subject Grades:
  - Mathematics: 85.0 (Grade: B)
  - Physics: 78.0 (Grade: C)
  - Chemistry: 92.0 (Grade: A)
  - English: 88.0 (Grade: B)

Average Grade: 85.75
Status: Excellent
==================================================
```

## 📝 Lab Report

To create the PDF submission:

1. Review `labwork/LAB_REPORT.txt` for the complete report template
2. Follow `labwork/SCREENSHOT_GUIDE.txt` for screenshot instructions
3. Take 38 screenshots as specified
4. Insert screenshots into the report
5. Format and export as PDF

## 🧪 Testing

The program includes comprehensive tests for:
- ✅ Valid grade addition (0-100 range)
- ✅ Invalid grade rejection
- ✅ Average calculation accuracy
- ✅ Status determination
- ✅ Search functionality
- ✅ Statistical analysis
- ✅ Polymorphic behavior

## 📈 Code Statistics

- **Total Lines**: 441 lines across 6 Java files
- **Classes**: 5 custom classes
- **Methods**: 18+ methods
- **Concepts**: 12+ OOP and programming concepts

## 🏆 Assignment Requirements

This project fulfills all requirements for:
- Lab Work: Object Oriented Programming (20 Marks)
- Demonstrates: Classes, Objects, Variables, Constants, Methods, Loops, Selection Statements

## 📄 License

This is educational coursework. Use responsibly and in accordance with your institution's academic integrity policies.

## 🤝 Acknowledgments

Developed as part of university OOP coursework demonstrating comprehensive understanding of object-oriented programming principles and Java best practices.

---

**Note**: If you're reviewing this code for academic purposes, ensure you fully understand every component. Be prepared to explain design decisions, modify functionality, and answer questions about implementation choices.
