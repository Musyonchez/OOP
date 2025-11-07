# OOP GROUP PROJECT - PLANNING DOCUMENT

## 📋 PROJECT REQUIREMENTS ANALYSIS

### Core OOP Concepts (MUST HAVE)
- [ ] Abstraction
- [ ] Encapsulation
- [ ] Polymorphism
- [ ] Inheritance

### Documentation Requirements
- [ ] Algorithm flowchart
- [ ] Pseudocode
- [ ] Source code with comments
- [ ] Screenshots (input and output)
- [ ] Reference citations (APA format)
- [ ] Challenges section

### Extra Points Opportunities
- [ ] Search algorithm implementation
- [ ] Sort algorithm implementation
- [ ] Database access (SQL or file-based)

### Report Structure
- [ ] Cover page (group members)
- [ ] Introduction (problem definition and solution)
- [ ] Algorithm introduction (flowchart/pseudocode)
- [ ] Source code documentation
- [ ] Challenges and solutions
- [ ] References (APA citations)
- [ ] Output as PDF with screenshots

---

## 💡 RECOMMENDED PROJECT: LIBRARY MANAGEMENT SYSTEM

### Why This Project?

**1. Clear OOP Demonstration:**
- **Abstraction**: Abstract classes for User, MediaItem
- **Encapsulation**: Private fields, public methods
- **Polymorphism**: Different user types (Member, Librarian), media types (Book, Magazine, DVD)
- **Inheritance**: User → Member/Librarian, MediaItem → Book/Magazine/DVD

**2. Algorithm Opportunities:**
- **Search**: Find books by title, author, ISBN, category
- **Sort**: Sort by title, author, date, popularity, availability

**3. Database Need:**
- Books/Media catalog
- User records
- Borrowing transactions
- Return history

**4. Real-World Relevance:**
- Everyone understands library systems
- Clear problem definition
- Practical use case

---

## 🎯 PROJECT SCOPE

### Core Features
1. **User Management**
   - Register new members
   - Librarian authentication
   - View user profiles
   - Update user information

2. **Media Management**
   - Add books/magazines/DVDs
   - Remove media items
   - Update media details
   - View catalog

3. **Borrowing System**
   - Borrow media (with due dates)
   - Return media
   - Calculate late fees
   - View borrowed items

4. **Search & Sort**
   - Search by title, author, ISBN, category
   - Sort by various criteria
   - Filter available items

5. **Reports**
   - Most borrowed items
   - Overdue items
   - User borrowing history
   - Available vs borrowed statistics

---

## 🏗️ CLASS STRUCTURE (OOP Design)

### Abstract Classes (Abstraction)

**1. User (Abstract)**
```
Abstract Class User
- id: String
- name: String
- email: String
- phone: String
+ abstract displayInfo()
+ abstract getPermissions()
```

**2. MediaItem (Abstract)**
```
Abstract Class MediaItem
- id: String
- title: String
- available: boolean
- borrowedBy: String
+ abstract getType()
+ abstract calculateLateFee(days)
```

### Concrete Classes (Inheritance)

**3. Member extends User**
```
Class Member extends User
- membershipDate: Date
- borrowedItems: List
- fines: double
+ borrowItem()
+ returnItem()
+ displayInfo() [Override]
```

**4. Librarian extends User**
```
Class Librarian extends User
- employeeId: String
- position: String
+ addMedia()
+ removeMedia()
+ displayInfo() [Override]
```

**5. Book extends MediaItem**
```
Class Book extends MediaItem
- author: String
- isbn: String
- pages: int
+ getType() [Override]
+ calculateLateFee(days) [Override]
```

**6. Magazine extends MediaItem**
```
Class Magazine extends MediaItem
- issueNumber: int
- publisher: String
+ getType() [Override]
+ calculateLateFee(days) [Override]
```

**7. DVD extends MediaItem**
```
Class DVD extends MediaItem
- director: String
- duration: int
+ getType() [Override]
+ calculateLateFee(days) [Override]
```

**8. Library (Manager Class)**
```
Class Library
- mediaItems: List
- users: List
- transactions: List
+ searchByTitle()
+ searchByAuthor()
+ sortByTitle()
+ sortByDate()
+ borrowMedia()
+ returnMedia()
```

**9. Transaction**
```
Class Transaction
- transactionId: String
- userId: String
- mediaId: String
- borrowDate: Date
- dueDate: Date
- returnDate: Date
```

---

## 🔍 ALGORITHMS TO IMPLEMENT

### 1. Search Algorithms

**Linear Search** (for small datasets)
```
Algorithm: searchByTitle(title)
Input: title string
Output: List of matching media items

For each item in mediaItems:
    If item.title contains title (case-insensitive):
        Add to results
Return results
```

**Binary Search** (for sorted data - ISBN search)
```
Algorithm: searchByISBN(isbn)
Input: isbn string
Output: Book or null

Sort books by ISBN
left = 0, right = books.length - 1
While left <= right:
    mid = (left + right) / 2
    If books[mid].isbn == isbn:
        Return books[mid]
    Else if books[mid].isbn < isbn:
        left = mid + 1
    Else:
        right = mid - 1
Return null
```

### 2. Sort Algorithms

**Bubble Sort** (educational value, simple)
```
Algorithm: sortByTitle()
For i from 0 to n-1:
    For j from 0 to n-i-1:
        If items[j].title > items[j+1].title:
            Swap items[j] and items[j+1]
```

**Quick Sort** (efficient, extra points)
```
Algorithm: quickSortByAuthor(arr, low, high)
If low < high:
    pivot = partition(arr, low, high)
    quickSort(arr, low, pivot-1)
    quickSort(arr, pivot+1, high)
```

---

## 💾 DATABASE DESIGN

### Option 1: SQLite (Recommended)
```sql
-- Users table
CREATE TABLE users (
    id VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20),
    type VARCHAR(20), -- 'member' or 'librarian'
    membership_date DATE,
    fines DECIMAL(10,2)
);

-- Media table
CREATE TABLE media (
    id VARCHAR(10) PRIMARY KEY,
    title VARCHAR(200),
    type VARCHAR(20), -- 'book', 'magazine', 'dvd'
    available BOOLEAN,
    author VARCHAR(100), -- for books
    isbn VARCHAR(20), -- for books
    issue_number INT, -- for magazines
    director VARCHAR(100) -- for dvds
);

-- Transactions table
CREATE TABLE transactions (
    id VARCHAR(10) PRIMARY KEY,
    user_id VARCHAR(10),
    media_id VARCHAR(10),
    borrow_date DATE,
    due_date DATE,
    return_date DATE,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (media_id) REFERENCES media(id)
);
```

### Option 2: Text File (CSV)
```
users.txt:
ID,Name,Email,Phone,Type,MembershipDate,Fines

media.txt:
ID,Title,Type,Available,Author,ISBN,IssueNumber,Director

transactions.txt:
ID,UserID,MediaID,BorrowDate,DueDate,ReturnDate
```

---

## 📊 FLOWCHARTS NEEDED

### 1. Main Program Flow
```
Start
  ↓
Display Menu
  ↓
User Choice?
  ├─ 1: User Management → (Register/Login/View)
  ├─ 2: Media Management → (Add/Remove/Update/View)
  ├─ 3: Borrow/Return → (Borrow/Return/View)
  ├─ 4: Search/Sort → (Search/Sort Options)
  ├─ 5: Reports → (Statistics/History)
  └─ 6: Exit
  ↓
End
```

### 2. Borrow Media Flowchart
```
Start
  ↓
Enter Member ID
  ↓
Valid Member? ─No→ Error Message → Return
  ↓ Yes
Enter Media ID
  ↓
Media Exists? ─No→ Error Message → Return
  ↓ Yes
Media Available? ─No→ "Already Borrowed" → Return
  ↓ Yes
Create Transaction
  ↓
Set Due Date (14 days)
  ↓
Update Media (available = false)
  ↓
Save to Database
  ↓
Display Success Message
  ↓
End
```

### 3. Search Algorithm Flowchart
```
Start
  ↓
Enter Search Term
  ↓
Select Search Type (Title/Author/ISBN)
  ↓
Initialize Results List
  ↓
For Each Media Item
  ↓
Does it match? ─No→ Continue
  ↓ Yes
Add to Results
  ↓
Display Results
  ↓
Results Empty? ─Yes→ "No matches found"
  ↓ No
Display List
  ↓
End
```

---

## 📝 PSEUDOCODE EXAMPLES

### Borrow Media Algorithm
```
ALGORITHM BorrowMedia(memberId, mediaId)
    INPUT: memberId (String), mediaId (String)
    OUTPUT: Transaction object or error message

    BEGIN
        // Validate member
        member = findMemberById(memberId)
        IF member == NULL THEN
            RETURN "Member not found"
        END IF

        // Check for outstanding fines
        IF member.fines > 0 THEN
            RETURN "Please pay outstanding fines first"
        END IF

        // Validate media
        media = findMediaById(mediaId)
        IF media == NULL THEN
            RETURN "Media not found"
        END IF

        // Check availability
        IF NOT media.available THEN
            RETURN "Media is currently unavailable"
        END IF

        // Create transaction
        transaction = NEW Transaction()
        transaction.id = generateId()
        transaction.userId = memberId
        transaction.mediaId = mediaId
        transaction.borrowDate = TODAY
        transaction.dueDate = TODAY + 14 DAYS

        // Update media status
        media.available = FALSE
        media.borrowedBy = memberId

        // Save to database
        saveTransaction(transaction)
        updateMedia(media)

        RETURN transaction
    END
```

### Search Algorithm
```
ALGORITHM SearchByTitle(searchTerm)
    INPUT: searchTerm (String)
    OUTPUT: List of matching media items

    BEGIN
        results = NEW List()
        searchTerm = toLowerCase(searchTerm)

        FOR EACH item IN mediaItems DO
            itemTitle = toLowerCase(item.title)

            IF itemTitle CONTAINS searchTerm THEN
                results.add(item)
            END IF
        END FOR

        RETURN results
    END
```

### Sort Algorithm (Bubble Sort)
```
ALGORITHM SortByTitle(mediaList)
    INPUT: mediaList (List of MediaItem)
    OUTPUT: Sorted list

    BEGIN
        n = mediaList.length

        FOR i = 0 TO n-1 DO
            FOR j = 0 TO n-i-1 DO
                IF mediaList[j].title > mediaList[j+1].title THEN
                    // Swap
                    temp = mediaList[j]
                    mediaList[j] = mediaList[j+1]
                    mediaList[j+1] = temp
                END IF
            END FOR
        END FOR

        RETURN mediaList
    END
```

---

## 🎨 USER INTERFACE (Console-Based)

```
╔══════════════════════════════════════════════════════╗
║        LIBRARY MANAGEMENT SYSTEM v1.0                ║
╚══════════════════════════════════════════════════════╝

Main Menu:
1. User Management
   1.1 Register New Member
   1.2 Login as Librarian
   1.3 View Profile

2. Media Management (Librarian Only)
   2.1 Add New Book
   2.2 Add New Magazine
   2.3 Add New DVD
   2.4 Remove Media
   2.5 View All Media

3. Borrowing Operations
   3.1 Borrow Media
   3.2 Return Media
   3.3 View My Borrowed Items

4. Search & Sort
   4.1 Search by Title
   4.2 Search by Author
   4.3 Search by ISBN
   4.4 Sort by Title
   4.5 Sort by Author

5. Reports
   5.1 Most Borrowed Items
   5.2 Overdue Items
   5.3 Available Items

6. Exit

Enter your choice:
```

---

## ⚠️ EXPECTED CHALLENGES & SOLUTIONS

### Challenge 1: Database Connection
**Problem**: Managing database connections and SQL queries
**Solution**:
- Use try-catch blocks
- Implement connection pooling
- Create a DatabaseManager class to centralize all DB operations

### Challenge 2: Date Handling
**Problem**: Calculating due dates, overdue days, late fees
**Solution**:
- Use java.time.LocalDate for modern date handling
- Create utility methods for date calculations
- Store dates in ISO format (yyyy-MM-dd)

### Challenge 3: Data Validation
**Problem**: Invalid user input, data integrity
**Solution**:
- Input validation methods
- Exception handling
- Regular expressions for email, phone validation

### Challenge 4: Search Performance
**Problem**: Slow searches with large datasets
**Solution**:
- Implement indexing
- Use appropriate search algorithms
- Consider caching frequently searched items

### Challenge 5: Polymorphism Implementation
**Problem**: Demonstrating clear polymorphic behavior
**Solution**:
- Use abstract methods that child classes must override
- Implement different late fee calculations per media type
- Show runtime polymorphism with User references

---

## 📚 REFERENCES (APA Format - Examples)

Eckel, B. (2006). *Thinking in Java* (4th ed.). Prentice Hall.

Gamma, E., Helm, R., Johnson, R., & Vlissides, J. (1994). *Design patterns: Elements of reusable object-oriented software*. Addison-Wesley.

Oracle. (2024). *Java documentation*. https://docs.oracle.com/en/java/

Bloch, J. (2018). *Effective Java* (3rd ed.). Addison-Wesley Professional.

Sedgewick, R., & Wayne, K. (2011). *Algorithms* (4th ed.). Addison-Wesley Professional.

---

## 📅 PROJECT TIMELINE (Suggested)

**Week 1**: Planning & Design
- Finalize project idea
- Create class diagrams
- Design database schema
- Assign roles to group members

**Week 2**: Core Implementation
- Implement abstract classes
- Implement concrete classes
- Set up database
- Basic CRUD operations

**Week 3**: Features & Algorithms
- Implement search algorithms
- Implement sort algorithms
- Add borrowing/return logic
- Test all features

**Week 4**: Documentation & Testing
- Write comprehensive comments
- Create flowcharts
- Write pseudocode
- Take screenshots
- Write report
- Format as PDF

---

## 👥 GROUP MEMBER ROLES (Suggested)

**Member 1**: Database & Data Management
- Design database schema
- Implement DatabaseManager class
- Handle all database operations

**Member 2**: Core Classes & OOP
- Implement abstract classes
- Implement inheritance hierarchy
- Ensure proper encapsulation

**Member 3**: Algorithms & Search/Sort
- Implement search algorithms
- Implement sort algorithms
- Optimize performance

**Member 4**: UI & Integration
- Create user interface
- Integrate all components
- Testing and bug fixing

**Everyone**: Documentation
- Each person documents their part
- Collaborate on report
- Review and edit together

---

## ✅ FINAL CHECKLIST

### Code
- [ ] All 4 OOP concepts clearly demonstrated
- [ ] Search algorithm implemented
- [ ] Sort algorithm implemented
- [ ] Database access working
- [ ] Comprehensive comments
- [ ] No compilation errors
- [ ] Logical program flow

### Documentation
- [ ] Cover page with all group members
- [ ] Introduction (problem + solution)
- [ ] Flowcharts for main algorithms
- [ ] Pseudocode for key algorithms
- [ ] Source code included
- [ ] Screenshots of input/output
- [ ] Challenges and solutions section
- [ ] APA references list
- [ ] Proper formatting
- [ ] Exported as PDF

### Testing
- [ ] All features tested
- [ ] Edge cases handled
- [ ] Error handling works
- [ ] Database operations successful
- [ ] Search/sort produce correct results

---

END OF PLANNING DOCUMENT
