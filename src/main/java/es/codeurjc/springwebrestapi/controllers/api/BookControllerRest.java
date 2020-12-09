package es.codeurjc.springwebrestapi.controllers.api;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.springwebrestapi.models.Book;
import es.codeurjc.springwebrestapi.models.BookDto;
import es.codeurjc.springwebrestapi.models.mappers.BookMapper;
import es.codeurjc.springwebrestapi.services.BookService;

@RestController
@RequestMapping("/api")
public class BookControllerRest {

    private BookService bookService;
    private BookMapper bookMapper;

    public BookControllerRest(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @JsonView(BookDto.Basic.class)
    @GetMapping("/books")
    public List<BookDto> getBooks() {
        return this.convertToDto(this.bookService.findAll());
    }

    private List<BookDto> convertToDto(Collection<Book> books){
        return books
        .stream()
        .map(book -> this.bookMapper.toDto(book))
        .collect(Collectors.toList());
    }

}
