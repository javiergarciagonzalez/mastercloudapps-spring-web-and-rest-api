package es.codeurjc.springwebrestapi.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {

    private Long id;
    private String title;
    private String summary;
    private String author;
    private String publisher;
    private String publicationDate;
    private Integer rating;
    private Boolean hasCustomImage;

    @JsonCreator
    public Book(
        @JsonProperty("title") String title,
        @JsonProperty("summary") String summary,
        @JsonProperty("author") String author,
        @JsonProperty("publisher") String publisher,
        @JsonProperty("publicationDate") String publicationDate,
        @JsonProperty("rating") Integer rating
    ) {
        this.title = title;
        this.summary = summary;
        this.author = author;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.rating = rating;
        this.hasCustomImage = false;
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

    public String getSummary() {
        return this.summary;
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

    public Boolean getHasCustomImage() {
        return hasCustomImage;
    }

    public Integer getRating() {
        return this.rating;
    }

    public void setHasCustomImage(Boolean value) {
        this.hasCustomImage = value;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return
            "Book: [id="+id+
            ", title="+title+
            ", summary="+summary+
            ", author="+author.toString()+
            ", publisher="+publisher+
            ", publicationDate="+publicationDate+
            ", rating:"+rating.toString()+"]";
    }
}
