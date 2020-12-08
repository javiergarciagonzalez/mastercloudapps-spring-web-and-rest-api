package es.codeurjc.springwebrestapi.services;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import es.codeurjc.springwebrestapi.models.Book;

@Service
public class BookService {

    private ConcurrentMap<Long, Book> books = new ConcurrentHashMap<>();
    private AtomicLong nextId = new AtomicLong();

    public BookService() {
        this.preloadBooks();
    }

    public Collection<Book> findAll() {
        return books.values();
    }

    public void save(Book book) {
        long id = nextId.getAndIncrement();
        if (book.getRating() == null) {
            book.setRating(0);
        }
        book.setId(id);
        this.books.put(id, book);
    }

    public Book findById(long id) {
        return this.books.get(id);
    }

    public void itsOwnImage(Book book) {
        book.setHasCustomImage(true);
    }

    private void preloadBooks() {
        Book[] books;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            books = objectMapper.readValue(new URL("file:src/main/resources/static/books.json"), Book[].class);
            Arrays.asList(books).forEach(book -> this.save(book));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
