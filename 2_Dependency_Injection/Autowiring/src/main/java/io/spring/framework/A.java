package io.spring.framework;

public class A {
    B b;

    A() {
//        this.b = b;
        System.out.println("a is created");
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    void print() {
        System.out.println("hello a");
    }

    void display() {
        print();
        b.print();
    }
}
