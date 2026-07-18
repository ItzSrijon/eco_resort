package Piya.controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class GuestDashboardController
{
    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void updateProfileButtonOA(ActionEvent actionEvent) {
    }

    @FXML
    public void submitFeedbackButtonOA(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/summer/section1/group7/eco_resort/Piya/submitFeedback.fxml"));

        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);

        stage.show();

    }

    @javafx.fxml.FXML
    public void browseActivitiesButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void logoutButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void reservationHistoryButtonOA(ActionEvent actionEvent) {
    }


    @FXML
    public void bookRoomButtonOA(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/summer/section1/group7/eco_resort/Piya/bookRoom.fxml"));

        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);

        stage.show();

    }
}