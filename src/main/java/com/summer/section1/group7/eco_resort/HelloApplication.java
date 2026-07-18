package com.summer.section1.group7.eco_resort;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Piya/guestLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Welcome to the Registration Page!");
        stage.setScene(scene);
        stage.show();
    }
}
