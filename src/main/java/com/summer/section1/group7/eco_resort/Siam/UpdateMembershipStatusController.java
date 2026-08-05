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
            showAlert(Alert.AlertType.ERROR, "Error", null, "Please enter Guest ID.");
            return;
        }

        try {
            FileInputStream fis = new FileInputStream("gymMember.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    GymMember gm = (GymMember) ois.readObject();
                    memberList.add(gm);
                    if (gm.getGuestId().equalsIgnoreCase(guestIDTF.getText().trim())) {
                        guestNameTF.setText(gm.getGuestName());
                        phoneNumberTF.setText(gm.getPhoneNumber());
                        emailAddressTF.setText(gm.getEmail());
                        currentStatusTF.setText(gm.getStatus());
                        showAlert(Alert.AlertType.INFORMATION,
                                "Success",
                                null,
                                "Guest loaded successfully.");

                        break;
                    }

                } catch (EOFException e) {

                    ois.close();
                    return;
                }
            }

            if (guestNameTF.getText().isEmpty()) {

                showAlert(Alert.AlertType.ERROR, "Not Found", null, "Gym Member not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void backToDashboardOA(ActionEvent actionEvent) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("GymManagerDashboard.fxml"));

            Node node = loader.load();

            mainPane.getChildren().setAll(node);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
    @FXML
    public void updateStatusOA(ActionEvent actionEvent) {

        if (memberList.isEmpty()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Error",
                    null,
                    "Search member first."
            );
            return;
        }

        if (newStatusCB.getValue() == null) {

            showAlert(Alert.AlertType.ERROR, "Error", null, "Please select a new status.");
            return;
        }
        if (currentStatusTF.getText().equalsIgnoreCase(newStatusCB.getValue())) {

            showAlert(Alert.AlertType.ERROR, "Invalid Status", null, "Current status and new status are the same.\nPlease select a different status.");
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

            showAlert(Alert.AlertType.ERROR, "Error", null, "Gym Member not found.");
            return;
        }

        try {

            FileOutputStream fos = new FileOutputStream("gymMember.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (GymMember gm : memberList) {
                oos.writeObject(gm);
            }

            oos.close();

            currentStatusTF.setText(newStatusCB.getValue());

            showAlert(Alert.AlertType.INFORMATION, "Success", null, "Membership status updated successfully.");
            clearFields();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void showAlert(Alert.AlertType type, String title, String header, String message) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
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
}