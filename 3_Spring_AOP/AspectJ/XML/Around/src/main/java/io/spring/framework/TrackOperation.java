package io.spring.framework;

import org.aspectj.lang.ProceedingJoinPoint;

public class TrackOperation {

    public Object myAdvice(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Additional Concern Before calling actual method");
        Object obj = pjp.proceed();
        System.out.println("Additional Concern After calling actual method");
        return obj;
    }

}
