package com.company.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {
    public void start(Stage stage) throws Exception {
        String fxmlFile = "/fxml/firstPage.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
        stage.setTitle("Вход");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        ClientConnection.createConnection();
        System.out.println("connection established");
        launch(args);
    }
}
