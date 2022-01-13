package io.spring.framework;

public class PrintableFactory {

    public Printable getPrintable(){
        return new A(); //return any one instance, either A or B
    }

}
