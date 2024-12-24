package com.sacak.forumcivillian.service;

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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
                .lastReplier(userOptional.get().getUserName())
                .commentCount(1)
                .build();
        postRepository.save(post);
        Comment comment = Comment.builder()
                .postId(post.getId())
                .content(dto.content())
                .userId(userIdOpt.get())
                .imageUrl(dto.imageUrl())
                .build();
        commentService.save(comment);
        return true;

    }

    public List<VwAllPost> getAllPostsOnTopic(Long topicId) {
        return postRepository.findAllByTopicId(topicId);
    }

    public VwPost getPostById(Long postId) {
        System.out.println(postId);
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


}
