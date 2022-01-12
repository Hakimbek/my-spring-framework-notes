package io.spring.framework;

public class Address {
    private String city;
    private String state;
    private String country;

    // All arg constructor
    public Address(String city, String state, String country) {
        this.city = city;
        this.state = state;
        this.country = country;
    }

    // toString method
    public String toString() {
        return city + " " + state + " " + country;
    }
}
