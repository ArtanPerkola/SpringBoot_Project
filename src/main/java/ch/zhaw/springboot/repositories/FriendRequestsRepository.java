package ch.zhaw.springboot.repositories;

import ch.zhaw.springboot.entities.FriendRequests;
import ch.zhaw.springboot.entities.FriendRequestsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendRequestsRepository extends JpaRepository<FriendRequests, FriendRequestsId> {

    @Query("SELECT fr FROM FriendRequests fr WHERE fr.id.requester = ?1")
    public List<FriendRequests> findFriendRequestsByRequesterId(Long requesterId);

    @Query("SELECT fr FROM FriendRequests fr WHERE fr.id.requestee = ?1")
    public List<FriendRequests> findFriendRequestsByRequesteeId(Long requesteeId);
}
