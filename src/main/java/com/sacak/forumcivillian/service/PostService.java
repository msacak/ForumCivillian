package com.sacak.forumcivillian.service;

import com.sacak.forumcivillian.constants.CheckUserRank;
import com.sacak.forumcivillian.dto.request.NewPostRequest;
import com.sacak.forumcivillian.entity.Comment;
import com.sacak.forumcivillian.entity.Post;
import com.sacak.forumcivillian.entity.User;
import com.sacak.forumcivillian.entity.enums.EState;
import com.sacak.forumcivillian.exceptions.ErrorType;
import com.sacak.forumcivillian.exceptions.ForumCivillianException;
import com.sacak.forumcivillian.repository.PostRepository;
import com.sacak.forumcivillian.utility.JwtManager;
import com.sacak.forumcivillian.views.VwAllPost;
import com.sacak.forumcivillian.views.VwComment;
import com.sacak.forumcivillian.views.VwPost;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
                .topicId(dto.topicId())
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

    public List<VwAllPost> get5TopPostByTopicId(Long topicId) {
        return postRepository.get5TopPostByTopicId(topicId);
    }

    public Page<VwAllPost> findAllPostsByTopicId(Long topicId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findByTopicIdAndStateOrderByUpdateAtDesc(topicId, pageable, EState.ACTIVE)
                .map(post -> {
                    User user = userService.findById(post.getUserId());
                    return new VwAllPost(
                            post.getId(),
                            user.getUserName(),
                            post.getLastReplier(),
                            post.getTitle(),
                            post.getCreateAt(),
                            post.getUpdateAt(),
                            post.getTotalComments()
                    );
                });
    }

    public VwPost getPostById(Long postId,int page,int size) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ForumCivillianException(ErrorType.POST_NOT_FOUND));
        Page<VwComment> commentList = commentService.findAllCommentsByPostId(postId,page,size);
        String author = userService.findUserNameByUserId(post.getUserId());

        return new VwPost(postId,author,post.getTitle(),commentList.getContent(),commentList.getTotalPages());
    }

    public List<VwAllPost> findAllVwPostsByQuery(String query, int maxResult, int offSet) {
        System.out.println(query);
        return postRepository.findAllVwPostsByQuery(query,maxResult,offSet);

    }


    public Post findById(Long postId) {
        return postRepository.findById(postId).orElseThrow(()->new ForumCivillianException(ErrorType.POST_NOT_FOUND));
    }

    public void save(Post post) {
        postRepository.save(post);
    }
}
