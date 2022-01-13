# Spring AOP Example
There are given examples of **Spring1.2 old style AOP** (dtd based) implementation.

Though it is supported in spring 3, but it is recommended to use spring aop with **AspectJ**.

There are 4 types of advices supported in Spring1.2 old style AOP implementation.

- **Before Advice** it is executed before the actual method call.
- **After Advice** it is executed after the actual method call. If method returns a value, it is executed after returning value.
- **Around Advice** it is executed before and after the actual method call.
- **Throws Advice** it is executed if actual method throws exception.

## 1. MethodBeforeAdvice Example

### A.java
Create a class that contains actual business logic.

```java
public class A {  
  public void m() {
    System.out.println("actual business logic");
  }  
}  
```

### BeforeAdvisor.java
Now, create the advisor class that implements MethodBeforeAdvice interface.

```java
public class BeforeAdvisor implements MethodBeforeAdvice {  
    @Override  
    public void before(Method method, Object[] args, Object target) throws Throwable {  
        System.out.println("additional concern before actual logic");  
    }  
}  
```

### applicationContext.xml
In xml file, create 3 beans, one for A class, second for Advisor class and third for ProxyFactoryBean class.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="a" class="io.spring.framework.A"/>
    <bean id="beforeAdvisor" class="io.spring.framework.BeforeAdvisor"/>

    <bean id="proxy" class="org.springframework.aop.framework.ProxyFactoryBean">
        <property name="target" ref="a"/>
        <property name="interceptorNames">
            <list>
                <value>beforeAdvisor</value>
            </list>
        </property>
    </bean>

</beans>
```

### Understanding ProxyFactoryBean class:

The ProxyFactoryBean class is provided by Spring Famework. It contains 2 properties target and interceptorNames. The instance of A class will be considered as target object and the instance of advisor class as interceptor. You need to pass the advisor object as the list object as in the xml file given above.

The ProxyFactoryBean class is written something like this:

```java
public class ProxyFactoryBean{  
  private Object target;  
  private List interceptorNames;  
  
  //getters and setters  
}  
```

### Main.java
Now, let's call the actual method.

```java
public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        A a = (A) context.getBean("proxy");
        a.m();

    }
}
```
