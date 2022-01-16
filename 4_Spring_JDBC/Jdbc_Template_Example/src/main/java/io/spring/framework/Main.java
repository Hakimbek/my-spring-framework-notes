package io.spring.framework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        EmployeeDao dao = (EmployeeDao) ctx.getBean("employeeDao");
        int status = dao.saveEmployee(new Employee(1, "Hakim", 35000));
        System.out.println(status);


//        int status = dao.updateEmployee(new Employee(1, "Hakim", 15000));
//        System.out.println(status);

//        Employee employee = new Employee();
//        employee.setId(1);
//        int status = dao.deleteEmployee(employee);
//        System.out.println(status);

    }
}
