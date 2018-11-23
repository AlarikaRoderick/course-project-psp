package com.company.controllers;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.company.dao.session.SessionService;
import com.company.entities.SessionEntity;
import com.company.entities.current.CurrentFilmEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class AdminFilmSessionPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label filmNameLabel;

    @FXML
    private Label filmGenreLabel;

    @FXML
    private Label filmTimeLabel;

    @FXML
    private TableView sessionTable;

    @FXML
    private TableColumn idColumn;

    @FXML
    private TableColumn sessionDateColumn;

    @FXML
    private TableColumn sessionHourColumn;

    @FXML
    private TableColumn sessionMinuteColumn;

    @FXML
    private TableColumn hallNumberColumn;

    @FXML
    private TextField sessionHourField;

    @FXML
    private TextField sessionMinuteField;

    @FXML
    private TextField sessionHallField;

    @FXML
    private DatePicker sessionDateField;

    @FXML
    private Button addSessionButton;

    @FXML
    private Button updateSessionButton;

    @FXML
    private Button deleteSessionButton;

    @FXML
    private Button backButton;

    private SessionService sessionService = new SessionService();

    @FXML
    void initialize() {

        filmNameLabel.setText(CurrentFilmEntity.getFilm().getFilmName());
        filmGenreLabel.setText(CurrentFilmEntity.getFilm().getFilmGenre());
        filmTimeLabel.setText(CurrentFilmEntity.getFilm().getFilmTime());

        idColumn.setCellValueFactory(new PropertyValueFactory<SessionEntity, Integer>("id_session"));
        sessionDateColumn.setCellValueFactory(new PropertyValueFactory<SessionEntity, Date>("sessionDate"));
        sessionHourColumn.setCellValueFactory(new PropertyValueFactory<SessionEntity, Integer>("sessionTimeHour"));
        sessionMinuteColumn.setCellValueFactory(new PropertyValueFactory<SessionEntity, Integer>("sessionTimeMinute"));
        hallNumberColumn.setCellValueFactory(new PropertyValueFactory<SessionEntity, Integer>(""));
        List<SessionEntity> sessions = sessionService.findAllSessions();

        ObservableList<SessionEntity> sessionEntities = FXCollections.observableList(sessions);
        if(sessionEntities != null) {
            sessionTable.setItems(sessionEntities);
        }
        initClick();
    }

    private void initClick() {
        sessionTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1){
                    Date sessionDate = ((SessionEntity)sessionTable.getSelectionModel().getSelectedItem()).getSessionDate();
                    int sessionHour = ((SessionEntity)sessionTable.getSelectionModel().getSelectedItem()).getSessionTimeHour();
                    int sessionMinute = ((SessionEntity)sessionTable.getSelectionModel().getSelectedItem()).getSessionTimeMinute();
                    sessionDateField.getEditor().setText(String.valueOf(sessionDate));
                    sessionHourField.setText(String.valueOf(sessionHour));
                    sessionMinuteField.setText(String.valueOf(sessionMinute));
                }
            }
        });
    }


}
