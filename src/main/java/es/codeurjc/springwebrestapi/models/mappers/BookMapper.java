package es.codeurjc.springwebrestapi.models.mappers;

import java.util.List;

import org.springframework.stereotype.Service;

import es.codeurjc.springwebrestapi.models.Book;
import es.codeurjc.springwebrestapi.models.BookDto;
import es.codeurjc.springwebrestapi.models.Comment;

@Service
public class BookMapper {

    public Book toDomain(BookDto book){
        return new Book(
            book.getTitle(),
            book.getSummary(),
            book.getAuthor(),
            book.getPublisher(),
            book.getPublicationDate(),
            book.getRating()
        );
    }

    public BookDto toDto(Book book, List<Comment> comments){
        return new BookDto(
            book.getTitle(),
            book.getSummary(),
            book.getAuthor(),
            book.getPublisher(),
            book.getPublicationDate(),
            book.getRating(),
            book.getId(),
            comments);
    }

}
