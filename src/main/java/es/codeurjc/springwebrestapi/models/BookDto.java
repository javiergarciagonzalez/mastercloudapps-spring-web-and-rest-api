package es.codeurjc.springwebrestapi.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonView;

public class BookDto {

    public interface Basic {}

    @JsonView(Basic.class)
    public final Long id;

    @JsonView(Basic.class)
    public final String title;

    public String summary;
    public String author;
    public String publisher;
    public String publicationDate;
    public Integer rating;

    @JsonCreator
    public BookDto(
        String title,
        String summary,
        String author,
        String publisher,
        String publicationDate,
        Integer rating,
        Long id
    ) {
        this.title = title;
        this.summary = summary;
        this.author = author;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.rating = rating;
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getSummary() {
        return this.summary;
    }

    public Long getId() {
        return this.id;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public String getPublicationDate() {
        return this.publicationDate;
    }

    public Integer getRating() {
        return this.rating;
    }
}