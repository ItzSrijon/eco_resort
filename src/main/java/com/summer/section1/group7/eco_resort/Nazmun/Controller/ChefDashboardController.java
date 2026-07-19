package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ChefDashboardController
{
    @javafx.fxml.FXML
    private Label loginErrorLabel;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void ecoMealPlanningButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void foodWasteLogButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void foodQualityButtonOA(ActionEvent actionEvent) {
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
    public void kitchenStaffButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void menuManagementButtonOA(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(
                    "/com/summer/section1/group7/eco_resort/Nazmun/ChefManageMenu2.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            loginErrorLabel.setText("Failed to open Menu Management.");
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void kitchenInventoryButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void dailyPreparationButtonOA(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(
                    "/com/summer/section1/group7/eco_resort/Nazmun/ChefOverseeDailyPreparation3.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            loginErrorLabel.setText("Failed to open Daily Preparation.");
            e.printStackTrace();
        }
    }
}