package com.sacak.forumcivillian.controller;

import com.sacak.forumcivillian.dto.response.BaseResponse;
import com.sacak.forumcivillian.service.UserService;
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

    @GetMapping("/verify")  //Normally it was @PostMapping, changed it to see if i can just copy-paste verification link to browser and set isVerified parameter true.
    public ResponseEntity<BaseResponse<String>> verifyEmail(@RequestParam String token){
        if(userService.verifyUser(token)){
            return ResponseEntity.ok(BaseResponse.<String>builder()
                    .data("Mail verification successful")
                    .success(true)
                    .code(200)
                    .message("Account is now verified")
                    .build());
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build(); //user already verified or token doesn't exist
    }
}
