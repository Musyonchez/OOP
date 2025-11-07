# Library Management System - Group Project

A comprehensive Object-Oriented Programming project demonstrating Abstraction, Encapsulation, Polymorphism, and Inheritance.

## 📁 Project Structure

```
project/
├── README.md                      # This file
├── PROJECT_PLANNING.md            # Detailed planning document
├── PROJECT_SUMMARY.md             # Quick reference guide
│
├── src/                           # Source code
│   ├── models/                    # OOP classes
│   │   ├── User.java              # Abstract user class
│   │   ├── Member.java            # Member extends User
│   │   ├── Librarian.java         # Librarian extends User
│   │   ├── MediaItem.java         # Abstract media class
│   │   ├── Book.java              # Book extends MediaItem
│   │   ├── Magazine.java          # Magazine extends MediaItem
│   │   ├── DVD.java               # DVD extends MediaItem
│   │   └── Transaction.java       # Transaction data class
│   │
│   ├── managers/                  # Management classes
│   │   └── Library.java           # Library manager (main logic)
│   │
│   ├── database/                  # Database/file handling
│   │   ├── DatabaseManager.java  # Database operations
│   │   └── FileManager.java      # Text file operations
│   │
│   ├── utils/                     # Utility classes
│   │   ├── SearchAlgorithms.java # Search implementations
│   │   ├── SortAlgorithms.java   # Sort implementations
│   │   └── InputValidator.java   # Input validation
│   │
│   └── Main.java                  # Program entry point
│
├── docs/                          # Documentation
│   ├── flowcharts/                # Algorithm flowcharts
│   ├── pseudocode/                # Algorithm pseudocode
│   ├── screenshots/               # Input/output screenshots
│   ├── REPORT.md                  # Final report draft
│   └── references.txt             # APA citations
│
└── data/                          # Data storage
    ├── users.txt                  # User data
    ├── media.txt                  # Media catalog
    └── transactions.txt           # Transaction history
```

## 🎯 Project Goals

### Required OOP Concepts
- [x] **Abstraction**: Abstract User and MediaItem classes
- [x] **Encapsulation**: Private fields with public getters/setters
- [x] **Polymorphism**: Method overriding for different types
- [x] **Inheritance**: Clear class hierarchy

### Extra Points
- [x] **Search Algorithms**: Linear and Binary search
- [x] **Sort Algorithms**: Bubble sort and Quick sort
- [x] **Database Access**: Text file or SQL storage

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Text editor or IDE (VS Code, IntelliJ, Eclipse)
- Git for version control

### Setup
```bash
cd project/src
javac Main.java
java Main
```

## 👥 Group Members

1. [Name] - Database & Data Management
2. [Name] - Core OOP Classes
3. [Name] - Algorithms (Search & Sort)
4. [Name] - UI & Integration

## 📋 Task Checklist

### Week 1: Foundation (Planning & Setup)
- [ ] Finalize group members
- [ ] Review planning documents
- [ ] Set up project structure
- [ ] Create abstract classes
- [ ] Define interfaces

### Week 2: Core Implementation
- [ ] Implement User classes (Member, Librarian)
- [ ] Implement MediaItem classes (Book, Magazine, DVD)
- [ ] Implement Library manager
- [ ] Set up database/file operations
- [ ] Basic CRUD operations

### Week 3: Features & Algorithms
- [ ] Implement search algorithms
- [ ] Implement sort algorithms
- [ ] Add borrow/return functionality
- [ ] Calculate late fees
- [ ] Error handling

### Week 4: Documentation & Testing
- [ ] Draw flowcharts
- [ ] Write pseudocode
- [ ] Take screenshots
- [ ] Write comprehensive report
- [ ] Test all features
- [ ] Format as PDF

## 📊 Features

### Core Features
1. **User Management**
   - Register new members
   - Librarian login
   - View user profiles

2. **Media Catalog**
   - Add books, magazines, DVDs
   - View all media items
   - Update media details
   - Remove media items

3. **Borrowing System**
   - Borrow media (14-day period)
   - Return media
   - Calculate late fees
   - View borrowed items

4. **Search & Sort**
   - Search by title, author, ISBN
   - Sort by title, author, date
   - Filter available items

5. **Reports**
   - Most borrowed items
   - Overdue items
   - User borrowing history

## 🔍 Algorithms Implemented

### Search Algorithms
- **Linear Search**: O(n) - Search by title/author
- **Binary Search**: O(log n) - Search by ISBN (sorted data)

### Sort Algorithms
- **Bubble Sort**: O(n²) - Simple sorting for educational value
- **Quick Sort**: O(n log n) - Efficient sorting algorithm

## 💾 Database Schema

### users.txt
```
ID|Name|Email|Phone|Type|MembershipDate|Fines
```

### media.txt
```
ID|Title|Type|Available|Author|ISBN|IssueNumber|Director
```

### transactions.txt
```
ID|UserID|MediaID|BorrowDate|DueDate|ReturnDate
```

## 📝 Documentation Requirements

### Deliverables
1. ✅ Algorithm flowcharts (Main flow, Borrow, Search)
2. ✅ Pseudocode (Key algorithms)
3. ✅ Source code with comments
4. ✅ Input/Output screenshots
5. ✅ Challenges and solutions
6. ✅ APA format references
7. ✅ Final report as PDF

### Report Structure
1. Cover page (all group members)
2. Introduction (problem definition & solution)
3. Algorithm explanation with flowcharts/pseudocode
4. Source code documentation
5. Challenges faced and solutions
6. References (APA citations)
7. Appendix (screenshots)

## ⚠️ Common Challenges & Solutions

### Challenge 1: Database Connection
**Solution**: Start with text files, upgrade to SQL if time permits

### Challenge 2: Date Handling
**Solution**: Use java.time.LocalDate for modern date operations

### Challenge 3: Polymorphism Demonstration
**Solution**: Override methods in child classes with different implementations

### Challenge 4: Search Performance
**Solution**: Implement both linear and binary search, compare performance

## 📚 References

- Eckel, B. (2006). *Thinking in Java* (4th ed.). Prentice Hall.
- Oracle. (2024). *Java documentation*. https://docs.oracle.com/en/java/
- Gamma, E., et al. (1994). *Design patterns*. Addison-Wesley.

## 🎓 Academic Integrity

This is a group project. All members should:
- Understand every line of code
- Be able to explain design decisions
- Contribute meaningfully to the implementation
- Properly cite all external resources

## 📞 Next Steps

1. Read PROJECT_PLANNING.md for detailed requirements
2. Review PROJECT_SUMMARY.md for quick reference
3. Assign roles to group members
4. Start with abstract classes
5. Implement one feature at a time
6. Test frequently
7. Document as you go

---

**Status**: Planning Phase Complete ✅
**Next**: Begin Implementation
