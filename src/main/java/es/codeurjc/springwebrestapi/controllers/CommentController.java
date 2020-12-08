package es.codeurjc.springwebrestapi.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.codeurjc.springwebrestapi.models.Book;
import es.codeurjc.springwebrestapi.models.Comment;
import es.codeurjc.springwebrestapi.services.BookService;
import es.codeurjc.springwebrestapi.services.CommentService;

@Controller
public class CommentController {

    private final CommentService commentService;
    private final BookService bookService;

    public CommentController(CommentService commentService, BookService bookService) {
        this.commentService = commentService;
        this.bookService = bookService;
    }

    @PostMapping("comment/new/{bookId}")
    public String createComment(@PathVariable long id, Model model, Comment comment) {
        commentService.save(comment);
        Book book = bookService.findById(id);
        model.addAttribute("comment", comment);
        model.addAttribute("book", book);

        return "book_details/base";
    }

    @GetMapping("comment/delete/{id}/{bookId}")
    public String deleteComment(@PathVariable long id, @PathVariable long bookId, Model model) {
        Book book = bookService.findById(bookId);
        List<Comment> allComments = commentService.findAllCommentsPerBook(bookId);
        model.addAttribute("book", book);
        commentService.deleteById(id);
        model.addAttribute("comments", allComments);
        model.addAttribute("commentsCount", allComments.size());

        return "book_details/base";
    }


}
