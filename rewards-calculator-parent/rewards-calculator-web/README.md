# Calculate the reward points

## Introduction

This service will calculated the rewards points based on the purchase amount by customer. This spring boot micro service also includes the below features

- Junit parameterized test cases
- Code coverage 
- Functional test cases (Possitive & Negative)
- Logging using logback
- Actuator metrics
- Exception handling
- Java documentation
- Lombok for automatic code generation

## Technologies Used

- Java 17 (This will work with java 11 also. We need to modify the version in the pom.xml to point to 11 (_<java.version>11</java.version>_)).
- SpringBoot 3.0.x

If you are using java.version 11, then make sure java_home also should point to java 11 to build the project in the command prompt.

## Tools used

- Maven for build
- Jacoco for coverage
- STS 4.x for development

## Coverage Details
![alt text](https://github.com/sureshdharisi/codechallenge/blob/master/reward-calculator/CodeCoverage_Details.PNG?raw=true)

## Testing

### Prerequisites to test
- Use configuration micro service to setup configuration values
- use customer transactions micro service to setup transactions data

## Test data and Results

Customer ID| Expected results |
------------------- | -------------------|
ABC | 250 |



## Junit test cases details
![alt text](https://github.com/sureshdharisi/codechallenge/blob/master/reward-calculator/Junit_Test_cases.PNG?raw=true)


### Some sample requests
* Input -1 
```
http://localhost:8787/rewards/calculate/ABC
```

```json
{
    "points": 250
}
```

### Some error responses
* Input -1 
```
http://localhost:8787/rewards/calculate/ABC
```

```json
{
    "errorMessage": "Failed to convert value of type 'java.lang.String' to required type 'java.lang.Double'; For input string: \"abc\"",
    "errorCode": "RC000"
}
```


### How to run?
1. Download the code from github using below url

```
git clone https://github.com/sureshdharisi/rewards_calculator.git
```
2. Goto the project where pom.xml is located 

```
cd rewards_calculator/rewards-calculator-parent/rewards-calculator-web
```
3. Run the maven command. The below command will run the junit test cases automatically

```
mvn clean install
```
4. Run the application using spring boot plugin.

```
mvn spring-boot:run
```
### Management URL
````
http://localhost:8787/rewards/manage
````
### Generate spotbug report
1. Run below command to generate spot bug report
````
mvn site
````
Now report will be generated in the target folder and the location is "rewards-calculator-web\target\site\spotbugs.html"

## Spotbugs report
![alt text](https://github.com/sureshdharisi/codechallenge/blob/develop/reward-calculator/Spotbugs_report.PNG?raw=true)
