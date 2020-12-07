package es.codeurjc.springwebrestapi.models;

public class Book {

    private Long id;
    private String title;
    private String summary;
    private String author;
    private String publisher;
    private String publicationDate;
    private String imageName;
    private Integer rating;

    public Book(String title, String summary, String author, String publisher , String publicationDate) {
        this.title = title;
        this.summary = summary;
        this.author = author;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.rating = 0;
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

    public void addCustomImageName(String path) {
        this.imageName = "/images/" + path + ("/image-" + this.id + ".jpg");
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
