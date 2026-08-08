package com.summer.section1.group7.eco_resort.Srijon.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;

public class receptionistDashboardController {

    @FXML private AnchorPane contentArea;
    @FXML private Button btnCreateReservation;
    @FXML private Button btnViewReservation;
    @FXML private Button btnCancelReservation;
    @FXML private Button btnAssignRoom;
    @FXML private Button btnUpdateRoomStatus;
    @FXML private Button btnViewRooms;
    @FXML private Button btnOccupancySummary;
    @FXML private Button btnUpdateGuestInfo;
    @FXML private Button btnLogout;

    @FXML
    public void initialize() {
        // load default view
        openViewReservation(null);
    }

    @FXML public void openCreateReservation(ActionEvent event) {
        loadUI("/com/summer/section1/group7/eco_resort/Srijon/Receptionist/createReservation.fxml");
    }

    @FXML public void openViewReservation(ActionEvent event) {
        loadUI("/com/summer/section1/group7/eco_resort/Srijon/Receptionist/viewReservation.fxml");
    }

    @FXML public void openCancelReservation(ActionEvent event) {
        loadUI("/com/summer/section1/group7/eco_resort/Srijon/Receptionist/cancelReservation.fxml");
    }

    @FXML public void openAssignRoom(ActionEvent event) {
        loadUI("/com/summer/section1/group7/eco_resort/Srijon/Receptionist/assignRoom.fxml");
    }

    @FXML public void openUpdateRoomStatus(ActionEvent event) {
        loadUI("/com/summer/section1/group7/eco_resort/Srijon/Receptionist/updateRoomStatus.fxml");
    }

    @FXML public void openViewRooms(ActionEvent event) {
        loadUI("/com/summer/section1/group7/eco_resort/Srijon/Receptionist/viewRooms.fxml");
    }

    @FXML public void openOccupancySummary(ActionEvent event) {
        loadUI("/com/summer/section1/group7/eco_resort/Srijon/Receptionist/occupancySummary.fxml");
    }

    @FXML public void openUpdateGuestInfo(ActionEvent event) {
        loadUI("/com/summer/section1/group7/eco_resort/Srijon/Receptionist/updateGuestInfo.fxml");
    }

    @FXML public void openLogout(ActionEvent event) {
        loadUI("/com/summer/section1/group7/eco_resort/Login.fxml");
    }

    private void loadUI(String fxmlPath) {
        try {
            URL url = getClass().getResource(fxmlPath);
            System.out.println("Loading UI: " + fxmlPath + " -> " + url);
            if (url == null) {
                System.err.println("FXML not found: " + fxmlPath);
                return;
            }
            Parent root = FXMLLoader.load(url);

            contentArea.getChildren().clear();
            contentArea.getChildren().add(root);
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
