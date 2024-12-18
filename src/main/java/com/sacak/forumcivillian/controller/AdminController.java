package com.sacak.forumcivillian.controller;

import com.sacak.forumcivillian.dto.request.AdminLoginRequest;
import com.sacak.forumcivillian.dto.response.BaseResponse;
import com.sacak.forumcivillian.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.sacak.forumcivillian.constants.RestApis.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ADMIN)
@CrossOrigin("*")
public class AdminController {

    private AdminService adminService;

    @PostMapping(LOGIN)
    public ResponseEntity<BaseResponse<String>> login(@RequestBody AdminLoginRequest dto){
        return ResponseEntity.ok(BaseResponse.<String>builder()
                .code(200)
                .success(true)
                .message("Hello Admin")
                .data(adminService.login(dto))
                .build());
    }
}
