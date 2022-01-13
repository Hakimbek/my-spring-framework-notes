package io.spring.framework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Validator validator = (Validator) applicationContext.getBean("proxy");

        try {
            validator.validate(17);
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }
}
