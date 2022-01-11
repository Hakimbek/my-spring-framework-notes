package io.spring.injection;

public class Employee {
    private int id;
    private String name;

    // Constructors
    public Employee() {System.out.println("def cons");}

    public Employee(int id) {this.id = id;}

    public Employee(String name) {  this.name = name;}

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Show method
    void show(){
        System.out.println(id + " " + name);
    }

}
