package com.sacak.forumcivillian.controller;

import com.sacak.forumcivillian.dto.response.BaseResponse;
import com.sacak.forumcivillian.entity.User;
import com.sacak.forumcivillian.service.UserService;
import com.sacak.forumcivillian.views.VwUserProfilePage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.sacak.forumcivillian.constants.RestApis.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
@CrossOrigin("*")
public class UserController {

    private final UserService userService;


    @GetMapping
    public ResponseEntity<BaseResponse<VwUserProfilePage>> getUserProfile(@RequestParam Long userId) {
        return ResponseEntity.ok(BaseResponse.<VwUserProfilePage>builder()
                .data(userService.findVwUserProfile(userId))
                .message("User profile")
                .build());
    }
}
