#  Daily Finance App Automation 

This project is an automation testing framework developed for [Daily Finance](https://dailyfinance.roadtocareer.net/), built using **Selenium WebDriver**, **TestNG**, and **Gradle**, following the **Page Object Model (POM)** design pattern.

# Project Workflow
## 📁 Page Object Model Structure

The project is organized with clear separation of concerns, where each page of the application has its own class containing web elements and page-specific methods:

* **Login Page** – Handles login actions and validation.
* **Registration Page** – Manages new user registration.
* **User Dashboard Page** – Adds cost details after login.
* **Profile Page** – Supports profile update and image upload.

##  Test Runner Packages

### 🔐 Login Test Runner

* Attempts login with **invalid admin credentials**.
* Logs in with **valid admin credentials**.
* Logs in with the **last registered user**, fetched from `users.json`.
* Supports **data-driven login** using users from `user.csv`.

### 📝 Registration Test Runner

* Registers a **new user** by filling all required fields.
* Saves user details into a `users.json` file for further use.

### 👤 Profile Test Runner

* Updates the profile information.
* Uploads a profile image.

### 📊 User Dashboard Test Runner

* Logs in the **last registered user**.
* Adds cost details on the dashboard.

##  Test Suites

The tests are organized into two main suites for flexible execution:

* ✅ **Smoke Suite**: Covers critical path tests to verify core functionalities are working.
* 🔁 **Regression Suite**: Covers all major flows for thorough testing after changes.

## 🛠 Technology Used

* **Language**: Java
* **Automation Framework**: Selenium WebDriver
* **Test Framework**: TestNG
* **Build Tool**: Gradle
* **Design Pattern**: Page Object Model (POM)
* **Data Files**: `users.json`, `user.csv`


## How to run this project
1. Clone the repository.
   
2. Run the desired test suite:

   ```bash
   gradle clean test -PsuiteName="smokeSuite.xml"
   # or
   gradle clean test -PsuiteName="regressionSuite.xml"
   ```
3. Generate Allure report:

   ```bash
   allure generate allure-results --clean -output 
   allure serve allure-results
   ```

## Automation Video

![Watch the Test Automation](https://github.com/user-attachments/assets/a56c85bc-aad4-4ee4-aa5e-1722dbae2674)

## Allure Report

[Report - View HTML](http://192.168.0.100:49701/index.html)
