package com.sacak.forumcivillian.controller;

import com.sacak.forumcivillian.dto.request.NewCommentRequest;
import com.sacak.forumcivillian.dto.response.BaseResponse;
import com.sacak.forumcivillian.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.sacak.forumcivillian.constants.RestApis.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(COMMENT)
@CrossOrigin("*")
public class CommentController {
    private final CommentService commentService;

    public ResponseEntity<BaseResponse<Boolean>> createComment(NewCommentRequest dto) {
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .code(200)
                        .success(commentService.createComment(dto))
                        .message("New comment created")
                        .data(null)
                .build());
    }
}
