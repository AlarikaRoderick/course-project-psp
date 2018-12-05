package com.company.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.company.dao.film.FilmService;
import com.company.dao.session.SessionService;
import com.company.dao.ticket.TicketService;
import com.company.entities.FilmEntity;
import com.company.entities.SessionEntity;
import com.company.entities.TicketEntity;
import com.company.entities.current.CurrentUserEntity;
import com.company.service.ChangeWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserMakeOrderPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button findSessionButton;

    @FXML
    private Button addOrderButton;

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<String> filmNameComboBox;

    @FXML
    private TableView sessionForCurrentFilmTable;

    @FXML
    private TableColumn idSessionColumn;

    @FXML
    private TableColumn dateColumn;

    @FXML
    private TableColumn hourColumn;

    @FXML
    private TableColumn minuteColumn;

    @FXML
    private TableView ticketColumn;

    @FXML
    private TableColumn idTicketColumn;

    @FXML
    private TableColumn priceColumn;

    @FXML
    private TableColumn placeRowColumn;

    @FXML
    private TableColumn placeNumberColumn;

    @FXML
    private Button findTicketsButton;

    private FilmService filmService = new FilmService();
    private SessionService sessionService = new SessionService();
    private TicketService ticketService = new TicketService();
    private ChangeWindow changeWindow = new ChangeWindow();

    @FXML
    void initialize() {
        List<FilmEntity> films = filmService.findAllFilms();
        List<String> filmNames = new ArrayList<>();
        for (FilmEntity film : films){
            filmNames.add(film.getFilmName());
        }
        ObservableList<String> filmEntities = FXCollections.observableArrayList(filmNames);
        filmNameComboBox.setItems(filmEntities);
    }

    public void findSessions() {
        String filmName = filmNameComboBox.getSelectionModel().getSelectedItem();
        List<SessionEntity> sessions = sessionService.findSessionsByFilmName(filmName);
        idSessionColumn.setCellValueFactory(new PropertyValueFactory<SessionEntity, Integer>("id_session"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<SessionEntity, Date>("sessionDate"));
        hourColumn.setCellValueFactory(new PropertyValueFactory<SessionEntity, Integer>("sessionTimeHour"));
        minuteColumn.setCellValueFactory(new PropertyValueFactory<SessionEntity, Integer>("sessionTimeMinute"));
        ObservableList<SessionEntity> sessionEntities = FXCollections.observableList(sessions);
        sessionForCurrentFilmTable.setItems(sessionEntities);
    }

    public void findTickets() {
        idTicketColumn.setCellValueFactory(new PropertyValueFactory<TicketEntity, Integer>("id_ticket"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<TicketEntity, Integer>("ticketPrice"));
        placeRowColumn.setCellValueFactory(new PropertyValueFactory<TicketEntity, Integer>("placeRow"));
        placeNumberColumn.setCellValueFactory(new PropertyValueFactory<TicketEntity, Integer>("placeNumber"));
        int idSession = ((SessionEntity)sessionForCurrentFilmTable.getSelectionModel().getSelectedItem()).getId_session();
        List<TicketEntity> tickets = ticketService.findFreeTickets(idSession);
        ObservableList<TicketEntity> ticketEntities = FXCollections.observableList(tickets);
        ticketColumn.setItems(ticketEntities);
    }

    public void addUserTicket(){
        int idTicket = ((TicketEntity)ticketColumn.getSelectionModel().getSelectedItem()).getId_ticket();
        int idUser = CurrentUserEntity.getUser().getId_user();
        ticketService.setUserTicket(idUser, idTicket);
    }

    public void back(){
        try {
            changeWindow.changeWindow(backButton, "/fxml/userPage.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
