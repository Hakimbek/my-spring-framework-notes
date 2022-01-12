# Setter Injection with Dependent Object
Like Constructor Injection, we can inject the dependency of another bean using setters. In such a case, we use the property element. Here, our scenario is Employee HAS-A Address. The Address class object will be termed as the dependent object. Let's see the Address class first:

### Address.java
This class contains four properties, setters and getters and toString() method.

```java
public class Address {  
  private String city;
  private String state;
  private String country;  
  
  //getters and setters  
  
  public String toString() {  
    return city + " " + state + " " + country;  
  }  
```

### Employee.java
It contains three properties id, name and address(dependent object) , setters and getters with displayInfo() method.

```java
public class Employee {  
  private int id;  
  private String name;  
  private Address address;  
  
  //setters and getters  
  
  void displayInfo() {  
    System.out.println(id + " " + name);  
    System.out.println(address);  
  }  
}  
```

### applicationContext.xml
The **ref** attribute of **property** elements is used to define the reference of another bean.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="address" class="io.spring.framework.Address">
        <property name="city" value="Urgench"/>
        <property name="state" value="Xorezm"/>
        <property name="country" value="Uzbekistan"/>
    </bean>

    <bean id="employee" class="io.spring.framework.Employee">
        <property name="id" value="1"/>
        <property name="name" value="Hakimbek Bahramov"/>
        <property name="address" ref="address"/>
    </bean>

</beans>
```

### Main.java
This class gets the bean from the applicationContext.xml file and calls the displayInfo() method.

```java
public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Employee employee = (Employee) context.getBean("employee");
        employee.displayInfo();
        
    }
}
```
