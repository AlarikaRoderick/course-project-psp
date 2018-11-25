package com.company.dao.user;

import com.company.entities.UserEntity;

import java.util.List;

public class UserService {
    private UserDAOImpl userDAO = new UserDAOImpl();

    public UserService(){}

    public UserEntity findUser(int id){
        return new UserDAOImpl().findUserById(id);
    }

    public void saveUser(UserEntity user){
        new UserDAOImpl().saveUser(user);
    }

    public void updateUser(UserEntity user){
        new UserDAOImpl().updateUser(user);
    }

    public void deleteUser(UserEntity user){
        new UserDAOImpl().deleteUser(user);
    }

    public List<UserEntity> findAllUsers() {
        return new UserDAOImpl().findAll();
    }
}
