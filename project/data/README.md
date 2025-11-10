# Sample Data Files

This directory contains sample data files for testing and demonstration of the Library Management System.

## 📂 Files Included

### 1. `users.txt` - User Database
Contains 12 users (10 members + 2 librarians)

**Format**: `userID|name|email|phone|userType|[type-specific fields]`

**Member Format**: `id|name|email|phone|Member|fines|borrowedItemsList`
**Librarian Format**: `id|name|email|phone|Librarian|employeeID`

**Sample Users**:
- **Members**: M001-M010 (John Smith, Sarah Johnson, Michael Brown, etc.)
- **Librarians**: L001-L002 (Alice Cooper, Bob Williams)

**Test Scenarios**:
- M003 (Michael Brown): Has $15.50 in fines, 2 items borrowed
- M005 (James Wilson): Has $25.00 in fines (cannot borrow)
- M010 (Mary Garcia): Has $5.00 in fines
- Others: Clean accounts with various borrowed items

### 2. `media.txt` - Media Catalog
Contains 42 items (12 books + 8 magazines + 10 DVDs)

**Format**: `id|title|available|mediaType|[type-specific fields]`

**Book Format**: `id|title|available|Book|author|isbn|publisher|year`
**Magazine Format**: `id|title|available|Magazine|issueNumber|publisher|publicationDate`
**DVD Format**: `id|title|available|DVD|director|duration|releaseYear`

**Sample Items**:

**Books (B001-B012)**:
- Classic literature (To Kill a Mockingbird, 1984, Pride and Prejudice)
- Fantasy (Harry Potter, The Hobbit, Lord of the Rings)
- Science fiction (Brave New World)
- Status: Some available, some borrowed

**Magazines (M001-M008)**:
- National Geographic, Time, Scientific American
- The Economist, Wired, Popular Science
- Various issue numbers and dates

**DVDs (D001-D010)**:
- Classic films (Shawshank Redemption, The Godfather)
- Modern blockbusters (Inception, The Dark Knight)
- Animation (The Lion King, Spirited Away)
- Directors: Nolan, Tarantino, Zemeckis, etc.

**Availability**:
- Available (true): 27 items
- Borrowed (false): 15 items

### 3. `transactions.txt` - Transaction History
Contains 15 transaction records

**Format**: `transactionID|userID|mediaID|borrowDate|dueDate|returnDate|lateFee`

**Date Format**: `YYYY-MM-DD`
**returnDate**: Empty if not yet returned
**lateFee**: Amount in dollars (0.0 if no late fee)

**Sample Transactions**:
- T001-T006: Magazine transactions (various statuses)
- T007-T012: Book and DVD transactions (currently borrowed)
- T013-T015: Completed transactions with late fees

**Test Scenarios**:
- **On-time returns**: T001, T004, T006, T014 (no late fees)
- **Late returns**: T002 (1 day late, $0.50), T013 (13 days late, $6.50), T015 (11 days late, $22.00)
- **Currently borrowed**: T003, T005, T007-T012 (empty returnDate)

## 🎯 Data Statistics

| Category | Count | Details |
|----------|-------|---------|
| **Total Users** | 12 | 10 members, 2 librarians |
| **Total Media** | 42 | 12 books, 8 magazines, 10 DVDs |
| **Available** | 27 | ~64% availability rate |
| **Borrowed** | 15 | ~36% currently on loan |
| **Transactions** | 15 | 12 completed, 3 with late fees |
| **Total Fines** | $45.50 | Across 3 members |

## 🧪 Testing Scenarios

### User Management
1. **Login as Member**: M001 (John Smith)
2. **Login as Librarian**: L001 (Alice Cooper)
3. **Member with Fines**: M003 ($15.50), M005 ($25.00), M010 ($5.00)
4. **Member Cannot Borrow**: M005 (fines exceed limit)

### Media Catalog
1. **Search by Title**: "Harry Potter", "1984", "Inception"
2. **Search by Author**: "Orwell", "Tolkien", "Nolan"
3. **Filter by Type**: Books, Magazines, DVDs
4. **Check Availability**: Mix of available and borrowed items

### Borrowing
1. **Successful Borrow**: M001 borrowing available item
2. **Failed Borrow (Fines)**: M005 attempting to borrow
3. **Failed Borrow (Unavailable)**: Trying to borrow B003 (already borrowed)
4. **Failed Borrow (Librarian)**: L001 cannot borrow (not a member)

### Returning
1. **On-time Return**: Any currently borrowed item before due date
2. **Late Return**: Keep item past due date and calculate fees
3. **Update Fines**: See late fee added to member's account

### Search Algorithms
1. **Linear Search**: Search "The" → finds "The Great Gatsby", "The Hobbit", etc.
2. **Binary Search**: Search ISBN "978-0-439-70818-8" → finds Harry Potter

### Sort Algorithms
1. **Bubble Sort**: Display all books sorted by title
2. **Quick Sort**: Display all media sorted alphabetically

## 📋 How to Use

### Option 1: Copy to Database Folder
```bash
# If your FileManager uses /database/ folder
cp data/*.txt database/
```

### Option 2: Update FileManager Paths
Edit `FileManager.java` to point to `/data/` folder:
```java
private static final String DATA_DIR = "data/";
private static final String USERS_FILE = DATA_DIR + "users.txt";
```

### Option 3: Load on First Run
The system can start with empty files and you can:
1. Add users manually through the menu
2. Add media items through librarian options
3. Create transactions by borrowing items

**Recommended**: Use these pre-populated files to save time and have realistic test data immediately.

## 🔍 Data Validation

All data in these files follows the correct format and includes:
- ✅ Valid user IDs (M001-M010, L001-L002)
- ✅ Valid media IDs (B001-B012, M001-M008, D001-D010)
- ✅ Valid transaction IDs (T001-T015)
- ✅ Realistic dates and names
- ✅ Proper pipe-delimited format
- ✅ Consistent referential integrity (borrowed items match transactions)

## 🎓 For Screenshots

Use these scenarios to capture comprehensive screenshots:

1. **Main Menu**: Show all options
2. **User Login**: M001 (member), L001 (librarian)
3. **Browse Catalog**: Display all media sorted
4. **Search Demo**: Search for "Harry" → shows Harry Potter
5. **Borrow Success**: M001 borrows available book
6. **Borrow Failure**: M005 blocked due to fines
7. **Return Demo**: Return borrowed item
8. **Late Fee**: Return item past due date, show fee calculation
9. **Member Info**: Display M003's account (fines + borrowed items)
10. **Add Media**: Librarian adds new book
11. **View Transactions**: Show transaction history
12. **Error Handling**: Try to borrow non-existent item

## 📊 Visual Data Summary

### User Distribution
```
Members:    ██████████ 83% (10 users)
Librarians: ██         17% (2 users)
```

### Media Distribution
```
Books:      ███████    29% (12 items)
Magazines:  ████       19% (8 items)
DVDs:       █████      24% (10 items)
```

### Media Status
```
Available:  ████████████████ 64% (27 items)
Borrowed:   ████████         36% (15 items)
```

### Fine Status
```
No Fines:   ████████   80% (8 members)
Has Fines:  ██         20% (2 members)
Can't Borrow: █        10% (1 member - M005)
```

## 🔐 Test Credentials

**For Login Testing**:
- Member: `M001` (John Smith) - Clean account
- Member with Fines: `M003` (Michael Brown) - $15.50 fines, can still borrow
- Member Blocked: `M005` (James Wilson) - $25.00 fines, cannot borrow
- Librarian: `L001` (Alice Cooper) - Full system access

## ⚠️ Notes

1. **Date Sensitivity**: Some transactions have specific dates. For realistic testing, you may want to update dates to be recent.

2. **Borrowed Items**: The `borrowedItemsList` in users.txt should match media items with `available=false` and transactions with empty `returnDate`.

3. **Late Fees Match**: User fines should equal sum of late fees from their transactions.

4. **ISBN Format**: Books use realistic ISBN-13 format (978-X-XX-XXXXXX-X)

5. **Realistic Data**: All names, titles, authors, and directors are real (no dummy data)

---

**Ready to Use**: Load these files and the system is fully operational with realistic test data for demonstrations and screenshots.
