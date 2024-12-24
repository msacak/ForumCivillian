package com.sacak.forumcivillian.utility.data;

import com.sacak.forumcivillian.entity.Post;

import java.util.List;

public class PostGenerator {
    public static List<Post> generatePosts() {
        Post post1 = Post.builder()
                .title("The benefits of planting trees")
                .userId(1L)
                .topicId(1L)
                .commentCount(1)
                .lastReplier("specialist")
                .build();
        Post post2 = Post.builder()
                .title("Top 5 movies to watch on a rainy day")
                .userId(2L)
                .topicId(1L)
                .commentCount(1)
                .lastReplier("karaceki")
                .build();
        Post post3 = Post.builder()
                .title("Best way to decorate your living room for winter")
                .userId(3L)
                .topicId(1L)
                .commentCount(1)
                .lastReplier("specialist")
                .build();

        Post post4 = Post.builder()
                .title("The evolution of music genres over the decades")
                .userId(1L)
                .topicId(1L)
                .commentCount(1)
                .lastReplier("horns")
                .build();

        return List.of(post1, post2, post3, post4);

    }
}
