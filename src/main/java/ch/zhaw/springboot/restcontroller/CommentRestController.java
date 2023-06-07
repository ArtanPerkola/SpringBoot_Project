package ch.zhaw.springboot.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.springboot.entities.Comment;
import ch.zhaw.springboot.repositories.CommentRepository;

@RestController
public class CommentRestController {

    @Autowired
    private CommentRepository repository;

    @RequestMapping(value = "comments", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> getComments() {
        List<Comment> result = this.repository.findAll();

        if (!result.isEmpty()) {
            return new ResponseEntity<List<Comment>>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Comment>>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "comments/post/{postId}", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable("postId") long postId) {
        List<Comment> result = this.repository.findAllCommentsByPost(postId);

        if (!result.isEmpty()) {
            return new ResponseEntity<List<Comment>>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Comment>>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "comments", method = RequestMethod.POST)
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        // Save the comment in the repository
        Comment createdComment = repository.save(comment);
    
        // Return the created comment with HTTP status CREATED
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @RequestMapping(value = "comments/{commentId}", method = RequestMethod.PUT)
    public ResponseEntity<Comment> updateComment(@PathVariable("commentId") long commentId, @RequestBody Comment updatedComment) {
        // Retrieve the comment to be updated
        Comment existingComment = repository.findCommentById(commentId);

        if (existingComment != null) {
            // Update the attributes of the existing comment with the new values
            existingComment.setText(updatedComment.getTextContent());
            existingComment.setAuthor(updatedComment.getUser());

            // Save the updated comment in the repository
            Comment savedComment = repository.save(existingComment);

            // Return the updated comment with HTTP status OK
            return new ResponseEntity<>(savedComment, HttpStatus.OK);
        } else {
            // Return HTTP status NOT_FOUND if the comment does not exist
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "comments/{commentId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteComment(@PathVariable("commentId") long commentId) {
        // Check if the comment exists
        boolean commentExists = repository.existsById(commentId);

        if (commentExists) {
            // Delete the comment from the repository
            repository.deleteById(commentId);

            // Return HTTP status NO_CONTENT for successful deletion
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // Return HTTP status NOT_FOUND if the comment does not exist
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}