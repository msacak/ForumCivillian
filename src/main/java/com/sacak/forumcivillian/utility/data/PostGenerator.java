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

        Post post5 = Post.builder()
                .title("The Rise of Esports: A New Era in Competitive Sports")
                .userId(1L)
                .topicId(2L)  // Topic ID set to 2L for sports
                .commentCount(10)
                .lastReplier("JaneSmith")
                .build();

        Post post6 = Post.builder()
                .title("Top 5 Football Players to Watch in 2024")
                .userId(2L)
                .topicId(2L)
                .commentCount(15)
                .lastReplier("SarahJ")
                .build();

        Post post7 = Post.builder()
                .title("Analyzing the Future of Basketball in Europe")
                .userId(3L)
                .topicId(2L)
                .commentCount(12)
                .lastReplier("AnnaLee")
                .build();

        Post post8 = Post.builder()
                .title("Why Track and Field Athletes Are the Unsung Heroes")
                .userId(1L)
                .topicId(2L)
                .commentCount(13)
                .lastReplier("DavidW")
                .build();

        return List.of(post1, post2, post3, post4,post5,post6,post7,post8);

    }
}
