package com.sacak.forumcivillian.views;

import com.sacak.forumcivillian.entity.Comment;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VwPost {

    Long id;
    String author;
    String title;
    List<VwComment> commentList;
}
