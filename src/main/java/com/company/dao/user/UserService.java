package com.company.dao.user;

import com.company.entities.UserEntity;

import java.util.List;

public class UserService {
    private UserDAOImpl userDAO = new UserDAOImpl();

    public UserService(){}

    public UserEntity findUser(int id){
        return userDAO.findUserById(id);
    }

    public void saveUser(UserEntity user){
        userDAO.saveUser(user);
    }

    public void updateUser(UserEntity user){
        userDAO.updateUser(user);
    }

    public void deleteUser(UserEntity user){
        userDAO.deleteUser(user);
    }

    public List<UserEntity> findAllUsers() {
        return userDAO.findAll();
    }
}
