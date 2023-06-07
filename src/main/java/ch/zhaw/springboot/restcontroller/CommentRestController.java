package ch.zhaw.springboot.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
}
