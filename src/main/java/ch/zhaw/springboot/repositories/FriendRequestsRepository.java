package ch.zhaw.springboot.repositories;

import ch.zhaw.springboot.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendRequestsRepository extends JpaRepository<FriendRequests, FriendRequestsId> {

    @Query("SELECT fr FROM FriendRequests fr WHERE fr.id.requester = ?1")
    public List<FriendRequests> findFriendRequestsByRequesterId(Long requesterId);

    @Query("SELECT fr FROM FriendRequests fr WHERE fr.id.requestee = ?1")
    public List<FriendRequests> findFriendRequestsByRequesteeId(Long requesteeId);

    @Modifying
    @Query("DELETE FROM FriendRequests fr WHERE fr.requester.id = :requesterId AND fr.requestee.id = :requesteeId")
    void deleteFriendRequest(@Param("requesterId") Long requesterId, @Param("requesteeId") Long requesteeId);

}
