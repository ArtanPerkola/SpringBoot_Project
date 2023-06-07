package ch.zhaw.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ch.zhaw.springboot.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.id = ?1")
    public Post findPostById(long id);

    @Query("SELECT p FROM Post p WHERE p.user.id = ?1")
    public List<Post> findAllPostsByUser(long userId);
}
