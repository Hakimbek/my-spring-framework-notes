# Example of Spring JdbcTemplate

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
