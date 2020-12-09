package es.codeurjc.springwebrestapi.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Comment {

    private String content;
    private User user;
    private Integer rating;
    @JsonIgnore
    private String publishedDate;
    @JsonIgnore
    private Long id;
    @JsonIgnore
    private Long bookId;

    public Comment(String content, Integer rating, User user, Long bookId) {
        this.content = content;
        this.rating = rating;
        this.user = user;
        this.bookId = bookId;
        this.publishedDate = this.getCurrentDateTime();
    }

    private String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return now.format(formatter);
    }

    public String getContent() {
        return this.content;
    }

    public String getPublishedDate() {
        return this.publishedDate;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public Long getBookId() {
        return this.bookId;
    }

    public Integer getRating() {
        return this.rating;
    }

    public User getUser() {
        return this.user;
    }

    public Long getId() {
        return this.id;
    }

}
