# Example of PreparedStatement in Spring JdbcTemplate
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
