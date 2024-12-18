package com.sacak.forumcivillian.service;

import com.sacak.forumcivillian.dto.request.NewCommentRequest;
import com.sacak.forumcivillian.entity.Comment;
import com.sacak.forumcivillian.entity.User;
import com.sacak.forumcivillian.exceptions.ErrorType;
import com.sacak.forumcivillian.exceptions.ForumCivillianException;
import com.sacak.forumcivillian.repository.CommentRepository;
import com.sacak.forumcivillian.utility.JwtManager;
import com.sacak.forumcivillian.views.VwComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final JwtManager jwtManager;
    private final UserService userService;

    public Boolean createComment(NewCommentRequest dto){
        Optional<Long> userIdOptional = jwtManager.validateToken(dto.token());
        if(userIdOptional.isEmpty()) throw new ForumCivillianException(ErrorType.USER_NOT_FOUND);
        Optional<User> userOptional = userService.findById(userIdOptional.get());
        if(userOptional.isEmpty()) throw new ForumCivillianException(ErrorType.USER_NOT_FOUND);
        Comment comment = Comment.builder()
                .userId(userIdOptional.get())
                .postId(dto.postId())
                .content(dto.content())
                .imageUrl(dto.imageUrl())
                .build();
        commentRepository.save(comment);

        return true;
    }

    public void saveCommentForNewPost(Comment comment) {
        commentRepository.save(comment);
    }

    public List<VwComment> findAllCommentsOfPost(Long postId) {
        return commentRepository.findAllCommentsByPostId(postId);

    }
}
