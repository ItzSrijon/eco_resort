package com.summer.section1.group7.eco_resort.Siam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.time.LocalDate;

public class U3G3_ScheduleTrainingSessionController {
    @FXML
    private DatePicker sessionDateDP;
    @FXML
    private TextField phoneNumberTF;
    @FXML
    private TextField guestNameTF;
    @FXML
    private ComboBox<String> trainerCB;
    @FXML
    private ComboBox<String> sessionTimeCB;
    @FXML
    private TextField guestIdTF;
    @FXML
    private AnchorPane mainPane;
    private GymMember loadedGuest;
    @FXML
    public void initialize() {

        sessionTimeCB.getItems().addAll("08:00 AM - 10:00 AM", "10:00 AM - 12:00 PM",
                "12:00 PM - 02:00 PM", "02:00 PM - 04:00 PM", "04:00 PM - 06:00 PM",
                "06:00 PM - 08:00 PM", "08:00 PM - 10:00 PM", "09:00 PM - 11:00 PM");
        trainerCB.getItems().addAll("Ashik Rahman", "Rafi Hossain", "Siam Mahmud", "Shakib Atulla", "Nusrat Jahan", "Maliha Islam");
    }


    @FXML
    public void loadGuestOA(ActionEvent actionEvent) {
        if (guestIdTF.getText().trim().isEmpty()) {

            showAlert(Alert.AlertType.ERROR, "Error", "Please enter Guest ID.");

            return;
        }

        loadedGuest = GymManager.findGymMember(guestIdTF.getText().trim());

        if (loadedGuest == null) {

            clearGuestInfo();

            showAlert(Alert.AlertType.ERROR, "Not Found", "Gym member not found.");

            return;
        }

        guestNameTF.setText(loadedGuest.getGuestName());
        phoneNumberTF.setText(loadedGuest.getPhoneNumber());

        showAlert(Alert.AlertType.INFORMATION, "Success", "Gym member loaded successfully.");

    }
    @FXML
    public void scheduleSessionOA(ActionEvent actionEvent) {

        if (loadedGuest == null) {
            showAlert(Alert.AlertType.ERROR, "Error",  "Load guest first.");
            return;
        }

        if (sessionDateDP.getValue() == null || sessionTimeCB.getValue() == null || trainerCB.getValue() == null) {

            showAlert(Alert.AlertType.ERROR, "Error", "Please fill all fields.");
            return;
        }
        if (sessionDateDP.getValue().isBefore(LocalDate.now())) {

            showAlert(Alert.AlertType.ERROR, "Invalid Date", "Please select today's date or a future date.");
            return;
        }

        TrainingSession session = new TrainingSession(
                loadedGuest.getGuestId(),
                loadedGuest.getGuestName(),
                loadedGuest.getPhoneNumber(),
                sessionTimeCB.getValue(),
                trainerCB.getValue(),
                sessionDateDP.getValue());

        File file = new File("trainingSession.bin");

        try {
            FileOutputStream fos;
            ObjectOutputStream oos;
            if (file.exists()) {
                fos = new FileOutputStream(file, true);
                oos = new AppendableObjectOutputStream(fos);

            } else {
                fos = new FileOutputStream(file);
                oos = new ObjectOutputStream(fos);
            }
            oos.writeObject(session);
            oos.close();

            showAlert(Alert.AlertType.INFORMATION, "Session Scheduled",
                    "Training Session Successfully Scheduled!\n\n"
                            + "Guest ID : " + loadedGuest.getGuestId()
                            + "\nGuest : " + loadedGuest.getGuestName()
                            + "\nDate : " + sessionDateDP.getValue()
                            + "\nTime : " + sessionTimeCB.getValue()
                            + "\nTrainer : " + trainerCB.getValue()
            );

            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void clearFields() {

        guestIdTF.clear();
        guestNameTF.clear();
        phoneNumberTF.clear();
        sessionDateDP.setValue(null);
        sessionTimeCB.getSelectionModel().clearSelection();
        trainerCB.getSelectionModel().clearSelection();
        loadedGuest = null;
    }

    private void showAlert(Alert.AlertType type, String title,String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void clearGuestInfo() {
        guestNameTF.clear();
        phoneNumberTF.clear();
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