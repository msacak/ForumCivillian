package com.sacak.forumcivillian.repository;

import com.sacak.forumcivillian.entity.User;
import com.sacak.forumcivillian.entity.enums.EState;
import com.sacak.forumcivillian.views.VwUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserName(String username);

    @Query("SELECT new com.sacak.forumcivillian.views.VwUser(u.id,u.firstName,u.lastName,u.email,u.userName,u.avatar,u.birthday,u.phone,u.address,u.about,u.totalComments) FROM User u WHERE u.id=?1")
    Optional<VwUser> findVwUserById(Long id);

    @Query("SELECT u.userName FROM User u WHERE u.id=?1")
    Optional<String> findUsernameByUserId(Long userId);

    Optional<User> findByEmailAndState(String email, EState state);

}
