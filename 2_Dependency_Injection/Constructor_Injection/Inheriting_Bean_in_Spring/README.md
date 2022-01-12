# Inheriting Bean in Spring
By using the parent attribute of bean, we can specify the inheritance relation between the beans. In such case, parent bean values will be inherited to the current bean.

Let's see the simple example to inherit the bean.

### Employee.java
This class contains three properties, three constructor and show() method to display the values.

```java
public class Employee {  
  private int id;  
  private String name;  
  private Address address;  
  
  public Employee() {}  
  
  public Employee(int id, String name) {  
    this.id = id;  
    this.name = name;  
  }  
  
  public Employee(int id, String name, Address address) {  
    this.id = id;  
    this.name = name;  
    this.address = address;  
  }  
  
  void show() {  
    System.out.println(id + " " + name);  
    System.out.println(address);  
  }  
  
}  
```

### Address.java

```
public class Address {  
  private String city;
  private String state;
  private String country;  
  
  public Address(String city, String state, String country) {  
    this.city = city;  
    this.state = state;  
    this.country = country;  
  }
  
  public String toString(){  
    return city + " " + state + " " + country;  
  }  
  
}  
```

### applicationContext.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

  <bean id="employee1" class="io.spring.framework.Employee">  
    <constructor-arg value="101" />  
    <constructor-arg  value="Hakim" />
  </bean>  
  
  <bean id="address" class="io.spring.framework.Address">  
    <constructor-arg value="Urgench" />
    <constructor-arg value="Xorezm" />
    <constructor-arg value="Uzbekistan" />
  </bean>  
  
  <bean id="employee2" class="io.spring.framework.Employee" parent="employee1">  
    <constructor-arg ref="address" />
  </bean>  

</beans>
```

### Main.java
This class gets the bean from the applicationContext.xml file and calls the show method.

```
public class Main {
    public static void main(String[] args) {
        
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Employee employee = (Employee) context.getBean("employee2");
        employee.show();
        
    }
}
```
