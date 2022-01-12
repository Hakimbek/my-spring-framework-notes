package io.spring.framework;

public class Employee {
    private int id;
    private String name;
    private Address address; //Aggregation

    // Default constructor
    public Employee() {
        System.out.println("def cons");
    }

    // All arg constructor
    public Employee(int id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    // Show method
    void show() {
        System.out.println(id + " " + name);
        System.out.println(address.toString());
    }
}
