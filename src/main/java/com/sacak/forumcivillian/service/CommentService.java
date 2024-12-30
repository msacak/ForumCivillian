package com.sacak.forumcivillian.service;

import com.sacak.forumcivillian.constants.CheckUserRank;
import com.sacak.forumcivillian.dto.request.NewCommentRequest;
import com.sacak.forumcivillian.entity.Comment;
import com.sacak.forumcivillian.entity.Post;
import com.sacak.forumcivillian.entity.User;
import com.sacak.forumcivillian.entity.enums.EState;
import com.sacak.forumcivillian.exceptions.ErrorType;
import com.sacak.forumcivillian.exceptions.ForumCivillianException;
import com.sacak.forumcivillian.repository.CommentRepository;
import com.sacak.forumcivillian.utility.JwtManager;
import com.sacak.forumcivillian.views.VwComment;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final JwtManager jwtManager;
    private final UserService userService;
    private final PostService postService;


    public CommentService(
            CommentRepository commentRepository,
            JwtManager jwtManager,
            UserService userService,
            @Lazy PostService postService) {
        this.commentRepository = commentRepository;
        this.jwtManager = jwtManager;
        this.userService = userService;
        this.postService = postService;
    }
    @Transactional
    public Boolean createComment(NewCommentRequest dto){
        Long userId = jwtManager.validateToken(dto.token()).orElseThrow(()-> new ForumCivillianException(ErrorType.INVALID_TOKEN));
        User user = userService.findById(userId);
        Post post = postService.findById(dto.postId());
        Comment comment = Comment.builder()
                .userId(userId)
                .postId(dto.postId())
                .content(dto.content())
                .imageUrl(dto.imageUrl())
                .build();
        commentRepository.save(comment);
        user.setTotalComments(user.getTotalComments() + 1);
        CheckUserRank.checkUserRank(user);
        userService.save(user);
        post.setTotalComments(post.getTotalComments() + 1);
        post.setLastReplier(user.getUserName());
        post.setUpdateAt(System.currentTimeMillis());
        postService.save(post);
        return true;
    }


    public Page<VwComment> findAllCommentsByPostId(Long postId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return commentRepository.findAllByPostIdAndStateOrderByCreateAt(postId,pageable,EState.ACTIVE)
                .map(comment -> {
                    User user = userService.findById(comment.getUserId());
                    return new VwComment(
                            comment.getId(),
                            user.getUserName(),
                            user.getTotalComments(),
                            user.getAvatar(),
                            user.getUserRank(),
                            comment.getContent(),comment.
                            getImageUrl(),comment.getCreateAt(),
                            comment.getUpdateAt());
                });

    }

    public void save(Comment comment) {
        commentRepository.save(comment);
    }
}
