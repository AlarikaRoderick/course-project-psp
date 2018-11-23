package com.company.entities.current;

import com.company.entities.UserEntity;

public class CurrentUserEntity {
    private static UserEntity user;

    public static UserEntity getUser() {
        return user;
    }

    public static void setUser(UserEntity user) {
        CurrentUserEntity.user = user;
    }
}
