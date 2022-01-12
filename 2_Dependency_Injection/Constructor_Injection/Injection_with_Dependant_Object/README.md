# Constructor Injection with Dependent Object
If there is a HAS-A relationship between the classes, we create the instance of a dependent object (contained object) first then pass it as an argument of the main class 
constructor. Here, our scenario is Employee HAS-A Address. The Address class object will be termed as the dependent object. Let's see the Address class first:

### Address.java
This class contains three properties, one constructor and toString() method to return the values of these object.

```java
public class Address {  
  private String city;  
  private String state;  
  private String country;  
  
  // All args constructor
  public Address(String city, String state, String country) {  
    this.city = city;  
    this.state = state;  
    this.country = country;  
  }  
  
  // toString method
  public String toString() {  
    return city + " " + state + " " + country;  
  }  
}
```

### Employee.java
It contains three properties id, name and address(dependent object) ,two constructors and show() method to show the records of the current object including the depedent object.

```java
public class Employee {  
  private int id;  
  private String name;  
  private Address address; //Aggregation  
  
  // Default constructor
  public Employee() {
    System.out.println("def cons");
  }  
  
  // All args constructor
  public Employee(int id, String name, Address address) {    
    this.id = id;  
    this.name = name;  
    this.address = address;  
  }  
  
  // Show method
  void show() {  
    System.out.println(id + " " + name);  
    System.out.println(address.toString());  
  }  
}  
```

### applicationContext.xml
The ref attribute is used to define the reference of another object, such way we are passing the dependent object as an constructor argument.

```xml
<?xml version="1.0" encoding="UTF-8"?>  
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  
  <bean id="address" class="my.spring.framework.dependencyInjection.consInjectionWithDepObj.Employee">  
    <constructor-arg value="Urgench" />
    <constructor-arg value="Xorezm" />  
    <constructor-arg value="Uzbekistan" />
  </bean>  
  
  <bean id="employee" class="my.spring.framework.dependencyInjection.consInjectionWithDepObj.Employee">  
    <constructor-arg value="22" type="int" />  
    <constructor-arg value="Hakim" />
    <constructor-arg ref="address" />  
  </bean>  
  
</beans>  
```

### Main.java
This class gets the bean from the applicationContext.xml file and calls the show method.

```java 
public class Main {  
    public static void main(String[] args) {  
          
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Employee employee = (Employee) applicationContext.getBean("employee");
        employee.show(); 
          
    }  
}  
```
