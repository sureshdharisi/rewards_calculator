# rewards_calculator

This will calculate the total reward points for the customer. We need to setup the data before sending request for rewards calculation.
It has individual micro services for configuration, transactions and calculations. 

Use below services to setup data:

### rewards-calculator-config-ms

This micro service will be used to create reqard points configuration data.

### rewards-calc-cust-trans-ms

This micro service will handle the customer transactions. Create few customer transactions to calculate the total rewards.

### rewards-calculator-web

This micro service will handle the request to calculare the total rewards.
