package com.sacak.forumcivillian.utility.data;

import com.sacak.forumcivillian.entity.Admin;
import com.sacak.forumcivillian.entity.enums.EUserRole;
import com.sacak.forumcivillian.repository.AdminRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataGenerator {

    private final AdminRepository adminRepository;


    @PostConstruct
    public void createAdmin(){
        adminRepository.save(Admin.builder()
                        .email("admin@test.com")
                        .password("sifre123")
                        .role(EUserRole.ADMIN)
                .build());
    }

}
