package com.company.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.company.dao.ticket.TicketService;
import com.company.entities.TicketEntity;
import com.company.entities.current.CurrentFilmEntity;
import com.company.entities.current.CurrentSessionEntity;
import com.company.service.AdminTicketSessionService;
import com.company.service.ChangeWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.json.simple.JSONObject;

import javax.persistence.criteria.CriteriaBuilder;

public class AdminTicketSessionPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label sessionDateLabel;

    @FXML
    private Label filmNameLabel;

    @FXML
    private Label sessionHourLabel;

    @FXML
    private TableView ticketTable;

    @FXML
    private TableColumn idColumn;

    @FXML
    private TableColumn ticketPriceColumn;

    @FXML
    private TableColumn placeRowColumn;

    @FXML
    private TableColumn placeNumberColumn;

    @FXML
    private TableColumn idSessionColumn;

    @FXML
    private TextField ticketPriceField;

    @FXML
    private TextField idSessionField;

    @FXML
    private TextField placeRowField;

    @FXML
    private TextField placeNumberField;

    @FXML
    private Button addTicketButton;

    @FXML
    private Button updateTicketButton;

    @FXML
    private Button deleteTicketButton;

    @FXML
    private Button backButton;

    @FXML
    private Label sessionMinuteLabel;

    private TicketService ticketService = new TicketService();
    private AdminTicketSessionService adminTicketSessionService = new AdminTicketSessionService();
    private ChangeWindow changeWindow = new ChangeWindow();

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        sessionDateLabel.setText(String.valueOf(CurrentSessionEntity.getSession().getSessionDate()));
        filmNameLabel.setText(CurrentFilmEntity.getFilm().getFilmName());
        sessionHourLabel.setText(String.valueOf(CurrentSessionEntity.getSession().getSessionTimeHour()));
        sessionMinuteLabel.setText(String.valueOf(CurrentSessionEntity.getSession().getSessionTimeMinute()));

        setTicketTable();
        initClick();
    }

    private void setTicketTable() throws IOException, ClassNotFoundException {
        idColumn.setCellValueFactory(new PropertyValueFactory<TicketEntity, Integer>("id_ticket"));
        ticketPriceColumn.setCellValueFactory(new PropertyValueFactory<TicketEntity, Integer>("ticketPrice"));
        placeRowColumn.setCellValueFactory(new PropertyValueFactory<TicketEntity, Integer>("placeRow"));
        placeNumberColumn.setCellValueFactory(new PropertyValueFactory<TicketEntity, Integer>("placeNumber"));
        idSessionColumn.setCellValueFactory(new PropertyValueFactory<TicketEntity, Integer>("idSessionTicket"));
        JSONObject object = adminTicketSessionService.getTickets();
        List<TicketEntity> tickets = (List<TicketEntity>) object.get("ticketList");
        List<TicketEntity> currentTickets = new ArrayList<>();
        for (TicketEntity ticket : tickets){
            if (ticket.getIdSessionTicket() == CurrentSessionEntity.getSession().getId_session()){
                currentTickets.add(ticket);
            }
        }
        ObservableList<TicketEntity> ticketEntities = FXCollections.observableList(currentTickets);
        ticketTable.setItems(ticketEntities);
    }

    private void initClick() {
        ticketTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1){
                    int id = ((TicketEntity)ticketTable.getSelectionModel().getSelectedItem()).getId_ticket();
                    int price = ((TicketEntity)ticketTable.getSelectionModel().getSelectedItem()).getTicketPrice();
                    int placeRow = ((TicketEntity)ticketTable.getSelectionModel().getSelectedItem()).getPlaceRow();
                    int placeNumber = ((TicketEntity)ticketTable.getSelectionModel().getSelectedItem()).getPlaceNumber();
                    ticketPriceField.setText(String.valueOf(price));
                    placeRowField.setText(String.valueOf(placeRow));
                    placeNumberField.setText(String.valueOf(placeNumber));
                }
            }
        });
    }

    public void addTicket(){
        TicketEntity ticket = createTicket();
        try {
            String request = adminTicketSessionService.addTicket(ticket);
            if (request.equals("successfulAdd")){
                System.out.println("Билет добавлен");
                clearFields();
                initialize();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        ticketPriceField.clear();
        placeRowField.clear();
        placeNumberField.clear();
    }

    public void updateTicket(){
        TicketEntity ticket = createTicket();
        int id = ((TicketEntity)ticketTable.getSelectionModel().getSelectedItem()).getId_ticket();
        ticket.setId_ticket(id);
        try{
            String request = adminTicketSessionService.updateTicket(ticket);
            if (request.equals("successfulUpdate")){
                System.out.println("Билет обновлен");
                clearFields();
                setTicketTable();
            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void deleteTicket(){
        TicketEntity ticket = createTicket();
        int id = ((TicketEntity)ticketTable.getSelectionModel().getSelectedItem()).getId_ticket();
        ticket.setId_ticket(id);
        try{
            String request = adminTicketSessionService.deleteTicket(ticket);
            if (request.equals("successfulDelete")){
                System.out.println("Билет удален");
                clearFields();
                initialize();
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

    private TicketEntity createTicket() {
        int ticketPrice = Integer.valueOf(ticketPriceField.getText());
        int idSession = CurrentSessionEntity.getSession().getId_session();
        int placeRow = Integer.valueOf(placeRowField.getText());
        int placeNumber = Integer.valueOf(placeNumberField.getText());
        TicketEntity ticket = new TicketEntity(ticketPrice, idSession, 0, placeNumber, placeRow);
        return ticket;
    }
}
