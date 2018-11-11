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
    void initialize() {

    }

    private ChangeWindow changeWindow = new ChangeWindow();

    public void goToFilmPage(){
        try {
            changeWindow.changeWindow(filmButton, "src/main/resources/fxml/adminFilmPage.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void goToSessionPage(){
        try{
            changeWindow.changeWindow(sessionButton, "src/main/resources/fxml/adminSessionPage.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void goToTicketPage(){
        try{
            changeWindow.changeWindow(ticketButton, "src/main/resources/fxml/adminTicketPage.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void goToUserPage(){
        try{
            changeWindow.changeWindow(userButton, "src/main/resources/fxml/adminUserPage.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
