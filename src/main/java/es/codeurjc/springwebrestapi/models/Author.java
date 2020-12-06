package es.codeurjc.springwebrestapi.models;

public class Author {

    private String name;
    private String surname;

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Author get() {
        return this;
    }

    public String toString() {
        return this.name + " " + this.surname;
    }
}
