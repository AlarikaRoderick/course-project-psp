package com.company.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.company.service.ChangeWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminFirstPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button filmButton;

    @FXML
    private Button sessionButton;

    @FXML
    private Button ticketButton;

    @FXML
    private Button userButton;

    @FXML
    private Button exitButton;

    @FXML
    void initialize() {

    }

    private ChangeWindow changeWindow = new ChangeWindow();

    public void goToFilmPage(){
        try {
            changeWindow.changeWindow(filmButton, "/fxml/adminFilmPage.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void goToUserPage(){
        try{
            changeWindow.changeWindow(userButton, "/fxml/adminUserPage.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void goToFirstPage(){
        try{
            changeWindow.changeWindow(exitButton, "/fxml/firstPage.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
