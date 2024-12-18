package com.sacak.forumcivillian.dto.request;

public record NewCommentRequest(

        String token,
        Long postId,
        String content,
        String imageUrl

) {

}
