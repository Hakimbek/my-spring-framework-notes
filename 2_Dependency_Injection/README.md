# Dependency Injection
When writing a complex Java application, application classes should be as independent as possible of other Java classes to increase the possibility to reuse these classes and to test them independently of other classes while unit testing. Dependency Injection helps in gluing these classes together and at the same time keeping them independent.

What is dependency injection exactly? Let's look at these two words separately. Here the dependency part translates into an association between two classes. For example, class A is dependent of class B. Now, let's look at the second part, injection. All this means is, class B will get injected into class A by the IoC.

Dependency injection can happen in the way of passing parameters to the constructor or by post-construction using setter methods.

# IoC Container
The IoC container is responsible for **instantiating, configuring** and **assembling** the objects. The IoC container gets information from the XML file and works accordingly. The main tasks performed by IoC container are:
- *to instantiate the application class*
- *to configure the object*
- *to assemble the dependencies between the objects*

The container gets its instructions on what objects to instantiate, configure, and assemble by reading the configuration metadata provided. The configuration metadata can be represented either by **XML, Java annotations** or **Java code**.

### There are two types of IoC containers. They are:
- **BeanFactory**
- **ApplicationContext**

### Difference between BeanFactory and the ApplicationContext
The *org.springframework.beans.factory.BeanFactory* and the *org.springframework.context.ApplicationContext* interfaces act as the IoC container. The ApplicationContext interface is built on top of the BeanFactory interface. It adds some extra functionality than BeanFactory such as simple integration with Spring's AOP, message resource handling (for I18N), event propagation and application layer specific context (e.g. WebApplicationContext) for web applications. So it is better to use ApplicationContext than BeanFactory.

### Using BeanFactory
The XmlBeanFactory is the implementation class for the BeanFactory interface. To use the BeanFactory, we need to create the instance of XmlBeanFactory class as given below:

```java
Resource resource = new ClassPathResource("applicationContext.xml");  
BeanFactory factory = new XmlBeanFactory(resource);
```

The constructor of the XmlBeanFactory class receives the Resource object, so we need to pass the resource object to create the object of BeanFactory.

### Using ApplicationContext
The ClassPathXmlApplicationContext class is the implementation class of the ApplicationContext interface. We need to instantiate the ClassPathXmlApplicationContext class to use the ApplicationContext as given below:

```java
ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
```

The constructor of the ClassPathXmlApplicationContext class receives a string, so we can pass the name of the xml file to create the instance of ApplicationContext.

## Dependency Injection in Spring
Dependency Injection (DI) is a design pattern that removes the dependency from the programming code so that it can be easy to manage and test the application. 
Dependency Injection makes our programming code loosely coupled. In such case we write the code as:

```java
class Employee {  
  Address address;  
  
  // Constructor
  Employee(Address address) {  
    this.address = address;  
  } 
  
  // Setter
  public void setAddress(Address address) {  
    this.address = address;  
  }  
  
}  
```

In such case, instance of Address class is provided by external souce such as XML file either by constructor or setter method.

### Spring framework provides two ways to inject dependency
- By Constructor
- By Setter method

## Dependency Injection by Constructor
We can inject the dependency by constructor. The `<constructor-arg>` subelement of `<bean>` is used for constructor injection. Here we are going to inject

- primitive and String-based values
- Dependent object (contained object)
- Collection values etc.

## Dependency Injection by setter method
We can inject the dependency by setter method also. The `<property>` subelement of `<bean>` is used for setter injection. Here we are going to inject

- primitive and String-based values
- Dependent object (contained object)
- Collection values etc.

## Difference between constructor and setter injection
1. **Partial dependency:** can be injected using setter injection but it is not possible by constructor. Suppose there are 3 properties in a class, having 3 arg constructor and setter methods. In such a case, if you want to pass information for only one property, it is possible by the setter method only. 
2. **Overriding:** Setter injection overrides the constructor injection. If we use both constructor and setter injection, IOC containers will use the setter injection.
3. **Changes:** We can easily change the value by setter injection. It doesn't always create a new bean instance like a constructor. So setter injection is more flexible than constructor injection.


