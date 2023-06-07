package ch.zhaw.springboot.entities;

import java.io.Serializable;
import java.util.Objects;

public class FollowersId implements Serializable {

    private Long follower;
    private Long following;

    public FollowersId() {
    }

    public FollowersId(Long follower, Long following) {
        this.follower = follower;
        this.following = following;
    }

    public Long getFollower() {
        return follower;
    }

    public Long getFollowing() {
        return following;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        FollowersId that = (FollowersId) o;
        return Objects.equals(follower, that.follower) &&
                Objects.equals(following, that.following);
    }

    @Override
    public int hashCode() {
        return Objects.hash(follower, following);
    }
}
