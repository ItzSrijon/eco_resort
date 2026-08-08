package com.summer.section1.group7.eco_resort.Piya.controller;

import com.summer.section1.group7.eco_resort.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SecurityDashboardController {

    private User currentUser;

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    @FXML
    public void checkInRecordButtonOA(ActionEvent event) {
        openPage(event, "/com/summer/section1/group7/eco_resort/Piya/CheckInManagement.fxml");
    }

    @FXML
    public void searchReservationButtonOA(ActionEvent event) {
        openPageWithUser(event, "/com/summer/section1/group7/eco_resort/Piya/SearchReservation.fxml");
    }

    @FXML
    public void checkOutManagementButtonOA(ActionEvent event) {
        openPage(event, "/com/summer/section1/group7/eco_resort/Piya/CheckOutManagement.fxml");
    }

    @FXML
    public void checkInSearchRecordButtonOA(ActionEvent event) {
        openPage(event, "/com/summer/section1/group7/eco_resort/Piya/SearchCheckInRecords.fxml");
    }

    @FXML
    public void reportIncidentButtonOA(ActionEvent event) {
        openPageWithUser(event, "/com/summer/section1/group7/eco_resort/Piya/ReportIncident.fxml");
    }

    @FXML
    public void viewIncidentButtonOA(ActionEvent event) {
        openPage(event, "/com/summer/section1/group7/eco_resort/Piya/ViewIncident.fxml");
    }

    @FXML
    public void lostFoundButtonOA(ActionEvent event) {
        openPageWithUser(event, "/com/summer/section1/group7/eco_resort/Piya/ReportLostAndFoundItems.fxml");
    }

    @FXML
    public void logoutButtonOA(ActionEvent event) {
        openPage(event, "/com/summer/section1/group7/eco_resort/Piya/Login.fxml");
    }

    private void openPage(ActionEvent event, String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openPageWithUser(ActionEvent event, String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Scene scene = new Scene(loader.load());

            Object controller = loader.getController();

            if (controller instanceof ReportIncidentController) {
                ((ReportIncidentController) controller).setCurrentUser(currentUser);
            } else if (controller instanceof SearchReservationController) {
                ((SearchReservationController) controller).setCurrentUser(currentUser);
            }

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}