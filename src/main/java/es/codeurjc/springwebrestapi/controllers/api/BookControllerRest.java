package es.codeurjc.springwebrestapi.controllers.api;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.springwebrestapi.models.Book;
import es.codeurjc.springwebrestapi.models.BookDto;
import es.codeurjc.springwebrestapi.models.Comment;
import es.codeurjc.springwebrestapi.models.mappers.BookMapper;
import es.codeurjc.springwebrestapi.services.BookService;
import es.codeurjc.springwebrestapi.services.CommentService;

@RestController
@RequestMapping("/api")
public class BookControllerRest {

    private BookService bookService;
    private CommentService commentService;
    private BookMapper bookMapper;

    public BookControllerRest(BookService bookService, BookMapper bookMapper, CommentService commentService) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
        this.commentService = commentService;
    }

    @JsonView(BookDto.Basic.class)
    @GetMapping("/books")
    public List<BookDto> getBooks() {
        return this.convertToDto(this.bookService.findAll());
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable Long id) {
        BookDto book = this.convertToDto(bookService.findById(id));

        if (book != null) {
            return ResponseEntity.ok(book);
        }

        return ResponseEntity.notFound().build();
    }

    private List<BookDto> convertToDto(Collection<Book> books){

        return books
        .stream()
        .map(book -> {
            List<Comment> comments = this.commentService.findAllCommentsPerBook(book.getId());
            return this.bookMapper.toDto(book, comments);
        })
        .collect(Collectors.toList());
    }

    private BookDto convertToDto(Book book){
        List<Comment> comments = this.commentService.findAllCommentsPerBook(book.getId());
        return this.bookMapper.toDto(book, comments);
    }

}
