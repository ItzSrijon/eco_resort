package com.summer.section1.group7.eco_resort;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // absolute resource path ensures the FXML is found on the classpath
        URL fxmlUrl = HelloApplication.class.getResource("/com/summer/section1/group7/eco_resort/Login.fxml");
        System.out.println("Login.fxml URL = " + fxmlUrl);
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
