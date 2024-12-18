package com.sacak.forumcivillian.repository;

import com.sacak.forumcivillian.entity.Comment;
import com.sacak.forumcivillian.views.VwComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {


    @Query("SELECT new com.sacak.forumcivillian.views.VwComment(c.id,u.userName,u.userRank,c.content,c.imageUrl) " +
            "FROM Comment c " +
            "JOIN User u ON c.userId=u.id " +
            "WHERE c.postId=?1 ORDER BY c.createAt")
    List<VwComment> findAllCommentsByPostId(Long postId);
}
