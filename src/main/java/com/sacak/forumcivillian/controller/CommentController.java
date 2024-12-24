package com.sacak.forumcivillian.controller;

import com.sacak.forumcivillian.dto.request.NewCommentRequest;
import com.sacak.forumcivillian.dto.response.BaseResponse;
import com.sacak.forumcivillian.service.CommentService;
import com.sacak.forumcivillian.views.VwComment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sacak.forumcivillian.constants.RestApis.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(COMMENT)
@CrossOrigin("*")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/create-comment")
    public ResponseEntity<BaseResponse<Boolean>> createComment(@RequestBody NewCommentRequest dto) {
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .code(200)
                        .success(commentService.createComment(dto))
                        .message("New comment created")
                        .data(null)
                .build());
    }

    @GetMapping("get-all-post-comments")
    public ResponseEntity<BaseResponse<List<VwComment>>> getAllPostComments(@RequestParam Long postId) {
        return ResponseEntity.ok(BaseResponse.<List<VwComment>>builder()
                .code(200)
                .success(true)
                .data(commentService.findAllCommentsOfPost(postId))
                .message("Comments on post displayed")
                .build());
    }


}
