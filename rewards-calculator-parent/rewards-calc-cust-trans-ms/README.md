# How to deploy and use rewards calculator customer transaction micro service

## H2 configuration details:
```
Database connection url: jdbc:h2:file:/data/rewards-cust-transaction
H2 console access URL: http://localhost:8686/customer/transaction/h2-console
username: admin
password: admin
```


### Drop predefined data structure
```sql
DROP SEQUENCE cust_transaction_seq;
DROP TABLE CUST_TRANSACTIONS;
```
### Create sequence using below query
```sql
CREATE SEQUENCE cust_transaction_seq START WITH 1 INCREMENT BY 1;
```
### Create table
```sql

CREATE TABLE CUST_TRANSACTIONS
(ID INTEGER NOT NULL,
CUSTMER_ID VARCHAR(50) NOT NULL,
TRANSACTION_AMT INTEGER ,
TRANSACTION_DT DATE ,
CREAE_DT DATE,
PRIMARY KEY (ID));
```