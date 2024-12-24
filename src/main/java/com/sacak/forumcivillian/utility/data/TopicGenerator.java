package com.sacak.forumcivillian.utility.data;

import com.sacak.forumcivillian.entity.Topic;

import java.util.ArrayList;
import java.util.List;

public class TopicGenerator {

    public static List<Topic> generateTopics() {
        List<Topic> topics = new ArrayList<Topic>();
        Topic offTopic = Topic.builder()
                .topicName("Off-Topic")
                .build();
        Topic sports = Topic.builder().topicName("Sports").build();
        return List.of(offTopic, sports);
    }


}
