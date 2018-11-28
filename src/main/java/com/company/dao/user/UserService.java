package com.company.dao.user;

import com.company.entities.UserEntity;

import java.util.List;

public class UserService {
    private UserDAOImpl userDAO = new UserDAOImpl();

    public UserService(){}

    public UserEntity findUser(String login, String password){
        return new UserDAOImpl().findUserById(login, password);
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
