package es.codeurjc.springwebrestapi.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import es.codeurjc.springwebrestapi.models.Comment;
import es.codeurjc.springwebrestapi.models.User;

@Service
public class CommentService {

    private ConcurrentMap<Long, Comment> comments = new ConcurrentHashMap<>();
    private AtomicLong nextId = new AtomicLong();

    public CommentService() {
        this.preloadComments();
    }

    public void save(Comment comment) {
        Long id = nextId.getAndIncrement();
        comment.setId(id);
        comments.put(id, comment);
    }

    public Comment findById(Long id) {
        return this.comments.get(id);
    }

    public List<Comment> findAllCommentsPerBook(Long bookId) {
        List<Comment> allComments = new ArrayList<Comment>();
        comments.values().forEach(comment -> {
            if (comment.getBookId() == bookId) {
                allComments.add(comment);
            }
        });
        return allComments;
    }

    public void deleteById(Long id) {
       comments.remove(id);
    }

    public User getSessionUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User("","");
        }
        return user;
    }

    public Boolean isAnyFieldMissing(Comment comment) {
        return
            comment.getContent() == null||
            comment.getUser() == null||
            comment.getRating() == null;
    }

    private void preloadComments() {
        User user = new User("John", "Doe");
        String content = "My life changed after reading this book, now I do not go out for social plans that often anymore.";
        Integer rating = 4;
        Long[] bookIds = {0L,1L,2L,3L,4L};

        Arrays.asList(bookIds).forEach(id -> {
            Comment comment = new Comment(content, rating, user, id);
            this.save(comment);
        });
    }
}
