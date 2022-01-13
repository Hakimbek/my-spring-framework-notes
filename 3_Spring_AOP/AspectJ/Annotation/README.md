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

--

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
