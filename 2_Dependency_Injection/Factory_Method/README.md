# Dependency Injection with Factory Method in Spring
Spring framework provides facility to inject bean using factory method. To do so, we can use two attributes of bean element.

- **factory-method:** represents the factory method that will be invoked to inject the bean.
- **factory-bean:** represents the reference of the bean by which factory method will be invoked. It is used if factory method is non-static.

A method that returns instance of a class is called **factory method**.

```java
public class A {  
  public static A getA() { //factory method  
    return new A();  
  }  
}  
```

## Factory Method Types
There can be three types of factory method:

1. A **static factory method** that returns instance of **its own** class. It is used in singleton design pattern.

```xml
<bean id="a" class="io.spring.framework.A" factory-method="getA" />
```

2. A **static factory method** that returns an instance of **another** class. It is used when an instance is not known and decided at runtime.

```xml
<bean id="b" class="io.spring.framework.A" factory-method="getB" />
```

3. A **non-static factory method** that returns instance of **another** class. It is used when an instance is not known and decided at runtime.

```xml
<bean id="a" class="io.spring.framework.A"></bean>  
<bean id="b" class="io.spring.framework.A" factory-method="getB" factory-bean="a"></bean>
```

---

## Type 1
Let's see the full example to inject dependency using factory method in spring. To create this example, we have created 3 files.

- A.java
- applicationContext.xml
- Main.java

### A.java
This class is a singleton class.

```java
public class A {  
  private static final A obj = new A();  
  
  private A() {
    System.out.println("private constructor");
  }
  
  public static A getA() {  
    System.out.println("factory method ");  
    return obj;  
  }
  
  public void msg(){  
    System.out.println("hello user");  
  }  
}  
```

### applicationContext.xml

### Test.java
This class gets the bean from the applicationContext.xml file and calls the msg method.

```
public class Main {
  public static void main(String[] args) {  

    ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");  
    A a = (A) context.getBean("a");  
    a.msg();  
    
  }  
}  
```
