package org.example;

import org.example.enums.UserType;
import org.example.pojos.Feed;
import org.example.pojos.User;
import org.example.services.ConnectionService;
import org.example.services.FeedService;
import org.example.services.TimelineService;
import org.example.services.UserService;

public class Main {
    /**
     * Design a facebook like newsfeed system where user can post posts, follow users
     * and also able to generate the newsfeed of the users.
     * The newsfeed api should also support pagination.
     * Design Facebook APIs (Create Post, Delete Post, Get Feed, Get Feed Paginated, Follow, Unfollow)
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Hello world!");

         UserService userService = new UserService();
         ConnectionService connectionService = new ConnectionService();
         TimelineService timelineService = new TimelineService();
         FeedService feedService = new FeedService(connectionService, timelineService);

         User user1 = userService.createUser("user1", UserType.NORMAL);
         User user2 = userService.createUser("user2", UserType.NORMAL);
         User user3 = userService.createUser("user3", UserType.NORMAL);
         User user4 = userService.createUser("user4", UserType.NORMAL);
         User user5 = userService.createUser("user5", UserType.NORMAL);



         connectionService.addFollower(user1, user2);
         connectionService.addFollower(user1, user3);
         connectionService.addFollower(user1, user4);
         connectionService.addFollower(user1, user5);

         feedService.createFeed(user1.getUuid(), "post1");
        Feed post2 = feedService.createFeed(user1.getUuid(), "post2");
        feedService.createFeed(user1.getUuid(), "post3");
        feedService.createFeed(user1.getUuid(), "post4");

        System.out.println();
        timelineService.getTimeline(user1, 0);

        System.out.println();
        timelineService.getTimeline(user2, 0);

        feedService.deleteFeed(post2.getFeedId());

        System.out.println();
        timelineService.getTimeline(user2, 0);

        connectionService.removeFollower(user1, user2);

        System.out.println();
        timelineService.getTimeline(user2, 0);
    }
}