# PanData Bank (ScalarDB Application)


This file introduces the transaction application using ScalarDB for database course and provides a start tutorial.

## Introduction

Banks use different database systems, and to implement the ACID properties of transactions, we introduce the ScalarDB transaction management layer.

Our work:

- Implementation of user registration and secure login based on Spring Security. Due to time constraints, additional user information such as mobile numbers, residence, and email verification functionality were not included.
- utilizes ScalarDB for unified transaction management, allowing users to log in to PanData Bank with accounts from different banks to perform **deposits, withdrawals, and transfers**.
- Different databases are independent but can implement cross-database transactions (transfers, queries, etc).

## Prerequisites

---

- Programming
    - Java (OpenJDK 17)
- Web Framework
    - SpringBoot 3.1.1
    - MyBatis 3.0
- Database
    - MySQL 8.0
    - PostegreSQL 12.0
- Universal Transaction Manager
    - ScalarDB 3.9.1

## Team Contributions

---

Luo Jing:

- Backend Framework Design
- ScalarDB Integration
- Frontend GUI Development

Fan Lingyun:

- Frontend design
- Presentation preparation

## Run the Application

---

1. Clone this repository
2. Open this project in your local IDE (recommend IntelliJ IDEA)
3. Reload `pom.xml`
4. Initialize your local database (refer to the next section)
5. Run `Application.java`
6. Access to local port: [http://localhost:8080/](http://localhost:8080/)

## Initialization

---

### Create Databases

You need to install MySQL and PostgreSQL. Create databases in the terminal:

1. MySQL

```java
mysql -u username -p

CREATE DATABASE database_name;
```

1. PostgreSQL

```java
psql -U username

CREATE DATABASE database_name;
```

### Connect Databases

Modify the corresponding username and password in the SpringBoot and ScalarDB configuration files to ensure the application connects to your local databases.

`scalardb.properties`

```java
# Define the "mysql" storage. (replace the red part)
scalar.db.multi_storage.storages.mysql.storage=jdbc
scalar.db.multi_storage.storages.mysql.contact_points=jdbc:mysql://localhost:3306/
scalar.db.multi_storage.storages.mysql.username=root
scalar.db.multi_storage.storages.mysql.password=yourpassword

# Define the "postgresql" storage.
scalar.db.multi_storage.storages.postgresql.storage=jdbc
scalar.db.multi_storage.storages.postgresql.contact_points=jdbc:postgresql://localhost:5432/
scalar.db.multi_storage.storages.postgresql.username=postgres
scalar.db.multi_storage.storages.postgresql.password=yourpassword
```

`application.properties`

```java
# jdbc (replace the red part)
spring.datasource.url=jdbc:mysql://localhost:3306/
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.datasource.type: com.zaxxer.hikari.HikariDataSource
```

## System Workflow

---

After creating and connecting the databases, tables will be automatically created in MySQL and PostgreSQL when the application starts, as described below:

### Schema

```json
{
  "mysqlBank.accounts": {
    "transaction": true,
    "partition-key": [
      "accountId"
    ],
    "columns": {
      "accountId": "TEXT",
      "password": "TEXT"
      "name": "TEXT",
      "balance": "INT"
	    "accountEmail": "TEXT"
    }
  },
  "mysqlBank.transactions": {
    "transaction": true,
    "partition-key": [
      "transactionId"
    ],
    "columns": {
      "transactionId": "TEXT",
      "fromTable": "TEXT",
      "fromId": "TEXT",
      "toTable": "TEXT",
      "toId": "TEXT",
      "amount": "INT",
      "date": "TEXT",
      "flag": "INT"
    }
  },
  "postgreBank.accounts": {
    "transaction": true,
    "partition-key": [
      "accountId"
    ],
    "columns": {
      "accountId": "TEXT",
      "password": "TEXT"
      "name": "TEXT",
      "balance": "INT"
	    "accountEmail": "TEXT"
    }
  },
  "postgreBank.transactions": {
    "transaction": true,
    "partition-key": [
      "transactionId"
    ],
    "columns": {
      "transactionId": "TEXT",
      "fromTable": "TEXT",
      "fromId": "TEXT",
      "toTable": "TEXT",
      "toId": "TEXT",
      "amount": "INT",
      "date": "TEXT",
      "flag": "INT"
    }
  }
}
```

### Default account tables

- For MySQL database

| account_id | user_name | password | balance | email |
| --- | --- | --- | --- | --- |
| m1 | watanabe | 123456 | 1000 | watanabe@keio.jp |
| m2 | tanaka | 123456 | 2000 | tanaka@keio.jp |
| m3 | nakamura | 123456 | 3000 | nakamura@keio.jp |
- For PostgreSQL database

| account_id | user_name | password | balance | email |
| --- | --- | --- | --- | --- |
| p1 | john | 123456 | 10000 | john@keio.jp |
| p2 | annie | 123456 | 20000 | annie@keio.jp |
| p3 | tom | 123456 | 30000 | tom@keio.jp |

### Functions

Login, Register, deposit, withdraw, transfer, query, …
