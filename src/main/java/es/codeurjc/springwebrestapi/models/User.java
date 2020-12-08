package es.codeurjc.springwebrestapi.models;

public class User {

    private String name;
    private String lastName;

    public User(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return this.name + " " + this.lastName;
    }
}
