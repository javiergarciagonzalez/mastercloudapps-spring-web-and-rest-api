package es.codeurjc.springwebrestapi.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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
        @JsonProperty("title") String title,
        @JsonProperty("id") Long id
    ) {
        this.title = title;
        this.id = id;
    }

    @JsonCreator
    public BookDto(
        @JsonProperty("title") String title,
        @JsonProperty("summary") String summary,
        @JsonProperty("author") String author,
        @JsonProperty("publisher") String publisher,
        @JsonProperty("publicationDate") String publicationDate,
        @JsonProperty("rating") Integer rating,
        @JsonProperty("id") Long id
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
