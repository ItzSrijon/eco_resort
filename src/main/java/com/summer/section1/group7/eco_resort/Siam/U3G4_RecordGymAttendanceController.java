package com.summer.section1.group7.eco_resort.Siam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class U3G4_RecordGymAttendanceController {
    @FXML
    private TextField packageTF;
    @FXML
    private TextField guestNameTF;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private TextField statusTF;
    @FXML
    private TextField guestIdTF;
    @FXML
    private ComboBox<String> attendanceCB;
    @FXML
    private TextField phoneNumberTF;
    @FXML
    private DatePicker attendanceDateDP;
    @FXML
    private TextField checkInTimeTF;
    private GymMember loadedMember;


    @FXML
    public void initialize() {
        attendanceCB.getItems().addAll("Present", "Absent");
    }

    @FXML
    public void searchGuestOA(ActionEvent actionEvent) {

        loadedMember = null;

        if (guestIdTF.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", null, "Please enter Guest ID.");

            return;

        }
        loadedMember = GymManager.findGymMember(guestIdTF.getText().trim());
        if (loadedMember == null) {
            guestNameTF.clear();
            phoneNumberTF.clear();
            packageTF.clear();
            statusTF.clear();
            showAlert(Alert.AlertType.ERROR, "Not Found", null, "Gym member not found.");

            return;

        }
        guestNameTF.setText(loadedMember.getGuestName());
        phoneNumberTF.setText(loadedMember.getPhoneNumber());
        packageTF.setText(loadedMember.getPackageName());
        statusTF.setText(loadedMember.getStatus());
        showAlert(Alert.AlertType.INFORMATION, "Success", null, "Gym member loaded successfully.");

    }
    @FXML
    public void recordAttendanceOA(ActionEvent actionEvent) {

        if (loadedMember == null) {
            showAlert(Alert.AlertType.ERROR, "Error", null, "Search a gym member first.");
            return;
        }
        if (!loadedMember.getStatus().equalsIgnoreCase("Active")) {
            showAlert(Alert.AlertType.ERROR, "Inactive Membership",
                    null, "This member does not have an active gym membership.");
            return;
        }
        if (attendanceDateDP.getValue() == null || attendanceCB.getValue() == null) {

            showAlert(Alert.AlertType.ERROR, "Error", null, "Please fill all fields.");
            return;
        }

        if (!attendanceDateDP.getValue().equals(LocalDate.now())) {
            showAlert(Alert.AlertType.ERROR, "Invalid Date",
                    null, "Attendance can only be recorded for today's date.");
            return;
        }
        String checkInTime = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a"));
        checkInTimeTF.setText(checkInTime);

        Attendance attendance = new Attendance(
                loadedMember.getGuestId(),
                loadedMember.getGuestName(),
                loadedMember.getPhoneNumber(),
                checkInTime,
                attendanceCB.getValue(),
                attendanceDateDP.getValue()
        );

        File file = new File("attendance.bin");
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
            oos.writeObject(attendance);
            oos.close();
            showAlert(Alert.AlertType.INFORMATION, "Success", null,
                    "Attendance recorded successfully.\n\n" + "Check-in Time : " + checkInTime);
            clearFields();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void clearFields() {
        guestIdTF.clear();
        guestNameTF.clear();
        phoneNumberTF.clear();
        packageTF.clear();
        statusTF.clear();
        attendanceDateDP.setValue(null);
        checkInTimeTF.clear();
        attendanceCB.getSelectionModel().clearSelection();
        loadedMember = null;
    }

    private void showAlert(Alert.AlertType type, String title, String header, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
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
    public void viewAttendancesOA(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewGymAttendance.fxml"));
            Node node = loader.load();
            mainPane.getChildren().setAll(node);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}