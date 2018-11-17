package com.company.server;

import com.company.adapter.FilmAdapter;
import com.company.dao.film.FilmService;
import com.company.dao.user.UserService;
import com.company.entities.*;
import com.company.server.service.SendObjectService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONObject;
import java.io.IOException;
import java.util.List;


public class ServerWorker {
    private SendObjectService sendObjectService;
    private FilmAdapter filmAdapter;

    public ServerWorker (SendObjectService sendObjectService){
        this.sendObjectService = sendObjectService;
    }

    public void runServerWorker() throws IOException, ClassNotFoundException {
        boolean repeat = true;
        CinemaEntity cinema = new CinemaEntity();
        FilmEntity film = new FilmEntity();
        HallEntity hall = new HallEntity();
        OrderEntity order = new OrderEntity();
        SessionEntity session = new SessionEntity();
        TicketEntity ticket = new TicketEntity();
        UserEntity user;
        UserService userService = new UserService();
        FilmService filmService = new FilmService();
        do {
            JSONObject message = this.sendObjectService.getObject();
            String action = (String) message.get("action");
            switch (action){
                case "signIn":
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
                            }
                            else
                                this.sendObjectService.sendMessage("suchUserExist");
                        }
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
                    //ObservableList<FilmEntity> filmEntityObservableList = filmAdapter.convertFromListToObservableList(films);
                    ObservableList<FilmEntity> filmEntityObservableList = FXCollections.observableList(films);
                    JSONObject object = new JSONObject();
                    object.put("filmList", filmEntityObservableList);
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
            }
        }while (repeat);
    }
}
