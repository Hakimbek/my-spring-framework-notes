package io.spring.framework;

import org.springframework.aop.ThrowsAdvice;

public class ThrowAdvisor implements ThrowsAdvice {

    public void afterThrowing(Exception ex){
        System.out.println("additional concern if exception occurs");
    }

}
