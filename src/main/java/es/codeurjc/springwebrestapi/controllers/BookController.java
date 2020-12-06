package es.codeurjc.springwebrestapi.controllers;

import es.codeurjc.springwebrestapi.services.BookService;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String getBasePage(Model model) {
        model.addAttribute("books", bookService.findAll());

        return "base";
    }


    @GetMapping("/book/new")
    public String newBook(Model model) {
        ArrayList<String> publishers = new ArrayList<String>();
        publishers.add("Red circle");
        publishers.add("Blue circle");
        publishers.add("Green circle");
        publishers.add("Yellow circle");
        model.addAttribute("publishers", publishers);
        return "newBook/base";
    }
}
