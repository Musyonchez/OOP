# PAWET-B Organizational Design System

**Pan-African Wildlife, Environment, and Tourism Bureau**
Employee Management System with SQL Database Integration

---

## 📋 Project Overview

This is Part 2 of the PAWET-B digital development project - focusing on **Organizational Design**. The system demonstrates all core OOP principles using Java, with real SQL database integration (SQLite), search algorithms, and sort algorithms.

### Features

- **Employee Management** - Manage Managers, Scientists, and Technicians
- **Department Management** - Track departments, budgets, and regions
- **Project Management** - Oversee research and conservation projects
- **Equipment Management** - Assign equipment to technicians
- **Search Operations** - Linear and Binary search algorithms
- **Sort Operations** - Bubble Sort and Quick Sort algorithms
- **Real SQL Database** - SQLite for persistent data storage
- **Interactive Console** - User-friendly menu system

---

## 🏗️ System Architecture

### Class Structure (11 Java Classes)

```
pawet/
├── src/
│   ├── Main.java                     # Entry point with menu system
│   ├── models/                       # Domain entities
│   │   ├── Employee.java            # Abstract base class
│   │   ├── Manager.java             # Extends Employee
│   │   ├── Scientist.java           # Extends Employee
│   │   ├── Technician.java          # Extends Employee
│   │   ├── Department.java
│   │   ├── Project.java
│   │   └── Equipment.java
│   ├── database/
│   │   └── DatabaseManager.java     # SQL database operations
│   ├── utils/
│   │   ├── SearchAlgorithms.java    # Linear & Binary search
│   │   └── SortAlgorithms.java      # Bubble & Quick sort
│   └── managers/
│       └── Organization.java         # Central coordinator
└── data/
    └── pawet.db                      # SQLite database (auto-created)
```

### OOP Principles Demonstrated

- ✅ **Abstraction** - Employee abstract class with abstract methods
- ✅ **Encapsulation** - Private fields with public getters/setters
- ✅ **Inheritance** - Manager, Scientist, Technician extend Employee
- ✅ **Polymorphism** - displayInfo() method overridden in each subclass

### Algorithms

- **Linear Search** - O(n) complexity, flexible partial matching
- **Binary Search** - O(log n) complexity, fast exact matching
- **Bubble Sort** - O(n²) complexity, educational implementation
- **Quick Sort** - O(n log n) complexity, production-grade sorting

---

## 🚀 Quick Start

### Prerequisites

1. **Java Development Kit (JDK)** - Version 8 or higher
   - Check: `java -version`
   - Download: https://www.oracle.com/java/technologies/downloads/

2. **SQLite JDBC Driver** - Required for database operations
   - Download: https://github.com/xerial/sqlite-jdbc/releases
   - Get: `sqlite-jdbc-3.45.0.0.jar` (or latest version)
   - Place in project root or reference in classpath

### Installation Steps

#### Option 1: Using Terminal (Linux/Mac)

```bash
# 1. Navigate to project directory
cd /home/musyonchez/Code/OOP/pawet

# 2. Download SQLite JDBC driver and SLF4J logging (if not already present)
wget https://github.com/xerial/sqlite-jdbc/releases/download/3.45.0.0/sqlite-jdbc-3.45.0.0.jar
wget https://repo1.maven.org/maven2/org/slf4j/slf4j-api/1.7.36/slf4j-api-1.7.36.jar
wget https://repo1.maven.org/maven2/org/slf4j/slf4j-simple/1.7.36/slf4j-simple-1.7.36.jar

# 3. Compile all Java files (including SeedData)
javac -d bin -cp ".:sqlite-jdbc-3.45.0.0.jar" src/models/*.java src/database/*.java src/utils/*.java src/managers/*.java src/Main.java src/SeedData.java

# 4. (Optional) Seed the database with sample data
java --enable-native-access=ALL-UNNAMED -cp "bin:sqlite-jdbc-3.45.0.0.jar:slf4j-api-1.7.36.jar:slf4j-simple-1.7.36.jar" SeedData
# This populates: 22 employees, 5 departments, 8 projects, 12 equipment items

# 5. Run the program
java --enable-native-access=ALL-UNNAMED -cp "bin:sqlite-jdbc-3.45.0.0.jar:slf4j-api-1.7.36.jar:slf4j-simple-1.7.36.jar" Main

# OR use the convenient run script:
./run.sh
```

#### Option 2: Using IDE (IntelliJ IDEA / Eclipse)

1. Open project in IDE
2. Download `sqlite-jdbc-3.45.0.0.jar`
3. Add JAR to project libraries:
   - **IntelliJ**: File → Project Structure → Libraries → + → Java → Select JAR
   - **Eclipse**: Right-click project → Build Path → Add External Archives → Select JAR
4. Run `Main.java`

#### Option 3: Using Visual Studio Code

1. Install "Extension Pack for Java"
2. Download SQLite JDBC JAR
3. Create `.vscode/settings.json`:
```json
{
    "java.project.referencedLibraries": [
        "lib/**/*.jar",
        "sqlite-jdbc-3.45.0.0.jar"
    ]
}
```
4. Press F5 to run

---

## 📖 Usage Guide

### First Run

On first startup, the system will:
1. Create `data/pawet.db` database file
2. Initialize 4 SQL tables (employees, departments, projects, equipment)
3. Display the main menu

### Main Menu Options

```
════════════════════════════════════════════════════════
                    MAIN MENU
════════════════════════════════════════════════════════
1. Employee Management     - Add/remove/view employees
2. Department Management   - Manage departments
3. Project Management      - Manage projects
4. Equipment Management    - Manage equipment
5. Search Operations       - Find employees using various criteria
6. Sort Operations         - Sort employees, compare algorithms
7. Reports & Statistics    - View system statistics
0. Exit System
════════════════════════════════════════════════════════
```

### Database Seeding (Recommended for Testing)

To quickly populate the database with realistic sample data, run:

```bash
java --enable-native-access=ALL-UNNAMED -cp "bin:sqlite-jdbc-3.45.0.0.jar:slf4j-api-1.7.36.jar:slf4j-simple-1.7.36.jar" SeedData
```

**This will create:**
- 5 Departments (Marine Conservation, Wildlife Research, Forestry Management, Tourism Development, Data Analytics)
- 22 Employees:
  - 5 Managers (Alice Mbeki, David Osei, Fatima Hassan, Joseph Ndlovu, Sarah Kamau)
  - 10 Scientists (various specializations)
  - 7 Technicians (various certifications)
- 8 Active Projects (Coral Reef Restoration, Lion Population Study, etc.)
- 12 Equipment Items (Research vessels, drones, cameras, etc.)

**Benefits of seeding:**
- Instant demonstration-ready system
- Test search algorithms with 22 employees
- Compare sort algorithms with real data
- Perfect for taking screenshots for assignment submission

### Sample Workflow

**Option A: Using Seeded Data (Fast)**
1. Run SeedData (as shown above)
2. Launch Main program
3. Explore pre-populated data in all menus
4. Test search/sort algorithms immediately

**Option B: Manual Entry (From Scratch)**
1. **Create Departments** (Option 2 → 1)
   - Add: Marine Conservation, Wildlife Research, Forestry Management

2. **Add Employees** (Option 1)
   - Add Managers with budget authority
   - Add Scientists with specializations
   - Add Technicians with certifications

3. **Create Projects** (Option 3 → 1)
   - Assign to departments
   - Assign lead scientists

4. **Add Equipment** (Option 4 → 1)
   - Assign to technicians

5. **Search & Sort** (Options 5 & 6)
   - Test linear vs binary search
   - Compare bubble vs quick sort

6. **View Reports** (Option 7)
   - System statistics
   - Employee summaries

---

## 🗄️ Database Schema

### employees table
```sql
CREATE TABLE employees (
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    email TEXT,
    phone TEXT,
    department_id TEXT,
    role TEXT NOT NULL,
    salary REAL,
    hire_date TEXT,
    role_type TEXT NOT NULL,
    specialization TEXT,         -- for Scientists
    team_list TEXT,              -- for Managers (comma-separated IDs)
    budget_authority REAL,       -- for Managers
    project_list TEXT,           -- for Scientists (comma-separated IDs)
    equipment_list TEXT,         -- for Technicians (comma-separated IDs)
    certifications TEXT          -- for Technicians
);
```

### departments table
```sql
CREATE TABLE departments (
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    region TEXT,
    budget REAL,
    manager_id TEXT,
    employee_count INTEGER DEFAULT 0
);
```

### projects table
```sql
CREATE TABLE projects (
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    description TEXT,
    department_id TEXT,
    lead_scientist_id TEXT,
    budget REAL,
    start_date TEXT,
    end_date TEXT,
    status TEXT                   -- ACTIVE, COMPLETED, SUSPENDED
);
```

### equipment table
```sql
CREATE TABLE equipment (
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    type TEXT,
    serial_number TEXT,
    department_id TEXT,
    assigned_to_id TEXT,
    purchase_date TEXT,
    status TEXT                   -- AVAILABLE, IN_USE, MAINTENANCE, RETIRED
);
```

---

## 🧪 Testing the System

### Test Scenario 1: Employee Management

```
1. Add Manager:
   ID: M001, Name: Alice Mbeki
   Department: D001 (Marine Conservation)
   Salary: $85,000, Budget Authority: $500,000

2. Add Scientist:
   ID: S001, Name: Kofi Annan
   Department: D001
   Salary: $70,000, Specialization: Marine Biology

3. Add Technician:
   ID: T001, Name: Amara Okafor
   Department: D001
   Salary: $45,000, Certifications: Diving, Boat Operation
```

### Test Scenario 2: Search Algorithms

```
1. Linear Search:
   Search by ID: M001 (immediate result)

2. Binary Search:
   Search by ID: M001 (sorts list first, then searches)
   Compare: Shows efficiency gain for large datasets

3. Name Search:
   Search: "Alice" (partial match, finds Alice Mbeki)
```

### Test Scenario 3: Sort Algorithms

```
1. Bubble Sort by Name:
   Shows comparison count and swap count

2. Quick Sort by Name:
   Shows milliseconds taken

3. Compare Algorithms:
   Runs both and displays performance comparison
   Quick Sort typically 50x+ faster
```

---

## 📊 Algorithm Complexity Analysis

### Search Algorithms

| Algorithm | Time Complexity | Best For |
|-----------|----------------|----------|
| Linear Search | O(n) | Small datasets, partial matching |
| Binary Search | O(log n) | Large sorted datasets, exact matching |

**Performance Example (1,000,000 items):**
- Linear Search: 1,000,000 comparisons
- Binary Search: 20 comparisons
- **Speed Improvement: 50,000x faster**

### Sort Algorithms

| Algorithm | Time Complexity | Space Complexity | Best For |
|-----------|----------------|------------------|----------|
| Bubble Sort | O(n²) | O(1) | Small datasets, education |
| Quick Sort | O(n log n) avg | O(log n) | Large datasets, production |

**Performance Example (1,000 items):**
- Bubble Sort: ~500,000 comparisons
- Quick Sort: ~10,000 comparisons
- **Speed Improvement: 50x faster**

---

## 🐛 Troubleshooting

### Error: `ClassNotFoundException: org.sqlite.JDBC`

**Solution:** SQLite JDBC driver not found
```bash
# Download the driver
wget https://github.com/xerial/sqlite-jdbc/releases/download/3.45.0.0/sqlite-jdbc-3.45.0.0.jar

# Include in classpath when running
java -cp "bin:sqlite-jdbc-3.45.0.0.jar" Main
```

### Error: Database file not found

**Solution:** The `data/` directory doesn't exist
```bash
mkdir -p data
# Program will auto-create pawet.db on next run
```

### Error: `SQLException: database is locked`

**Solution:** Another process is using the database
```bash
# Close all instances of the program
# Or delete and recreate the database
rm data/pawet.db
```

---

## 📚 Documentation

- **PROJECT_VISION.md** - Complete project vision and conceptual framework
- **IMPLEMENTATION_PLAN.md** - Detailed implementation specifications
- **docs/flowcharts/** - Mermaid flowcharts (8 diagrams):
  - Main program flow, Borrow media algorithm
  - Linear search, Binary search, Quick sort algorithms
  - Employee inheritance hierarchy (UML class diagram)
  - System architecture (Facade pattern)
  - Database architecture (DAO pattern)
- **docs/pseudocode/** - Algorithm pseudocode (3 files)
- **docs/PROJECT_REPORT.txt** - Comprehensive project report
- **docs/SCREENSHOT_GUIDE.txt** - Screenshot requirements for submission

---

## 🎓 Academic Context

This project demonstrates:

- **OOP Principles** - All four core principles (Abstraction, Encapsulation, Inheritance, Polymorphism)
- **Data Structures** - Lists, collections, object relationships
- **Algorithms** - Search (linear, binary) and Sort (bubble, quick) with complexity analysis
- **Database Integration** - SQL CRUD operations with JDBC
- **Software Design** - Manager/Facade pattern, separation of concerns
- **Professional Practices** - Error handling, input validation, user experience

**Assignment Requirements Met:**
- ✅ Abstraction (Employee abstract class)
- ✅ Encapsulation (private fields, public methods)
- ✅ Polymorphism (method overriding)
- ✅ Inheritance (employee hierarchy)
- ✅ Algorithms (search and sort)
- ✅ Database access (SQLite integration)

---

## 👥 Team Information

- **Course**: Object-Oriented Programming
- **Assignment**: Part 2 - Organizational Design
- **Development**: Individual implementation (team names added at submission)
- **Timeline**: November 2024

---

## 📄 License

Educational project for OOP course assignment.

---

## 🔗 Related Projects

- **Part 1**: Personnel Management System
- **Part 2**: Organizational Design (this project)
- **Part 3**: [Future development]

---

**For questions or issues, refer to the comprehensive documentation in the `docs/` directory.**
