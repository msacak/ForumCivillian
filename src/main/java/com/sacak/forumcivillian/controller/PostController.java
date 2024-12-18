package com.sacak.forumcivillian.controller;

import com.sacak.forumcivillian.dto.request.NewPostRequest;
import com.sacak.forumcivillian.dto.response.BaseResponse;
import com.sacak.forumcivillian.dto.response.PostResponse;
import com.sacak.forumcivillian.entity.Post;
import com.sacak.forumcivillian.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.sacak.forumcivillian.constants.RestApis.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(POST)
@CrossOrigin("*")
public class PostController {
    private final PostService postService;

    @PostMapping("/create-post")
    public ResponseEntity<BaseResponse<Boolean>> createPost(@RequestBody NewPostRequest dto){
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .code(200)
                        .success(true)
                        .message("New post created")
                        .data(postService.createPost(dto))
                .build());
    }

    @GetMapping("/get-post")
    public ResponseEntity<BaseResponse<PostResponse>> getPost(@RequestParam Long postId){
        return ResponseEntity.ok(BaseResponse.<PostResponse>builder()
                        .code(200)
                        .success(true)
                        .message("Post found")
                        .data(postService.getPost(postId))
                .build());
    }
}
