package com.sacak.forumcivillian.service;

import com.sacak.forumcivillian.entity.Topic;
import com.sacak.forumcivillian.repository.TopicRepository;
import com.sacak.forumcivillian.views.VwAllPost;
import com.sacak.forumcivillian.views.VwTopic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;
    private final PostService postService;

    public List<VwTopic> getAllTopics() {
        List<VwTopic> vwTopics = new ArrayList<>();
        List<Topic> topics = topicRepository.findAll();
        for (Topic topic : topics) {
            List<VwAllPost> vwAllPosts = postService.getAllPostsOnTopic(topic.getId());
            vwTopics.add(new VwTopic(topic.getId(),topic.getTopicName(),vwAllPosts));
        }
        return vwTopics;
    }
}
