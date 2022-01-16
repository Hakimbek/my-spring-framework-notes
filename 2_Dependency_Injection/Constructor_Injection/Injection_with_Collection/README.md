# Constructor Injection with Collection
We can inject collection values by constructor in spring framework. There can be used three elements inside the constructor-arg element.

It can be:
- **List**
- **Set**
- **Map**

Each collection can have string based and non-string based values.
In this example, we are taking the example of Forum where One question can have multiple answers. There are three pages:

- Question.java
- applicationContext.xml
- Main.java

In this example, we are using list that can have duplicate elements, you may use set that have only unique elements. But, you need to change list to set in the applicationContext.xml file and List to Set in the Question.java file.

### Question.java
This class contains three properties, two constructors and **displayInfo()** method that prints the information. Here, we are using List to contain the multiple answers.

```java
public class Question {  
  private int id;  
  private String name;  
  private List<String> answers;  
  
  public Question() {}  
  
  public Question(int id, String name, List<String> answers) {    
    this.id = id;  
    this.name = name;  
    this.answers = answers;  
  }  
  
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
    <constructor-arg value="3" />  
    <constructor-arg value="What is java?" />
    <constructor-arg>  
      <list>  
        <value>Java is a programming language</value>  
        <value>Java is a Platform</value>  
        <value>Java is an Island of Indonasia</value>  
      </list>  
    </constructor-arg>  
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

---

## Constructor Injection with Non-String Collection (having Dependent Object)
If we have dependent object in the collection, we can inject these information by using the *ref* element inside the list, set or map.

In this example, we are taking the example of Forum where One question can have multiple answers. But Answer has its own information such as *answerId, answer* and *postedBy*. There are four pages used in this example:

- Question.java
- Answer.java
- applicationContext.xml
- Main.java

In this example, we are using list that can have duplicate elements, you may use set that have only unique elements. But, you need to change list to set in the applicationContext.xml file and List to Set in the Question.java file.

### Question.java
This class contains three properties, two constructors and displayInfo() method that prints the information. Here, we are using List to contain the multiple answers.

```java
public class Question {  
  private int id;  
  private String name;  
  private List<Answer> answers;  
  
  public Question() {}  
  
  public Question(int id, String name, List<Answer> answers) {  
    this.id = id;  
    this.name = name;  
    this.answers = answers;  
  }  
  
  public void displayInfo() {  
    System.out.println(id + " " + name);  
    System.out.println("answers are:");  
    Iterator<Answer> itr = answers.iterator();  
    while (itr.hasNext()) {  
        System.out.println(itr.next());  
    }  
  }  
  
}  
```

### Answer.java
This class has three properties id, name and by with constructor and toString() method.

```java
public class Answer {  
  private int id;  
  private String name;  
  private String by;  
  
  public Answer() {}  
  
  public Answer(int id, String name, String by) {  
    super();  
    this.id = id;  
    this.name = name;  
    this.by = by;  
  }  
  
  public String toString() {  
    return id + " " + name + " " + by;  
  }  
}  
```

### applicationContext.xml
The **ref** element is used to define the reference of another bean. Here, we are using bean attribute of ref element to specify the reference of another bean.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

  <bean id="answer1" class="io.spring.framework.Answer">  
    <constructor-arg value="1" /> 
    <constructor-arg value="Java is a programming language" />  
    <constructor-arg value="Hakim" />
  </bean>  
  
  <bean id="answer2" class="io.spring.framework.Answer">  
    <constructor-arg value="2" />
    <constructor-arg value="Java is a Platform" />
    <constructor-arg value="Orif" />
  </bean>  
  
  <bean id="question" class="io.spring.framework.Question">  
    <constructor-arg value="3" />  
    <constructor-arg value="What is java?" />  
    <constructor-arg>  
      <list>  
        <ref bean="answer1" />
        <ref bean="answer2" />
      </list>  
    </constructor-arg>  
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

---

## Constructor Injection with Map
In this example, we are using map as the answer that have answer with posted username. Here, we are using key and value pair both as a string.

Like previous examples, it is the example of forum where one question can have multiple answers.

### Question.java
This class contains three properties, two constructors and displayInfo() method to display the information.

```java
public class Question {  
  private int id;  
  private String name;  
  private Map<String,String> answers;  
  
  public Question() {}  
  
  public Question(int id, String name, Map<String, String> answers) {  
    this.id = id;  
    this.name = name;  
    this.answers = answers;  
  }  
  
  public void displayInfo() {
        System.out.println("question id: " + id);
        System.out.println("question name: " + name);
        System.out.println("Answers....");
        Set<Map.Entry<String, String>> set = answers.entrySet();
        Iterator<Map.Entry<String, String>> itr = set.iterator();
        while (itr.hasNext()) {
            Map.Entry<String,String> entry = itr.next();
            System.out.println("Answer: " + entry.getKey() + " Posted By: " + entry.getValue());
        }
    }
}  
```

### applicationContext.xml
The entry attribute of map is used to define the key and value information.

```xml
<?xml version="1.0" encoding="UTF-8"?>  
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  
  <bean id="question" class="io.spring.framework.Question">  
    <constructor-arg value="11" />  
    <constructor-arg value="What is Java?" />
    <constructor-arg>  
      <map>  
        <entry key="Java is a Programming Language" value="Hakimbek Bhramov"></entry>  
        <entry key="Java is a Platform" value="Orif"></entry>  
        <entry key="Java is an Island" value="Xurshida"></entry>  
      </map>  
    </constructor-arg>  
  </bean>  
  
</beans>  
```

### Main.java
This class gets the bean from the applicationContext.xml file and calls the displayInfo() method.

```java
public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Question question = (Question) context.getBean("question");
        question.displayInfo();

    }
}
```

---

## Constructor Injection with Non-String Map (having dependent Object)
In this example, we are using map as the answer that have Answer and User. Here, we are using key and value pair both as an object. Answer has its own information such as *answerId, answer* and *postedDate*, User has its own information such as *userId, username* and *emailId*.

Like previous examples, it is the example of forum where one question can have multiple answers.

### Question.java
This class contains three properties, two constructors and displayInfo() method to display the information.

```java
public class Question {  
  private int id;  
  private String name;  
  private Map<Answer,User> answers;  
  
  public Question() {}  
  
  public Question(int id, String name, Map<Answer, User> answers) {  
    this.id = id;  
    this.name = name;  
    this.answers = answers;  
  }  
  
  public void displayInfo(){
        System.out.println("question id: " + id);
        System.out.println("question name: " + name);
        System.out.println("Answers....");
        Set<Map.Entry<Answer, User>> set = answers.entrySet();
        Iterator<Map.Entry<Answer, User>> itr = set.iterator();
        while (itr.hasNext()) {
            Map.Entry<Answer, User> entry = itr.next();
            Answer ans = entry.getKey();
            User user = entry.getValue();
            System.out.println("Answer Information:");
            System.out.println(ans);
            System.out.println("Posted By:");
            System.out.println(user);
        }
    }
  }
  
}  
```

### Answer.java

```java
public class Answer {  
  private int id;  
  private String answer;  
  private Date postedDate;
  
  public Answer() {}  
  
  public Answer(int id, String answer, Date postedDate) {  
    this.id = id;  
    this.answer = answer;  
    this.postedDate = postedDate;  
  }  
  
  public String toString(){  
    return "Id:" + id + " Answer: " + answer + " Posted Date: " + postedDate;  
  }  
}  
```

### User.java

```java
public class User {  
  private int id;  
  private String name,email;  
  
  public User() {}  
  
  public User(int id, String name, String email) {  
    this.id = id;  
    this.name = name;  
    this.email = email;  
  }  
  
  public String toString(){  
    return "Id: " + id + " Name: " + name + " Email Id: " + email;  
  }  
}  
```

### applicationContext.xml
The **key-ref** and **value-ref** attributes of entry element is used to define the reference of bean in the map.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

  <bean id="answer1" class="io.spring.framework.Answer">  
    <constructor-arg value="1" />  
    <constructor-arg value="Java is a Programming Language" />  
    <constructor-arg value="12/12/2001" />  
  </bean>  
  
  <bean id="answer2" class="io.spring.framework.Answer">  
    <constructor-arg value="2" />  
    <constructor-arg value="Java is a Platform" />  
    <constructor-arg value="12/12/2003" />  
  </bean>  
  
  <bean id="user1" class="io.spring.framework.User">  
    <constructor-arg value="1" />  
    <constructor-arg value="Hakim Bahramov" />  
    <constructor-arg value="abduhakim.bahramov@gmail.com" />  
  </bean>  
  
  <bean id="user2" class="io.spring.framework.User">  
    <constructor-arg value="2" />  
    <constructor-arg value="Xurshida Bahramova" />  
    <constructor-arg value="khurshida.bakhramova@gmail.com" />  
  </bean>  
  
  <bean id="question" class="io.spring.framework.Question">  
    <constructor-arg value="1" />  
    <constructor-arg value="What is Java?" />  
    <constructor-arg>  
      <map>  
        <entry key-ref="answer1" value-ref="user1" />  
        <entry key-ref="answer2" value-ref="user2" />  
      </map>  
    </constructor-arg>  
  </bean>  

</beans>
```

### Main.java
This class gets the bean from the applicationContext.xml file and calls the displayInfo() method to display the information.

```java
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Question question = (Question) context.getBean("question");
        question.displayInfo();
    }
}
```
