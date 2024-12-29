package com.sacak.forumcivillian.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {

    INTERNAL_SERVER_ERROR(500,"Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR),
    VALIDATION_ERROR(400,"Parameters you've entered are wrong, please try again.", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4001,"User not found",HttpStatus.BAD_REQUEST),
    PASSWORDS_DO_NOT_MATCH(4002,"Passwords do not match", HttpStatus.BAD_REQUEST),
    USER_NOT_VERIFIED(4003,"User not verified", HttpStatus.BAD_REQUEST),
    WRONG_PASSWORD(4004,"Wrong password", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(4005,"Invalid token",HttpStatus.BAD_REQUEST),
    ADMIN_NOT_FOUND(9001,"Admin not found", HttpStatus.BAD_REQUEST),
    POST_NOT_FOUND(5001,"Post not found", HttpStatus.NOT_FOUND),
    TOPIC_NOT_FOUND(6001,"Topic not found", HttpStatus.NOT_FOUND),
    ;

    int code;
    String message;
    HttpStatus httpStatus;
}
