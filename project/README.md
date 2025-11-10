# Library Management System

> A comprehensive Java application demonstrating Object-Oriented Programming concepts, algorithms, and database operations.

## 📚 Project Overview

The Library Management System is a console-based application built for an Object-Oriented Programming course project. It demonstrates core OOP principles (Abstraction, Encapsulation, Polymorphism, Inheritance) while implementing practical library operations.

**Course**: Object-Oriented Programming
**Language**: Java (JDK 8+)
**Database**: File-based (text files)
**Type**: Group Project (20 marks)
**Status**: ✅ **COMPLETED**

## ✨ Features

### Core Functionality
- **User Management**: Members and Librarians with role-based access
- **Media Catalog**: Books, Magazines, and DVDs with full CRUD operations
- **Borrowing System**: Automated borrowing, returning, and late fee calculation
- **Search Algorithms**: Linear search (O(n)) and Binary search (O(log n))
- **Sort Algorithms**: Quick sort (O(n log n)) for catalog organization
- **Database Access**: File-based persistence with pipe-delimited format
- **Reports**: System statistics, overdue items, member reports

### OOP Concepts Demonstrated
- ✅ **Abstraction**: Abstract base classes (User, MediaItem)
- ✅ **Encapsulation**: Private fields with controlled access
- ✅ **Polymorphism**: Method overriding and overloading
- ✅ **Inheritance**: Clear class hierarchies

## 🏗️ Project Structure

```
project/
├── src/                          # Source code
│   ├── Main.java                 # Entry point
│   ├── models/                   # Domain entities
│   │   ├── User.java            # Abstract base class
│   │   ├── Member.java          # Extends User
│   │   ├── Librarian.java       # Extends User
│   │   ├── MediaItem.java       # Abstract base class
│   │   ├── Book.java            # Extends MediaItem
│   │   ├── Magazine.java        # Extends MediaItem
│   │   ├── DVD.java             # Extends MediaItem
│   │   └── Transaction.java     # Borrowing records
│   ├── managers/                 # Business logic
│   │   └── Library.java         # Main coordinator
│   ├── database/                 # Data persistence
│   │   └── FileManager.java     # File I/O operations
│   └── utils/                    # Algorithms
│       ├── SearchAlgorithms.java
│       └── SortAlgorithms.java
├── data/                         # Database files
│   ├── users.txt                # User records
│   ├── media.txt                # Media catalog
│   ├── transactions.txt         # Transaction history
│   └── README.md                # Data format documentation
├── docs/                         # Documentation
│   ├── flowcharts/              # Mermaid flowcharts (5 PNG files)
│   ├── pseudocode/              # Algorithm pseudocode (3 files)
│   ├── PROJECT_REPORT.txt       # Complete project report
│   ├── SCREENSHOT_CHECKLIST.txt # Guide for screenshots
│   └── REPORT_CONVERSION_GUIDE.txt # PDF conversion guide
├── README.md                     # This file
└── QUICK_START.md               # 5-minute getting started guide
```

## 🚀 Quick Start

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Terminal/Command Prompt

### Installation

```bash
# Navigate to source directory
cd project/src

# Compile all Java files
javac Main.java

# Run the application
java Main
```

### First Run

The system comes with sample data (12 users, 30 media items, 13 transactions).

**Test Credentials**:
- Member (clean account): `M001` (John Smith)
- Member with fines: `M003` (Michael Brown - $15.50)
- Member blocked: `M005` (James Wilson - $25.00)
- Librarian: `L001` (Alice Cooper)

See [QUICK_START.md](QUICK_START.md) for detailed tutorials.

## 📖 Documentation

### Complete Documentation Available

1. **PROJECT_REPORT.txt** - Full academic report (30+ pages)
   - Introduction and problem definition
   - OOP concepts explained with code examples
   - Algorithm pseudocode and complexity analysis
   - System architecture and design patterns
   - Challenges faced and solutions
   - APA format references

2. **Flowcharts** - 5 Mermaid diagrams (PNG format)
   - Main program flow
   - Borrow media algorithm
   - Linear search algorithm
   - Binary search algorithm
   - Quick sort algorithm

3. **Pseudocode** - 3 algorithm implementations
   - Linear search (O(n))
   - Binary search (O(log n))
   - Quick sort (O(n log n))

4. **SCREENSHOT_CHECKLIST.txt** - Guide for 31 screenshots
   - User management
   - Media operations
   - Borrowing/returning
   - Search and sort
   - Error handling

5. **REPORT_CONVERSION_GUIDE.txt** - Convert text report to PDF
   - Microsoft Word method
   - Google Docs method
   - LibreOffice method
   - LaTeX method

6. **QUICK_START.md** - Get running in 5 minutes
   - Installation steps
   - Tutorial walkthroughs
   - Test scenarios
   - Troubleshooting

## 📊 System Statistics

**Code Metrics**:
- **Total Lines**: 2,335 lines
- **Total Classes**: 13
- **Packages**: 4
- **Abstract Classes**: 2
- **Concrete Classes**: 11

**Sample Data**:
- **Users**: 12 (10 members, 2 librarians)
- **Media Items**: 30 (12 books, 8 magazines, 10 DVDs)
- **Transactions**: 13 (10 active, 3 completed)

## 🎯 Key Features Explained

### Search Algorithms

**Linear Search** (O(n))
- Searches by partial title match
- Case-insensitive
- Returns all matching results

**Binary Search** (O(log n))
- Searches by exact ISBN
- Requires sorted data
- 50,000x faster for 1M records

### Sort Algorithm

**Quick Sort** (O(n log n) average)
- Divide-and-conquer approach
- In-place sorting
- Industry-standard algorithm

### Late Fee System

Polymorphic calculation:
- **Books**: $1.00/day
- **Magazines**: $0.50/day
- **DVDs**: $2.00/day

## 👥 Group Members

1. **[Student Name 1]** - [Student ID] - [Contribution]
2. **[Student Name 2]** - [Student ID] - [Contribution]
3. **[Student Name 3]** - [Student ID] - [Contribution]
4. **[Student Name 4]** - [Student ID] - [Contribution]

## ✅ Project Deliverables

### Completed ✅

- [x] Source code (13 Java files, 2,335 lines)
- [x] OOP concepts implementation
- [x] Search algorithms (linear, binary)
- [x] Sort algorithm (quick sort)
- [x] Database access (file-based)
- [x] Flowcharts (5 Mermaid diagrams)
- [x] Pseudocode (3 algorithms)
- [x] Complete project report (text format)
- [x] Sample data files
- [x] Screenshot guide (31 screenshots)
- [x] Documentation (6 comprehensive files)

### To Do (by Student)

- [ ] Take 31 screenshots (follow SCREENSHOT_CHECKLIST.txt)
- [ ] Convert report to PDF (follow REPORT_CONVERSION_GUIDE.txt)
- [ ] Fill in group member names
- [ ] Submit final PDF

## 🎓 Academic Standards Met

**OOP Concepts** (Required):
- ✅ Abstraction - Abstract User and MediaItem classes
- ✅ Encapsulation - Private fields with controlled access
- ✅ Polymorphism - Method overriding (displayInfo, calculateLateFee)
- ✅ Inheritance - User→Member/Librarian, MediaItem→Book/Magazine/DVD

**Algorithms** (Extra Points):
- ✅ Linear Search - O(n) for flexible searching
- ✅ Binary Search - O(log n) for efficient lookups
- ✅ Quick Sort - O(n log n) for sorting

**Database** (Extra Points):
- ✅ File-based persistence
- ✅ CRUD operations
- ✅ Data validation

**Documentation** (Required):
- ✅ Flowcharts with complexity analysis
- ✅ Pseudocode for all algorithms
- ✅ Well-commented source code
- ✅ Challenges section
- ✅ APA format references

## 🚀 Usage Examples

### View User Profile
```
Main Menu → 1 (User Management)
          → 3 (View User Profile)
          → M001
```

### Borrow a Book
```
Main Menu → 1 (User Management)
          → 4 (Login as M001)
          → 5 (Back)
          → 3 (Borrowing Operations)
          → 1 (Borrow Media)
          → M001, B007
```

### Search for Books
```
Main Menu → 4 (Search & Sort)
          → 1 (Linear Search)
          → "Harry"
```

See [QUICK_START.md](QUICK_START.md) for complete tutorials.

## 🔧 Troubleshooting

**Issue**: "User not found" for all users

**Solution**: Ensure you're in `src/` directory when running:
```bash
cd /home/musyonchez/Code/OOP/project/src
java Main
```

**Issue**: Compilation errors

**Solution**: Recompile from scratch:
```bash
rm *.class **/*.class
javac Main.java
```

See [QUICK_START.md](QUICK_START.md) for more troubleshooting.

## 📚 References

- Oracle. (2024). Java SE 8 documentation. https://docs.oracle.com/javase/8/docs/
- Cormen, T. H., et al. (2022). Introduction to algorithms (4th ed.). MIT Press.
- Bloch, J. (2018). Effective Java (3rd ed.). Addison-Wesley.
- Gamma, E., et al. (1994). Design patterns. Addison-Wesley.

Full references in [PROJECT_REPORT.txt](docs/PROJECT_REPORT.txt) - Section 10.

## 📞 Contact

**Course**: Object-Oriented Programming
**Institution**: [University Name]
**Instructor**: [Instructor Name]
**Submission Date**: [Date]

## 🎉 Project Complete!

All code, documentation, and deliverables are ready. Follow these final steps:

1. ✅ Review [PROJECT_REPORT.txt](docs/PROJECT_REPORT.txt)
2. ⏳ Take screenshots using [SCREENSHOT_CHECKLIST.txt](docs/SCREENSHOT_CHECKLIST.txt)
3. ⏳ Convert to PDF using [REPORT_CONVERSION_GUIDE.txt](docs/REPORT_CONVERSION_GUIDE.txt)
4. ⏳ Submit final PDF

**Estimated time to complete**: 3-4 hours (screenshots + PDF conversion)

---

**Version**: 1.0
**Status**: Complete ✅
**Total Development Time**: [Fill in]
**Lines of Code**: 2,335

**Good luck with your submission!** 🚀
