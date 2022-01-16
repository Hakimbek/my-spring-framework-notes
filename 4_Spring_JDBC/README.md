# Spring JDBC
Spring **JdbcTemplate** is a powerful mechanism to connect to the database and execute **SQL queries**. It internally uses **JDBC api**, but eliminates a lot of problems of **JDBC API**.

## Problems of JDBC API

- We need to write a lot of code before and after executing the query, such as creating connection, statement, closing resultset, connection etc.
- We need to perform exception handling code on the database logic.
- We need to handle transaction.
- Repetition of all these codes from one to another database logic is a time consuming task.
