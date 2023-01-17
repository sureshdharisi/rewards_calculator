# How to deploy and use rewards calculator configuration micro service

## H2 configuration details:
```
Database connection url: jdbc:h2:file:/data/rewards-config
H2 console access URL: http://localhost:8585/rewards/config/h2-console
username: admin
password: admin
```

### Drop predefined data structure
```sql
DROP SEQUENCE rewards_config_seq;
DROP TABLE REWARDS_CONFIG;
```
### Create sequence using below query
```sql
CREATE SEQUENCE rewards_config_seq START WITH 1 INCREMENT BY 1;
```
### Create table
```sql
CREATE TABLE REWARDS_CONFIG
(ID INTEGER NOT NULL,
LOWER_LIMIT INTEGER NOT NULL,
UPPER_LIMIT INTEGER ,
POINTS INTEGER NOT NULL,
CREAE_DT DATE,
UPDATE_DT DATE,
PRIMARY KEY (ID));


```