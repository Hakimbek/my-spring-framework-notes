# Constructor Injection with Collection
We can inject collection values by constructor in spring framework. There can be used three elements inside the constructor-arg element.

It can be:
- list
- set
- map
Each collection can have string based and non-string based values.
In this example, we are taking the example of Forum where One question can have multiple answers. There are three pages:

- Question.java
- applicationContext.xml
- Test.java
In this example, we are using list that can have duplicate elements, you may use set that have only unique elements. But, you need to change list to set in the applicationContext.xml file and List to Set in the Question.java file.

### Question.java
This class contains three properties, two constructors and displayInfo() method that prints the information. Here, we are using List to contain the multiple answers.
