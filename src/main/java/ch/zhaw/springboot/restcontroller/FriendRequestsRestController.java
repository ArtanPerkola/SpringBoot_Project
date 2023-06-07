package ch.zhaw.springboot.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.springboot.entities.FriendRequests;
import ch.zhaw.springboot.repositories.FriendRequestsRepository;

import java.util.List;

@RestController
public class FriendRequestsRestController {

    @Autowired
    private FriendRequestsRepository repository;

    @RequestMapping(value = "friendrequests", method = RequestMethod.GET)
    public ResponseEntity<List<FriendRequests>> getFriendRequests() {
        List<FriendRequests> result = this.repository.findAll();

        if (!result.isEmpty()) {
            return new ResponseEntity<List<FriendRequests>>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<FriendRequests>>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "friendrequests/{requesterId}", method = RequestMethod.GET)
    public ResponseEntity<List<FriendRequests>> getFriendRequestsByRequesterId(
            @PathVariable("requesterId") Long requesterId) {
        List<FriendRequests> result = this.repository.findFriendRequestsByRequesterId(requesterId);

        if (!result.isEmpty()) {
            return new ResponseEntity<List<FriendRequests>>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<FriendRequests>>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "friendrequests", method = RequestMethod.POST)
    public ResponseEntity<FriendRequests> createFriendRequest(@RequestBody FriendRequests friendRequest) {
        FriendRequests createdFriendRequest = repository.save(friendRequest);
        return new ResponseEntity<>(createdFriendRequest, HttpStatus.CREATED);
    }

    @RequestMapping(value = "friendrequests/{requesterId}/{requesteeId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteFriendRequest(@PathVariable("requesterId") Long requesterId,
            @PathVariable("requesteeId") Long requesteeId) {
        try {
            this.repository.deleteFriendRequest(requesterId, requesteeId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
