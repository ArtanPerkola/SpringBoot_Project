package ch.zhaw.springboot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String textContent;
    private long timestamp;

    @ManyToOne
    private User user;

    @ManyToOne
    private Post post;

    public Comment(String textContent, long timestamp, User user, Post post) {
        this.textContent = textContent;
        this.timestamp = timestamp;
        this.user = user;
        this.post = post;
    }

    public Comment() {
    }

    public long getId() {
        return id;
    }

    public String getTextContent() {
        return textContent;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public User getUser() {
        return user;
    }

    public Post getPost() {
        return post;
    }

    public void setText(String textContent) {
        this.textContent = textContent;
    }

    public void setAuthor(User user) {
        this.user = user;
    }
}
