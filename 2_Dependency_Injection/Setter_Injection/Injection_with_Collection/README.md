# Setter Injection with Collection
We can inject collection values by setter method in spring framework. There can be used three elements inside the property element.

It can be:
- **List**
- **Set**
- **Map**

Each collection can have string based and non-string based values.
In this example, we are taking the example of Forum where One question can have multiple answers. There are three pages:

- Question.java
- applicationContext.xml
- Main.java
- 
In this example, we are using list that can have duplicate elements, you may use set that have only unique elements. But, you need to change list to set in the applicationContext.xml file and List to Set in the Question.java file.

### Question.java
This class contains three properties with setters and getters and displayInfo() method that prints the information. Here, we are using List to contain the multiple answers.

```java
public class Question {  
  private int id;  
  private String name;  
  private List<String> answers;  
  
  //setters and getters  
  
  public void displayInfo() {  
    System.out.println(id + " " + name);  
    System.out.println("answers are:");  
    Iterator<String> itr = answers.iterator();  
    while (itr.hasNext()) {  
        System.out.println(itr.next());  
    }  
  }  
  
}  
```

### applicationContext.xml
The list element of constructor-arg is used here to define the list.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="question" class="io.spring.framework.Question">
        <property name="id" value="1"/>
        <property name="name" value="What is Java?"/>
        <property name="answers">
            <list>
                <value>Java is a programming language</value>
                <value>Java is a platform</value>
                <value>Java is an Island</value>
            </list>
        </property>
    </bean>

</beans>
```

### Main.java
This class gets the bean from the applicationContext.xml file and calls the displayInfo method.

```java
public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Question question = (Question) context.getBean("question");
        question.displayInfo();

    }
}
```
