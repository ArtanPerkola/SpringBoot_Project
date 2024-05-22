package ch.zhaw.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ch.zhaw.springboot.entities.Process;

@Repository
public interface ProcessRepository extends JpaRepository<Process, Integer> {

    @Query("SELECT p FROM Process p WHERE p.id = ?1")
    Process findById(int id);
}
