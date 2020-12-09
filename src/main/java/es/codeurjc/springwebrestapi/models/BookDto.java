package es.codeurjc.springwebrestapi.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

public class BookDto {

    public interface BasicDto {}

    @JsonView(BasicDto.class)
    private final Long id;

    @JsonView(BasicDto.class)
    private final String title;

    private final String summary;
    private final String author;
    private final String publisher;
    private final String publicationDate;
    private final Integer rating;
    private final List<Comment> comments;

    public BookDto(
        String title,
        String summary,
        String author,
        String publisher,
        String publicationDate,
        Integer rating,
        Long id,
        List<Comment> comments
    ) {
        this.title = title;
        this.summary = summary;
        this.author = author;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.rating = rating;
        this.id = id;
        this.comments = comments;
    }

    public String getTitle() {
        return this.title;
    }

    public String getSummary() {
        return this.summary;
    }

    @JsonIgnore
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

    @JsonIgnore
    public List<Comment> getCommets() {
        return this.comments;
    }
}
