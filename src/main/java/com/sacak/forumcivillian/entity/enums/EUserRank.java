package com.sacak.forumcivillian.entity.enums;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@Getter
public enum EUserRank {
    PEASANT,//0-10
    FOOTMAN,//10-50
    GRUNT,//50-100
    KNIGHT,//100-250
    WARLORD,//250-500
    CHAMPION,//500-1000
    HERO,//1000-5000
    LICH_KING//5000++

}
