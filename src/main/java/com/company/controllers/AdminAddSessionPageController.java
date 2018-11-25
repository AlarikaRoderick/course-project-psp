package com.company.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import com.company.entities.SessionEntity;
import com.company.entities.current.CurrentFilmEntity;
import com.company.service.AdminAddSessionService;
import com.company.service.ChangeWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AdminAddSessionPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField sessionHallField;

    @FXML
    private TextField sessionHourField;

    @FXML
    private TextField sessionMinuteField;

    @FXML
    private Button addButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button backButton;

    @FXML
    private DatePicker sessionDateField;

    private AdminAddSessionService adminAddSessionService = new AdminAddSessionService();
    private ChangeWindow changeWindow = new ChangeWindow();

    @FXML
    void initialize() {

    }

    public void addSession(){
        Date sessionDate = Date.valueOf(sessionDateField.getValue());
        int sessionHall = Integer.valueOf(sessionHallField.getText());
        int sessionHour = Integer.valueOf(sessionHourField.getText());
        int sessionMinute = Integer.valueOf(sessionMinuteField.getText());
        SessionEntity session = new SessionEntity(sessionDate, CurrentFilmEntity.getFilm().getId_film(),
                sessionHall, sessionHour, sessionMinute);
        try{
            String request = adminAddSessionService.addSession(session);
            if (request.equals("successfulAdd")){
                System.out.println("Сеанс добавлен");
                clear();
            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void back(){
        try {
            changeWindow.changeWindow(backButton, "/fxml/adminFilmSessionPage.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clear(){
        sessionDateField.getEditor().clear();
        sessionHallField.clear();
        sessionHourField.clear();
        sessionMinuteField.clear();
    }
}
