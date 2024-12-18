package com.sacak.forumcivillian.service;

import com.sacak.forumcivillian.dto.request.NewPostRequest;
import com.sacak.forumcivillian.dto.response.PostResponse;
import com.sacak.forumcivillian.entity.Comment;
import com.sacak.forumcivillian.entity.Post;
import com.sacak.forumcivillian.entity.User;
import com.sacak.forumcivillian.exceptions.ErrorType;
import com.sacak.forumcivillian.exceptions.ForumCivillianException;
import com.sacak.forumcivillian.repository.CommentRepository;
import com.sacak.forumcivillian.repository.PostRepository;
import com.sacak.forumcivillian.utility.JwtManager;
import com.sacak.forumcivillian.views.VwComment;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final JwtManager jwtManager;
    private final UserService userService;
    private final CommentService commentService;

    @Transactional
    public Boolean createPost(NewPostRequest dto) {
        Optional<Long> userIdOpt = jwtManager.validateToken(dto.token());
        if(userIdOpt.isEmpty()) throw new ForumCivillianException(ErrorType.INVALID_TOKEN);
        Optional<User> userOptional = userService.findById(userIdOpt.get());
        if(userOptional.isEmpty()) throw new ForumCivillianException(ErrorType.USER_NOT_FOUND);
        Post post = Post.builder()
                .title(dto.title())
                .userId(userIdOpt.get())
                .build();
        postRepository.save(post);
        Comment comment = Comment.builder()
                .postId(post.getId())
                .content(dto.content())
                .userId(userIdOpt.get())
                .imageUrl(dto.imageUrl())
                .build();
        commentService.saveCommentForNewPost(comment);
        return true;

    }

    public PostResponse getPost(Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if(postOptional.isEmpty()) throw new ForumCivillianException(ErrorType.POST_NOT_FOUND);
        Post post = postOptional.get();
        Optional<User> userOptional = userService.findById(post.getUserId());
        if(userOptional.isEmpty()) throw new ForumCivillianException(ErrorType.USER_NOT_FOUND);
        User user = userOptional.get();
        PostResponse postResponse = PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .updatedAt(Instant.ofEpochMilli(post.getUpdateAt()).atZone(ZoneId.systemDefault()).toLocalDateTime())
                .createdAt(Instant.ofEpochMilli(post.getCreateAt()).atZone(ZoneId.systemDefault()).toLocalDateTime())
                .author(user.getUserName())
                .build();

        List<VwComment> commentList = commentService.findAllCommentsOfPost(postId);
        postResponse.setCommentList(commentList);
        postResponse.setLastReplier(commentList.getLast().author());
        return postResponse;
    }


}
