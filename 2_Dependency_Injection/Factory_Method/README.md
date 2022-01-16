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
<bean id="a" class="io.spring.framework.A"/>  
<bean id="b" class="io.spring.framework.A" factory-method="getB" factory-bean="a"/>
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

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="a" class="io.spring.framework.A" factory-method="getA" />

</beans>
```

### Main.java
This class gets the bean from the applicationContext.xml file and calls the msg method.

```java
public class Main {
  public static void main(String[] args) {  

    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
    A a = (A) context.getBean("a");  
    a.msg();  
    
  }  
}  
```

---

## Type 2
Let's see the simple code to inject the dependency by static factory method that returns the instance of another class.
To create this example, we have created 6 files.

- Printable.java
- A.java
- B.java
- PrintableFactory.java
- applicationContext.xml
- Main.java

### Printable.java

```java
public interface Printable {  
  void print();  
}  
```

### A.java

```java
public class A implements Printable{  
   
    @Override  
    public void print() {  
        System.out.println("hello a");  
    }  
  
}  
```

### B.java

```java
public class B implements Printable{  
   
    @Override  
    public void print() {  
        System.out.println("hello b");  
    }  
  
}  
```

### PrintableFactory.java

```java
public class PrintableFactory {  
  public static Printable getPrintable(){  
    // return new B();  
      return new A(); //return any one instance, either A or B  
  }  
}  
```

### applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="printableFactory" class="io.spring.framework.PrintableFactory" factory-method="getPrintable" />

</beans>
```

### Main.java
This class gets the bean from the applicationContext.xml file and calls the print() method.

```java
public class Main {  
  public static void main(String[] args) {  
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
    Printable p = (Printable) context.getBean("printableFactory");  
    p.print();  
  }  
}  
```

---

## Type 3
Let's see the example to inject the dependency by non-static factory method that returns the instance of another class.

To create this example, we have created 6 files.

- Printable.java
- A.java
- B.java
- PrintableFactory.java
- applicationContext.xml
- Main.java

All files are same as previous, you need to change only 2 files: PrintableFactory and applicationContext.xml.

### PrintableFactory.java

```java
public class PrintableFactory {  

  //non-static factory method  
  public Printable getPrintable(){  
    return new A(); //return any one instance, either A or B  
  }  
  
}  
```

### applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="printableFactory" class="com.javatpoint.PrintableFactory" />
    <bean id="printableFactoryObject" class="io.spring.framework.PrintableFactory" factory-method="getPrintable" factory-bean="printableFactory" />

</beans>
```
