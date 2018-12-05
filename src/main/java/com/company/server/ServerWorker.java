package com.company.server;

import com.company.adapter.FilmAdapter;
import com.company.dao.film.FilmService;
import com.company.dao.session.SessionService;
import com.company.dao.ticket.TicketService;
import com.company.dao.user.UserService;
import com.company.entities.*;
import com.company.server.service.SendObjectService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ServerWorker {
    private SendObjectService sendObjectService;
    private FilmAdapter filmAdapter;

    public ServerWorker (SendObjectService sendObjectService){
        this.sendObjectService = sendObjectService;
    }

    public void runServerWorker() throws IOException, ClassNotFoundException {
        boolean repeat = true;
        JSONObject object = new JSONObject();
        CinemaEntity cinema = new CinemaEntity();
        FilmEntity film = new FilmEntity();
        HallEntity hall = new HallEntity();
        SessionEntity session = new SessionEntity();
        TicketEntity ticket = new TicketEntity();
        UserEntity user;
        UserService userService = new UserService();
        FilmService filmService = new FilmService();
        SessionService sessionService = new SessionService();
        TicketService ticketService = new TicketService();
        do {
            JSONObject message = this.sendObjectService.getObject();
            String action = (String) message.get("action");
            switch (action){
                case "signIn":
                    boolean isUser = false;
                    user = (UserEntity) message.get("user");
                    List<UserEntity> allUsers = userService.findAllUsers();
                    for (UserEntity userEntity: allUsers) {
                        String login = user.getUserLogin();
                        String password = user.getUserPassword();
                        if (userEntity.getUserLogin().equals(login) && userEntity.getUserPassword().equals(password)){
                            System.out.println("Такой пользователь есть в БД");
                            if (userEntity.isAdmin()){
                                System.out.println("Администратор");
                                this.sendObjectService.sendMessage("suchUserAdmin");
                                isUser = true;
                                break;
                            }
                            else {
                                this.sendObjectService.sendMessage("suchUserExist");
                                isUser = true;
                                break;
                            }
                        }
                    }
                    if (!isUser){
                        this.sendObjectService.sendMessage("noSuchUser");
                    }
                    break;
                case "signUp":
                    user = (UserEntity) message.get("newUser");
                    userService.saveUser(user);
                    System.out.println("Пользователь сохранен в БД");
                    this.sendObjectService.sendMessage("successfulSignUp");
                    break;
                case "getFilms":
                    List<FilmEntity> films = filmService.findAllFilms();
                    object = new JSONObject();
                    object.put("filmList", films);
                    this.sendObjectService.sendObject(object);
                    break;
                case "getSessions":
                    List<SessionEntity> sessions = sessionService.findAllSessions();
                    object = new JSONObject();
                    object.put("sessionList", sessions);
                    this.sendObjectService.sendObject(object);
                    break;
                case "getTickets":
                    List<TicketEntity> tickets = ticketService.findAllTickets();
                    object = new JSONObject();
                    object.put("ticketList", tickets);
                    this.sendObjectService.sendObject(object);
                    break;
                case "getUsers":
                    List<UserEntity> users = userService.findAllUsers();
                    object = new JSONObject();
                    object.put("userList", users);
                    this.sendObjectService.sendObject(object);
                    break;
                case "updateFilm":
                    film = (FilmEntity) message.get("film");
                    filmService.updateFilm(film);
                    System.out.println("Фильм обновлен");
                    this.sendObjectService.sendMessage("successfulUpdate");
                    break;
                case "deleteFilm":
                    film = (FilmEntity) message.get("delFilm");
                    filmService.deleteFilm(film);
                    System.out.println("Фильм удален");
                    this.sendObjectService.sendMessage("successfulDelete");
                    break;
                case "addFilm":
                    film = (FilmEntity) message.get("addFilm");
                    filmService.saveFilm(film);
                    System.out.println("Фильм добавлен");
                    this.sendObjectService.sendMessage("successfulAdd");
                    break;
                case "updateUser":
                    user = (UserEntity) message.get("user");
                    userService.updateUser(user);
                    System.out.println("Пользователь обновлен");
                    this.sendObjectService.sendMessage("successfulUpdate");
                    break;
                case "deleteUser":
                    user = (UserEntity) message.get("user");
                    userService.deleteUser(user);
                    System.out.println("Пользователь удален");
                    this.sendObjectService.sendMessage("successfulDelete");
                    break;
                case "addUser":
                    user = (UserEntity) message.get("user");
                    userService.saveUser(user);
                    System.out.println("Пользователь добавлен");
                    this.sendObjectService.sendMessage("successfulAdd");
                    break;
                case "addSession":
                    session = (SessionEntity) message.get("session");
                    sessionService.saveSession(session);
                    System.out.println("Сеанс добавлен");
                    this.sendObjectService.sendMessage("successfulAdd");
                    break;
                case "updateSession":
                    session = (SessionEntity) message.get("session");
                    sessionService.updateSession(session);
                    System.out.println("Сеанс обновлен");
                    this.sendObjectService.sendMessage("successfulUpdate");
                    break;
                case "deleteSession":
                    session = (SessionEntity) message.get("session");
                    sessionService.deleteSession(session);
                    System.out.println("Сеанс удален");
                    this.sendObjectService.sendMessage("successfulDelete");
                    break;
                case "addTicket":
                    ticket = (TicketEntity) message.get("ticket");
                    ticketService.saveTicket(ticket);
                    System.out.println("Билет добавлен");
                    this.sendObjectService.sendMessage("successfulAdd");
                    break;
                case "updateTicket":
                    ticket = (TicketEntity) message.get("ticket");
                    ticketService.updateTicket(ticket);
                    System.out.println("Билет обновлен");
                    this.sendObjectService.sendMessage("successfulUpdate");
                    break;
                case "deleteTicket":
                    ticket = (TicketEntity) message.get("ticket");
                    ticketService.deleteTicket(ticket);
                    System.out.println("Билет удален");
                    this.sendObjectService.sendMessage("successfulDelete");
                    break;
                case "editUser":
                    user = (UserEntity) message.get("user");
                    userService.updateUser(user);
                    System.out.println("Пользователь обновлен");
                    this.sendObjectService.sendMessage("successfulEdit");
                    break;
            }
        }while (repeat);
    }
}
