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

