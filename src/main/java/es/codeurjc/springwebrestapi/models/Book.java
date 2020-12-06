package es.codeurjc.springwebrestapi.models;

public class Book {

    private Long id;
    private String title;
    private String summary;
    private Author author;
    private String publisher;
    private int publicationDate;

    public Book(String title, String summary, Author author, String publisher ,int publicationDate) {
        this.title = title;
        this.summary = summary;
        this.author = author;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
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

    @Override
    public String toString() {
        return "Book: [id="+id+", title="+title+", summary="+summary+", author="+author.toString()+", publisher="+publisher+", publicationDate="+publicationDate+"]";
    }
}
