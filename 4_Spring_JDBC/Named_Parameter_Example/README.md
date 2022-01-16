# Spring NamedParameterJdbcTemplate Example
Spring provides another way to insert data by named parameter. In such way, we use names instead of ?(question mark). So it is better to remember the data for the column.

### Simple example of named parameter query

```java
insert into employee values (:id, :name, :salary)  
```

## Example of NamedParameterJdbcTemplate class
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
It contains on property jdbcTemplate and one method save.

```java
public class EmployeeDao {
    private NamedParameterJdbcTemplate jdbcTemplate;
    
    public EmployeeDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Employee employee) {
        String query = "insert into employee values (:id, :name, :salary)";

        Map<String, Object> map = new HashMap<>();
        map.put("id", employee.getId());
        map.put("name", employee.getName());
        map.put("salary", employee.getSalary());

        jdbcTemplate.execute(query, map, new PreparedStatementCallback() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }
}
```

### applicationContext.xml
The DriverManagerDataSource is used to contain the information about the database such as driver class name, connnection URL, username and password.

There are a property named datasource in the **NamedParameterJdbcTemplate** class of DriverManagerDataSource type. So, we need to provide the reference of DriverManagerDataSource object in the NamedParameterJdbcTemplate class for the datasource property.

Here, we are using the NamedParameterJdbcTemplate object in the EmployeeDao class, so we are passing it by the constructor but you can use setter method also.

```xml
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

    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate">
        <constructor-arg ref="dataSource"/>
    </bean>

    <bean id="employeeDao" class="io.spring.framework.EmployeeDao">
        <constructor-arg ref="jdbcTemplate"/>
    </bean>

</beans>
```

### Main.java
This class gets the bean from the applicationContext.xml file and calls the save method.

```java
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        EmployeeDao dao = (EmployeeDao) context.getBean("employeeDao");

        dao.save(new Employee(2, "Hurshida", 25000));
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
