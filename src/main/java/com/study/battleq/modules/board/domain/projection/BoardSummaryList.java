package com.study.battleq.modules.board.domain.projection;

import com.study.battleq.modules.board.domain.entity.User;
import com.study.battleq.modules.user.domain.entity.UserEntity;

public interface BoardSummaryList {
    String getId();

    String getTitle();

    String getContent();

    String getCategory();

    User getUser();

    interface UserSummary {
        String getEmail();
        String getNickName();
    }

    int getUp();

    int getDown();

    int getBest();
}
