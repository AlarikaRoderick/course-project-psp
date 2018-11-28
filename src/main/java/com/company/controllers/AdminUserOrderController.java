package com.company.controllers;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.company.adapter.FilmAdapter;
import com.company.dao.film.FilmService;
import com.company.dao.order.OrderService;
import com.company.dao.session.SessionService;
import com.company.dao.ticket.TicketService;
import com.company.entities.FilmEntity;
import com.company.entities.Order;
import com.company.entities.SessionEntity;
import com.company.entities.TicketEntity;
import com.company.entities.current.CurrentUserEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminUserOrderController {

    @FXML
    private Label surnameLabel;

    @FXML
    private Label nameLabel;

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
    private TableColumn filmNameColumn;

    @FXML
    private Button findSessionButton;

    @FXML
    private Button addOrderButton;

    @FXML
    private Button deleteOrderButton;

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
    private OrderService orderService = new OrderService();
    private SessionService sessionService = new SessionService();
    private TicketService ticketService = new TicketService();

    @FXML
    void initialize() {
        surnameLabel.setText(CurrentUserEntity.getUser().getUserSurname());
        nameLabel.setText(CurrentUserEntity.getUser().getUserName());
        List<FilmEntity> films = filmService.findAllFilms();
        List<String> filmNames = new ArrayList<>();
        for (FilmEntity film : films){
            filmNames.add(film.getFilmName());
        }
        ObservableList<String> filmEntities = FXCollections.observableArrayList(filmNames);
        filmNameComboBox.setItems(filmEntities);

        List<Order> orders = orderService.findAllOrders(CurrentUserEntity.getUser().getId_user());
        ObservableList<Order> orderObservableList = FXCollections.observableList(orders);
        idColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("idTicket"));
        sessionDateColumn.setCellValueFactory(new PropertyValueFactory<Order, Date>("sessionDate"));
        sessionHourColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("sessionHour"));
        sessionMinuteColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("sessionMinute"));
        filmNameColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("filmName"));
        sessionTable.setItems(orderObservableList);
    }

    public void findSessions(){
        String filmName = filmNameComboBox.getSelectionModel().getSelectedItem();
        List<SessionEntity> sessions = sessionService.findSessionsByFilmName(filmName);
        idSessionColumn.setCellValueFactory(new PropertyValueFactory<SessionEntity, Integer>("id_session"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<SessionEntity, Date>("sessionDate"));
        hourColumn.setCellValueFactory(new PropertyValueFactory<SessionEntity, Integer>("sessionTimeHour"));
        minuteColumn.setCellValueFactory(new PropertyValueFactory<SessionEntity, Integer>("sessionTimeMinute"));
        ObservableList<SessionEntity> sessionEntities = FXCollections.observableList(sessions);
        sessionForCurrentFilmTable.setItems(sessionEntities);
    }

    public void findTickets(){
        idTicketColumn.setCellValueFactory(new PropertyValueFactory<TicketEntity, Integer>("id_ticket"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<TicketEntity, Integer>("ticketPrice"));
        int idSession = ((SessionEntity)sessionForCurrentFilmTable.getSelectionModel().getSelectedItem()).getId_session();
        List<TicketEntity> tickets = ticketService.findTicketsBySessionId(idSession);
        ObservableList<TicketEntity> ticketEntities = FXCollections.observableList(tickets);
        ticketColumn.setItems(ticketEntities);
    }
}
