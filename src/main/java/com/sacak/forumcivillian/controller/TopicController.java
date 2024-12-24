package com.sacak.forumcivillian.controller;

import com.sacak.forumcivillian.dto.response.BaseResponse;
import com.sacak.forumcivillian.entity.Topic;
import com.sacak.forumcivillian.service.TopicService;
import com.sacak.forumcivillian.views.VwTopic;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sacak.forumcivillian.constants.RestApis.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(TOPIC)
@CrossOrigin("*")
public class TopicController {

    private final TopicService topicService;

    @GetMapping("get-all-topics")
    public ResponseEntity<BaseResponse<List<VwTopic>>> getAllPostsOnTopic(){
        return ResponseEntity.ok(BaseResponse.<List<VwTopic>>builder()
                .code(200)
                .success(true)
                .message("All topics")
                .data(topicService.getAllTopics())
                .build());
    }
}
