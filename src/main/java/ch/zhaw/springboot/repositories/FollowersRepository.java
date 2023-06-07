package ch.zhaw.springboot.repositories;

import ch.zhaw.springboot.entities.Followers;
import ch.zhaw.springboot.entities.FollowersId;
import ch.zhaw.springboot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowersRepository extends JpaRepository<Followers, FollowersId> {

    @Query("SELECT f FROM Followers f WHERE f.follower.id = ?1")
    public List<Followers> findFollowersByFollowerId(Long followerId);

    @Query("SELECT f FROM Followers f WHERE f.following.id = ?1")
    public List<Followers> findFollowingByUserId(Long userId);

    @Modifying
    @Query("DELETE FROM Followers f WHERE f.follower.id = :followerId AND f.following.id = :followingId")
    void deleteFollower(@Param("followerId") Long followerId, @Param("followingId") Long followingId);

}
