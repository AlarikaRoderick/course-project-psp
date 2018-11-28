package com.company.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.company.entities.UserEntity;
import com.company.entities.current.CurrentUserEntity;
import com.company.service.ChangeWindow;
import com.company.service.EditUserPageService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.persistence.criteria.CriteriaBuilder;

public class EditUserPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button saveButton;

    @FXML
    private Button backButton;

    private EditUserPageService editUserPageService = new EditUserPageService();
    private ChangeWindow changeWindow = new ChangeWindow();

    @FXML
    void initialize() {
        surnameField.setText(CurrentUserEntity.getUser().getUserSurname());
        nameField.setText(CurrentUserEntity.getUser().getUserName());
        ageField.setText(String.valueOf(CurrentUserEntity.getUser().getUserAge()));
        loginField.setText(CurrentUserEntity.getUser().getUserLogin());
        passwordField.setText(CurrentUserEntity.getUser().getUserPassword());

    }

    public void save(){
        int id = CurrentUserEntity.getUser().getId_user();
        String surname = surnameField.getText();
        String name = nameField.getText();
        int age = Integer.valueOf(ageField.getText());
        String login = loginField.getText();
        String password = passwordField.getText();

        UserEntity user = new UserEntity(name, surname, age, login, password, false);
        user.setId_user(id);
        try{
            String request = editUserPageService.updateUser(user);
            if (request.equals("successfulEdit")){
                System.out.println("Пользователь обновлен");
                CurrentUserEntity.setUser(user);
                initialize();
            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void back(){
        try{
            changeWindow.changeWindow(backButton, "/fxml/userPage.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
