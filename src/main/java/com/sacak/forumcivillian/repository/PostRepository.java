package com.sacak.forumcivillian.repository;

import com.sacak.forumcivillian.entity.Post;
import com.sacak.forumcivillian.views.VwAllPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    @Query("SELECT new com.sacak.forumcivillian.views.VwAllPost(p.id,u.userName,p.lastReplier,p.title,p.createAt,p.updateAt,p.totalComments) FROM Post p JOIN User u ON u.id=p.userId WHERE p.topicId=?1")
    List<VwAllPost> findAllByTopicId(Long topicId);
}
