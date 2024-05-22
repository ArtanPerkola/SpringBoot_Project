package ch.zhaw.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ch.zhaw.springboot.entities.Ownership;

@Repository
public interface OwnershipRepository extends JpaRepository<Ownership, Integer> {

    @Query("SELECT o FROM Ownership o WHERE o.id = ?1")
    Ownership findById(int id);
}
