package io.spring.framework;

import java.util.Iterator;
import java.util.List;

public class Question {
    private int id;
    private String name;
    private List<String> answers;

    // Default constructor
    public Question() {

    }

    // All args constructor
    public Question(int id, String name, List<String> answers) {
        this.id = id;
        this.name = name;
        this.answers = answers;
    }

    // Display method
    public void displayInfo() {
        System.out.println(id + " " + name);
        System.out.println("answers are:");
        Iterator<String> itr = answers.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}
