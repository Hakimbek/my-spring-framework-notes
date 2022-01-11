# Injecting primitive and string-based values
Let's see the simple example to inject primitive and string-based values. We have created three files here:

- Employee.java
- applicationContext.xml
- Test.java

---

### Employee.java
It is a simple class containing two fields id and name. There are four constructors and one method in this class.

```java
package com.javatpoint;  
  
public class Employee {  
  private int id;  
  private String name;  
  
  // Constructors
  public Employee() {
    System.out.println("def cons");
  }  
  
  public Employee(int id) {
    this.id = id;
  }  
  
  public Employee(String name) {  
    this.name = name;
  }  
  
  public Employee(int id, String name) {  
    this.id = id;  
    this.name = name;  
  }  
  
  // Show method
  void show(){  
    System.out.println(id + " " + name);  
  }  
  
}  
```

### applicationContext.xml
We are providing the information into the bean with this file. The constructor-arg element invokes the constructor. In such a case, a parameterized constructor of int type will be invoked. The value attribute of the constructor-arg element will assign the specified value. The type attribute specifies that the int parameter constructor will be invoked.

```xml
<?xml version="1.0" encoding="UTF-8"?>  
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  
<bean id="employee" class="com.javatpoint.Employee">  
  <constructor-arg value="10" type="int" /> 
</bean>  
  
</beans>  
```

### Test.java
This class gets the bean from the applicationContext.xml file and calls the show method.

```java
package com.javatpoint;  
  
import org.springframework.beans.factory.BeanFactory;  
import org.springframework.beans.factory.xml.XmlBeanFactory;  
import org.springframework.core.io.*;  
  
public class Test {  
  public static void main(String[] args) {  
  
    Resource r=new ClassPathResource("applicationContext.xml");  
    BeanFactory factory=new XmlBeanFactory(r);  
          
    Employee s=(Employee)factory.getBean("e");  
    s.show();  
  }  
  
}  
```
### Output: 10 null

---

### Injecting string-based values
If you don't specify the type attribute in the constructor-arg element, by default string type constructor will be invoked.

```xml
<bean id="employee" class="com.javatpoint.Employee">  
  <constructor-arg value="10" />
</bean>  
```
If you change the bean element as given above, string parameter constructor will be invoked and the output will be 0 10.

### Output: 0 10

---

You may also pass the string literal as following:

```xml
<bean id="employee" class="com.javatpoint.Employee">  
  <constructor-arg value="Hakim" />
</bean>  
```
### Output: 0 Hakim

---

You may pass integer literal and string both as following

```xml
<bean id="employee" class="com.javatpoint.Employee">  
  <constructor-arg value="10" type="int" />  
  <constructor-arg value="Hakim" />  
</bean>  
```
### Output: 10 Hakim
