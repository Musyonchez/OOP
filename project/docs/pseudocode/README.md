# Library Management System - Pseudocode Documentation

This directory contains pseudocode for the three main algorithms implemented in the system.

## 📋 Algorithms Included

### 1. Linear Search (`1_linear_search.txt`)
- **Purpose**: Search media items by title using substring matching
- **Complexity**: O(n) time, O(k) space (k = number of results)
- **Use Case**: Flexible search on unsorted data
- **Demonstrates**: Sequential iteration, string matching, accumulating results

### 2. Binary Search (`2_binary_search.txt`)
- **Purpose**: Efficiently find books by exact ISBN
- **Complexity**: O(log n) time, O(1) space
- **Use Case**: Fast lookup in sorted data
- **Demonstrates**: Divide-and-conquer, halving search space, exact matching

### 3. Quick Sort (`3_quick_sort.txt`)
- **Purpose**: Sort media catalog by title
- **Complexity**: O(n log n) average, O(n²) worst case
- **Use Case**: General-purpose in-place sorting
- **Demonstrates**: Recursion, partitioning, divide-and-conquer

## 📖 Format and Structure

Each pseudocode file includes:

1. **Header Section**
   - Algorithm name and purpose
   - Time and space complexity analysis
   - Best use cases

2. **Main Algorithm**
   - Step-by-step pseudocode
   - Clear input/output specifications
   - Structured control flow (IF/WHILE/FOR)

3. **Example Trace**
   - Sample data walkthrough
   - Step-by-step execution showing how algorithm works
   - Variable state at each iteration

4. **Complexity Analysis**
   - Best, average, and worst case scenarios
   - Explanation of why complexity is what it is
   - Comparison with alternative approaches

5. **Advantages/Disadvantages**
   - When to use this algorithm
   - When NOT to use this algorithm
   - Trade-offs compared to alternatives

6. **Use Cases**
   - Specific examples from the library system
   - Real-world applications

## 🎯 Using in Your Report

### Include All Three Algorithms
These pseudocode files are report-ready and can be:
- Copy-pasted directly into your document
- Used as reference when writing the algorithm section
- Included as appendices

### Recommended Report Structure

```
3. ALGORITHMS SECTION

3.1 Linear Search Algorithm
    - Purpose and use case
    - Pseudocode (copy from 1_linear_search.txt)
    - Complexity analysis
    - Example trace

3.2 Binary Search Algorithm
    - Purpose and use case
    - Pseudocode (copy from 2_binary_search.txt)
    - Complexity analysis
    - Comparison with linear search

3.3 Quick Sort Algorithm
    - Purpose and use case
    - Pseudocode (copy from 3_quick_sort.txt)
    - Complexity analysis
    - Partition process explanation
```

## 🔄 Relationship to Flowcharts

The pseudocode files correspond to flowcharts in `/docs/flowcharts/`:

| Pseudocode File | Flowchart File | Algorithm |
|----------------|----------------|-----------|
| `1_linear_search.txt` | `3_linear_search_algorithm.mmd` | Linear Search |
| `2_binary_search.txt` | `4_binary_search_algorithm.mmd` | Binary Search |
| `3_quick_sort.txt` | `5_quick_sort_algorithm.mmd` | Quick Sort |

**Consistency**: The pseudocode matches the flowchart logic exactly. Use both together to:
- Show visual representation (flowchart)
- Provide textual algorithm (pseudocode)
- Demonstrate the same logic in two formats

## 📊 Algorithm Comparison

### Search Algorithms

| Feature | Linear Search | Binary Search |
|---------|--------------|---------------|
| **Time** | O(n) | O(log n) |
| **Requires Sorting** | No | Yes |
| **Match Type** | Substring | Exact |
| **Best For** | Small/unsorted data | Large sorted data |
| **Implementation** | Simple | Moderate |

### Sorting Algorithms

| Feature | Bubble Sort | Quick Sort |
|---------|------------|------------|
| **Time (Average)** | O(n²) | O(n log n) |
| **Time (Worst)** | O(n²) | O(n²) |
| **Space** | O(1) | O(log n) |
| **Stable** | Yes | No |
| **Best For** | Educational | Production |

## 💡 Key Concepts Demonstrated

### 1. **Linear Search** - Sequential Processing
- Simple iteration through a list
- Case-insensitive substring matching
- Building a results collection
- Handling "not found" gracefully

### 2. **Binary Search** - Divide and Conquer
- Efficient search by eliminating half the data each step
- Requires sorted data (prerequisite)
- Logarithmic performance scaling
- Trade-off: sorting overhead vs search speed

### 3. **Quick Sort** - Recursive Partitioning
- Choosing a pivot element
- Partitioning around the pivot
- Recursive sorting of sub-arrays
- In-place sorting (space efficient)

## 🎓 Academic Standards

### Pseudocode Conventions Used

1. **Keywords**: ALL CAPS (IF, WHILE, FOR, SET, RETURN)
2. **Indentation**: Shows structure and nesting
3. **Comments**: Explain complex steps
4. **Variables**: Descriptive names (searchTerm, pivotIndex)
5. **Operators**: Clear symbols (==, <=, !=)
6. **Line Numbers**: Hierarchical (1.1, 1.2, etc.)

### Suitable for Academic Reports

These pseudocode files follow standard academic conventions and are:
- ✅ Clear and unambiguous
- ✅ Language-agnostic (not tied to Java)
- ✅ Properly structured with control flow
- ✅ Well-commented and explained
- ✅ Include complexity analysis
- ✅ Demonstrate understanding

## 📚 References

### Algorithm Textbooks
- Cormen, T. H., Leiserson, C. E., Rivest, R. L., & Stein, C. (2022). *Introduction to Algorithms* (4th ed.). MIT Press.
- Sedgewick, R., & Wayne, K. (2011). *Algorithms* (4th ed.). Addison-Wesley.

### Search Algorithms
- Knuth, D. E. (1998). *The Art of Computer Programming, Volume 3: Sorting and Searching* (2nd ed.). Addison-Wesley.

### Quick Sort
- Hoare, C. A. R. (1962). Quicksort. *The Computer Journal*, 5(1), 10-16.

## ✅ Checklist for Report

Before finalizing your report, verify:
- [ ] All three algorithms have pseudocode included
- [ ] Complexity analysis is explained for each
- [ ] Example traces show how algorithms work
- [ ] Pseudocode matches the flowcharts
- [ ] Advantages/disadvantages are discussed
- [ ] Use cases in library system are mentioned
- [ ] Comparison between algorithms is included

## 💡 Tips

1. **Explain, Don't Just List**: After showing pseudocode, explain WHY the algorithm works
2. **Use Visual + Text**: Combine flowcharts (visual) with pseudocode (textual)
3. **Compare Approaches**: Discuss when to use linear vs binary search
4. **Analyze Complexity**: Show understanding by explaining Big O notation
5. **Real-World Context**: Connect algorithms to actual library system features

---

**Ready for Report Integration**: All three pseudocode files are formatted for direct inclusion in your academic report.
