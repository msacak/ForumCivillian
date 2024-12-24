package com.sacak.forumcivillian.utility.data;

import com.sacak.forumcivillian.entity.Admin;
import com.sacak.forumcivillian.entity.User;
import com.sacak.forumcivillian.entity.enums.EUserRole;
import com.sacak.forumcivillian.repository.*;
import com.sacak.forumcivillian.utility.EncryptionService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataGenerator {

    private final AdminRepository adminRepository;
    private final TopicRepository topicRepository;
    private final EncryptionService encryptionService;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;


    @PostConstruct
    public void generateData(){
        if(adminRepository.count() == 0){
            adminRepository.save(generateAdmin());
        }
        if(userRepository.count() == 0){
            userRepository.saveAll(generateUsers());
        }
        if(topicRepository.count() == 0){
            topicRepository.saveAll(TopicGenerator.generateTopics());
        }
        if(postRepository.count() == 0){
            postRepository.saveAll(PostGenerator.generatePosts());
        }
        if(commentRepository.count() == 0){
            commentRepository.saveAll(CommentGenerator.generateComments());
        }
    }

    public Admin generateAdmin(){
        return Admin.builder()
                .email("admin@test.com")
                .password("sifre123")
                .role(EUserRole.ADMIN)
                .build();
    }

    public List<User> generateUsers() {
        User user1 = User.builder()
                .email("user1@gmail.com")
                .userRole(EUserRole.USER)
                .userName("specialist")
                .password(encryptionService.encryptPassword("sifre123"))
                .totalComments(15)
                .build();
        User user2 = User.builder()
                .email("user2@gmail.com")
                .userRole(EUserRole.USER)
                .userName("karaceki")
                .password(encryptionService.encryptPassword("sifre123"))
                .totalComments(27)
                .build();
        User user3 = User.builder()
                .email("user3@gmail.com")
                .userRole(EUserRole.USER)
                .userName("horns")
                .password(encryptionService.encryptPassword("sifre123"))
                .totalComments(165)
                .build();
        return List.of(user1, user2, user3);
    }

}
