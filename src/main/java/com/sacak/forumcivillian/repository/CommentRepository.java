package com.sacak.forumcivillian.repository;

import com.sacak.forumcivillian.entity.Comment;
import com.sacak.forumcivillian.entity.enums.EState;
import com.sacak.forumcivillian.views.VwComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {



    Page<Comment> findAllByPostIdAndStateOrderByCreateAt(Long postId, Pageable pageable, EState state);
}
