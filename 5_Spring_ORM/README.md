# Spring with ORM Frameworks
Spring provides API to easily integrate Spring with **ORM** frameworks such as **Hibernate, JPA(Java Persistence API), JDO(Java Data Objects), Oracle Toplink** and **iBATIS**.

## Advantage of ORM Frameworks with Spring
- **Less coding is required:** By the help of Spring framework, you don't need to write extra codes before and after the actual database logic such as getting the connection, starting transaction, commiting transaction, closing connection etc.
- **Easy to test:** Spring's IoC approach makes it easy to test the application.
- **Better exception handling:** Spring framework provides its own API for exception handling with ORM framework.
- **Integrated transaction management:** By the help of Spring framework, we can wrap our mapping code with an explicit template wrapper class or AOP style method interceptor.

# Hibernate and Spring Integration
We can simply integrate hibernate application with spring application.

In hibernate framework, we provide all the database information **hibernate.cfg.xml** file.

But if we are going to integrate the hibernate application with spring, we don't need to create the **hibernate.cfg.xml** file. We can provide all the information in the **applicationContext.xml** file.

### Advantage of Spring framework with hibernate
The Spring framework provides **HibernateTemplate** class, so you don't need to follow so many steps like create Configuration, BuildSessionFactory, Session, beginning and committing transaction etc.
So it saves a lot of code.

Understanding problem without using spring:

Let's understand it by the code of hibernate given below:

```java
//creating configuration  
Configuration cfg = new Configuration();    
cfg.configure("hibernate.cfg.xml");    
    
//creating seession factory object    
SessionFactory factory = cfg.buildSessionFactory();    
    
//creating session object    
Session session = factory.openSession();    
    
//creating transaction object    
Transaction t = session.beginTransaction();    
        
Employee e1 = new Employee(111,"Hakim",40000);    
session.persist(e1); //persisting the object    
    
t.commit(); //transaction is commited    
session.close();    
```
