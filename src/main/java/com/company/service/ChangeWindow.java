package com.company.service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ChangeWindow {

    public void changeWindow(Button button, String window) throws IOException {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
        URL path = new File(window).toURL();
        Parent root = FXMLLoader.load(path);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
