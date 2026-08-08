package com.summer.section1.group7.eco_resort.Siam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import java.io.*;
import java.time.LocalDate;
import javafx.collections.ObservableList;

public class U3G7_AssignFitnessPackageController {
    @FXML
    private TextField phoneTF;
    @FXML
    private TextField startDateTF;
    @FXML
    private TextField currentPackageTF;
    @FXML
    private TextField guestNameTF;
    @FXML
    private TextField expiryDateTF;
    @FXML
    private ComboBox<String> newFitnessPackageCB;
    @FXML
    private TextField guestIdTF;
    @FXML
    private AnchorPane mainPane;
    private GymMember loadedMember;

    @FXML
    public void initialize() {
        newFitnessPackageCB.getItems().addAll("Basic", "Premium", "VIP");

    }

    @FXML
    public void searchGuestOA(ActionEvent actionEvent) {

        loadedMember = null;

        guestNameTF.clear();
        phoneTF.clear();
        currentPackageTF.clear();
        startDateTF.clear();
        expiryDateTF.clear();

        newFitnessPackageCB.getSelectionModel().clearSelection();

        if (guestIdTF.getText().trim().isEmpty()) {

            showAlert(Alert.AlertType.ERROR, "Error", "Please enter Guest ID."
            );

            return;
        }

        loadedMember = GymManager.findGymMember(guestIdTF.getText().trim()
        );

        if (loadedMember == null) {
            showAlert(Alert.AlertType.ERROR, "Not Found", "Gym member not found.");

            return;
        }

        guestNameTF.setText(loadedMember.getGuestName());
        phoneTF.setText(loadedMember.getPhoneNumber());
        currentPackageTF.setText(loadedMember.getPackageName());

        startDateTF.setText(loadedMember.getRegistrationDate().toString());

        expiryDateTF.setText(loadedMember.getRegistrationDate().plusMonths(loadedMember.getDuration()).toString());

    }

    @FXML
    public void assignPackageOA(ActionEvent actionEvent) {

        if (loadedMember == null) {

            showAlert(Alert.AlertType.ERROR, "Error", "Search a gym member first.");

            return;
        }

        if (newFitnessPackageCB.getValue() == null) {

            showAlert(Alert.AlertType.ERROR, "Error", "Please select a fitness package.");

            return;
        }

        String newPackage = newFitnessPackageCB.getValue();

        if (loadedMember.getPackageName().equalsIgnoreCase(newPackage)) {

            showAlert(Alert.AlertType.ERROR, "Invalid Package", "This member already has the selected package.");

            return;
        }

        LocalDate startDate = LocalDate.now();

        loadedMember.setPackageName(newPackage);
        loadedMember.setRegistrationDate(startDate);

        ObservableList<GymMember> memberList = GymManager.loadMembers();

        for (GymMember gm : memberList) {

            if (gm.getGuestId().equalsIgnoreCase(loadedMember.getGuestId())) {
                gm.setPackageName(newPackage);
                gm.setRegistrationDate(startDate);

                break;
            }
        }
        GymManager.saveMembers(memberList);

        showAlert(Alert.AlertType.INFORMATION, "Success", "Fitness package assigned successfully."
        );

        clearFields();
    }
    private void showAlert(Alert.AlertType type, String title, String message) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    public void backButtonOA(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GymManagerDashboard.fxml"));
            Node node = loader.load();
            mainPane.getChildren().setAll(node);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void clearFields() {

        guestIdTF.clear();
        guestNameTF.clear();
        phoneTF.clear();
        currentPackageTF.clear();
        startDateTF.clear();
        expiryDateTF.clear();
        newFitnessPackageCB.getSelectionModel().clearSelection();

        loadedMember = null;
    }
}