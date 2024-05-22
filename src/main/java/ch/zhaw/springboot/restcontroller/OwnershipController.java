package ch.zhaw.springboot.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ch.zhaw.springboot.entities.Ownership;
import ch.zhaw.springboot.repositories.OwnershipRepository;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ownerships")
public class OwnershipController {

    @Autowired
    private OwnershipRepository ownershipRepository;

    @GetMapping("")
    public ResponseEntity<List<Ownership>> getAllOwnerships() {
        List<Ownership> ownerships = ownershipRepository.findAll();
        return new ResponseEntity<>(ownerships, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ownership> getOwnershipById(@PathVariable("id") int id) {
        Ownership ownership = ownershipRepository.findById(id);
        if (ownership != null) {
            return new ResponseEntity<>(ownership, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Ownership> createOwnership(@RequestBody Ownership ownership) {
        Ownership createdOwnership = ownershipRepository.save(ownership);
        return new ResponseEntity<>(createdOwnership, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ownership> updateOwnership(@PathVariable("id") int id,
            @RequestBody Ownership ownershipDetails) {
        Ownership ownership = ownershipRepository.findById(id);
        if (ownership != null) {
            ownership.setDateAcquired(ownershipDetails.getDateAcquired());
            Ownership updatedOwnership = ownershipRepository.save(ownership);
            return new ResponseEntity<>(updatedOwnership, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwnership(@PathVariable("id") int id) {
        Ownership ownership = ownershipRepository.findById(id);
        if (ownership != null) {
            ownershipRepository.delete(ownership);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
