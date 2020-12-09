package es.codeurjc.springwebrestapi.controllers;

import es.codeurjc.springwebrestapi.models.Book;
import es.codeurjc.springwebrestapi.models.Comment;
import es.codeurjc.springwebrestapi.services.BookService;
import es.codeurjc.springwebrestapi.services.CommentService;
import es.codeurjc.springwebrestapi.services.ImageService;
import es.codeurjc.springwebrestapi.services.PublisherService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BookController {

    private static final String BOOKS_FOLDER = "books";

    private final BookService bookService;
    private final PublisherService publisherService;
    private final ImageService imageService;
    private final CommentService commentService;

    public BookController(BookService bookService, PublisherService publisherService, ImageService imageService, CommentService commentService) {
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.imageService = imageService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public String getBasePage(Model model) {
        model.addAttribute("books", this.bookService.findAll());

        return "base";
    }

    @GetMapping("/book/new")
    public String gerNewBookPage(Model model) {
        model.addAttribute("publishers", this.publisherService.findAll());

        return "new_book/base";
    }

    @PostMapping("/book/new")
    public String newBook(Model model, Book book, MultipartFile image) throws IOException {
        bookService.save(book);
        if (image.getSize() != 0) {
            bookService.itsOwnImage(book);
            imageService.saveImage(BOOKS_FOLDER, book.getId(), image);
        }

        model.addAttribute("title", book.getTitle());

        return "saved_book";
    }

    @GetMapping("/book/{id}")
    public String getImage( Model model, @PathVariable Long id, HttpSession session) {
        Book book = bookService.findById(id);
        List<Comment> comments = commentService.findAllCommentsPerBook(id);
        model.addAttribute("book", book);
        model.addAttribute("comments", comments);
        model.addAttribute("userSession", commentService.getSessionUser(session));

        return "book_details/base";
    }

    @GetMapping("/book/{id}/image")
    public ResponseEntity<Object> getImage(@PathVariable int id) throws MalformedURLException {
        return imageService.createResponseFromImage(BOOKS_FOLDER, id);
    }

    @PostMapping("book/{id}/rate")
    public String rateBook(@PathVariable long id, Model model, Integer rating, HttpSession session) {
        Book book = bookService.findById(id);
        List<Comment> comments = commentService.findAllCommentsPerBook(id);
        book.setRating(rating);
        model.addAttribute("book", book);
        model.addAttribute("comments", comments);
        model.addAttribute("userSession", commentService.getSessionUser(session));

        return "book_details/base";
    }

}
