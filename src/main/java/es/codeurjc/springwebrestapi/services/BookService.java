package es.codeurjc.springwebrestapi.services;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import es.codeurjc.springwebrestapi.models.Book;

@Service
public class BookService {

    private ConcurrentMap<Long, Book> books = new ConcurrentHashMap<>();
    private AtomicLong nextId = new AtomicLong();

    public BookService() {
        save(new Book("super book", "Some description", "Javier García", "Super Editorial", "2020-12-06"));
    }

    public Collection<Book> findAll() {
        return books.values();
    }

    public void save(Book book) {
        long id = nextId.getAndIncrement();

        book.setId(id);
        this.books.put(id, book);
    }
}
