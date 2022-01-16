package io.spring.framework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");

        EmployeeDao dao=(EmployeeDao)ctx.getBean("employeeDao");
        dao.saveEmployeeByPreparedStatement(new Employee(1,"Hakim",35000));
    }
}
