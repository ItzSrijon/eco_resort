package com.summer.section1.group7.eco_resort.Siam;

import com.summer.section1.group7.eco_resort.User;
import com.summer.section1.group7.eco_resort.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.time.LocalDate;

public class ScheduleTrainingSessionController {
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
    private User loadedGuest;
    @FXML
    public void initialize() {

        sessionTimeCB.getItems().addAll("08:00 AM - 10:00 AM", "10:00 AM - 12:00 PM",
                "12:00 PM - 02:00 PM", "02:00 PM - 04:00 PM", "04:00 PM - 06:00 PM",
                "06:00 PM - 08:00 PM", "08:00 PM - 10:00 PM", "09:00 PM - 11:00 PM");
        trainerCB.getItems().addAll("Ashik Rahman", "Rafi Hossain", "Siam Mahmud", "Tahmid Khan", "Nusrat Jahan", "Maliha Islam");
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
    @FXML
    public void loadGuestOA(ActionEvent actionEvent) {

        String guestId = guestIdTF.getText().trim();
        if (guestId.isEmpty()) {

            showAlert(Alert.AlertType.ERROR, "Error", null, "Please enter Guest ID.");
            return;
        }

        loadedGuest = null;

        for (User user : UserManager.getUserList()) {

            if (user.getUserId().equalsIgnoreCase(guestId)
                    && user.getRole().equalsIgnoreCase("Guest")) {

                loadedGuest = user;
                break;
            }

        }

        if (loadedGuest == null) {

            guestNameTF.clear();
            phoneNumberTF.clear();

            showAlert(Alert.AlertType.ERROR, "Not Found", null, "Guest not found.");
            return;
        }

        guestNameTF.setText(loadedGuest.getName());
        phoneNumberTF.setText(loadedGuest.getPhoneNumber());

        showAlert(Alert.AlertType.INFORMATION, "Success", null, "Guest loaded successfully.");

    }
    @FXML
    public void scheduleSessionOA(ActionEvent actionEvent) {

        if (loadedGuest == null) {
            showAlert(Alert.AlertType.ERROR, "Error", null, "Load guest first.");
            return;
        }

        if (sessionDateDP.getValue() == null || sessionTimeCB.getValue() == null || trainerCB.getValue() == null) {

            showAlert(Alert.AlertType.ERROR, "Error", null, "Please fill all fields."
            );
            return;
        }
        if (sessionDateDP.getValue().isBefore(LocalDate.now())) {

            showAlert(Alert.AlertType.ERROR, "Invalid Date", null, "Please select today's date or a future date.");
            return;
        }

        TrainingSession session = new TrainingSession(
                loadedGuest.getUserId(),
                loadedGuest.getName(),
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

            showAlert(Alert.AlertType.INFORMATION, "Session Scheduled", null,
                    "Training Session Successfully Scheduled!\n\n"
                            + "Guest ID : " + loadedGuest.getUserId()
                            + "\nGuest : " + loadedGuest.getName()
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

    private void showAlert(Alert.AlertType type, String title, String header, String message) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}