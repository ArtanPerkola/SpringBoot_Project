package ch.zhaw.springboot.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping(value = "posts", method = RequestMethod.POST)
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        // Save the post in the repository
        Post savedPost = repository.save(post);

        // Return the saved post with HTTP status CREATED
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    @RequestMapping(value = "posts/{postId}", method = RequestMethod.PUT)
    public ResponseEntity<Post> updatePost(@PathVariable("postId") long postId, @RequestBody Post updatedPost) {
        // Retrieve the post to be updated
        Optional<Post> existingPostOptional = repository.findById(postId);

        if (existingPostOptional.isPresent()) {
            // Get the existing post object
            Post existingPost = existingPostOptional.get();

            // Update the attributes of the existing post with the new values
            existingPost.setTextContent(updatedPost.getTextContent());
            existingPost.setImageContent(updatedPost.getImageContent());

            // Save the updated post in the repository
            Post savedPost = repository.save(existingPost);

            // Return the updated post with HTTP status OK
            return new ResponseEntity<>(savedPost, HttpStatus.OK);
        } else {
            // Return HTTP status NOT_FOUND if the post does not exist
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "posts/{postId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePost(@PathVariable("postId") long postId) {
        Optional<Post> postOptional = repository.findById(postId);

        if (postOptional.isPresent()) {
            repository.deleteById(postId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
