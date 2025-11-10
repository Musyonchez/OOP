# Object-Oriented Programming - Complete Course Work

> Comprehensive Java projects demonstrating OOP concepts, algorithms, and GUI development for university coursework.

## 📚 Overview

This repository contains three complete Java projects for Object-Oriented Programming course:

1. **Lab Work** - Student Grade Management System (Console)
2. **Project** - Library Management System (Advanced OOP + Algorithms)
3. **Assignment** - Student Management System (Java Swing GUI)

**Total Code**: ~4,700 lines across 33 Java classes
**Status**: ✅ **ALL COMPLETE**

## 📁 Project Structure

```
OOP/
├── README.md                    # This file - Course overview
├── CLAUDE.md                    # AI collaboration documentation
├── .gitignore                   # Git ignore rules
│
├── labwork/                     # Assignment 1: Lab Work (20 marks)
│   ├── Person.java              # Abstract parent class
│   ├── Student.java             # Student (extends Person)
│   ├── Grade.java               # Grade management
│   ├── GradeManager.java        # Student collection
│   ├── InvalidGradeException.java # Custom exception
│   ├── Main.java                # Entry point
│   ├── LAB_REPORT.txt           # Report template
│   ├── SCREENSHOT_GUIDE.txt     # Screenshot guide
│   └── CLAUDE.md                # Transparency document
│
├── project/                     # Assignment 2: Group Project (20 marks)
│   ├── src/                     # Source code (13 classes)
│   │   ├── models/              # Domain entities
│   │   ├── managers/            # Business logic
│   │   ├── database/            # File I/O
│   │   └── utils/               # Algorithms
│   ├── data/                    # Database files
│   ├── docs/                    # Documentation
│   │   ├── flowcharts/          # 5 Mermaid diagrams
│   │   ├── pseudocode/          # Algorithm pseudocode
│   │   ├── PROJECT_REPORT.txt   # Complete report
│   │   └── SCREENSHOT_CHECKLIST.txt
│   ├── README.md                # Project documentation
│   └── QUICK_START.md           # Getting started guide
│
└── assignment/                  # Assignment 3: GUI Application (10 marks)
    ├── src/                     # Source code (7 classes)
    │   ├── models/              # Data models
    │   ├── gui/                 # Swing components
    │   └── utils/               # File operations
    ├── data/                    # Data files
    ├── docs/                    # Documentation
    │   ├── ASSIGNMENT_REPORT.txt # Report template
    │   └── SCREENSHOT_GUIDE.txt  # Screenshot guide
    └── README.md                # Assignment documentation
```

## 🎯 Assignments Summary

### 1️⃣ Lab Work - Student Grade Management (20 marks)

**Type**: Individual | **Format**: Console Application
**Status**: ✅ Complete

**Features**:
- Student and grade management
- Statistical analysis
- Exception handling
- Comprehensive OOP demonstrations

**OOP Concepts**:
- ✅ Classes & Objects
- ✅ Variables & Constants
- ✅ Methods & Functions
- ✅ Inheritance (Person → Student)
- ✅ Polymorphism (Method overriding/overloading)
- ✅ Encapsulation (Private fields)
- ✅ Exception Handling (Custom exception)
- ✅ Loops (for, while, do-while, for-each)
- ✅ Selection (if-else, switch)

**Code Stats**: 441 lines | 6 classes

**Quick Start**:
```bash
cd labwork
javac *.java
java Main
```

---

### 2️⃣ Project - Library Management System (20 marks)

**Type**: Group | **Format**: Console Application + Advanced Features
**Status**: ✅ Complete

**Features**:
- User management (Members, Librarians)
- Media catalog (Books, Magazines, DVDs)
- Borrowing system with late fees
- Search algorithms (Linear O(n), Binary O(log n))
- Sort algorithms (Quick Sort O(n log n))
- File-based database
- Statistics and reports

**OOP Concepts**:
- ✅ Abstraction (Abstract classes)
- ✅ Encapsulation (Data hiding)
- ✅ Polymorphism (Method overriding)
- ✅ Inheritance (Class hierarchies)

**Algorithms**:
- ✅ Linear Search - O(n)
- ✅ Binary Search - O(log n)
- ✅ Quick Sort - O(n log n)

**Documentation**:
- ✅ 5 Flowcharts (Mermaid diagrams)
- ✅ Pseudocode for algorithms
- ✅ Complete project report (30+ pages)
- ✅ Screenshot checklist (31 screenshots)

**Code Stats**: 2,335 lines | 13 classes

**Quick Start**:
```bash
cd project/src
javac Main.java
java Main
```

---

### 3️⃣ Assignment - Student Management GUI (10 marks)

**Type**: Group | **Format**: Java Swing GUI Application
**Status**: ✅ Complete

**Features**:
- Student management (Add/Edit/Delete/Search)
- Grade management with dropdowns
- Statistics dashboard
- Menu system with shortcuts
- File persistence
- Tabbed interface

**Swing Components** (15+):
- ✅ JFrame, JPanel, JTabbedPane
- ✅ JTable, JButton, JTextField
- ✅ JComboBox, JLabel, JTextArea
- ✅ JMenuBar, JMenu, JMenuItem
- ✅ JDialog, JOptionPane
- ✅ JScrollPane
- ✅ Layout Managers (Border, Grid, Flow)

**Code Stats**: ~1,200 lines | 7 classes

**Quick Start**:
```bash
cd assignment/src
javac gui/*.java models/*.java utils/*.java
java gui.MainFrame
```

## 📊 Overall Statistics

| Metric | Lab Work | Project | Assignment | **Total** |
|--------|----------|---------|------------|-----------|
| **Lines of Code** | 441 | 2,335 | 1,200 | **~4,000** |
| **Classes** | 6 | 13 | 7 | **26** |
| **Marks** | 20 | 20 | 10 | **50** |
| **Documentation** | Reports | Reports + Flowcharts + Pseudocode | Reports | **Complete** |
| **Status** | ✅ Done | ✅ Done | ✅ Done | ✅ **ALL DONE** |

## 🎓 Learning Outcomes

### Core OOP Principles
✅ **Classes & Objects** - Demonstrated across all projects
✅ **Encapsulation** - Private fields with public methods
✅ **Inheritance** - Class hierarchies (Person→Student, User→Member/Librarian)
✅ **Polymorphism** - Method overriding and overloading
✅ **Abstraction** - Abstract classes and methods

### Advanced Topics
✅ **Exception Handling** - Custom exceptions and validation
✅ **Data Structures** - ArrayList, collections
✅ **File I/O** - Reading/writing text files
✅ **Algorithms** - Search and sort implementations
✅ **GUI Development** - Swing components and layouts
✅ **Design Patterns** - Manager pattern, MVC-inspired

### Programming Skills
✅ **Control Structures** - Loops (for, while, do-while), conditionals
✅ **Event Handling** - GUI event listeners
✅ **Data Validation** - Input checking and error handling
✅ **Code Organization** - Package structure, modularity
✅ **Documentation** - Comments, reports, guides

## 🚀 Quick Access

### Run Lab Work
```bash
cd labwork && javac *.java && java Main
```

### Run Project
```bash
cd project/src && java Main
```

### Run GUI Assignment
```bash
cd assignment/src && java gui.MainFrame
```

## 📝 Documentation

Each project includes comprehensive documentation:

### Lab Work
- `LAB_REPORT.txt` - Complete lab report template
- `SCREENSHOT_GUIDE.txt` - 38 screenshots guide
- `CLAUDE.md` - AI transparency document

### Project
- `PROJECT_REPORT.txt` - 30+ page detailed report
- `SCREENSHOT_CHECKLIST.txt` - 31 screenshots guide
- `REPORT_CONVERSION_GUIDE.txt` - PDF conversion help
- `QUICK_START.md` - 5-minute tutorial
- 5 Flowcharts (PNG format)
- 3 Pseudocode files

### Assignment
- `ASSIGNMENT_REPORT.txt` - Complete assignment report
- `SCREENSHOT_GUIDE.txt` - 13-15 screenshots guide

## ✅ Submission Checklist

### Lab Work
- [x] Source code (6 Java files)
- [x] Compilation successful
- [x] Program runs correctly
- [ ] Screenshots taken (38 total)
- [ ] Report converted to PDF
- [ ] Submitted

### Project
- [x] Source code (13 Java files)
- [x] OOP concepts demonstrated
- [x] Algorithms implemented
- [x] Flowcharts created (5)
- [x] Pseudocode written (3)
- [x] Sample data provided
- [ ] Screenshots taken (31 total)
- [ ] Report converted to PDF
- [ ] Group members filled in
- [ ] Submitted

### Assignment
- [x] Source code (7 Java files)
- [x] Swing components demonstrated (15+)
- [x] GUI functional
- [x] Sample data provided
- [ ] Screenshots taken (13-15 total)
- [ ] Report converted to PDF
- [ ] Group members filled in
- [ ] Submitted

## 🔧 Prerequisites

- **Java Development Kit (JDK)**: Version 8 or higher
- **Terminal/Command Prompt**: For compilation and execution
- **Text Editor/IDE**: For viewing/editing code (optional)
- **Screenshot Tool**: For documentation

## 🎯 Key Features Comparison

| Feature | Lab Work | Project | Assignment |
|---------|----------|---------|------------|
| **Interface** | Console | Console | GUI (Swing) |
| **Complexity** | Basic | Advanced | Medium |
| **OOP Depth** | Moderate | Deep | Moderate |
| **Algorithms** | None | 3 (Search/Sort) | None |
| **Database** | None | File-based | File-based |
| **Documentation** | Report | Report + Flowcharts | Report |
| **Flowcharts** | None | 5 diagrams | None |
| **GUI Components** | None | None | 15+ components |

## 📚 Technologies Used

- **Language**: Java (JDK 8+)
- **GUI**: Java Swing (Assignment only)
- **Data Storage**: Text files (pipe-delimited format)
- **Diagrams**: Mermaid (for flowcharts)
- **Version Control**: Git

## 👥 Group Members

### Lab Work (Individual)
- [Your Name] - [Student ID]

### Project (Group)
1. [Student Name 1] - [Student ID] - [Contribution]
2. [Student Name 2] - [Student ID] - [Contribution]
3. [Student Name 3] - [Student ID] - [Contribution]
4. [Student Name 4] - [Student ID] - [Contribution]

### Assignment (Group)
1. [Student Name 1] - [Student ID] - [Contribution]
2. [Student Name 2] - [Student ID] - [Contribution]
3. [Student Name 3] - [Student ID] - [Contribution]
4. [Student Name 4] - [Student ID] - [Contribution]

## 🎓 Academic Integrity

All code and documentation are provided for educational purposes.

**Important**:
- See `CLAUDE.md` for AI assistance transparency
- Understand every line of code before submission
- Be prepared to explain design decisions
- Able to modify code on request

## 📞 Course Information

- **Course**: Object-Oriented Programming
- **Institution**: [University Name]
- **Instructor**: [Instructor Name]
- **Academic Year**: 2024/2025
- **Semester**: [Semester]

## 🏆 Achievements

✅ **3/3 Assignments Complete**
✅ **All OOP Concepts Demonstrated**
✅ **Comprehensive Documentation**
✅ **Clean, Well-Organized Code**
✅ **Ready for Submission**

## 📖 Additional Resources

### Java Documentation
- Oracle Java Docs: https://docs.oracle.com/javase/8/docs/
- Java Tutorials: https://docs.oracle.com/javase/tutorial/

### OOP Learning
- "Head First Java" by Kathy Sierra
- "Effective Java" by Joshua Bloch
- "Thinking in Java" by Bruce Eckel

### Algorithms
- "Introduction to Algorithms" by Cormen et al.
- "Algorithms" by Robert Sedgewick

## 🆘 Troubleshooting

### Compilation Errors
```bash
# Clean and recompile
find . -name "*.class" -delete
cd [project-folder]/src
javac Main.java  # Or appropriate main file
```

### Runtime Errors
- Ensure you're in the correct directory
- Check that data files exist (for Project and Assignment)
- Verify Java version: `java -version`

### GUI Not Showing
```bash
# For Assignment - ensure GUI classes compiled
cd assignment/src
javac gui/*.java models/*.java utils/*.java
java gui.MainFrame
```

## 📈 Next Steps

1. ✅ Review all code
2. ⏳ Take required screenshots
3. ⏳ Fill in group member information
4. ⏳ Convert reports to PDF
5. ⏳ Submit assignments

**Estimated time for screenshots + PDF**: 6-8 hours total

---

## 🎉 Final Notes

All three assignments are **code-complete** and **fully functional**.

What remains:
- Taking screenshots for documentation
- Converting text reports to PDF format
- Filling in personal/group information
- Final submission

**You're almost done! Good luck with your submissions!** 🚀

---

**Version**: 1.0
**Last Updated**: November 2024
**Repository Status**: Complete ✅
