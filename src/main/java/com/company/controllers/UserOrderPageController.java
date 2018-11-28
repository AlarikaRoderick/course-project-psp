package com.company.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.company.dao.order.OrderService;
import com.company.entities.Order;
import com.company.entities.current.CurrentUserEntity;
import com.company.service.ChangeWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserOrderPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView userOrderTable;

    @FXML
    private TableColumn idTicketColumn;

    @FXML
    private TableColumn sessionDateColumn;

    @FXML
    private TableColumn placeRowColumn;

    @FXML
    private TableColumn placeNumberColumn;

    @FXML
    private TableColumn sessionHourColumn;

    @FXML
    private TableColumn sessionMinuteColumn;

    @FXML
    private TableColumn filmNameColumn;

    @FXML
    private Button deleteOrderButton;

    @FXML
    private Button backButton;

    private OrderService orderService = new OrderService();
    private ChangeWindow changeWindow = new ChangeWindow();

    @FXML
    void initialize() {
        idTicketColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("idTicket"));
        sessionDateColumn.setCellValueFactory(new PropertyValueFactory<Order, Date>("sessionDate"));
        placeRowColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("placeRow"));
        placeNumberColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("placeNumber"));
        sessionHourColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("sessionHour"));
        sessionMinuteColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("sessionMinute"));
        filmNameColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("filmName"));

        List<Order> orders = orderService.findAllOrders(CurrentUserEntity.getUser().getId_user());
        ObservableList<Order> orderObservableList = FXCollections.observableList(orders);
        userOrderTable.setItems(orderObservableList);
    }

    public void deleteOrder(){
        int id = ((Order)userOrderTable.getSelectionModel().getSelectedItem()).getIdTicket();
        orderService.deleteOrder(id);
        initialize();
    }

    public void back(){
        try {
            changeWindow.changeWindow(backButton, "/fxml/userPage.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
