package ch.zhaw.springboot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String textContent;
    private String imageContent;
    private long timestamp;

    @ManyToOne
    private User user;

    public Post(String textContent, String imageContent, long timestamp, User user) {
        this.textContent = textContent;
        this.imageContent = imageContent;
        this.timestamp = timestamp;
        this.user = user;
    }

    public Post() {
    }

    public long getId() {
        return id;
    }

    public String getTextContent() {
        return textContent;
    }

    public String getImageContent() {
        return imageContent;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    
    }

    public void setImageContent(String imageContent) {
        this.imageContent = imageContent;
    }
}
