package com.sacak.forumcivillian.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.sacak.forumcivillian.views.VwComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PostResponse {
    Long id;
    String author;
    String lastReplier;
    String title;
    @Builder.Default
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDateTime createdAt = LocalDateTime.now();
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Builder.Default
    LocalDateTime updatedAt =LocalDateTime.now();
    Integer totalReplies;
    @Builder.Default
    List<VwComment> commentList = new ArrayList<>();

}
