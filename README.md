Student API

This project is a RESTful API for managing student information, implemented using the in a Spring Boot 
application. The API supports CRUD (Create, Read, Update, Delete) operations on student entities.

Brief description or introduction of the project.

Table of Contents
Prerequisites
Build and Run Instructions
1. Clone the Repository
2. Build the Project
3. Configure Database
4. Run the Application
API Endpoints
Running Unit Tests
Customizing Configuration

Prerequisites:
   Java JDK 17
   Apache Maven
   IntelliJ IDEA Community Edition 2023.3.1
   Dependencies for the project in pom.xml
   
Build and Run Instructions:
1. Clone the Repository

   bash

   git clone https://github.com/Roopak-dot/studentapi.git

   cd your-project

3. Build the Project

   bash

For Maven projects

mvn clean install

3. Configure Database:
   Before running the StudentapiApplication for first time. Create a database with the name school we can change 
   database name but we have to change this name also in applicaion.properties in resources.
   For this we will use SQL query: CREATE DATABASE school;
   This StudentapiApplication will automatically create the table with the name student. Now we can perform CRUD 
   operation on student table.

4. Run the Application

   bash

For Maven projects

mvn spring-boot:run
   
   Also, StudentapiApplication can be run with direct run button in Intellij Idea.

The application will be accessible on postman at http://localhost:8080/students. Adjust the port if you have configured 
a different one.

API Endpoints:
Get All Students:
bash
GET /students

Get Student by ID:
bash
GET /students/{id}

Add a New Student:
bash
POST /students

Update Student by ID:
bash
PUT /students/{id}

Delete Student by ID:
bash
DELETE /students/{id}

Running Unit Tests:
To run the unit tests and generate a code coverage report:

bash

For Maven projects

mvn test jacoco:report

The code coverage report will be available in the target/site/jacoco directory.

Customizing Configuration:
Information on how to customize application configuration, including relevant configuration files.
Paste these properties in application.properties in resources.

spring.datasource.url=jdbc:mysql://localhost:3306/school

spring.datasource.username=username

spring.datasource.password=password

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update

spring.jmx.enabled=true
Note: Change username and password according to you.


