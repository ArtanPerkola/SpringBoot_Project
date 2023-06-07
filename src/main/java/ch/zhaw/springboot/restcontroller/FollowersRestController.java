package ch.zhaw.springboot.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.springboot.entities.*;
import ch.zhaw.springboot.repositories.FollowersRepository;

import java.util.List;

@RestController
public class FollowersRestController {

    @Autowired
    private FollowersRepository repository;

    @RequestMapping(value = "followers", method = RequestMethod.GET)
    public ResponseEntity<List<Followers>> getFollowers() {
        List<Followers> result = this.repository.findAll();

        if (!result.isEmpty()) {
            return new ResponseEntity<List<Followers>>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Followers>>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "followers/{followerId}", method = RequestMethod.GET)
    public ResponseEntity<List<Followers>> getFollowersByFollowerId(@PathVariable("followerId") Long followerId) {
        List<Followers> result = this.repository.findFollowersByFollowerId(followerId);

        if (!result.isEmpty()) {
            return new ResponseEntity<List<Followers>>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Followers>>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "followers", method = RequestMethod.POST)
    public ResponseEntity<Followers> createFollower(@RequestBody Followers follower) {
        Followers createdFollower = repository.save(follower);
        return new ResponseEntity<>(createdFollower, HttpStatus.CREATED);
    }

    @RequestMapping(value = "followers/delete/{followerId}/{followingId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteFollower(@PathVariable("followerId") Long followerId,
            @PathVariable("followingId") Long followingId) {
        repository.deleteFollower(followerId, followingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
