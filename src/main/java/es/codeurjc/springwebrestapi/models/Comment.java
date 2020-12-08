package es.codeurjc.springwebrestapi.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Comment {

    private String content;
    private String publishedDate;
    private long id;
    private Integer rating;
    private long bookId;
    private User user;

    public Comment(String content, Integer rating, User user, long bookId) {
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

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public Long getBookId() {
        return this.bookId;
    }
}
