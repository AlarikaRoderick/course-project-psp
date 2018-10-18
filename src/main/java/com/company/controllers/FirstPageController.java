package com.company.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.company.dao.cinema.CinemaService;
import com.company.entities.CinemaEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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

    @FXML
    void initialize() {
        CinemaService cinemaService = new CinemaService();
        CinemaEntity cinemaEntity = new CinemaEntity();
        cinemaEntity.setCinemaName("Cinema");
        cinemaEntity.setCinemaAddress("street");
        cinemaEntity.setCinemaPhone("+1111111");
        cinemaEntity.setCinemaUnderground("Malina");
        cinemaService.saveCinema(cinemaEntity);
    }
}
