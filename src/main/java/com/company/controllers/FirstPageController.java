package com.company.controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import com.company.entities.UserEntity;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class FirstPageController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginInputField;

    @FXML
    private PasswordField passwordInputField;

    @FXML
    private Button enterButton;

    @FXML
    private Button signUpButton;

    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    socket = new Socket("localhost", 1111);
                    objectInputStream = new ObjectInputStream(socket.getInputStream());
                    objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    UserEntity user = new UserEntity();
                    String login = loginInputField.getText().trim();
                    String password = passwordInputField.getText().trim();
                    user.setUserLogin(login);
                    user.setUserPassword(password);
                    System.out.println(login + " " + password);
                    objectOutputStream.writeObject(user);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
    }

    private void createConnection(ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) throws IOException {
        Socket socket = new Socket("localhost", 1111);
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
    }
}
