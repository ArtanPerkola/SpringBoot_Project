package ch.zhaw.springboot.repositories;

import ch.zhaw.springboot.entities.Followers;
import ch.zhaw.springboot.entities.FollowersId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowersRepository extends JpaRepository<Followers, FollowersId> {

    @Query("SELECT f FROM Followers f WHERE f.id.follower = ?1")
    public List<Followers> findFollowersByFollowerId(Long followerId);

    @Query("SELECT f FROM Followers f WHERE f.id.following = ?1")
    public List<Followers> findFollowingByUserId(Long userId);
}
