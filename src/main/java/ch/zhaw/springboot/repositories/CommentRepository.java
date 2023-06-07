package ch.zhaw.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ch.zhaw.springboot.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c WHERE c.id = ?1")
    public Comment findCommentById(long id);

    @Query("SELECT c FROM Comment c WHERE c.post.id = ?1")
    public List<Comment> findAllCommentsByPost(long postId);
}
