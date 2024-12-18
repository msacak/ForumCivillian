package com.sacak.forumcivillian.repository;

import com.sacak.forumcivillian.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

}
