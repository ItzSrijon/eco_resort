package com.summer.section1.group7.eco_resort;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;


public class SceneSwitcher {

    public static Stage stage;


    public static void switchTo(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource(fxmlFileName));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Navigation Error");
            alert.setHeaderText("Could not open the requested screen.");
            alert.setContentText("Missing or invalid file: " + fxmlFileName);
            alert.showAndWait();
        }
    }
}