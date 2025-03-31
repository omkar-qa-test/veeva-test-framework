# Selenium-Cucumber-TestNG Automation Framework

## Overview
A robust test automation framework for web application testing using:
- **Selenium WebDriver** for browser automation
- **Cucumber** for BDD-style test definitions
- **TestNG** for test execution and reporting
- **Maven** for dependency management

## Features
✔ Cross-browser testing (Chrome/Firefox/Edge)  
✔ Parallel test execution  
✔ Automatic report generation  
✔ Page Object Model design pattern  
✔ Configurable properties 
✔ Self-cleaning report archiving system  

## Prerequisites
- Java 11+
- Maven 3.8+
- Chrome/Firefox browsers

## Setup
1. Clone repository:  
   git clone https://github.com/omkar-qa-test/veeva-test-framework.git
2. Execution
   
   2.1 Change current directory to:

         cd veeva-test-framework\framework
   
    2.2 Run all tests:
    
         mvn clean test
   
4. Reports

   After execution, find reports at:
   
    Basic JSON :

      **target/cucumber-reports/cpLandingPage.json**

   Detailed HTML:

      **target/cucumber-reports/cucumber-html-reports/feature-overview.html**

   Archived reports:

      **target/archived-reports/**
