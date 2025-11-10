# PAWET-B Quick Start Guide

**Get up and running in 5 minutes!**

---

## ⚡ Fast Setup

### Step 1: Navigate to Project
```bash
cd /home/musyonchez/Code/OOP/pawet
```

### Step 2: Compile (One-time)
```bash
mkdir -p bin
javac -d bin -cp ".:sqlite-jdbc-3.45.0.0.jar" src/models/*.java src/database/*.java src/utils/*.java src/managers/*.java src/Main.java
```

### Step 3: Run
```bash
java -cp "bin:sqlite-jdbc-3.45.0.0.jar" Main
```

---

## 📝 First-Time Use Tutorial

### 1. Start the Program
```bash
java -cp "bin:sqlite-jdbc-3.45.0.0.jar" Main
```

You'll see:
```
════════════════════════════════════════════════════════
   PAWET-B ORGANIZATIONAL MANAGEMENT SYSTEM
   Pan-African Wildlife, Environment, and Tourism Bureau
════════════════════════════════════════════════════════

Loading data from database...
✓ Database connection established
✓ Database tables initialized
✓ Loaded 0 employees
✓ Loaded 0 departments
✓ Loaded 0 projects
✓ Loaded 0 equipment items
```

### 2. Add Your First Department
```
Main Menu → 2 (Department Management)
→ 1 (Add Department)

Enter:
- Department ID: D001
- Department Name: Marine Conservation
- Region: Coastal Region
- Budget: 1000000
- Manager ID: (press Enter to skip)

✓ Department added successfully: Marine Conservation
```

### 3. Add Your First Manager
```
Department Menu → 0 (Back to Main Menu)
Main Menu → 1 (Employee Management)
→ 1 (Add Manager)

Enter:
- Name: Alice Mbeki
- Email: alice.mbeki@pawet.org
- Phone: +254-700-123456
- Department ID: D001
- Role/Position: Department Head
- Salary: 85000
- Hire Date: 2024-01-15
- Budget Authority: 500000

✓ Manager added successfully: Alice Mbeki
```

### 4. Add a Scientist
```
Employee Menu → 2 (Add Scientist)

Enter:
- Name: Kofi Annan
- Email: kofi.annan@pawet.org
- Phone: +254-700-123457
- Department ID: D001
- Role/Position: Senior Marine Biologist
- Salary: 70000
- Hire Date: 2024-02-01
- Specialization: Coral Reef Ecology

✓ Scientist added successfully: Kofi Annan
```

### 5. Add a Technician
```
Employee Menu → 3 (Add Technician)

Enter:
- Name: Amara Okafor
- Email: amara.okafor@pawet.org
- Phone: +254-700-123458
- Department ID: D001
- Role/Position: Field Technician
- Salary: 45000
- Hire Date: 2024-03-01
- Certifications: Scuba Diving, Boat Operation, First Aid

✓ Technician added successfully: Amara Okafor
```

### 6. View All Employees
```
Employee Menu → 6 (View All Employees)

You'll see:
═══════════════════════════════════════════
ALL EMPLOYEES (3 total)
═══════════════════════════════════════════

M001       | Alice Mbeki               | MANAGER      | D001     | $   85,000.00
S001       | Kofi Annan                | SCIENTIST    | D001     | $   70,000.00
T001       | Amara Okafor              | TECHNICIAN   | D001     | $   45,000.00

═══════════════════════════════════════════
```

### 7. Test Search (Linear)
```
Back to Main → 5 (Search Operations)
→ 3 (Search Employee by Name)

Enter name: Kofi

═══════════════════════════════════════════
SEARCH RESULTS: 1 employee(s) found
═══════════════════════════════════════════

S001 - Kofi Annan (SCIENTIST)
  Department: D001 | Salary: $70,000.00
```

### 8. Test Sort (Quick Sort)
```
Search Menu → 0 (Back)
Main Menu → 6 (Sort Operations)
→ 2 (Sort by Name - Quick Sort)

→ Starting Quick Sort on 3 employees...
→ Quick Sort completed in 0.15 ms

═══════════════════════════════════════════
SORTED EMPLOYEES (Name (Quick Sort))
Total: 3 employees
═══════════════════════════════════════════

 1. M001           | Alice Mbeki               | MANAGER      | $   85,000.00
 2. T001           | Amara Okafor              | TECHNICIAN   | $   45,000.00
 3. S001           | Kofi Annan                | SCIENTIST    | $   70,000.00
```

### 9. View System Statistics
```
Sort Menu → 0 (Back)
Main Menu → 7 (Reports & Statistics)
→ 1 (System Statistics)

════════════════════════════════════════════════════════
PAWET-B SYSTEM STATISTICS
════════════════════════════════════════════════════════

PERSONNEL:
  Total Employees: 3
  - Managers: 1
  - Scientists: 1
  - Technicians: 1
  Total Payroll: $200,000.00
  Average Salary: $66,666.67

ORGANIZATION:
  Total Departments: 1
  Total Budget: $1,000,000.00

PROJECTS:
  Total Projects: 0
  Active Projects: 0

EQUIPMENT:
  Total Equipment: 0
  Available: 0
  In Use: 0
```

### 10. Exit
```
Reports Menu → 0 (Back)
Main Menu → 0 (Exit System)

✓ Database connection closed
✓ System shutdown complete. Thank you for using PAWET-B!
```

---

## 🎯 Complete Sample Data Setup

Want to populate with realistic data? Follow this sequence:

### Departments (5)
```
1. D001 - Marine Conservation - Coastal Region - $1,000,000
2. D002 - Wildlife Research - Savannah Region - $1,500,000
3. D003 - Forestry Management - Forest Region - $1,200,000
4. D004 - Tourism Development - Central HQ - $800,000
5. D005 - Data Analytics - Central HQ - $600,000
```

### Managers (5)
```
1. M001 - Alice Mbeki - D001 - $85,000 - Budget: $500,000
2. M002 - David Osei - D002 - $90,000 - Budget: $600,000
3. M003 - Fatima Hassan - D003 - $88,000 - Budget: $550,000
4. M004 - Joseph Ndlovu - D004 - $82,000 - Budget: $400,000
5. M005 - Sarah Kamau - D005 - $95,000 - Budget: $300,000
```

### Scientists (10)
```
1. S001 - Kofi Annan - D001 - $70,000 - Marine Biology
2. S002 - Thandiwe Moyo - D001 - $68,000 - Coral Ecology
3. S003 - Omar Ali - D002 - $75,000 - Wildlife Behavior
4. S004 - Grace Mwangi - D002 - $72,000 - Ecology
5. S005 - Ibrahim Diop - D002 - $70,000 - Conservation Biology
6. S006 - Amina Yusuf - D003 - $71,000 - Forestry Science
7. S007 - Chinwe Okeke - D003 - $69,000 - Botany
8. S008 - Makena Odhiambo - D004 - $66,000 - Tourism Ecology
9. S009 - Zuri Banda - D005 - $78,000 - Data Science
10. S010 - Kwame Asante - D005 - $76,000 - GIS & Remote Sensing
```

### Technicians (7)
```
1. T001 - Amara Okafor - D001 - $45,000 - Diving, Boat Operation
2. T002 - Lesedi Nkosi - D001 - $43,000 - Water Testing, Navigation
3. T003 - Jabari Mutua - D002 - $46,000 - GPS, Camera Traps
4. T004 - Nia Adeyemi - D002 - $44,000 - Drone Operation, Tracking
5. T005 - Sekou Traore - D003 - $45,000 - Chainsaw, Heavy Equipment
6. T006 - Asha Mohamed - D004 - $42,000 - Photography, Guiding
7. T007 - Tendai Mugabe - D005 - $48,000 - IT, Database Management
```

---

## 🧪 Testing Search & Sort

### Test Linear vs Binary Search
```
Add 20+ employees first, then:

1. Search using Linear Search (Option 5 → 1)
   - Note comparison count

2. Search using Binary Search (Option 5 → 2)
   - System sorts first
   - Note comparison count
   - See efficiency gain message
```

### Test Bubble vs Quick Sort
```
With 20+ employees:

1. Run Bubble Sort (Option 6 → 1)
   - Note comparisons and swaps
   - Note time

2. Run Quick Sort (Option 6 → 2)
   - Note milliseconds

3. Compare Performance (Option 6 → 5)
   - See side-by-side comparison
   - Quick Sort typically 10-50x faster
```

---

## 🔧 Quick Troubleshooting

### Program won't compile?
```bash
# Check if JAR is present
ls -lh sqlite-jdbc-3.45.0.0.jar

# If missing, download:
wget https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.45.0.0/sqlite-jdbc-3.45.0.0.jar
```

### Program won't run?
```bash
# Make sure you're in the pawet directory
pwd
# Should show: /home/musyonchez/Code/OOP/pawet

# Check compiled files exist
ls -l bin/

# Try running again
java -cp "bin:sqlite-jdbc-3.45.0.0.jar" Main
```

### "Invalid choice" errors?
- Enter only numbers (1, 2, 3, etc.)
- Don't add extra characters or spaces

### Date format errors?
- Always use format: YYYY-MM-DD
- Example: 2024-11-10 (correct)
- Not: 11/10/2024 or 10-11-2024 (incorrect)

---

## 💡 Pro Tips

1. **IDs are auto-generated** - The system generates M001, S001, T001 automatically

2. **Use Tab to navigate** - Some terminals support tab completion

3. **Department must exist first** - Create departments before adding employees

4. **Scientist must exist for projects** - Add scientists before creating projects they lead

5. **Equipment must be AVAILABLE** - Only assign equipment with "AVAILABLE" status

6. **Binary search sorts automatically** - No need to sort manually before using binary search

7. **Data persists** - Everything saves to SQLite database automatically

8. **Press 0 to go back** - Every menu has option 0 to return

---

## 📊 Screenshot Checklist for Assignment

To demonstrate all features, capture screenshots of:

1. ✅ System startup (database initialization)
2. ✅ Main menu
3. ✅ Add Manager (showing polymorphism)
4. ✅ Add Scientist (showing polymorphism)
5. ✅ Add Technician (showing polymorphism)
6. ✅ View all employees (3 types displayed)
7. ✅ Employee details (showing inheritance)
8. ✅ Department management
9. ✅ Project management
10. ✅ Equipment management
11. ✅ Linear search with comparison count
12. ✅ Binary search with efficiency message
13. ✅ Bubble sort with statistics
14. ✅ Quick sort with timing
15. ✅ Algorithm comparison (showing Quick Sort wins)
16. ✅ System statistics report
17. ✅ Error handling (try invalid input)
18. ✅ Exit message

---

**Ready to go! Start with Main Menu → 2 (Create a department) then proceed with employees.**
