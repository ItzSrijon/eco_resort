package com.summer.section1.group7.eco_resort.Siam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

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

    private ArrayList<GymMember> memberList = new ArrayList<>();

    @FXML
    public void initialize() {

        newFitnessPackageCB.getItems().addAll(
                "Basic",
                "Premium",
                "VIP"
        );

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

        memberList.clear();

        if (guestIdTF.getText().trim().isEmpty()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Error",
                    null,
                    "Please enter Guest ID."
            );
            return;
        }

        try {

            FileInputStream fis = new FileInputStream("gymMember.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {

                try {

                    GymMember gm = (GymMember) ois.readObject();

                    memberList.add(gm);

                    if (gm.getGuestId().equalsIgnoreCase(
                            guestIdTF.getText().trim())) {

                        loadedMember = gm;

                        guestNameTF.setText(gm.getGuestName());
                        phoneTF.setText(gm.getPhoneNumber());
                        currentPackageTF.setText(gm.getPackageName());

                        startDateTF.setText(
                                gm.getRegistrationDate().toString());

                        expiryDateTF.setText(
                                gm.getRegistrationDate()
                                        .plusMonths(gm.getDuration())
                                        .toString());

                        break;

                    }

                } catch (EOFException e) {

                    break;

                }

            }

            ois.close();

            if (loadedMember == null) {

                showAlert(
                        Alert.AlertType.ERROR,
                        "Not Found",
                        null,
                        "Gym member not found."
                );

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    @FXML
    public void assignPackageOA(ActionEvent actionEvent) {

        if (loadedMember == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Error",
                    null,
                    "Search a gym member first."
            );
            return;
        }

        if (newFitnessPackageCB.getValue() == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Error",
                    null,
                    "Please select a fitness package."
            );
            return;
        }

        if (loadedMember.getPackageName().equalsIgnoreCase(newFitnessPackageCB.getValue())) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Package",
                    null,
                    "This member already has the selected package."
            );
            return;
        }

        LocalDate startDate = LocalDate.now();
        LocalDate expiryDate = startDate.plusMonths(loadedMember.getDuration());

        for (GymMember gm : memberList) {

            if (gm.getGuestId().equalsIgnoreCase(guestIdTF.getText().trim())) {

                gm.setPackageName(newFitnessPackageCB.getValue());
                gm.setRegistrationDate(startDate);
                loadedMember = gm;

                break;
            }

        }

        try {

            FileOutputStream fos = new FileOutputStream("gymMember.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (GymMember gm : memberList) {
                oos.writeObject(gm);
            }

            oos.close();

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Success",
                    null,
                    "Fitness package assigned successfully."
            );
            clearFields();
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
        memberList.clear();

    }
    private void showAlert(Alert.AlertType type,
                            String title,
                            String header,
                            String message) {

        Alert alert = new Alert(type);

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        alert.showAndWait();

    }
    @FXML
    public void backButtonOA(ActionEvent actionEvent) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("GymManagerDashboard.fxml"));

            Node node = loader.load();

            mainPane.getChildren().setAll(node);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}