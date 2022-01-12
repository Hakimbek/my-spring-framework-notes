# Injecting primitive and string-based values by setter method
Let's see the simple example to inject primitive and string-based values by setter method. We have created three files here:

- Employee.java
- applicationContext.xml
- Main.java

### Employee.java
It is a simple class containing three fields id, name and city with its setters and getters and a method to display these informations.

```java
public class Employee {  
  private int id;  
  private String name;  
  private String city;  
  
  public int getId() {  
    return id;  
  }
  
  public void setId(int id) {  
    this.id = id;  
  }
  
  public String getName() {  
    return name;  
  }
  
  public void setName(String name) {  
    this.name = name;  
  }  
  
  public String getCity() {  
    return city;  
  }
  
  public void setCity(String city) {  
    this.city = city;  
  }
  
  void display() {  
    System.out.println(id + " " + name + " " + city);  
  }  
  
}  
```

### applicationContext.xml
We are providing the information into the bean by this file. The property element invokes the setter method. The value subelement of property will assign the specified value.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="employee" class="io.spring.framework.Employee">
        <property name="id" value="22"/>
        <property name="name" value="Hakim"/>
        <property name="city" value="Urgench"/>
    </bean>

</beans>
```

### Main.java
This class gets the bean from the applicationContext.xml file and calls the display method.

```java
public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Employee employee = (Employee) context.getBean("employee");
        employee.display();

    }
}
```
