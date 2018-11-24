package com.company.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import com.company.dao.film.FilmService;
import com.company.dao.hall.HallService;
import com.company.dao.session.SessionService;
import com.company.entities.FilmEntity;
import com.company.entities.HallEntity;
import com.company.entities.SessionEntity;
import com.company.entities.current.CurrentFilmEntity;
import com.company.service.AdminFilmSessionPageService;
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
    private HallService hallService = new HallService();
    private AdminFilmSessionPageService filmSessionPageService = new AdminFilmSessionPageService();
    private FilmService filmService = new FilmService();

    @FXML
    void initialize() {

        filmNameLabel.setText(CurrentFilmEntity.getFilm().getFilmName());
        filmGenreLabel.setText(CurrentFilmEntity.getFilm().getFilmGenre());
        filmTimeLabel.setText(CurrentFilmEntity.getFilm().getFilmTime());

        idColumn.setCellValueFactory(new PropertyValueFactory<SessionEntity, Integer>("id_session"));
        sessionDateColumn.setCellValueFactory(new PropertyValueFactory<SessionEntity, Date>("sessionDate"));
        sessionHourColumn.setCellValueFactory(new PropertyValueFactory<SessionEntity, Integer>("sessionTimeHour"));
        sessionMinuteColumn.setCellValueFactory(new PropertyValueFactory<SessionEntity, Integer>("sessionTimeMinute"));
        //hallNumberColumn.setCellValueFactory(new PropertyValueFactory<HallEntity, Integer>("hallName"));
        List<SessionEntity> sessions = sessionService.findAllSessions();
        List<SessionEntity> currentSessions = new ArrayList<>();
        for(SessionEntity session : sessions){
            if (session.getFilm().getId_film() == CurrentFilmEntity.getFilm().getId_film()){
                currentSessions.add(session);
            }
        }
        List<HallEntity> halls = hallService.findAllHalls();
        List<HallEntity> sessionHalls = new ArrayList<>();
        for (HallEntity hall : halls){
            for (SessionEntity session : currentSessions){
                if (hall.getId_hall() == session.getHall().getId_hall()){
                    sessionHalls.add(hall);
                }
            }
        }

        ObservableList<SessionEntity> sessionEntities = FXCollections.observableList(currentSessions);
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

    public void addSession(){
        Date sessionDate = Date.valueOf(sessionDateField.getValue());
        int sessionHour = Integer.valueOf(sessionHourField.getText());
        int sessionMinute = Integer.valueOf(sessionMinuteField.getText());
        int hallNumber = Integer.valueOf(sessionHallField.getText());
        SessionEntity session = createSession(sessionDate, sessionHour, sessionMinute, hallNumber);
        try {
            String request = filmSessionPageService.addSession(session);
            if (request.equals("successfulAdd")){
                System.out.println("Сеанс добавлен");
                initialize();
            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private SessionEntity createSession(Date sessionDate, int sessionHour, int sessionMinute, int hallNumber) {
        SessionEntity session = new SessionEntity();
        session.setSessionDate(sessionDate);
        session.setSessionTimeHour(sessionHour);
        session.setSessionTimeMinute(sessionMinute);
        int filmId = CurrentFilmEntity.getFilm().getId_film();
        FilmEntity film = filmService.findFilm(filmId);
        session.setFilm(film);
        HallEntity hall = hallService.findHall(hallNumber);
        session.setHall(hall);
        return session;
    }

}
