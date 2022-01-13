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
