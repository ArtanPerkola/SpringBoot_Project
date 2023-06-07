package ch.zhaw.springboot.entities;

import java.io.Serializable;
import java.util.Objects;

public class FriendRequestsId implements Serializable {

    private Long requester;
    private Long requestee;

    public FriendRequestsId() {
    }

    public FriendRequestsId(Long requester, Long requestee) {
        this.requester = requester;
        this.requestee = requestee;
    }

    public Long getRequester() {
        return requester;
    }

    public Long getRequestee() {
        return requestee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        FriendRequestsId that = (FriendRequestsId) o;
        return Objects.equals(requester, that.requester) &&
                Objects.equals(requestee, that.requestee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requester, requestee);
    }
}
