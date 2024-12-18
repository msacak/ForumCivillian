package com.sacak.forumcivillian.service;

import com.sacak.forumcivillian.dto.request.AdminLoginRequest;
import com.sacak.forumcivillian.entity.Admin;
import com.sacak.forumcivillian.exceptions.ErrorType;
import com.sacak.forumcivillian.exceptions.ForumCivillianException;
import com.sacak.forumcivillian.repository.AdminRepository;
import com.sacak.forumcivillian.utility.JwtManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    private final JwtManager jwtManager;

    public String login(AdminLoginRequest dto) {
        Optional<Admin> adminOptional = adminRepository.findByEmailAndPassword(dto.email(),dto.password());
        if (adminOptional.isPresent()) {
            return jwtManager.createAdminToken(adminOptional.get().getId(), dto.email());
        }
        throw new ForumCivillianException(ErrorType.ADMIN_NOT_FOUND);
    }

    public List<Admin> testFindAll(){
        return adminRepository.findAll();
    }
}
