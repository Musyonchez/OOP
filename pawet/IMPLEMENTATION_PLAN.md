# PAWET-B Organizational Design - Implementation Plan

## Project Overview
Building an Employee Management System for the Pan-African Wildlife, Environment, and Tourism Bureau (PAWET-B) demonstrating OOP principles with real SQL database integration.

## System Architecture

### Core Classes (11 Java Classes)

#### 1. Model Classes (6 classes)
- **Employee.java** (abstract base class)
  - Fields: id, name, email, phone, department, role, salary, hireDate
  - Abstract method: displayInfo()
  - Methods: getters/setters, toString()

- **Manager.java** (extends Employee)
  - Additional fields: teamList (List<String> employeeIds), budgetAuthority
  - Override: displayInfo()
  - Methods: addTeamMember(), removeTeamMember(), getTeamSize()

- **Scientist.java** (extends Employee)
  - Additional fields: specialization, projectList (List<String> projectIds)
  - Override: displayInfo()
  - Methods: addProject(), removeProject(), getProjectCount()

- **Technician.java** (extends Employee)
  - Additional fields: equipmentHandled (List<String> equipmentIds), certifications
  - Override: displayInfo()
  - Methods: addEquipment(), removeEquipment(), getEquipmentCount()

- **Department.java**
  - Fields: id, name, region, budget, managerId, employeeCount
  - Methods: displayInfo(), toString()

- **Project.java**
  - Fields: id, name, description, departmentId, leadScientistId, budget, startDate, endDate, status
  - Methods: displayInfo(), isActive(), getDuration()

- **Equipment.java**
  - Fields: id, name, type, serialNumber, departmentId, assignedToId, purchaseDate, status
  - Methods: displayInfo(), isAvailable()

#### 2. Database Layer (1 class)
- **DatabaseManager.java**
  - SQLite connection management
  - CRUD operations for all entities
  - Table creation (employees, departments, projects, equipment)
  - Prepared statements for security
  - Methods: loadEmployees(), saveEmployee(), updateEmployee(), deleteEmployee(), etc.

#### 3. Utility Layer (2 classes)
- **SearchAlgorithms.java**
  - linearSearchById() - O(n)
  - linearSearchByName() - O(n) with partial matching
  - binarySearchById() - O(log n) requires sorted data
  - searchByDepartment()
  - searchBySalaryRange()

- **SortAlgorithms.java**
  - bubbleSortByName() - O(n²)
  - quickSortByName() - O(n log n)
  - sortBySalary()
  - sortByHireDate()

#### 4. Manager Layer (1 class)
- **Organization.java**
  - Manages all collections (employees, departments, projects, equipment)
  - Coordinates database operations
  - Business logic for hiring, firing, promotions
  - Delegates to search/sort utilities
  - Report generation

#### 5. Presentation Layer (1 class)
- **Main.java**
  - Interactive console menu
  - User input validation
  - Menu navigation
  - Error handling

## Database Schema (SQLite)

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
    specialization TEXT,      -- for Scientists
    team_list TEXT,           -- for Managers (JSON array)
    budget_authority REAL,    -- for Managers
    project_list TEXT,        -- for Scientists (JSON array)
    equipment_list TEXT,      -- for Technicians (JSON array)
    certifications TEXT,      -- for Technicians
    FOREIGN KEY (department_id) REFERENCES departments(id)
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
    employee_count INTEGER DEFAULT 0,
    FOREIGN KEY (manager_id) REFERENCES employees(id)
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
    status TEXT,
    FOREIGN KEY (department_id) REFERENCES departments(id),
    FOREIGN KEY (lead_scientist_id) REFERENCES employees(id)
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
    status TEXT,
    FOREIGN KEY (department_id) REFERENCES departments(id),
    FOREIGN KEY (assigned_to_id) REFERENCES employees(id)
);
```

## Sample Data Plan

### Departments (5)
- D001: Marine Conservation - Coastal Region
- D002: Wildlife Research - Savannah Region
- D003: Forestry Management - Forest Region
- D004: Tourism Development - Central HQ
- D005: Data Analytics - Central HQ

### Employees (25)
- 8 Managers (one per major department + assistants)
- 10 Scientists (distributed across research departments)
- 7 Technicians (field equipment specialists)

### Projects (10)
- Coral reef restoration
- Elephant migration tracking
- Forest carbon sequestration study
- Sustainable tourism impact assessment
- etc.

### Equipment (15)
- GPS trackers
- Camera traps
- Water quality sensors
- Drones
- Field vehicles
- etc.

## Algorithms

### Search Algorithms
1. **Linear Search**
   - Use: Flexible searching with partial name matching
   - Complexity: O(n)
   - Best for: Small datasets, substring searches

2. **Binary Search**
   - Use: Fast exact ID lookup on sorted data
   - Complexity: O(log n)
   - Best for: Large datasets, exact matches

### Sort Algorithms
1. **Bubble Sort**
   - Use: Educational demonstration
   - Complexity: O(n²)
   - Best for: Small datasets, teaching

2. **Quick Sort**
   - Use: Production sorting
   - Complexity: O(n log n) average
   - Best for: Large datasets, real-world use

## Features

### Employee Management
- Add new employees (Manager/Scientist/Technician)
- Search employees (by ID, name, department)
- Update employee information
- Remove employees
- Sort employees (by name, salary, hire date)

### Department Management
- Create departments
- Assign managers
- View department roster
- Track department budgets

### Project Management
- Create projects
- Assign lead scientists
- Track project status
- View active projects

### Equipment Management
- Add equipment
- Assign to technicians
- Track equipment status
- View available equipment

### Reporting
- Employee statistics by department
- Salary analysis
- Project overview
- Equipment inventory

## Documentation Deliverables

### Mermaid Flowcharts (5)
1. Main program flow (startup → menu → operations → exit)
2. Add employee algorithm (polymorphism in action)
3. Linear search algorithm
4. Binary search algorithm
5. Quick sort algorithm

### Pseudocode Files (3)
1. Linear and Binary search
2. Bubble and Quick sort
3. Database CRUD operations

### Project Report
- Introduction and vision
- OOP principles demonstration
- Class diagrams and relationships
- Algorithm analysis with complexity
- Database design and SQL integration
- System features and capabilities
- Screenshots (30+)
- Challenges and solutions
- References (APA format)

### Screenshot Guide
- System startup and database initialization
- Menu navigation
- Adding each employee type (Manager, Scientist, Technician)
- Search demonstrations
- Sort demonstrations
- Department management
- Project management
- Equipment management
- Reports and statistics
- Error handling
- Database persistence

## File Structure

```
pawet/
├── src/
│   ├── Main.java
│   ├── models/
│   │   ├── Employee.java
│   │   ├── Manager.java
│   │   ├── Scientist.java
│   │   ├── Technician.java
│   │   ├── Department.java
│   │   ├── Project.java
│   │   └── Equipment.java
│   ├── database/
│   │   └── DatabaseManager.java
│   ├── utils/
│   │   ├── SearchAlgorithms.java
│   │   └── SortAlgorithms.java
│   └── managers/
│       └── Organization.java
├── data/
│   └── pawet.db (SQLite database)
├── docs/
│   ├── flowcharts/
│   │   ├── 1_main_program_flow.mmd
│   │   ├── 2_add_employee_algorithm.mmd
│   │   ├── 3_linear_search.mmd
│   │   ├── 4_binary_search.mmd
│   │   └── 5_quick_sort.mmd
│   ├── pseudocode/
│   │   ├── 1_search_algorithms.txt
│   │   ├── 2_sort_algorithms.txt
│   │   └── 3_database_operations.txt
│   ├── PROJECT_REPORT.txt
│   └── SCREENSHOT_GUIDE.txt
├── PROJECT_VISION.md
├── IMPLEMENTATION_PLAN.md
├── README.md
└── QUICK_START.md
```

## Development Timeline

1. ✅ Project structure and planning
2. Model classes implementation (1-2 hours)
3. Database layer with SQLite (1-2 hours)
4. Utility classes (search/sort) (1 hour)
5. Organization manager class (1 hour)
6. Main menu and user interface (1 hour)
7. Sample data and testing (1 hour)
8. Documentation creation (2-3 hours)

**Total Estimated Time:** 8-10 hours

## OOP Principles Demonstration

### Abstraction
- Employee abstract class defines contract for all employee types
- Abstract displayInfo() method implemented differently by each subclass

### Encapsulation
- All fields private with public getters/setters
- Business logic hidden inside methods
- Data validation in setters

### Inheritance
- Manager, Scientist, Technician inherit from Employee
- Code reuse through parent class
- Specialized behavior in child classes

### Polymorphism
- Runtime polymorphism through method overriding
- Different displayInfo() implementations
- Uniform handling through Employee references

## Extra Credit Components

✅ Search algorithms (linear O(n), binary O(log n))
✅ Sort algorithms (bubble O(n²), quick O(n log n))
✅ Database access (SQLite with JDBC)
✅ Comprehensive documentation
✅ Real-world complexity (multiple entity types)

---

**Ready to implement!**
