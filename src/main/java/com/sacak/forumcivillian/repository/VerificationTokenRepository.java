package com.sacak.forumcivillian.repository;

import com.sacak.forumcivillian.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {
    Optional<VerificationToken> findByToken(String token);
    void deleteByUserId(Long userId);
    List<VerificationToken> findAllByUserIdOrderByCreatedTimeDesc(Long userId);
}
