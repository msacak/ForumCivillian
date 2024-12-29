package com.sacak.forumcivillian.repository;

import com.sacak.forumcivillian.entity.Post;
import com.sacak.forumcivillian.views.VwAllPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    @Query("SELECT new com.sacak.forumcivillian.views.VwAllPost(p.id,u.userName,p.lastReplier,p.title,p.createAt,p.updateAt,p.totalComments) FROM Post p JOIN User u ON u.id=p.userId WHERE p.topicId=?1 ORDER BY p.updateAt LIMIT 5")
    List<VwAllPost> findTop5PostsByTopicId(Long topicId);

    @Query("SELECT new com.sacak.forumcivillian.views.VwAllPost(p.id,u.userName,p.lastReplier,p.title,p.createAt,p.updateAt,p.totalComments) FROM Post p JOIN User u ON u.id=p.userId WHERE p.topicId=?1 ORDER BY p.updateAt")
    List<VwAllPost> findALlPostByTopicId(Long topicId);
}
