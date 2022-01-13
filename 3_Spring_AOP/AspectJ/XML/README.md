# Spring AOP AspectJ Xml Configuration Example
Let's see the xml elements that are used to define advice.

- **aop:before** It is applied before calling the actual business logic method.
- **aop:after** It is applied after calling the actual business logic method.
- **aop:after-returning** it is applied after calling the actual business logic method. It can be used to intercept the return value in advice.
- **aop:around** It is applied before and after calling the actual business logic method.
- **aop:after-throwing** It is applied if actual business logic method throws exception.

## 1. aop:before Example
The AspectJ Before Advice is applied before the actual business logic method. You can perform any operation here such as conversion, authentication etc.

### Operation.java
Create a class that contains actual business logic.

```java
public class Operation {
    public void msg() {
        System.out.println("msg method invoked");
    }

    public int m() {
        System.out.println("m method invoked");
        return 2;
    }

    public int k() {
        System.out.println("k method invoked");
        return 3;
    }
}
```

### TrackOperation.java
Now, create the aspect class that contains before advice.

```java
public class TrackOperation {

    public void myAdvice(JoinPoint jp) { //it is advice
        System.out.println("additional concern");
        //System.out.println("Method Signature: " + jp.getSignature());
    }

}
```

### applicationContext.xml
Now create the applicationContext.xml file that defines beans.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd">

    <aop:aspectj-autoproxy/>

    <bean id="operationBean" class="io.spring.framework.Operation"/>
    <bean id="trackAspect" class="io.spring.framework.TrackOperation"/>

    <aop:config>
        <aop:aspect id="myAspect" ref="trackAspect" >
            <!-- @Before -->
            <aop:pointcut id="pointCutBefore" expression="execution(* io.spring.framework.Operation.*(..))" />
            <aop:before method="myAdvice" pointcut-ref="pointCutBefore" />
        </aop:aspect>
    </aop:config>

</beans>
```

### Main.java
Now, let's call the actual method.

```java
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Operation e = (Operation) context.getBean("operationBean");

        System.out.println("calling msg...");
        e.msg();

        System.out.println("calling m...");
        e.m();

        System.out.println("calling k...");
        e.k();
    }
}
```

## 2. aop:after example
The AspectJ after advice is applied after calling the actual business logic methods. It can be used to maintain log, security, notification etc.

Here, We are assuming that **Operation.java, TrackOperation.java** and **Test.java** files are same as given in aop:before example.

### applicationContext.xml
Now create the applicationContext.xml file that defines beans.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd">

    <aop:aspectj-autoproxy/>

    <bean id="operationBean" class="io.spring.framework.Operation"/>
    <bean id="trackAspect" class="io.spring.framework.TrackOperation"/>

    <aop:config>
        <aop:aspect id="myAspect" ref="trackAspect" >
            <!-- @After -->
            <aop:pointcut id="pointCutAfter" expression="execution(* io.spring.framework.Operation.*(..))" />
            <aop:after method="myAdvice" pointcut-ref="pointCutAfter" />
        </aop:aspect>
    </aop:config>

</beans>
```

## 3. aop:after-returning example
By using after returning advice, we can get the result in the advice.

### Operation.java
Create the class that contains business logic.

```java
public class Operation {
    public int m() {
        System.out.println("m method invoked");
        return 2;
    }

    public int k() {
        System.out.println("k method invoked");
        return 3;
    }
}
```

### TrackOperation.java
Create the aspect class that contains after returning advice.

```java
public class TrackOperation {

    public void myAdvice(JoinPoint jp, Object result) { //it is advice (after advice)
        System.out.println("additional concern");
        System.out.println("Method Signature: " + jp.getSignature());
        System.out.println("Result in advice: " + result);
        System.out.println("end of after returning advice...");
    }

}
```

### applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd">

    <aop:aspectj-autoproxy />

    <bean id="operationBean" class="io.spring.framework.Operation"/>
    <bean id="trackAspect" class="io.spring.framework.TrackOperation"/>

    <aop:config>
        <aop:aspect id="myAspect" ref="trackAspect" >
            <!-- @AfterReturning -->
            <aop:pointcut id="pointCutAfterReturning"   expression="execution(* io.spring.framework.Operation.*(..))" />
            <aop:after-returning method="myAdvice" returning="result" pointcut-ref="pointCutAfterReturning" />
        </aop:aspect>
    </aop:config>

</beans>
```

### Main.java
Now create the Test class that calls the actual methods.

```java
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Operation e = (Operation) context.getBean("operationBean");

        System.out.println("calling m...");
        System.out.println(e.m());

        System.out.println("calling k...");
        System.out.println(e.k());
    }
}
```

## 4. aop:around example
The AspectJ around advice is applied before and after calling the actual business logic methods.

### Operation.java
Create a class that contains actual business logic.

```java
public class Operation {
    public void msg() {
        System.out.println("msg method invoked");
    }

    public void m() {
        System.out.println("m method invoked");
    }

    public void k() {
        System.out.println("k method invoked");
    }
}
```

### TrackOperation.java
Create the aspect class that contains around advice.

You need to pass the **PreceedingJoinPoint** reference in the advice method, so that we can proceed the request by calling the proceed() method.

```java
public class TrackOperation {

    public Object myAdvice(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Additional Concern Before calling actual method");
        Object obj = pjp.proceed();
        System.out.println("Additional Concern After calling actual method");
        return obj;
    }
    
}
```

### applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd">

    <aop:aspectj-autoproxy />

    <bean id="opBean" class="io.spring.framework.Operation"/>
    <bean id="trackAspect" class="io.spring.framework.TrackOperation"/>

    <aop:config>
        <aop:aspect id="myAspect" ref="trackAspect" >
            <!-- @Around -->
            <aop:pointcut id="pointCutAround"   expression="execution(* io.spring.framework.Operation.*(..))" />
            <aop:around method="myAdvice" pointcut-ref="pointCutAround" />
        </aop:aspect>
    </aop:config>

</beans>
```

### Main.java
Now create the Test class that calls the actual methods.

```java
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Operation op = (Operation) context.getBean("opBean");
        op.msg();
        op.m();
    }
}
```

## 5. aop:after-throwing example
By using after throwing advice, we can print the exception in the TrackOperation class. Let's see the example of AspectJ AfterThrowing advice.

### Operation.java
Create the class that contains business logic.

```java
public class Operation {
    public void validate(int age) throws Exception {
        if (age < 18) {
            throw new ArithmeticException("Not valid age");
        } else {
            System.out.println("Thanks for vote");
        }
    }
}
```

### TrackOperation.java
Create the aspect class that contains after throwing advice.

Here, we need to pass the Throwable reference also, so that we can intercept the exception here.

```java
public class TrackOperation {

    public void myAdvice(JoinPoint jp,Throwable error) { //it is advice
        System.out.println("additional concern");
        System.out.println("Method Signature: " + jp.getSignature());
        System.out.println("Exception is: " + error);
        System.out.println("end of after throwing advice...");
    }

}
```

### applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd">

    <aop:aspectj-autoproxy />
    <bean id="operationBean" class="io.spring.framework.Operation"/>
    <bean id="trackAspect" class="io.spring.framework.TrackOperation"/>

    <aop:config>
        <aop:aspect id="myAspect" ref="trackAspect" >
            <!-- @AfterThrowing -->
            <aop:pointcut id="pointCutAfterThrowing"    expression="execution(* io.spring.framework.Operation.*(..))" />
            <aop:after-throwing method="myAdvice" throwing="error" pointcut-ref="pointCutAfterThrowing" />
        </aop:aspect>
    </aop:config>

</beans>
```

### Main.java

```java
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Operation op = (Operation) context.getBean("operationBean");
        
        System.out.println("calling validate...");
        try {
            op.validate(19);
        } catch (Exception e) {
            System.out.println("Exception");
        }
        
        System.out.println("calling validate again...");
        try {
            op.validate(11);
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }
}
```
