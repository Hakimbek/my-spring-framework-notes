# Spring JDBC
Spring **JdbcTemplate** is a powerful mechanism to connect to the database and execute **SQL queries**. It internally uses **JDBC api**, but eliminates a lot of problems of **JDBC API**.

## Problems of JDBC API

- We need to write a lot of code before and after executing the query, such as creating connection, statement, closing resultset, connection etc.
- We need to perform exception handling code on the database logic.
- We need to handle transaction.
- Repetition of all these codes from one to another database logic is a time consuming task.

## Advantage of Spring JdbcTemplate
Spring JdbcTemplate eliminates all the above mentioned problems of JDBC API. It provides you methods to write the queries directly, so it saves a lot of work and time.

## Spring Jdbc Approaches
Spring framework provides following approaches for JDBC database access:

- JdbcTemplate
- NamedParameterJdbcTemplate
- SimpleJdbcTemplate
- SimpleJdbcInsert and SimpleJdbcCall

## JdbcTemplate class
It is the central class in the Spring JDBC support classes. It takes care of creation and release of resources such as creating and closing of connection object etc. So it will not lead to any problem if you forget to close the connection.

It handles the exception and provides the informative exception messages by the help of excepion classes defined in the **org.springframework.dao** package.

We can perform all the database operations by the help of JdbcTemplate class such as insertion, updation, deletion and retrieval of the data from the database.

Let's see the methods of spring JdbcTemplate class.

| No | Method |	Description |
| -- | ------ | ----------- |
| 1 |	public int update(String query) |	is used to insert, update and delete records. |
| 2 |	public int update(String query,Object... args) |	is used to insert, update and delete records using PreparedStatement using given arguments. |
| 3 |	public void execute(String query) |	is used to execute DDL query. |
| 4 |	public T execute(String sql, PreparedStatementCallback action) |	executes the query by using PreparedStatement callback. |
| 5 |	public T query(String sql, ResultSetExtractor rse) |	is used to fetch records using ResultSetExtractor. |
| 6 |	public List query(String sql, RowMapper rse) |	is used to fetch records using RowMapper. |
