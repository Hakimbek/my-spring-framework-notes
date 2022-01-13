package io.spring.framework;

public class PrintableFactory {

    public static Printable getPrintable() {
        // return new B();
        return new A(); //return any one instance, either A or B
    }

}
