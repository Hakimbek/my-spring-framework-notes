# RowMapper Example | Fetching records by Spring JdbcTemplate
Like ResultSetExtractor, we can use RowMapper interface to fetch the records from the database using **query()** method of JdbcTemplate class. In the execute of we need to pass the instance of **RowMapper** now.

Syntax of query method using RowMapper

```java
public T query(String sql, RowMapper<T> rm)  
```

### RowMapper Interface
RowMapper interface allows to map a row of the relations with the instance of user-defined class. It iterates the ResultSet internally and adds it into the collection. So we don't need to write a lot of code to fetch the records as ResultSetExtractor.

### Advantage of RowMapper over ResultSetExtractor
RowMapper saves a lot of code becuase it internally adds the data of ResultSet into the collection.

## Example of RowMapper Interface to show all the records of the table
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

```java
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
It contains on property jdbcTemplate and one method **getAllEmployeesRowMapper**.

```java
public class EmployeeDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> getAllEmployeesRowMapper() {
        return jdbcTemplate.query("select * from employee", (rs, rowNumber) -> {
            Employee employee = new Employee();
            employee.setId(rs.getInt(1));
            employee.setName(rs.getString(2));
            employee.setSalary(rs.getInt(3));
            return employee;
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
This class gets the bean from the **applicationContext.xml** file and calls the **getAllEmployeesRowMapper()** method of EmployeeDao class.

```java
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
        EmployeeDao dao=(EmployeeDao)ctx.getBean("employeeDao");
        List<Employee> list=dao.getAllEmployeesRowMapper();

        for(Employee e:list) {
            System.out.println(e);
        }
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
