package com.sacak.forumcivillian.service;

import com.sacak.forumcivillian.constants.CheckUserRank;
import com.sacak.forumcivillian.dto.request.NewPostRequest;
import com.sacak.forumcivillian.entity.Comment;
import com.sacak.forumcivillian.entity.Post;
import com.sacak.forumcivillian.entity.User;
import com.sacak.forumcivillian.exceptions.ErrorType;
import com.sacak.forumcivillian.exceptions.ForumCivillianException;
import com.sacak.forumcivillian.repository.PostRepository;
import com.sacak.forumcivillian.utility.JwtManager;
import com.sacak.forumcivillian.views.VwAllPost;
import com.sacak.forumcivillian.views.VwComment;
import com.sacak.forumcivillian.views.VwPost;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final JwtManager jwtManager;
    private final UserService userService;
    private final CommentService commentService;

    public PostService(
            PostRepository postRepository,
            JwtManager jwtManager,
            UserService userService,
            @Lazy CommentService commentService) {
        this.postRepository = postRepository;
        this.jwtManager = jwtManager;
        this.userService = userService;
        this.commentService = commentService;
    }

    @Transactional
    public Boolean createPost(NewPostRequest dto) {
        Long userId = jwtManager.validateToken(dto.token()).orElseThrow(()->new ForumCivillianException(ErrorType.INVALID_TOKEN));
        User user = userService.findById(userId);
        Post post = Post.builder()
                .title(dto.title())
                .userId(userId)
                .lastReplier(user.getUserName())
                .totalComments(1)
                .build();
        postRepository.save(post);
        Comment comment = Comment.builder()
                .postId(post.getId())
                .content(dto.content())
                .userId(userId)
                .imageUrl(dto.imageUrl())
                .build();
        commentService.save(comment);
        CheckUserRank.checkUserRank(user);
        user.setTotalComments(user.getTotalComments() + 1);
        userService.save(user);
        return true;

    }

    public List<VwAllPost> getAllPostsOnTopic(Long topicId) {
        return postRepository.findAllByTopicId(topicId);
    }

    public VwPost getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ForumCivillianException(ErrorType.POST_NOT_FOUND));
        List<VwComment> commentList = commentService.findAllCommentsOfPost(postId);
        String author = userService.findUserNameByUserId(post.getUserId());

        return VwPost.builder()
                .id(postId)
                .author(author)
                .title(post.getTitle())
                .commentList(commentList)
                .build();
    }


    public Post findById(Long postId) {
        return postRepository.findById(postId).orElseThrow(()->new ForumCivillianException(ErrorType.POST_NOT_FOUND));
    }

    public void save(Post post) {
        postRepository.save(post);
    }
}
