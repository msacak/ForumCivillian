package com.sacak.forumcivillian.views;

import java.util.List;

public record VwTopic(

        Long id,
        String topicName,
        List<VwAllPost> postList


) {
}
