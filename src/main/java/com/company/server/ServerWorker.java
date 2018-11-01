package com.company.server;

import com.company.dao.user.UserService;
import com.company.entities.*;
import com.company.server.service.SendObjectService;
import org.json.simple.JSONObject;
import java.io.IOException;
import java.util.List;


public class ServerWorker {
    private SendObjectService sendObjectService;

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
            }

        }while (repeat);
    }
}
