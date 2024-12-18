package com.sacak.forumcivillian.exceptions;

import lombok.Getter;

@Getter
public class ForumCivillianException extends RuntimeException {
    private ErrorType errorType;
    public ForumCivillianException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
