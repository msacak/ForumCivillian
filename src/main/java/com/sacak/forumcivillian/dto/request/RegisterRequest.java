package com.sacak.forumcivillian.dto.request;

public record RegisterRequest(


        String firstName,
        String lastName,
        String email,
        String userName,
        String password,
        String rePassword

) {
}
