package io.spring.framework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Operation e = (Operation) context.getBean("operationBean");

        System.out.println("calling m...");
        System.out.println(e.m());
        System.out.println();

        System.out.println("calling k...");
        System.out.println(e.k());
    }
}
