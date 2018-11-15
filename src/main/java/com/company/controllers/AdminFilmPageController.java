package com.company.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.company.adapter.FilmAdapter;
import com.company.dao.film.FilmService;
import com.company.entities.FilmEntity;
import com.company.service.AdminFilmPageService;
import com.company.service.SendObjectService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONObject;

public class AdminFilmPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView filmTable;

    @FXML
    private TableColumn idColumn;

    @FXML
    private TableColumn filmNameColumn;

    @FXML
    private TableColumn genreFilmColumn;

    @FXML
    private TableColumn timeColumn;

    @FXML
    private TextField filmNameField;

    @FXML
    private TextField genreFilmField;

    @FXML
    private TextField timeField;

    @FXML
    private TextField ageRatingField;

    @FXML
    private Button updateFilmButton;

    @FXML
    private Button deleteFilmButton;

    @FXML
    private Button addNewFilmButton;

    private AdminFilmPageService adminFilmPageService;
    private FilmService filmService = new FilmService();
    private FilmAdapter filmAdapter;
    @FXML
    void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<FilmEntity, Integer>("id_film"));
        filmNameColumn.setCellValueFactory(new PropertyValueFactory<FilmEntity, String>("filmName"));
        genreFilmColumn.setCellValueFactory(new PropertyValueFactory<FilmEntity, String>("filmGenre"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<FilmEntity, String>("filmTime"));
        List<FilmEntity> films = filmService.findAllFilms();
        ObservableList<FilmEntity> filmEntities = FXCollections.observableList(films);
        //ObservableList<FilmEntity> filmEntities = filmAdapter.convertFromListToObservableList(films);
        filmTable.setItems(filmEntities);
    }
}
