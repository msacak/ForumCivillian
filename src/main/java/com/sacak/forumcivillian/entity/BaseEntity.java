package com.sacak.forumcivillian.entity;

import com.sacak.forumcivillian.entity.enums.EState;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@MappedSuperclass
public class BaseEntity {

    @Builder.Default
    Long createAt = System.currentTimeMillis();
    @Builder.Default
    Long updateAt = System.currentTimeMillis();
    @Builder.Default
    EState state = EState.ACTIVE;

}
