# Spring AOP AspectJ Annotation Example
Spring AspectJ AOP implementation provides many annotations:

- **@Aspect** declares the class as aspect.
- **@Pointcut** declares the pointcut expression.

The annotations used to create advices are given below:

- **@Before** declares the before advice. It is applied before calling the actual method.
- **@After** declares the after advice. It is applied after calling the actual method and before returning result.
- **@AfterReturning** declares the after returning advice. It is applied after calling the actual method and before returning result. But you can get the result value in the advice.
- **@Around** declares the around advice. It is applied before and after calling the actual method.
- **@AfterThrowing** declares the throws advice. It is applied if actual method throws exception.

## Understanding Pointcut
Pointcut is an expression language of Spring AOP.

The **@Pointcut** annotation is used to define the pointcut. We can refer the pointcut expression by name also.
Let's try the understand the pointcut expressions by the examples given below:

```java
@Pointcut("execution(public * *(..))")  
```
It will be applied on all the public methods.

---

```java
@Pointcut("execution(public Operation.*(..))")  
```
It will be applied on all the public methods of Operation class.

---

```java
@Pointcut("execution(* Operation.*(..))")  
```
It will be applied on all the methods of Operation class.

---

```java
@Pointcut("execution(public Employee.set*(..))")  
```
It will be applied on all the public setter methods of Employee class.

---

```java
@Pointcut("execution(int Operation.*(..))")  
```
It will be applied on all the methods of Operation class that returns int value.

---

# 1. @Before Example

The AspectJ Before Advice is applied before the actual business logic method. You can perform any operation here such as conversion, authentication etc.

### Operation.java
Create a class that contains actual business logic.

```java
public  class Operation {  
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
@Aspect  
public class TrackOperation {  
    @Pointcut("execution(* Operation.*(..))")  
    public void k() {} //pointcut name  
      
    @Before("k()") //applying pointcut on before advice  
    public void myAdvice(JoinPoint jp) { //it is advice (before advice)   
        System.out.println("additional concern");  
        //System.out.println("Method Signature: "  + jp.getSignature());  
    }  
}  
```

### applicationContext.xml
Now create the applicationContext.xml file that defines beans

### Main.java
Now, let's call the actual method.

```java
public class Main {  
    public static void main(String[] args) {  
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Operation e = (Operation) context.getBean("operationBean");

        System.out.println("calling msg");
        e.msg();
        System.out.println();

        System.out.println("calling m");
        e.m();
        System.out.println();

        System.out.println("calling k");
        e.k();
    }  
}  
```

# 2. @After Example

The AspectJ after advice is applied after calling the actual business logic methods. It can be used to maintain log, security, notification etc.

Here, We are assuming that **Operation.java, applicationContext.xml* and **Test.java** files are same as given in @Before example.

### TrackOperation.java
Create the aspect class that contains after advice.

```java
@Aspect  
public class TrackOperation {  
    @Pointcut("execution(* Operation.*(..))")  
    public void k() {} //pointcut name  
      
    @After("k()") //applying pointcut on after advice  
    public void myadvice(JoinPoint jp) { //it is advice (after advice)  
        System.out.println("additional concern");  
        //System.out.println("Method Signature: "  + jp.getSignature());  
    }  
}  
```

# 3. @AfterReturning Example
By using after returning advice, we can get the result in the advice.

### Operation.java
Create the class that contains business logic.

```java
public  class Operation{  
    public int m() {
      System.out.println("m() method invoked");
      return 2;
    }
    
    public int k() {
      System.out.println("k() method invoked");
      return 3;
    }  
}  
```

### TrackOperation.java
Create the aspect class that contains after returning advice.

```java
@Aspect
public class TrackOperation {

    @AfterReturning(pointcut = "execution(* Operation.*(..))", returning = "result")
    public void myAdvice(JoinPoint jp, Object result) { //it is advice (after returning advice)
        System.out.println("additional concern");
        System.out.println("Method Signature: " + jp.getSignature());
        System.out.println("Result in advice: " + result);
        System.out.println("end of after returning advice...");
    }

}
```

### applicationContext.xml
It is same as given in **@Before** and **@After** advice example

### Main.java
Now create the Test class that calls the actual methods.

```java
ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Operation e = (Operation) context.getBean("operationBean");

        System.out.println("calling m...");
        System.out.println(e.m());
        System.out.println();

        System.out.println("calling k...");
        System.out.println(e.k());
```

# 4. @Around Example
he AspectJ around advice is applied before and after calling the actual business logic methods.

Here, we are assuming that applicationContext.xml file is same as given in **@Before** and **@After** example.

### Operation.java
Create a class that contains actual business logic.

```java
public  class Operation {  
    public void msg() {
      System.out.println("msg() is invoked");
    }
    
    public void display() {
      System.out.println("display() is invoked");
    }  
}  
```

### TrackOperation.java
Create the aspect class that contains around advice.

You need to pass the **PreceedingJoinPoint** reference in the advice method, so that we can proceed the request by calling the proceed() method.

```java
@Aspect  
public class TrackOperation {  
    @Pointcut("execution(* Operation.*(..))")  
    public void abcPointcut(){}  
      
    @Around("abcPointcut()")  
    public Object myAdvice(ProceedingJoinPoint pjp) throws Throwable {  
        System.out.println("Additional Concern Before calling actual method");  
        Object obj = pjp.proceed();  
        System.out.println("Additional Concern After calling actual method");  
        return obj;  
    }  
}  
```

### Main.java
Now create the Test class that calls the actual methods.

```java
public class Test{  
    public static void main(String[] args){  
        ApplicationContext context = new new ClassPathXmlApplicationContext("applicationContext.xml");
        Operation op = (Operation) context.getBean("operationBean");  
        op.msg();  
        op.display();  
    }  
}  
```

# 5. @AfterThrowing Example
By using after throwing advice, we can print the exception in the TrackOperation class. Let's see the example of AspectJ AfterThrowing advice.

### Operation.java
Create the class that contains business logic.

```java
public  class Operation{  
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
@Aspect  
public class TrackOperation {  
    @AfterThrowing(pointcut = "execution(* Operation.*(..))", throwing= "error")  
    public void myAdvice(JoinPoint jp,Throwable error) { //it is advice    
        System.out.println("additional concern");  
        System.out.println("Method Signature: " + jp.getSignature());  
        System.out.println("Exception is: " + error);  
        System.out.println("end of after throwing advice...");  
    }  
}  
```

### applicationContext.xml
It is same as given in **@Before** and **@Advice** advice example

### Main.java
Now create the Test class that calls the actual methods.

```java
public class Test{  
    public static void main(String[] args) {  
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
        Operation op = (Operation) context.getBean("operationBean");  
        
        System.out.println("calling validate...");  
        try {  
           op.validate(19);  
        } catch(Exception e) {
           System.out.println("Exception");
        }
        
        System.out.println("calling validate again...");    
        try {  
           op.validate(11);  
        } catch(Exception e) {
           System.out.println("Exception");
        }  
    }  
}  
```
