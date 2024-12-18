package com.sacak.forumcivillian.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ErrorMessage {
    int code;
    String message;
    Boolean success;
    List<String> fields;

}
