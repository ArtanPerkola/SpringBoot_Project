package ch.zhaw.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ch.zhaw.springboot.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    public User findUserById(long id);

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    public User findUserByUsername(String username);

    @Query("SELECT u FROM User u")
    public List<User> findAllUsers();
}
