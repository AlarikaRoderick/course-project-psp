package com.company.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.company.entities.UserEntity;
import com.company.entities.current.CurrentUserEntity;
import com.company.service.ChangeWindow;
import com.company.service.FirstPageService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;

public class FirstPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginInputField;

    @FXML
    private PasswordField passwordInputField;

    @FXML
    private Button enterButton;

    @FXML
    private Button signUpButton;

    private FirstPageService firstPageService = new FirstPageService();
    private ChangeWindow changeWindow = new ChangeWindow();

    public void signIn() {
        UserEntity user = createUser();
        JSONObject object = new JSONObject();
        object.put("action", "signIn");
        object.put("user", user);
        try {
            String request = firstPageService.signInUser(object);
            switch (request){
                case "suchUserExist":
                    System.out.println("Вход выполнен успешно");
                    CurrentUserEntity.setUser(user);
                    changeWindow.changeWindow(enterButton, "/fxml/userPage.fxml");
                    break;
                case "suchUserAdmin":
                    System.out.println("Вы вошли как администратор");
                    changeWindow.changeWindow(enterButton, "/fxml/adminFirstPage.fxml");
                    break;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void signUp() {
        try {
            changeWindow.changeWindow(signUpButton, "/fxml/registerPage.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private UserEntity createUser(){
        UserEntity user = new UserEntity();
        String login = loginInputField.getText().trim();
        String password = passwordInputField.getText().trim();
        user.setUserLogin(login);
        user.setUserPassword(password);
        return user;
    }
}
