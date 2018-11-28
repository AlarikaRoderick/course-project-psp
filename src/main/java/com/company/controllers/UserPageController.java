package com.company.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.company.entities.current.CurrentUserEntity;
import com.company.service.ChangeWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class UserPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button makeOrderButton;

    @FXML
    private Button checkOrderButton;

    @FXML
    private Label surnameLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label ageLabel;

    @FXML
    private Label loginLabel;

    @FXML
    private Button editUserButton;

    @FXML
    private Button exitButton;

    private ChangeWindow changeWindow = new ChangeWindow();

    @FXML
    void initialize() {
        surnameLabel.setText(CurrentUserEntity.getUser().getUserSurname());
        nameLabel.setText(CurrentUserEntity.getUser().getUserName());
        ageLabel.setText(Integer.toString(CurrentUserEntity.getUser().getUserAge()));
        loginLabel.setText(CurrentUserEntity.getUser().getUserLogin());
    }

    public void editUser(){
        try{
            changeWindow.changeWindow(editUserButton, "/fxml/editUserPage.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void exit(){
        try {
            changeWindow.changeWindow(exitButton, "/fxml/firstPage.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void checkOrder(){
        try{
            changeWindow.changeWindow(checkOrderButton, "/fxml/userOrderPage.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void makeOrder(){
        try {
            changeWindow.changeWindow(makeOrderButton, "/fxml/userMakeOrderPage.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
