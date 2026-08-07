package com.summer.section1.group7.eco_resort.Siam;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class UpdateMembershipStatusController {

    @FXML
    private TextField phoneNumberTF;
    @FXML
    private TextField guestNameTF;
    @FXML
    private TextField guestIDTF;
    @FXML
    private TextField emailAddressTF;
    @FXML
    private ComboBox<String> newStatusCB;
    @FXML
    private TextField currentStatusTF;
    @FXML
    private AnchorPane mainPane;
    private ObservableList<GymMember> memberList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        newStatusCB.getItems().addAll("Active", "Expired", "Suspended");
    }

    @FXML
    public void searchMemberOA(ActionEvent actionEvent) {

        if (guestIDTF.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter Guest ID.");
            return;
        }

        memberList = GymManager.loadMembers();

        GymMember gm = GymManager.findGymMember(guestIDTF.getText().trim());

        if (gm == null) {
            clearFields();
            showAlert(Alert.AlertType.ERROR, "Not Found", "Gym Member not found.");
            return;
        }

        guestNameTF.setText(gm.getGuestName());
        phoneNumberTF.setText(gm.getPhoneNumber());
        emailAddressTF.setText(gm.getEmail());
        currentStatusTF.setText(gm.getStatus());

        showAlert(Alert.AlertType.INFORMATION, "Success", "Guest loaded successfully.");

    }

    @FXML
    public void updateStatusOA(ActionEvent actionEvent) {

        if (memberList.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Search member first.");
            return;
        }

        if (newStatusCB.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select a new status.");
            return;
        }

        if (currentStatusTF.getText().equalsIgnoreCase(newStatusCB.getValue())) {

            showAlert(Alert.AlertType.ERROR, "Invalid Status", "Current status and new status are the same.");
            return;
        }

        boolean found = false;

        for (GymMember gm : memberList) {

            if (gm.getGuestId().equalsIgnoreCase(guestIDTF.getText().trim())) {
                gm.setStatus(newStatusCB.getValue());
                found = true;
                break;
            }
        }

        if (!found) {
            showAlert(Alert.AlertType.ERROR, "Error", "Gym Member not found.");
            return;

        }

        GymManager.saveMembers(memberList);
        currentStatusTF.setText(newStatusCB.getValue());
        showAlert(Alert.AlertType.INFORMATION, "Success", "Membership status updated successfully.");

        clearFields();

    }
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void clearFields() {
        guestIDTF.clear();
        guestNameTF.clear();
        phoneNumberTF.clear();
        emailAddressTF.clear();
        currentStatusTF.clear();
        newStatusCB.getSelectionModel().clearSelection();
        newStatusCB.setValue(null);
        memberList.clear();
    }
    @FXML
    public void backToDashboardOA(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GymManagerDashboard.fxml"));
            Node node = loader.load();
            mainPane.getChildren().setAll(node);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}