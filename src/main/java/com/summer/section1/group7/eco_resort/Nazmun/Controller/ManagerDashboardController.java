package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerDashboardController
{
    @javafx.fxml.FXML
    private Label loginErrorLabel;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void reservationsButtonOA(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(
                    "/com/summer/section1/group7/eco_resort/Nazmun/ManageReservation2.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            loginErrorLabel.setText("Failed to open Reservations.");
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void roomManagementButtonOA(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(
                    "/com/summer/section1/group7/eco_resort/Nazmun/ManageRoomRate3.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            loginErrorLabel.setText("Failed to open Room Management.");
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void logoutButtonOA(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(
                    "/com/summer/section1/group7/eco_resort/Login.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void budgetFinanceButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void staffManagementButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void safetyInspectionButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void foodBeverageButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void ecoMetricsButtonOA(ActionEvent actionEvent) {
    }
}