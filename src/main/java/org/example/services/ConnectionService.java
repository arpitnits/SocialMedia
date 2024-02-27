package org.example.services;

import org.example.pojos.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectionService {

    public static Map<String, List<User>> followersMap = new HashMap<>();
    public void addFollower(User follower, User following) {
        List<User> followers = followersMap.getOrDefault(follower.getUuid(), new ArrayList<>());
        followers.add(following);
        followersMap.put(follower.getUuid(), followers);

        System.out.println("Updated followerList " + followersMap.get(follower.getUuid()));
    }

    public void removeFollower(User follower, User following) {
        List<User> followers = followersMap.getOrDefault(follower.getUuid(), new ArrayList<>());
        followers.remove(following);

        System.out.println("Updated followerList " + followersMap.get(follower.getUuid()));
    }

    public List<User> getFollowers(String follower) {
        return followersMap.getOrDefault(follower, new ArrayList<>());
    }
}
