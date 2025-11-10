# Quick Start Guide - Library Management System

Get up and running in 5 minutes!

## 🎯 Prerequisites

- Java JDK 8 or higher installed
- Terminal/Command Prompt access
- Text editor (optional, for viewing code)

## 📥 Setup (First Time Only)

### Step 1: Verify Java Installation

```bash
java -version
```

Expected output: `java version "1.8.0"` or higher

If not installed:
- **Linux**: `sudo apt install default-jdk`
- **macOS**: `brew install openjdk@8`
- **Windows**: Download from oracle.com/java

### Step 2: Navigate to Project

```bash
cd /home/musyonchez/Code/OOP/project/src
```

### Step 3: Compile the Program

```bash
javac Main.java
```

This compiles all 13 Java files automatically.

**Success**: No output = successful compilation
**Error**: Check that you're in the `src/` directory

## ▶️ Running the Program

### Start the System

```bash
java Main
```

You should see:

```
╔═══════════════════════════════════════════════════╗
║    LIBRARY MANAGEMENT SYSTEM v1.0                 ║
║    Demonstrating OOP Concepts & Algorithms        ║
╚═══════════════════════════════════════════════════╝
```

## 🎮 5-Minute Tutorial

### Tutorial 1: View a Member Profile

```
Main Menu → 1 (User Management)
           → 3 (View User Profile)
           → M001 (Enter user ID)
```

**Expected Output**: Member details with $0.00 fines, Active status

### Tutorial 2: View a Member with Fines

```
User Management → 3 (View User Profile)
                → M003
```

**Expected Output**: Member with $15.50 fines, Suspended status

### Tutorial 3: Login and Borrow a Book

```
User Management → 4 (Login)
                → M001
                → 5 (Back to Main Menu)

Main Menu → 3 (Borrowing Operations)
          → 1 (Borrow Media)
          → M001 (User ID)
          → B007 (Media ID - The Hobbit)
```

**Expected Output**: "Success! Item borrowed. Due date: [date]"

### Tutorial 4: Search for Books

```
Main Menu → 4 (Search & Sort)
          → 1 (Search by Title - Linear)
          → harry
```

**Expected Output**: "Harry Potter and the Sorcerer's Stone"

### Tutorial 5: View System Statistics

```
Main Menu → 5 (Reports & Statistics)
          → 1 (System Statistics)
```

**Expected Output**: Total users, media, transactions

## 🎪 Try These Operations

### ✅ Successful Operations

1. **Login as Clean Member**
   ```
   1 → 4 → M001
   ```

2. **Borrow Available Book**
   ```
   3 → 1 → M002 → B007
   ```

3. **Search by Title**
   ```
   4 → 1 → "the"
   ```

4. **View Librarian Profile**
   ```
   1 → 3 → L001
   ```

### ❌ Error Demonstrations

1. **Blocked by Fines**
   ```
   3 → 1 → M005 → B001
   Result: "Cannot borrow. Pay fines first."
   ```

2. **Already Borrowed Item**
   ```
   3 → 1 → M002 → B003
   Result: "Item already borrowed"
   ```

3. **Librarian Cannot Borrow**
   ```
   3 → 1 → L001 → B001
   Result: "Only members can borrow items"
   ```

4. **Invalid User ID**
   ```
   1 → 3 → XXX999
   Result: "User not found."
   ```

## 📋 Test Credentials Reference

Keep this handy while testing:

| User ID | Name           | Type      | Fines   | Status    |
|---------|----------------|-----------|---------|-----------|
| M001    | John Smith     | Member    | $0.00   | Active    |
| M002    | Sarah Johnson  | Member    | $0.00   | Active    |
| M003    | Michael Brown  | Member    | $15.50  | Suspended |
| M005    | James Wilson   | Member    | $25.00  | Blocked   |
| L001    | Alice Cooper   | Librarian | N/A     | Admin     |

## 🎯 Media IDs for Testing

**Available Books**:
- `B001` - To Kill a Mockingbird
- `B002` - 1984
- `B004` - Pride and Prejudice
- `B007` - The Hobbit
- `B008` - Brave New World

**Borrowed Books** (try to borrow these to see error):
- `B003` - The Great Gatsby (borrowed by M001)
- `B005` - The Catcher in the Rye (borrowed by M002)
- `B006` - Harry Potter (borrowed by M004)

**Magazines**:
- `M002` - Time Magazine (available)
- `M004` - The Economist (available)

**DVDs**:
- `D001` - The Shawshank Redemption (available)
- `D002` - The Godfather (available)
- `D007` - The Matrix (available)

## 🔄 Common Workflows

### Workflow 1: Complete Borrow-Return Cycle

```bash
# 1. Login
1 → 4 → M002

# 2. Borrow a book
5 → 3 → 1 → M002 → B007

# 3. Check your transactions
3 → M002
Note the Transaction ID (e.g., T014)

# 4. Return the book
2 → T014 (or your transaction ID)
```

### Workflow 2: Librarian Adding Media

```bash
# 1. Login as librarian
1 → 4 → L001

# 2. Access media management
5 → 2

# 3. Add a new book
1 → [Enter book details]

# 4. View all media (now includes your book)
4
```

### Workflow 3: Search Comparison

```bash
# Linear Search (substring, unsorted)
4 → 1 → "the"
Result: Multiple matches

# Binary Search (exact, sorted)
4 → 2 → 978-0-439-70818-8
Result: Single exact match
```

## 🛠️ Troubleshooting

### Issue: "User not found" for all users

**Cause**: Data files not loading

**Fix**:
```bash
# Check you're in src/ directory
pwd
# Should show: /home/musyonchez/Code/OOP/project/src

# Check data files exist
ls -l ../data/
# Should show: users.txt, media.txt, transactions.txt
```

### Issue: Compilation errors

**Cause**: Not in correct directory or missing files

**Fix**:
```bash
# Navigate to src
cd /home/musyonchez/Code/OOP/project/src

# Clean and recompile
rm *.class models/*.class managers/*.class database/*.class utils/*.class 2>/dev/null
javac Main.java
```

### Issue: No output when searching

**Cause**: Search term doesn't match any items

**Try**:
```
Search: "the" or "harry" or "1984"
```

### Issue: Cannot borrow any items

**Cause**: User has fines

**Fix**: Use a different user ID (M001, M002, M004, M006, M007, M008, M009, M010)

## 📚 Learn More

### Explore the Code

```bash
# View main class
cat Main.java | less

# View Member class (shows OOP concepts)
cat models/Member.java | less

# View search algorithms
cat utils/SearchAlgorithms.java | less
```

### Read Documentation

```bash
# Full project report
cat ../docs/PROJECT_REPORT.txt | less

# Data format documentation
cat ../data/README.md | less

# Screenshot guide
cat ../docs/SCREENSHOT_CHECKLIST.txt | less
```

### View Flowcharts

```bash
# Navigate to flowcharts
cd ../docs/flowcharts

# List all flowcharts
ls -lh *.png

# View in browser (Linux)
xdg-open 1_main_program_flow.png

# View in browser (macOS)
open 1_main_program_flow.png
```

## 🎓 Next Steps

1. **Take Screenshots** (for report)
   - Follow `docs/SCREENSHOT_CHECKLIST.txt`
   - 31 screenshots needed

2. **Read the Report**
   - `docs/PROJECT_REPORT.txt`
   - Understand OOP concepts
   - Learn algorithm complexity

3. **Modify the Code**
   - Add a new user
   - Add a new book
   - Change late fee rates

4. **Test Edge Cases**
   - Try invalid inputs
   - Test all error messages
   - Verify data persistence

## ⚡ Quick Commands Reference

```bash
# Compile
javac Main.java

# Run
java Main

# Clean compiled files
rm *.class **/*.class

# View data files
cat ../data/users.txt
cat ../data/media.txt
cat ../data/transactions.txt

# Count lines of code
find . -name "*.java" -exec wc -l {} + | tail -1
```

## 🎯 Success Criteria

You've successfully learned the system when you can:

- [ ] Navigate all menus without help
- [ ] Explain what each menu option does
- [ ] Successfully borrow and return items
- [ ] Use both search algorithms
- [ ] Understand why certain operations fail
- [ ] View and interpret system reports
- [ ] Identify which OOP concept each class demonstrates

## 💡 Pro Tips

1. **Use Arrow Keys**: Navigate command history with ↑/↓
2. **Tab Completion**: Use Tab to complete file names
3. **Keep Notes**: Document which user IDs you've used
4. **Test Systematically**: Follow the screenshot checklist order
5. **Save Output**: Use `java Main > output.txt` to save session

## 🚀 You're Ready!

Start exploring:
```bash
cd /home/musyonchez/Code/OOP/project/src
java Main
```

**First Task**: Try logging in as M001 and borrowing book B007!

---

**Need Help?**
- Check `README.md` for detailed information
- Review `docs/PROJECT_REPORT.txt` for concepts
- Follow `docs/SCREENSHOT_CHECKLIST.txt` for screenshots

**Happy Testing!** 🎉
