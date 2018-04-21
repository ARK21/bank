Bank Application
==============

 The application simulates the work of the bank. Two streams transfer money from account to account. 
 All operations saves in 3 log files. First file contains all logs. Second contains only info logs. 
 Third contains only errors.

Building
========

Enter the following commands in console

- git clone git@github.com:ARK21/bank.git
- cd bank
- mvn install
- cd target
- java -jar Bank.jar

Used technologies and libraries
===================================
- Java 9.0.4 
- job4j 1.2.17
- JUnit 4
- Maven 3.3.9
- Git

