# Assignment 7 PL
## _Kulnazarova Aiana COMCEH-24_ 
### Assignment Overview

**This Java project provides a comprehensive date manipulation utility with the following features:**

-Date validation (checks for valid day/month/year combinations including leap years)

-Date comparison and sorting

-Day of the week calculation (using Zeller's Congruence algorithm)

-Date difference calculation (in days)

-Colorful console output using JAnsi library

-Interactive user input with error handling

**The program allows users to:**

-Enter multiple dates

-View all entered dates with their corresponding days of the week

-See the dates sorted chronologically

-Calculate differences between all date combinations in days

### Compilation and Execution Instructions

**Prerequisites**

-Install Java Development Kit (JDK) (version 8 or higher recommended).

-Ensure org.fusesource.jansi library is available (for colored console output).

**Compilation**

"javac -cp ".;jansi-<version>.jar" Date.java"

Replace <version> with the correct version of jansi installed on your system.

**Execution**

"java -cp ".;jansi-<version>.jar" Date"

**Steps to Run**

### Input Format

When prompted, enter dates in the format: day month year (separated by spaces)

Example: 15 7 2023 for July 15, 2023

### Challenges Faced
-Implementing Zeller's Congruence algorithm correctly for day of week calculation

-Handling date validation edge cases (leap years, different month lengths)

-Creating a robust user input system with proper error handling

-Implementing date difference calculation accurately

-Integrating colorful console output with JAnsi

**Dependencies**
JAnsi - For colored console output

