# Automation Testing Framework for Tickhelp website
This repository contains an automation testing framework designed to test the functionality of the Tickhelp website

---

## Key Features

### 1. Design Pattern
- **Page Object Model (POM)**: Ensures modularity and maintainability of the code by separating the elements and actions of each page into dedicated classes.

### 2. Technologies Used
- **Programming Language**: Java
- **Frameworks and Tools**: Selenium WebDriver, TestNG
- **IDE**: IntelliJ IDEA

---
# TickHelp Test Automation Suite

[![Java](https://img.shields.io/badge/Java-17%2B-blue)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.1.0-green)](https://www.selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-7.6.1-red)](https://testng.org/doc/)

Automated test suite for TickHelp platform validation using Selenium WebDriver, TestNG, and Page Object Model (POM) design pattern.

## 🧪 Implemented Test Cases

### Reports Module
1. **Audit Log Verification**  
   `TC01_AuditLog...java`  
   - Creates ticket with timestamped name
   - Deletes ticket and verifies audit log entry
   - Validates system activity tracking

2. **Custom Reports Validation**  
   `TC01_CustomReports...java`  
   - Generates custom report with "Last Week" filter
   - Verifies report generation completeness
   - Validates multi-category selection

3. **CSV Export Functionality**  
   `TC02_CustomReports...java`  
   - Tests CSV file generation
   - Implements file download verification
   - Includes 30-second timeout handling

4. **Deleted Tickets Report**  
   `TC02_DeletedTicket...java`  
   - Creates and deletes test ticket
   - Verifies entry in deleted tickets report
   - Validates data persistence in trash system

5. **Due Dates Report Generation**  
   `TC01_DueDates...java`  
   - Tests period-based report generation
   - Validates visual report components
   - Includes image visibility checks

## 🛠 Technical Implementation
- **POM Architecture**: Separate page classes for Login, Navigation, Tickets, and Reports
- **Dynamic Data Handling**: Unique timestamps for test isolation
- **Cross-Browser Support**: Configurable through `config.properties`
- **CI/CD Ready**: Maven-based build system

## ⚙️ Configuration
```properties
# src/main/resources/config.properties
browser=chrome
baseUrl=https://tickhelp.com
downloadPath=./target/downloads

## Framework Structure

### **Main**: Selenium Framework
This contains the core implementation of the framework, including the following components:
- **BrowserActions**: Generic browser interaction methods.
- **Assertions**: Custom assertions for validating test results.
- **Utilities**: Reusable utility functions.
- **Config**: Configuration files and utilities for managing settings.
- **DriverManager**: WebDriver setup and teardown logic.

### **Test**: Pages and Test Cases
- **Pages**: 
  - Organized as a package for each page of the application.
  - Each package contains:
    - A class for the **elements** of the page (locators) and **actions** that can be performed on the page.
    

- **Tests**:
  - Contains the actual test cases that implement the test scenarios provided 

