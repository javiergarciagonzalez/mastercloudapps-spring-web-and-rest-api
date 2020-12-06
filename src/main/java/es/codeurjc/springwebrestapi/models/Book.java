package es.codeurjc.springwebrestapi.models;

public class Book {

    private Long id;
    private String title;

    public Book(String title) {
        this.setTitle(title);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public Long getId() {
        return this.id;
    }
}
