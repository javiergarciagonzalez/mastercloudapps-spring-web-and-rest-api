package es.codeurjc.springwebrestapi.services;

import java.util.ArrayList;
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
        this.save(new Comment("asdsad", 3, new User("Javier", "garcia"), 0));
    }

    public void save(Comment comment) {
        long id = nextId.getAndIncrement();
        comment.setId(id);
        comments.put(id, comment);
    }

    public List<Comment> findAllCommentsPerBook(long bookId) {
        List<Comment> allComments = new ArrayList<Comment>();
        comments.values().forEach(comment -> {
            if (comment.getBookId() == bookId) {
                allComments.add(comment);
            }
        });
        return allComments;
    }

    public void deleteById(long id) {
       comments.remove(id);
    }

    public User getSessionUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User("","");
        }
        return user;
    }
}
