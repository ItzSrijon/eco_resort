package com.summer.section1.group7.eco_resort.Srijon.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class receptionistDashboardController {

    @FXML
    private AnchorPane ContentArea;

    // Resource folder under resources/.../Receptionist/
    private static final String BASE = "/com/summer/section1/group7/eco_resort/Srijon/Receptionist/";

    private void loadUI(String fxmlName) {
        try {
            URL url = getClass().getResource(BASE + fxmlName);
            System.out.println("Trying: " + BASE + fxmlName);
            System.out.println("URL = " + url);

            if (url == null) {
                throw new RuntimeException("FXML not found: " + BASE + fxmlName);
            }

            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            // replace content and make it fill the AnchorPane
            ContentArea.getChildren().setAll(root);
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openCreateReservation(ActionEvent actionEvent) {
        loadUI("createReservation.fxml");
    }

    @FXML
    public void openViewReservation(ActionEvent actionEvent) {
        loadUI("viewReservation.fxml");
    }

    @FXML
    public void openCancelReservation(ActionEvent actionEvent) {
        loadUI("cancelReservation.fxml");
    }

    @FXML
    public void openAssignRoom(ActionEvent actionEvent) {
        loadUI("assignRoom.fxml");
    }

    @FXML
    public void openUpdateRoomStatus(ActionEvent actionEvent) {
        loadUI("updateRoomStatus.fxml");
    }

    @FXML
    public void openViewRooms(ActionEvent actionEvent) {
        loadUI("viewRooms.fxml");
    }

    @FXML
    public void openOccupancySummary(ActionEvent actionEvent) {
        loadUI("occupancySummary.fxml");
    }

    @FXML
    public void openUpdateGuestInfo(ActionEvent actionEvent) {
        loadUI("updateGuestInfo.fxml");
    }

    @FXML
    public void logout(ActionEvent actionEvent) {
        System.out.println("Logout clicked");
        // implement logout logic if needed
    }
}
