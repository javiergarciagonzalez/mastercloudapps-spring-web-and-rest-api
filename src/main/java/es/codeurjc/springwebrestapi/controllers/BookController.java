package es.codeurjc.springwebrestapi.controllers;

import es.codeurjc.springwebrestapi.services.BookService;
import es.codeurjc.springwebrestapi.services.PublisherService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    private BookService bookService;
    private PublisherService publisherService;

    public BookController(BookService bookService, PublisherService publisherService) {
        this.bookService = bookService;
        this.publisherService = publisherService;
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


}
