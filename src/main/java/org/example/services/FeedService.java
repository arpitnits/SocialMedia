package org.example.services;

import org.example.Utils.CommonUtils;
import org.example.pojos.Feed;
import org.example.pojos.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedService {

    final ConnectionService connectionService;
    final TimelineService timelineService;

    public FeedService(ConnectionService connectionService, TimelineService timelineService) {
        this.connectionService = connectionService;
        this.timelineService = timelineService;
    }
    public static Map<String,Feed> feedMap = new HashMap<>();
    public Feed createFeed(String uuid, String txt) {
        String feedId = CommonUtils.getUUID();
        Feed feed = Feed.builder().feedId(feedId).text(txt).uuid(uuid).build();
        feed.setFeedId(feedId);
        feedMap.put(feedId, feed);

        updateFeedTimeline(connectionService.getFollowers(feed.getUuid()), feed);

        System.out.println("created feed " + feed);

        return feedMap.get(feedId);
    }

    public Feed getFeed(String feedId) {
        if(!feedMap.containsKey(feedId)) {
            System.out.println("Feed not found");
            return null;
        }
        return feedMap.get(feedId);
    }

    public Feed deleteFeed(String feedId) {
        if(!feedMap.containsKey(feedId)) {
            System.out.println("Feed not found");
            return null;
        }
        Feed feed = feedMap.get(feedId);
        removeFeedTimeline(connectionService.getFollowers(feed.getUuid()), feed);
        return feedMap.remove(feedId);
    }



    public void updateFeedTimeline(List<User> users, Feed feed) {
        for (User user: users) {
            timelineService.addFeedToUserTimeline(user, feed);
        }
    }

    private void removeFeedTimeline(List<User> followers, Feed feed) {
        for (User user: followers) {
            timelineService.deleteFeedFromUserTimeline(user, feed);
        }
    }
}
