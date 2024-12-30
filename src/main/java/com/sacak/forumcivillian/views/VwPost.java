package com.sacak.forumcivillian.views;

import com.sacak.forumcivillian.entity.Comment;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

public record VwPost(

        Long id,
        String author,
        String title,
        List<VwComment> commentList,
        int totalPage
) {




}
