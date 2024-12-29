package com.sacak.forumcivillian.constants;

import com.sacak.forumcivillian.entity.User;
import com.sacak.forumcivillian.entity.enums.EUserRank;

public class CheckUserRank {
    public static void checkUserRank(User user){
        if(user.getTotalComments()>=5000){
            user.setUserRank(EUserRank.LICH_KING);
        }
        else if(user.getTotalComments()>=1000){
            user.setUserRank(EUserRank.HERO);
        }
        else if(user.getTotalComments()>=500){
            user.setUserRank(EUserRank.CHAMPION);
        }
        else if(user.getTotalComments()>=250){
            user.setUserRank(EUserRank.WARLORD);
        }
        else if(user.getTotalComments()>=100){
            user.setUserRank(EUserRank.KNIGHT);
        }
        else if(user.getTotalComments()>=50){
            user.setUserRank(EUserRank.GRUNT);
        }
        else if(user.getTotalComments()>=10){
            user.setUserRank(EUserRank.FOOTMAN);
        }
        else{
            user.setUserRank(EUserRank.PEASANT);
        }
    }
}
