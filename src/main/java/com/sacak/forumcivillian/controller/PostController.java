package com.sacak.forumcivillian.controller;

import com.sacak.forumcivillian.dto.request.NewPostRequest;
import com.sacak.forumcivillian.dto.response.BaseResponse;
import com.sacak.forumcivillian.service.PostService;
import com.sacak.forumcivillian.views.VwAllPost;
import com.sacak.forumcivillian.views.VwPost;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

//    @GetMapping("get-all-posts-on-topic")
//    public ResponseEntity<BaseResponse<List<VwAllPost>>> getAllPostsOnTopic(@RequestParam Long topicId){
//        return ResponseEntity.ok(BaseResponse.<List<VwAllPost>>builder()
//                .code(200)
//                .success(true)
//                .message("All posts on topic") //in the future not all posts but limited posts per page will be shown.
//                .data(postService.get5TopPostByTopicId(topicId))
//                .build());
//    }

    @GetMapping("get-post")
    public ResponseEntity<BaseResponse<VwPost>> getPost(@RequestParam Long postId,@RequestParam int page,@RequestParam int size){
        return ResponseEntity.ok(BaseResponse.<VwPost>builder()
                .code(200)
                .success(true)
                .message("Post")
                .data(postService.getPostById(postId,page,size))
                .build());
    }

    @GetMapping("search-post")
    public ResponseEntity<BaseResponse<List<VwAllPost>>> getPosts(
            @RequestParam String query,
            @RequestParam(defaultValue = "20") int limit,
            @RequestParam(defaultValue = "0") int offset) {

        List<VwAllPost> posts = postService.findAllVwPostsByQuery(query, limit, offset);
        return ResponseEntity.ok(BaseResponse.<List<VwAllPost>>builder()
                .code(200)
                .success(true)
                .message("Search results")
                .data(posts)
                .build());
    }





}
