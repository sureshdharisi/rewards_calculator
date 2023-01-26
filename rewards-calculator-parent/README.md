# Rewards calculator service

It will provide the service to calculate the reward points. We divided this application as 3 micro services as a part of implementation.

The available micro services are:

1. Rewards config store
2. Customer transactions service
3. Rewards calculator service

This application will provide the capabilities of creating and updating the configurations dynamically so that we can change the reward points configuration any time
without restarting the server.

## Major components and features used

1. Spring boot
2. H2 database to store database
3. In memory cache
4. Feign clients
5. Discovery service
6. Junits
7. Actuators

## High level flow diagram
![alt text](https://github.com/sureshdharisi/rewards_calculator/blob/develop/rewards-calculator-parent/record_calculator_design-High_Level_Diagram.png?raw=true)

## Component diagram
![alt text](https://github.com/sureshdharisi/rewards_calculator/blob/develop/rewards-calculator-parent/record_calculator_design-Architecture_Diagram.png?raw=true)

## How to use this application?
We need to setup the data before sending request for rewards calculation.
It has individual micro services for configuration, transactions and calculations. 

Use below services to setup data:

### reward-points-store

This micro service will be used to create reqard points configuration data.

### customer-transactions

This micro service will handle the customer transactions. Create few customer transactions to calculate the total rewards.

### rewards-calculator

This micro service will handle the request to calculare the total rewards.
