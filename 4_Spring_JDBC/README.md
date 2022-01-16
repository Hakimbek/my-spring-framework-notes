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

## Example of Spring JdbcTemplate

We are assuming that you have created the following table inside the database.

```sql
CREATE TABLE public.employee
(
    id integer,
    name character varying,
    salary double precision
);

```

### Employee.java
This class contains 3 properties with constructors and setter and getters.

```java
public class Employee {
    private int id;

    private String name;

    private double salary;
    
    // no-arg and parameterized constructors  
    // Getters and Setters
}
```

### EmployeeDao.java
It contains one property jdbcTemplate and three methods **saveEmployee(), updateEmployee** and **deleteEmployee()**.

```java
public class EmployeeDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveEmployee(Employee employee) {
        String query = "insert into employee values('" + employee.getId() + "', '" + employee.getName() + "', '" + employee.getSalary() + "')";
        return jdbcTemplate.update(query);
    }

    public int updateEmployee(Employee employee) {
        String query = "update employee set name = '" + employee.getName() + "', salary = '" + employee.getSalary() + "' where id = '" + employee.getId() + "' ";
        return jdbcTemplate.update(query);
    }

    public int deleteEmployee(Employee employee) {
        String query = "delete from employee where id='" + employee.getId() + "' ";
        return jdbcTemplate.update(query);
    }
}
```

### applicationContext.xml
The **DriverManagerDataSource** is used to contain the information about the database such as driver class name, connnection URL, username and password.

There are a property named datasource in the JdbcTemplate class of DriverManagerDataSource type. So, we need to provide the reference of DriverManagerDataSource object in the JdbcTemplate class for the datasource property.

Here, we are using the JdbcTemplate object in the EmployeeDao class, so we are passing it by the setter method but you can use constructor also.

```applicationContext.xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="org.postgresql.Driver" />
        <property name="url" value="jdbc:postgresql://localhost:5432/jdbc_template_example" />
        <property name="username" value="postgres" />
        <property name="password" value="5657" />
    </bean>

    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <bean id="employeeDao" class="io.spring.framework.EmployeeDao">
        <property name="jdbcTemplate" ref="jdbcTemplate"/>
    </bean>

</beans>
```

### Main.java
This class gets the bean from the **applicationContext.xml** file and calls the **saveEmployee()** method. You can also call **updateEmployee()** and **deleteEmployee()** method by uncommenting the code as well.

```java
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        EmployeeDao dao = (EmployeeDao) ctx.getBean("employeeDao");
        int status = dao.saveEmployee(new Employee(1, "Hakim", 35000));
        System.out.println(status);


//        int status = dao.updateEmployee(new Employee(1, "Hakim", 15000));
//        System.out.println(status);

//        Employee employee = new Employee();
//        employee.setId(1);
//        int status = dao.deleteEmployee(employee);
//        System.out.println(status);

    }
}
```

### Dependencies in my pom.xml file

```xml
<dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.3.15</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.3.15</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.3.14</version>
        </dependency>

        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.3.1</version>
        </dependency>
</dependencies>
```

## Example of PreparedStatement in Spring JdbcTemplate
We can execute parameterized query using Spring JdbcTemplate by the help of execute() method of JdbcTemplate class. To use parameterized query, we pass the instance of PreparedStatementCallback in the execute method.

### Syntax of execute method to use parameterized query

```java
public T execute(String sql, PreparedStatementCallback<T>);  
```

### PreparedStatementCallback interface
It processes the input parameters and output results. In such case, you don't need to care about single and double quotes.

## Example of using PreparedStatement in Spring
We are assuming that you have created the following table inside the database.

```sql
CREATE TABLE public.employee
(
    id integer,
    name character varying,
    salary double precision
);
```

### Employee.java
Remained unchanged as in the previous example

### EmployeeDao.java
It contains one property jdbcTemplate and one method **saveEmployeeByPreparedStatement**. You must understand the concept of annonymous class to understand the code of the method.

```java
public class EmployeeDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Boolean saveEmployeeByPreparedStatement(final Employee employee) {
        String query = "insert into employee values(?,?,?)";
        return jdbcTemplate.execute(query, (PreparedStatementCallback<Boolean>) ps -> {
            ps.setInt(1, employee.getId());
            ps.setString(2, employee.getName());
            ps.setDouble(3, employee.getSalary());

            return ps.execute();
        });
    }
}
```

### applicationContext.xml
Remained unchanged as in the previous example

### Main.java
This class gets the bean from the **applicationContext.xml** file and calls the **saveEmployeeByPreparedStatement()** method.

```java
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");

        EmployeeDao dao=(EmployeeDao)ctx.getBean("employeeDao");
        dao.saveEmployeeByPreparedStatement(new Employee(1,"Hakim",35000));
    }
}
```

### pom.xml 
Remained unchanged as in the previous example

## ResultSetExtractor Example | Fetching Records by Spring JdbcTemplate

We can easily fetch the records from the database using query() method of JdbcTemplate class where we need to pass the instance of ResultSetExtractor.

Syntax of query method using ResultSetExtractor

```java
public T query(String sql, ResultSetExtractor<T> rse)  
```

### ResultSetExtractor Interface
ResultSetExtractor interface can be used to fetch records from the database. It accepts a ResultSet and returns the list.

### Example of ResultSetExtractor Interface
We are assuming that you have created the following table inside the database.

```sql
CREATE TABLE public.employee
(
    id integer,
    name character varying,
    salary double precision
);
```

### Employee.java
This class contains 3 properties with constructors and setter and getters. It defines one extra method **toString()**.

```
public class Employee {
    private int id;

    private String name;

    private double salary;

    //no-arg and parameterized constructors  
    //getters and setters  
    
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}

```

### EmployeeDao.java
It contains on property jdbcTemplate and one method **getAllEmployees**.

```java
public class EmployeeDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> getAllEmployees() {
        return jdbcTemplate.query("select * from employee", rs -> {
            List<Employee> list = new ArrayList<>();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt(1));
                employee.setName(rs.getString(2));
                employee.setSalary(rs.getInt(3));
                list.add(employee);
            }
            return list;
        });
    }
}
```

### applicationContext.xml
Remained unchanged as in the previous examples

### Main.java
This class gets the bean from the **applicationContext.xml** file and calls the **getAllEmployees()** method of EmployeeDao class.

```java
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        EmployeeDao dao = (EmployeeDao) ctx.getBean("employeeDao");
        List<Employee> list = dao.getAllEmployees();

        for (Employee employee : list) {
            System.out.println(employee);
        }
    }
}
```

### pom.xml 
Remained unchanged as in the previous example
