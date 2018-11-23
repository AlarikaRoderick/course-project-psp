package com.company.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.company.dao.hall.HallService;
import com.company.dao.session.SessionService;
import com.company.dao.ticket.TicketService;
import com.company.entities.HallEntity;
import com.company.entities.SessionEntity;
import com.company.entities.TicketEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class UserPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView userImage;

    @FXML
    private Button affiche;

    @FXML
    private Button checkOrder;

    @FXML
    private Label sumOrder;

    private HallService hallService = new HallService();
    private SessionService sessionService = new SessionService();
    private TicketService ticketService = new TicketService();

    @FXML
    void initialize() {
        List<HallEntity> halls = hallService.findAllHalls();
        System.out.println(halls.isEmpty());
        List<SessionEntity> sessions = sessionService.findAllSessions();
        System.out.println(sessions.isEmpty());
        List<TicketEntity> tickets = ticketService.findAllTickets();
        System.out.println(tickets.isEmpty());
        //List<OrderEntity> orders = orderService.findAllOrders();
        //System.out.println(orders.isEmpty());
        /*for (OrderEntity order : orders){
            if (order.getUser().getId_user() == CurrentUserEntity.getUser().getId_user()){
                sumOrder.setText(String.valueOf(order.getOrderSum()));
            }
        }*/
    }
}
