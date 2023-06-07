package ch.zhaw.springboot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "FriendRequests")
@IdClass(FriendRequestsId.class)
public class FriendRequests {

    @Id
    @ManyToOne
    @JoinColumn(name = "requester_id")
    private User requester;

    @Id
    @ManyToOne
    @JoinColumn(name = "requestee_id")
    private User requestee;

    public FriendRequests() {
    }

    public FriendRequests(User requester, User requestee) {
        this.requester = requester;
        this.requestee = requestee;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public User getRequestee() {
        return requestee;
    }

    public void setRequestee(User requestee) {
        this.requestee = requestee;
    }
}
