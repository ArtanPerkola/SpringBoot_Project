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

import ch.zhaw.springboot.entities.User;
import ch.zhaw.springboot.repositories.UserRepository;

@RestController
public class UserRestController {

    @Autowired
    private UserRepository repository;

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers() {
        List<User> result = this.repository.findAll();

        if (!result.isEmpty()) {
            return new ResponseEntity<List<User>>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "users/{username}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        User result = this.repository.findUserByUsername(username);

        if (result != null) {
            return new ResponseEntity<User>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "users", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // Save the user in the repository
        User savedUser = repository.save(user);

        // Return the saved user with HTTP status CREATED
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @RequestMapping(value = "users/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("userId") long userId, @RequestBody User updatedUser) {
        // Retrieve the user to be updated
        User existingUser = repository.findUserById(userId);

        if (existingUser != null) {
            // Update the attributes of the existing user with the new values
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());

            // Save the updated user in the repository
            User savedUser = repository.save(existingUser);

            // Return the updated user with HTTP status OK
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        } else {
            // Return HTTP status NOT_FOUND if the user does not exist
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "users/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") long userId) {
        // Check if the user exists
        boolean userExists = repository.existsById(userId);

        if (userExists) {
            // Delete the user from the repository
            repository.deleteById(userId);

            // Return HTTP status NO_CONTENT for successful deletion
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // Return HTTP status NOT_FOUND if the user does not exist
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
