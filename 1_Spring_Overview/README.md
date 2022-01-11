# Spring Framework
Spring is a lightweight framework. It can be thought of as a ***framework of frameworks*** because it provides support to various frameworks such as *Struts, Hibernate, Tapestry, EJB, JSF, etc*. The framework, in broader sense, can be defined as a structure where we find solution of the various technical problems.
## Advantages of Spring Framework
### 1. **Predefined Templates**
   - Spring framework provides templates for JDBC, Hibernate, JPA etc. technologies. So there is no need to write too much code. It hides the basic steps of these technologies.
### 2. **Loose Coupling**
   - The Spring applications are loosely coupled because of dependency injection.
### 3. **Easy to test**
   - The Dependency Injection makes it easier to test the application. The EJB or Struts application requires a server to run the application but the Spring framework doesn't        require a server.
### 4. **Lightweight**
   - The Spring framework is lightweight because of its POJO implementation. The Spring Framework doesn't force the programmer to inherit any class or implement any interface.        That is why it is said to be non-invasive.
### 5. **Fast Development**
   - The Dependency Injection feature of Spring Framework and its support for various frameworks makes the development of JavaEE applications easy.
### 6. **Powerful abstraction**
   - It provides powerful abstraction to JavaEE specifications such as JMS, JDBC, JPA and JTA.
### 7. **Declarative support**
   - It provides declarative support for caching, validation, transactions and formatting.
## Spring Modules
The Spring framework comprises many modules such as core, beans, context, expression language, AOP, Aspects, Instrumentation, JDBC, ORM, OXM, JMS, Transaction, Web, Servlet, Struts etc. These modules are grouped into Test, Core Container, AOP, Aspects, Instrumentation, Data Access / Integration, Web (MVC / Remoting)

![This is spring modules](/image/spring-modules.png)

### - **Test**
  - This layer provides support for testing with *JUnit* and *TestNG*.
### - **Spring Core Container**
  - **Core and Beans**
    - These modules provide *IOC* and *Dependency Injection* features.
  - **Context**
    - This module supports *internationalization (I18N), EJB, JMS, Basic Remoting*.
  - **Expression Language**
    - It is an extension to the EL defined in *JSP*. It provides support for setting and getting property values, method invocation, accessing collections and indexers, named variables, logical and arithmetic operators, retrieval of objects by name, etc.
### - **AOP, Aspects and Instrumentation**
  - These modules support aspect oriented programming implementation where you can use *Advice, Pointcuts etc.* to decouple the code.
  - The aspects module provides support for integration with *AspectJ*.
  - The instrumentation module provides support for class instrumentation and classloader implementations.
### - **Data Access / Integration**
  - This group comprises *JDBC, ORM, OXM, JMS* and *Transaction modules*. These modules basically provide support to interact with the database.
### - **Web**
  - This group comprises *Web, Web-Servlet, Web-Struts* and *Web-Portlet*. These modules provide support to create web applications.
