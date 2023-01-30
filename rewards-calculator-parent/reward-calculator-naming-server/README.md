# Customer transactions micro service

## Introduction

This service will handle the to register each micro service into eureka server.

This micro service will support below services

URL: http://localhost:8761/

## Technologies Used

- Java 17 (This will work with java 11 also. We need to modify the version in the pom.xml to point to 11 (_<java.version>11</java.version>_)).
- SpringBoot 3.0.x

If you are using java.version 11, then make sure java_home also should point to java 11 to build the project in the command prompt.

## Tools used

- Maven for build
- Jacoco for coverage
- STS 4.x for development


### How to run?
1. Download the code from github using below url

    ```
    git clone https://github.com/sureshdharisi/rewards_calculator.git
    ```
2. Goto the project where pom.xml is located 

    ```
    cd rewards_calculator/rewards-calculator-parent/reward-calculator-naming-server
    ```
3. Run the maven command. The below command will run the junit test cases automatically

    ```
    mvn clean install
    ```
4. Run the application using spring boot plugin.

    ```
    mvn spring-boot:run
    ```

