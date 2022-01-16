package io.spring.framework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        EmployeeDao dao = (EmployeeDao) ctx.getBean("employeeDao");
        List<Employee> list = dao.getAllEmployees();

        for (Employee employee : list) {
            System.out.println(employee);
        }
    }
}

