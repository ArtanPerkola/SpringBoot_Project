package ch.zhaw.springboot.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.springboot.entities.Post;
import ch.zhaw.springboot.repositories.PostRepository;

@RestController
public class PostRestController {

    @Autowired
    private PostRepository repository;

    @RequestMapping(value = "posts", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> result = this.repository.findAll();

        if (!result.isEmpty()) {
            return new ResponseEntity<List<Post>>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Post>>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "posts/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> getPostsByUser(@PathVariable("userId") long userId) {
        List<Post> result = this.repository.findAllPostsByUser(userId);

        if (!result.isEmpty()) {
            return new ResponseEntity<List<Post>>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Post>>(HttpStatus.NOT_FOUND);
        }
    }
}
