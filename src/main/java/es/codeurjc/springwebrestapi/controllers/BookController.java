package es.codeurjc.springwebrestapi.controllers;

import es.codeurjc.springwebrestapi.models.Book;
import es.codeurjc.springwebrestapi.services.BookService;
import es.codeurjc.springwebrestapi.services.ImageService;
import es.codeurjc.springwebrestapi.services.PublisherService;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BookController {

    private static final String BOOKS_FOLDER = "books";

    private BookService bookService;
    private PublisherService publisherService;
    private ImageService imageService;

    public BookController(BookService bookService, PublisherService publisherService, ImageService imageService) {
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.imageService = imageService;
    }

    @GetMapping("/")
    public String getBasePage(Model model) {
        model.addAttribute("books", this.bookService.findAll());

        return "base";
    }

    @GetMapping("/book/new")
    public String newBook(Model model) {

        model.addAttribute("publishers", this.publisherService.findAll());
        return "newBook/base";
    }

    @PostMapping("/book/new")
    public String newBook(Model model, Book book, MultipartFile image) throws IOException {

        bookService.save(book);
        model.addAttribute("title", book.getTitle());
        imageService.saveImage(BOOKS_FOLDER, book.getId(), image);

        return "saved_book";
    }
}
