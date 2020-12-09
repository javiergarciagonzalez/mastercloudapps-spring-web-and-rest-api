package es.codeurjc.springwebrestapi.controllers.api;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.springwebrestapi.models.Book;
import es.codeurjc.springwebrestapi.models.BookDto;
import es.codeurjc.springwebrestapi.models.Comment;
import es.codeurjc.springwebrestapi.models.BookDto.BasicDto;
import es.codeurjc.springwebrestapi.models.mappers.BookMapper;
import es.codeurjc.springwebrestapi.services.BookService;
import es.codeurjc.springwebrestapi.services.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

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

    @Operation(summary = "Get all books and their id")
    @ApiResponses(value = {
        @ApiResponse(
        responseCode = "200", description = "Found all books", content = {@Content(
        mediaType = "application/json",
        schema = @Schema(implementation = BasicDto.class) )}
          ),
          @ApiResponse(
        responseCode = "404", description = "Books not found", content = @Content
        )
    })
    @JsonView(BasicDto.class)
    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> getBooks() {
        List<BookDto> books = this.convertToDto(this.bookService.findAll());
        if (books == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(books);
    }

    @Operation(summary = "Get a specific book by providing its id")
    @ApiResponses(value = {
        @ApiResponse(
        responseCode = "200", description = "Found the book", content = {@Content(
        mediaType = "application/json",
        schema = @Schema(implementation = BookDto.class) )}
          ),
          @ApiResponse(
        responseCode = "404", description = "Book not found", content = @Content
        )
    })
    @GetMapping("/book/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable Long id) {
        BookDto book = this.convertToDto(bookService.findById(id));

        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(book);
    }

    @Operation(summary = "Create a book")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Create Book object",
        required = true,
        content = @Content( schema = @Schema(implementation = BookDto.class))
    )
    @ApiResponses(value = {
        @ApiResponse(
        responseCode = "200", description = "Found the book", content = {@Content(
        mediaType = "application/json",
        schema = @Schema(implementation = BasicDto.class) )}
          ),
          @ApiResponse(
        responseCode = "404", description = "Book not found", content = @Content
        )
    })
    @PostMapping("/book")
    public ResponseEntity<Object> createPost(@RequestBody Book book) {
        bookService.save(book);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(book.getId()).toUri();

        return ResponseEntity.created(location).body(book);
    }
    @Operation(summary = "Create a comment for a specific book")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Create Comment",
        required = true,
        content = @Content( schema = @Schema(implementation = Comment.class))
    )
    @ApiResponses(value = {
        @ApiResponse(
        responseCode = "200", description = "A comment was created", content = {@Content(
        mediaType = "application/json",
        schema = @Schema(implementation = BookDto.class) )}
          ),
          @ApiResponse(
        responseCode = "404", description = "Book was not found", content = @Content
        )
    })
    @PostMapping("/comment/{bookId}")
    public ResponseEntity<Object> createComment(@RequestBody Comment comment, @PathVariable Long bookId) {
        Book book = bookService.findById(bookId);

        if (book == null || commentService.isAnyFieldMissing(comment)) {
            return ResponseEntity.notFound().build();
        }

        URI location = fromCurrentRequest().build().toUri();
        comment.setBookId(bookId);
        this.commentService.save(comment);


        return ResponseEntity.created(location).body(book);
    }

    @Operation(summary = "Delete a comment")
    @ApiResponses(value = {
        @ApiResponse(
        responseCode = "200", description = "A comment was created", content = {@Content(
        mediaType = "application/json")}
          ),
          @ApiResponse(
        responseCode = "404", description = "Comment was not found", content = @Content
        )
    })
    @DeleteMapping("/comment/{id}")
    public ResponseEntity<Object> deleteComment(@PathVariable Long id) throws IOException {
        Comment comment = commentService.findById(id);

        if (comment == null) {
            return ResponseEntity.notFound().build();
        }

        commentService.deleteById(id);
        return ResponseEntity.noContent().build();
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
