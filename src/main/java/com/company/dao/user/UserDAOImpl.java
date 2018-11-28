package com.company.dao.user;

import com.company.dbHandler.DbHandler;
import com.company.entities.UserEntity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    //private Connection connection = DbHandler.getInstance().getConnection();

    @Override
    public UserEntity findUserById(String login, String password) {
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        String findUser = "SELECT * FROM user WHERE user_login='" + login + "' and user_password='" + password +"'";
        ResultSet resultSet = null;
        UserEntity user = null;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(findUser);
            resultSet = preparedStatement.executeQuery(findUser);
            while (resultSet.next()){
                user = getUserFromDB(resultSet);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void saveUser(UserEntity user) {
        String saveUser = "INSERT INTO user (user_name, user_surname, user_age, user_login, user_password, isAdmin)"
                + "VALUES(?,?,?,?,?,?)";
        try{
            setUserInfo(user, saveUser);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void setUserInfo(UserEntity user, String saveUser) throws SQLException {
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(saveUser);
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getUserSurname());
        preparedStatement.setInt(3, user.getUserAge());
        preparedStatement.setString(4, user.getUserLogin());
        preparedStatement.setString(5, user.getUserPassword());
        preparedStatement.setBoolean(6, user.isAdmin());
        preparedStatement.executeUpdate();
    }

    @Override
    public void updateUser(UserEntity user) {
        int id = user.getId_user();
        String saveUser = "UPDATE user SET user_name=?, user_surname=?, user_age=?, user_login=?,"
                + "user_password=?, isAdmin=? WHERE id_user=" + id;
        try{
            setUserInfo(user, saveUser);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(UserEntity user) {
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        int id = user.getId_user();
        String delete = "DELETE FROM user WHERE id_user=" + id;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<UserEntity> findAll() {
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        ResultSet resultSet = null;
        List<UserEntity> users = new ArrayList<>();
        String selectAllClients = "SELECT * FROM user";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(selectAllClients);
            resultSet = preparedStatement.executeQuery(selectAllClients);

            while (resultSet.next()){
                UserEntity user = getUserFromDB(resultSet);
                users.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    private UserEntity getUserFromDB(ResultSet resultSet) throws SQLException {
        int id = Integer.valueOf(resultSet.getString(1));
        String userName = resultSet.getString(2);
        String userSurname = resultSet.getString(3);
        int userAge = Integer.valueOf(resultSet.getString(4));
        String userLogin = resultSet.getString(5);
        String userPassword = resultSet.getString(6);
        boolean isAdmin = resultSet.getBoolean(7);
        UserEntity user = new UserEntity(userName, userSurname, userAge, userLogin, userPassword, isAdmin);
        user.setId_user(id);
        return user;
    }
}
