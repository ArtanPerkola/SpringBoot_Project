package ch.zhaw.springboot.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ch.zhaw.springboot.entities.Process;
import ch.zhaw.springboot.repositories.ProcessRepository;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/processes")
public class ProcessController {

    @Autowired
    private ProcessRepository processRepository;

    @GetMapping("")
    public ResponseEntity<List<Process>> getAllProcesses() {
        List<Process> processes = processRepository.findAll();
        return new ResponseEntity<>(processes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Process> getProcessById(@PathVariable("id") int id) {
        Process process = processRepository.findById(id);
        if (process != null) {
            return new ResponseEntity<>(process, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Process> createProcess(@RequestBody Process process) {
        Process createdProcess = processRepository.save(process);
        return new ResponseEntity<>(createdProcess, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Process> updateProcess(@PathVariable("id") int id, @RequestBody Process processDetails) {
        Process process = processRepository.findById(id);
        if (process != null) {
            process.setDescription(processDetails.getDescription());
            Process updatedProcess = processRepository.save(process);
            return new ResponseEntity<>(updatedProcess, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcess(@PathVariable("id") int id) {
        Process process = processRepository.findById(id);
        if (process != null) {
            processRepository.delete(process);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
