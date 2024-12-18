package com.sacak.forumcivillian.views;

import com.sacak.forumcivillian.entity.enums.EUserRank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public record VwComment(
        Long id,
        String author,
        EUserRank userRank,
        String content,
        String imageUrl
) {}
