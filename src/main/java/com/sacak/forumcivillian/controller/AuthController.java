package com.sacak.forumcivillian.controller;

import com.sacak.forumcivillian.dto.request.RegisterRequest;
import com.sacak.forumcivillian.dto.request.UserLoginRequest;
import com.sacak.forumcivillian.dto.response.BaseResponse;
import com.sacak.forumcivillian.entity.User;
import com.sacak.forumcivillian.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.sacak.forumcivillian.constants.RestApis.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)
@CrossOrigin("*")
public class AuthController {

    private final UserService userService;

    @PostMapping(REGISTER)
    public ResponseEntity<BaseResponse<User>> register(@RequestBody RegisterRequest dto) {

        return ResponseEntity.ok(BaseResponse.<User>builder()
                .message("Registered successfully")
                .code(200)
                .success(true)
                .data(userService.register(dto))
                .build());
    }

    @PostMapping(LOGIN)
    public ResponseEntity<BaseResponse<String>> register(@RequestBody UserLoginRequest dto) {

        return ResponseEntity.ok(BaseResponse.<String>builder()
                .message("Login successful")
                .code(200)
                .success(true)
                .data(userService.login(dto))
                .build());
    }


}
