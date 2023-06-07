package ch.zhaw.springboot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String password;
    private String email;
    private String profilePicture;
    private String bio;

    public User(String username, String password, String email, String profilePicture, String bio) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.profilePicture = profilePicture;
        this.bio = bio;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getBio() {
        return bio;
    }
}
