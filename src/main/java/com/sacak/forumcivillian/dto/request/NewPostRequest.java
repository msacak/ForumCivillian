package com.sacak.forumcivillian.dto.request;

public record NewPostRequest(

        String token,
        Long topicId,
        String title,
        String content,
        String imageUrl //Optional if user Want to upload picture to a new post

) {
}
