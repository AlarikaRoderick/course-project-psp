package com.company.dao.user;

import com.company.entities.UserEntity;

import java.util.List;

public interface UserDAO {
    UserEntity findUserById(int id);
    void saveUser(UserEntity user);
    void updateUser(UserEntity user);
    void deleteUser(UserEntity user);
    List<UserEntity> findAll();
}
