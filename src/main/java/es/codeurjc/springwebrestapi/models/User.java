package es.codeurjc.springwebrestapi.models;

public class User {

    private String name;
    private String lastName;

    public User(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return this.name;
    }

    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String toString() {
        return this.name + " " + this.lastName;
    }
}
