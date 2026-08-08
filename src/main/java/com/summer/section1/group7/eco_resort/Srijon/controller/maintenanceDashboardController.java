package com.summer.section1.group7.eco_resort.Srijon.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class maintenanceDashboardController {

    @FXML private AnchorPane contentArea;

    @FXML
    public void initialize() {
        // Default view
        openViewRequests(null);
    }

    @FXML public void openCreateRequest(ActionEvent e) {
        loadUI("/com/summer/section1/group7/eco_resort/Srijon/Maintenance Officer/createMaintenanceRequest.fxml");
    }

    @FXML public void openViewRequests(ActionEvent e) {
        loadUI("/com/summer/section1/group7/eco_resort/Srijon/Maintenance Officer/viewMaintenanceRequest.fxml");
    }

    @FXML public void openUpdateRepairStatus(ActionEvent e) {
        loadUI("/com/summer/section1/group7/eco_resort/Srijon/Maintenance Officer/updateRepairStatus.fxml");
    }

    @FXML public void openUpdateEquipment(ActionEvent e) {
        loadUI("/com/summer/section1/group7/eco_resort/Srijon/Maintenance Officer/updateEquipment.fxml");
    }

    @FXML public void openViewEquipment(ActionEvent e) {
        loadUI("/com/summer/section1/group7/eco_resort/Srijon/Maintenance Officer/viewEquipment.fxml");
    }

    @FXML public void openLogWork(ActionEvent e) {
        loadUI("/com/summer/section1/group7/eco_resort/Srijon/Maintenance Officer/logWork.fxml");
    }

    @FXML public void openSummary(ActionEvent e) {
        loadUI("/com/summer/section1/group7/eco_resort/Srijon/Maintenance Officer/maintenanceSummary.fxml");
    }

    @FXML public void openCloseRequest(ActionEvent e) {
        loadUI("/com/summer/section1/group7/eco_resort/Srijon/Maintenance Officer/closeRequest.fxml");
    }

    @FXML public void openLogout(ActionEvent e) {
        loadUI("/com/summer/section1/group7/eco_resort/Login.fxml");
    }

    private void loadUI(String fxmlPath) {
        try {
            URL url = getClass().getResource(fxmlPath);
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
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
