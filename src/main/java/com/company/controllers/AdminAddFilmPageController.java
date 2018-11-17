package com.company.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.company.entities.FilmEntity;
import com.company.service.AdminAddFilmService;
import com.company.service.ChangeWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AdminAddFilmPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField filmNameField;

    @FXML
    private TextField filmGenreField;

    @FXML
    private TextField filmTimeField;

    @FXML
    private TextField filmRatingField;

    @FXML
    private Button addButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button backButton;

    private AdminAddFilmService adminAddFilmService = new AdminAddFilmService();
    private ChangeWindow changeWindow = new ChangeWindow();

    @FXML
    void initialize() {

    }

    public void addFilm(){
        String filmName = filmNameField.getText();
        String filmGenre = filmGenreField.getText();
        String filmTime = filmTimeField.getText();
        String filmRating = filmRatingField.getText();

        FilmEntity film = new FilmEntity(filmName, filmGenre, filmTime, filmRating);
        try {
            String request = adminAddFilmService.addFilm(film);
            if(request.equals("successfulAdd")){
                System.out.println("Фильм добавлен");
                clear();
            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void back(){
        try {
            changeWindow.changeWindow(backButton, "/fxml/adminFilmPage.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void clear(){
        filmNameField.clear();
        filmGenreField.clear();
        filmTimeField.clear();
        filmRatingField.clear();
    }
}
