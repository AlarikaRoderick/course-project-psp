package com.company.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.company.adapter.FilmAdapter;
import com.company.dao.film.FilmService;
import com.company.entities.FilmEntity;
import com.company.entities.current.CurrentFilmEntity;
import com.company.service.AdminFilmPageService;
import com.company.service.ChangeWindow;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
    private TableColumn filmGenreColumn;

    @FXML
    private TableColumn timeColumn;

    @FXML
    private TableColumn ageRatingColumn;

    @FXML
    private TextField filmNameField;

    @FXML
    private TextField filmGenreField;

    @FXML
    private TextField timeField;

    @FXML
    private TextField ageRatingField;

    @FXML
    private Button updateFilmButton;

    @FXML
    private Button deleteFilmButton;

    @FXML
    private Button findFilmSessionButton;

    @FXML
    private Button backButton;

    @FXML
    private Button addNewFilmButton;

    private AdminFilmPageService adminFilmPageService = new AdminFilmPageService();
    private FilmService filmService = new FilmService();
    private FilmAdapter filmAdapter = new FilmAdapter();
    private ChangeWindow changeWindow = new ChangeWindow();

    @FXML
    void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<FilmEntity, Integer>("id_film"));
        filmNameColumn.setCellValueFactory(new PropertyValueFactory<FilmEntity, String>("filmName"));
        filmGenreColumn.setCellValueFactory(new PropertyValueFactory<FilmEntity, String>("filmGenre"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<FilmEntity, String>("filmTime"));
        ageRatingColumn.setCellValueFactory(new PropertyValueFactory<FilmEntity, String>("filmRating"));
//        try {
//            request = adminFilmPageService.getFilms();
//            filmEntities = (ObservableList<FilmEntity>) request.get("filmList");
//        }catch (IOException | ClassNotFoundException e){
//            e.printStackTrace();
//        }
        List<FilmEntity> films = filmService.findAllFilms();
        ObservableList<FilmEntity> filmEntities = filmAdapter.convertFromListToObservableList(films);
        filmTable.setItems(filmEntities);
        initClick();
    }

    private void initClick() {
        filmTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1){
                    String filmName = ((FilmEntity)filmTable.getSelectionModel().getSelectedItem()).getFilmName();
                    String filmGenre = ((FilmEntity)filmTable.getSelectionModel().getSelectedItem()).getFilmGenre();
                    String filmTime = ((FilmEntity)filmTable.getSelectionModel().getSelectedItem()).getFilmTime();
                    String filmRating = ((FilmEntity)filmTable.getSelectionModel().getSelectedItem()).getFilmRating();
                    setTextFields(filmName, filmGenre, filmTime, filmRating);
                }
            }
        });
    }

    public void updateFilm(){
        FilmEntity film = getFilmFromTextFields();
        try{
            String request = adminFilmPageService.updateFilm(film);
            if (request.equals("successfulUpdate")){
                System.out.println(request);
                initialize();
                clear();
            }
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void deleteFilm(){
        FilmEntity film = getFilmFromTextFields();
        try{
            String request = adminFilmPageService.deleteFilm(film);
            if (request.equals("successfulDelete")){
                System.out.println(request);
                clear();
                initialize();
            }
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void goToAddFilmPage(){
        try{
            changeWindow.changeWindow(addNewFilmButton, "/fxml/adminAddFilmPage.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void goToAdminFirstPage(){
        try{
            changeWindow.changeWindow(backButton, "/fxml/adminFirstPage.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void goToFilmSessionPage(){
        try{
            FilmEntity film = getFilmFromTextFields();
            CurrentFilmEntity.setFilm(film);
            changeWindow.changeWindow(findFilmSessionButton, "/fxml/adminFilmSessionPage.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private FilmEntity getFilmFromTextFields() {
        int id = ((FilmEntity)filmTable.getSelectionModel().getSelectedItem()).getId_film();
        String filmName = filmNameField.getText();
        String filmGenre = filmGenreField.getText();
        String filmTime = timeField.getText();
        String filmRating = ageRatingField.getText();

        FilmEntity film = new FilmEntity(filmName, filmGenre, filmTime, filmRating);
        film.setId_film(id);

        return film;
    }

    private void setTextFields(String filmName, String filmGenre, String filmTime, String filmRating) {
        filmNameField.setText(filmName);
        filmGenreField.setText(filmGenre);
        timeField.setText(filmTime);
        ageRatingField.setText(filmRating);
    }

    private void clear(){
        filmNameField.clear();
        filmGenreField.clear();
        timeField.clear();
        ageRatingField.clear();
    }

}
