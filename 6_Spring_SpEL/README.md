# Spring Expression Language (SPEL) Tutorial
SpEL is an exression language supporting the features of querying and manipulating an object graph at runtime.

There are many expression languages available such as JSP EL, OGNL, MVEL and JBoss EL. SpEL provides some additional features such as method invocation and string templating functionality.

The SpEL API provides many interfaces and classes. They are as follows:

- Expression interface
- SpelExpression class
- ExpressionParser interface
- SpelExpressionParser class
- EvaluationContext interface
- StandardEvaluationContext class

```java
public class Main {
    public static void main(String[] args) {
        ExpressionParser expressionParser = new SpelExpressionParser();

        Expression exp = expressionParser.parseExpression("'Hello'");
        String value = (String) exp.getValue();
        System.out.println(value);
    }
}
```

## Other SPEL Example
Let's see a lot of useful examples of SPEL. Here, we are assuming all the examples have been written inside the **main()** method.

### Using concat() method with String

```java
ExpressionParser parser = new SpelExpressionParser();  
Expression exp = parser.parseExpression("'Welcome SPEL'.concat('!')");  
String message = (String) exp.getValue();  
System.out.println(message);  
```

### Converting String into byte array

```java
Expression exp = parser.parseExpression("'Hello World'.bytes");  
byte[] bytes = (byte[]) exp.getValue();  
for(int i=0;i<bytes.length;i++){  
    System.out.print(bytes[i]+" ");  
}  
```

### Getting length after converting string into bytes

```java
Expression exp = parser.parseExpression("'Hello World'.bytes.length");  
int length = (Integer) exp.getValue();  
System.out.println(length);  
```

### Converting String contents into uppercase letter

```java
Expression exp = parser.parseExpression("new String('hello world').toUpperCase()");  
String message = exp.getValue(String.class);  
System.out.println(message);  
```

## Operators in SPEL
We can use many operators in SpEL such as arithmetic, relational, logical etc. There are given a lot of examples of using different operators in SpEL.

```java
public class Main {  
   public static void main(String[] args) {  
      ExpressionParser parser = new SpelExpressionParser();  
  
      //arithmetic operator  
      System.out.println(parser.parseExpression("'Welcome SPEL'+'!'").getValue());  
      System.out.println(parser.parseExpression("10 * 10/2").getValue());  
      System.out.println(parser.parseExpression("'Today is: '+ new java.util.Date()").getValue());  
  
      //logical operator  
      System.out.println(parser.parseExpression("true and true").getValue());  
  
      //Relational operator  
      System.out.println(parser.parseExpression("'sonoo'.length()==5").getValue());  
   }  
}  
```

## Variable in SPEL | StandardEvaluationContext
In SpEL, we can store a value in the variable and use the variable in the method and call the method. To work on variable, we need to use **StandardEvaluationContext** class.

```java
public class Main {
    public static void main(String[] args) {
        Calculation calculation = new Calculation();
        StandardEvaluationContext context = new StandardEvaluationContext(calculation);

        ExpressionParser parser = new SpelExpressionParser();
        parser.parseExpression("number").setValue(context, 5);

        System.out.println(calculation.cube());
    }
}
```

### Dependency in my pom.xml file

```xml
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-expression</artifactId>
  <version>latest-ver</version>
</dependency>
```
