# Library Management System - Flowcharts

This directory contains 5 Mermaid flowcharts documenting the system architecture and algorithms.

## 📊 Flowcharts Included

### 1. Main Program Flow (`1_main_program_flow.mmd`)
- **Purpose**: Shows overall system navigation and menu structure
- **Complexity**: Medium
- **Demonstrates**: Complete user journey through the system
- **Use in Report**: System architecture overview section

### 2. Borrow Media Algorithm (`2_borrow_media_algorithm.mmd`)
- **Purpose**: Detailed step-by-step process for borrowing media
- **Complexity**: Medium
- **Demonstrates**: Business logic, validation, error handling
- **Use in Report**: Algorithm implementation section

### 3. Linear Search Algorithm (`3_linear_search_algorithm.mmd`)
- **Purpose**: Shows linear search implementation (O(n) complexity)
- **Complexity**: Simple
- **Demonstrates**: Search by title with substring matching
- **Use in Report**: Search algorithm section

### 4. Binary Search Algorithm (`4_binary_search_algorithm.mmd`)
- **Purpose**: Shows binary search implementation (O(log n) complexity)
- **Complexity**: Simple-Medium
- **Demonstrates**: Efficient search for sorted data (ISBN search)
- **Use in Report**: Search algorithm section

### 5. Quick Sort Algorithm (`5_quick_sort_algorithm.mmd`)
- **Purpose**: Shows quick sort implementation (O(n log n) complexity)
- **Complexity**: Medium
- **Demonstrates**: Efficient sorting with divide-and-conquer approach
- **Use in Report**: Sort algorithm section

## 🎨 How to Render Flowcharts

### Method 1: Mermaid Live Editor (Recommended)
1. Go to **https://mermaid.live/**
2. Copy the content of any `.mmd` file
3. Paste into the editor
4. The flowchart renders automatically
5. Click **"Download PNG"** or **"Download SVG"**

### Method 2: MermaidChart.com
1. Go to **https://www.mermaidchart.com/**
2. Create a free account (optional)
3. Click "New Diagram"
4. Paste the Mermaid code
5. Export as PNG/SVG/PDF

### Method 3: VS Code Extension
1. Install "Markdown Preview Mermaid Support" extension
2. Open any `.mmd` file
3. Preview with `Ctrl+Shift+V` (or `Cmd+Shift+V` on Mac)
4. Right-click → Export

### Method 4: Online Mermaid Editors
- **Mermaid Chart**: https://www.mermaidchart.com/
- **Mermaid Live**: https://mermaid.live/
- **StackEdit**: https://stackedit.io/ (supports Mermaid in Markdown)

## 📝 Using in Your Report

### For PDF Report:
1. Render each flowchart using one of the methods above
2. Export as **PNG** (recommended: 300 DPI for print quality)
3. Insert images into your Word/Google Docs document
4. Add captions:
   - "Figure 1: Main Program Flow"
   - "Figure 2: Borrow Media Algorithm"
   - etc.

### For Markdown/LaTeX Report:
Simply include the Mermaid code blocks directly:

```markdown
## Main Program Flow

```mermaid
[paste flowchart code here]
```
```

## 🎨 Color Legend

Flowcharts use consistent color coding:

- **Green** (#90EE90): Start/Success states
- **Pink** (#FFB6C1): End states
- **Red** (#FF6B6B): Error states
- **Yellow/Gold** (#FFD700): Decision points
- **Light Blue** (#87CEEB): Process steps
- **Purple** (#DDA0DD): Important operations
- **Orange** (#FFA500): Warning/Notice states

## 📐 Flowchart Symbols Guide

- **Rounded Rectangle** `([...])`: Start/End terminals
- **Rectangle** `[...]`: Process/Action
- **Diamond** `{...}`: Decision/Condition
- **Arrow** `-->`: Flow direction
- **Labeled Arrow** `-->|label|`: Conditional flow

## 🖼️ Export Settings for Best Quality

### For Report/Presentation:
- **Format**: PNG
- **Resolution**: 300 DPI minimum
- **Background**: White
- **Size**: Auto or fit to content

### For Digital/Web:
- **Format**: SVG (scalable, always sharp)
- **Background**: Transparent or white

## 📋 Flowchart Descriptions

### 1. Main Program Flow
**What it shows:**
- System startup and initialization
- Main menu with 5 categories
- Submenu navigation
- User access control (librarian check)
- Data persistence (save on exit)

**Key Decision Points:**
- Is user a librarian? (for media management)
- Which menu option selected?
- Back to main menu navigation

### 2. Borrow Media Algorithm
**What it shows:**
- Complete borrow transaction process
- 5 validation steps before success
- Error handling at each stage
- Database updates (transaction, media, user)

**Key Decision Points:**
- User exists?
- Is member (not librarian)?
- Has outstanding fines?
- Media exists?
- Media available?

### 3. Linear Search Algorithm
**What it shows:**
- Sequential search through list
- Case-insensitive substring matching
- Building results list
- Display logic

**Complexity**: O(n) - checks every item

### 4. Binary Search Algorithm
**What it shows:**
- Divide and conquer approach
- Requires sorted data
- Halves search space each iteration
- Exact match finding

**Complexity**: O(log n) - very efficient for large datasets

### 5. Quick Sort Algorithm
**What it shows:**
- Partition-based sorting
- Recursive divide and conquer
- In-place sorting
- Pivot selection and swapping

**Complexity**: O(n log n) average case

## 💡 Tips for Your Report

1. **Include all 5 flowcharts** - shows comprehensive system documentation
2. **Add brief descriptions** - explain what each flowchart demonstrates
3. **Reference in text** - "As shown in Figure 2, the borrow media algorithm..."
4. **Explain complexity** - mention Big O notation for algorithms
5. **Compare algorithms** - Linear (O(n)) vs Binary (O(log n)) search

## 🔍 Testing Flowcharts

Before including in your report, verify each flowchart:

1. ✅ Renders without errors in Mermaid editor
2. ✅ All paths lead to an end state
3. ✅ Decision diamonds have clear yes/no labels
4. ✅ Colors make sense and are consistent
5. ✅ Text is readable at export size

## 📚 References

Mermaid Documentation: https://mermaid.js.org/
Flowchart Syntax: https://mermaid.js.org/syntax/flowchart.html

---

**Need Help?**
- Flowcharts not rendering? Check syntax in Mermaid Live Editor
- Low quality exports? Use SVG format or increase PNG DPI
- Colors not showing? Some renderers may strip color styles

**Pro Tip**: Keep the `.mmd` source files! You can always re-export at different sizes/formats later.
