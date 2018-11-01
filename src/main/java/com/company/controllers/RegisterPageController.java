package com.company.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.company.entities.UserEntity;
import com.company.service.RegisterPageService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;

public class RegisterPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signUpButton;

    private RegisterPageService registerPageService = new RegisterPageService();

    @FXML
    void initialize() {

    }

    public void signUpUser(){
        UserEntity user = createUser();
        JSONObject object = new JSONObject();
        object.put("action", "signUp");
        object.put("newUser", user);
        try{
            String request = registerPageService.signUpUser(object);
            if (request.equals("successfulSignUp")) {
                System.out.println("Пользователь успешно зарегистрирован");
            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private UserEntity createUser() {
        UserEntity user = new UserEntity();
        user.setUserName(nameField.getText());
        user.setUserSurname(surnameField.getText());
        user.setUserAge(Integer.parseInt(ageField.getText()));
        user.setUserLogin(loginField.getText());
        user.setUserPassword(passwordField.getText());
        return user;
    }
}
