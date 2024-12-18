package com.sacak.forumcivillian.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BaseResponse<T> {

    @Builder.Default
    Boolean success = true;
    @Builder.Default
    Integer code =200;
    String message;
    T data;
}
