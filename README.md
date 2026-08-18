#MediCare Hospital Patient Admission & Ward Management System
Author: Onyebuchi Okolie
Institution: Rosebank College
A robust, console-based Java application designed to modernize and digitize patient records and ward bed management for MediCare Hospital. This system eliminates error-prone paper registers by providing a structured in-memory management framework. It handles patient onboarding, categorizes admissions, manages dynamic bed allocations across a 20-bed matrix layout, generates real-time occupancy analytics, and ensures high software reliability through automated JUnit testing.
Executive Summary & System Objectives
The MediCare Patient Admission System was developed to address structural operational challenges in hospital administrative workflows:
 * Elimination of Administrative Bottlenecks: Digital record registration and lookups replace physical paper logging.
 * Optimized Resource Allocation: Live tracking of a 4x5 ward matrix grid ensures real-time visibility into bed availability.
 * Polymorphic Data Handling: Distinct categorization of Inpatient, Outpatient, and Emergency cases via object-oriented design patterns.
 * Data Integrity & Validation: Built-in safeguards prevent duplicate patient IDs, invalid ward assignments, and bed double-allocations.
 * Continuous Integration: Integrated GitHub Actions workflows run continuous integration builds to verify code changes against automated test suites automatically.
Technical Stack & Architecture
 * Programming Language: Java 17 (LTS)
 * Build & Dependency Management: Apache Maven 3.x
 * Unit Testing Framework: JUnit 5 (Jupiter API)
 * Version Control & CI/CD: Git, GitHub, and GitHub Actions
 * Execution Environment: Cross-platform (Linux, macOS, Windows, Cloud Environments/Replit)
Repository File Hierarchy
PROG6112_Assignment_1/
│
├── .github/
│   └── workflows/
│       └── maven.yml               # Automated CI build & test workflow
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── PatientCategory.java   # Enum representing admission categories
│   │       ├── Patient.java          # Base domain model for general patient data
│   │       ├── Inpatient.java        # Derived class managing ward/bed attributes
│   │       ├── WardBedManager.java   # 2D array matrix engine managing 20-bed layout
│   │       └── HospitalSystem.java   # Main entry point and interactive console UI
│   │
│   └── test/
│       └── java/
│           └── HospitalSystemTest.java # JUnit 5 test suite covering logic and edge cases
│
├── pom.xml                         # Maven build configuration & JUnit 5 dependencies
└── README.md                       # Complete project documentation

Core System Functionalities
1. Patient Admission & Categorization
 * Supports registration across three distinct categories: INPATIENT, OUTPATIENT, and EMERGENCY.
 * Validates unique Patient IDs upon creation to prevent system duplicates.
 * Encapsulates patient details including name, age, gender, medical condition, and admission status.
2. Interactive Ward Bed Management (2D Matrix Engine)
 * Represents the hospital ward using a 4x5 2D matrix (20 total beds labelled B01 through B20).
 * Bed Allocation: Automatically assigns the next available matrix position to registered inpatients.
 * Bed Release: De-allocates assigned beds upon patient discharge or status update and clears matrix tracking.
 * Visual Matrix Layout: Prints a formatted grid of the ward status (showing bed IDs alongside assigned patient IDs or [EMPTY] indicators).
3. Record Lookups, Updates & Deletions
 * Fast lookup by Patient ID.
 * Allows updating demographic details (such as names or conditions).
 * Deleting a patient record automatically frees up any allocated bed currently reserved by that individual.
4. Ward Analytics & Occupancy Reporting
Generates real-time statistical reports displaying:
 * Total registered patient population.
 * Current number of available beds vs. occupied beds.
 * Live ward occupancy percentage calculation.
Installation & Setup Instructions
Prerequisites
Ensure you have the following installed on your machine:
 * Java Development Kit (JDK) 17 or higher.
 * Apache Maven 3.6+.
 * Git.
Local Setup Steps
 * Clone the Repository:
   git clone https://github.com/YOUR_USERNAME/PROG6112_Assignment_1.git
cd PROG6112_Assignment_1

 * Compile Source Files:
   mvn clean compile

 * Run the Console Application:
   mvn exec:java -Dexec.mainClass="HospitalSystem"

 * Execute Automated Unit Test Suite:
   mvn test

Interactive Menu Options & User Manual
When launched, the system presents an interactive menu prompt:
=== MediCare Patient Admission System ===
1. Register Patient
2. Search Patient
3. Update Patient
4. Delete Patient
5. Ward Bed Management
6. Reports
7. Sort Patients (by Surname)
8. Exit
Select an option:

Menu Operations:
 * Option 1 (Register Patient): Enter demographic details and choose category (1 for Inpatient, 2 for Outpatient, 3 for Emergency).
 * Option 2 (Search Patient): Look up patient profile details by entering their unique ID.
 * Option 3 (Update Patient): Modify first or last name entries for existing patient records.
 * Option 4 (Delete Patient): Remove a record from the database and free up any linked bed assignments.
 * Option 5 (Ward Bed Management): Access sub-options to allocate a bed, release a bed, or view the interactive 4x5 visual layout.
 * Option 6 (Reports): Display summary stats and total ward occupancy percentage.
 * Option 7 (Sort Patients): Sort all registered patient records alphabetically by surname.
Automated Continuous Integration (CI/CD)
This repository utilizes GitHub Actions to implement continuous integration. On every git push or pull_request to the main branch, the workflow runner executes:
 * Environment setup with Java 17 (Temurin JDK).
 * Maven project build (mvn -B test).
 * Execution of all 7 unit test cases in HospitalSystemTest.java.
A green checkmark (✅) on the GitHub repository indicates that all unit tests have passed successfully.
References & YouTube Learning Resources
The following technical video tutorials and documentation helped guide the design, structure, and testing implementation of this Java project:
Java Object-Oriented Programming (OOP) & Polymorphism:
 * Java OOPs Concepts in 60 Mins (Programming with Mosh)
   * https://www.youtube.com/watch?v=pTB0EiLXUC8
 * Java Inheritance & Encapsulation Guide (FreeCodeCamp)
   * https://www.youtube.com/watch?v=nJH84R3Lp9E
2D Matrix Arrays & Data Structures:
 * Java 2D Arrays Tutorial (Bro Code)
   * https://www.youtube.com/watch?v=AL3R48z803A
 * Working with Multi-Dimensional Arrays in Java (Neso Academy)
   * https://www.youtube.com/watch?v=L3-q2Gua3Is
Unit Testing with JUnit 5:
 * JUnit 5 Tutorial - Testing In Java (Coding with John)
   * https://www.youtube.com/watch?v=flO4T3z3680
 * Testing Exceptions & Assertions in JUnit 5 (Amigoscode)
   * https://www.youtube.com/watch?v=vZm0lHciFsQ
Build Tools & CI/CD Pipelines:
 * Apache Maven Crash Course for Beginners (Amigoscode)
   * https://www.youtube.com/watch?v=1YoP7I2R-7Y
 * Setting Up GitHub Actions for Java Projects (GitHub Docs)
   * https://www.youtube.com/watch?v=R8_veQiYBjU
