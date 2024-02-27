package org.example.services;

import org.example.pojos.Feed;
import org.example.pojos.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimelineService {

    static final Integer limit = 10;

    public static Map<String, List<Feed>> userTimelineMap = new HashMap<>();
    public List<Feed> getTimeline(User user, int pageNo) {
        List<Feed> userFeed = userTimelineMap.getOrDefault(user.getUuid(), new ArrayList<>());
        int startIndex = pageNo * limit;
        int endIndex = Math.min(startIndex + limit, userFeed.size());
        List<Feed> paginatedFeeds = userFeed.subList(startIndex, endIndex);
        System.out.println("Timeline for UserName: " + user.getName());
        for (Feed feed : paginatedFeeds) {
            System.out.println("Feeds: " + feed);
        }

        return paginatedFeeds;
    }

    public void addFeedToUserTimeline(User user, Feed feed) {
        List<Feed> userFeed = userTimelineMap.getOrDefault(user.getUuid(), new ArrayList<>());
        userFeed.add(feed);
        userTimelineMap.put(user.getUuid(), userFeed);
    }

    public void deleteFeedFromUserTimeline(User user, Feed feed) {
        List<Feed> userFeed = userTimelineMap.getOrDefault(user.getUuid(), new ArrayList<>());
        userFeed.remove(feed);
        userTimelineMap.put(user.getUuid(), userFeed);
    }
}
