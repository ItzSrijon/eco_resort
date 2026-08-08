package com.summer.section1.group7.eco_resort.Siam;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;

public class ViewAttendanceController {
    @FXML
    private TableColumn<Attendance, String> guestNameTC;
    @FXML
    private TableView<Attendance> attendanceTV;
    @FXML
    private Label todayAttendanceLabel;
    @FXML
    private Label totalDaysLabel;
    @FXML
    private TableColumn<Attendance, String> guestIdTC;
    @FXML
    private TableColumn<Attendance, String> attendanceStatusTC;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private TableColumn<Attendance, String> checkInTimeTC;
    @FXML
    private TextField guestIdTF;
    @FXML
    private TableColumn<Attendance, LocalDate> attendanceDateTC;
    private ObservableList<Attendance> attendanceList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        guestIdTC.setCellValueFactory(new PropertyValueFactory<>("guestId"));
        guestNameTC.setCellValueFactory(new PropertyValueFactory<>("guestName"));
        attendanceDateTC.setCellValueFactory(new PropertyValueFactory<>("attendanceDate"));
        checkInTimeTC.setCellValueFactory(new PropertyValueFactory<>("checkInTime"));
        attendanceStatusTC.setCellValueFactory(new PropertyValueFactory<>("attendanceStatus"));
        totalDaysLabel.setVisible(false);
        todayAttendanceLabel.setVisible(false);

    }
    @FXML
    public void searchAttendanceOA(ActionEvent actionEvent) {

        attendanceTV.getItems().clear();
        attendanceList.clear();
        totalDaysLabel.setVisible(false);
        todayAttendanceLabel.setVisible(false);
        if (guestIdTF.getText().trim().isEmpty()) {

            showAlert(Alert.AlertType.ERROR, "Error","Please enter Guest ID.");
            return;
        }

        int count = 0;

        try {

            FileInputStream fis = new FileInputStream("attendance.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    Attendance attendance = (Attendance) ois.readObject();

                    if (attendance.getGuestId().equalsIgnoreCase(guestIdTF.getText().trim())) {
                        attendanceList.add(attendance);
                        count++;

                    }
                } catch (EOFException e) {
                    ois.close();
                    break;

                }

            }

            attendanceTV.setItems(attendanceList);

            if (count == 0) {

                showAlert(Alert.AlertType.INFORMATION, "Not Found", "No attendance record found.");

            } else {
                totalDaysLabel.setText("Total Days Attended : " + count);
                totalDaysLabel.setVisible(true);
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    @FXML
    public void loadTodayAttendanceOA(ActionEvent actionEvent) {

        attendanceTV.getItems().clear();
        attendanceList.clear();
        totalDaysLabel.setVisible(false);
        todayAttendanceLabel.setVisible(false);

        int count = 0;

        try {
            FileInputStream fis = new FileInputStream("attendance.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {

                try {
                    Attendance attendance = (Attendance) ois.readObject();

                    if (attendance.getAttendanceDate().equals(LocalDate.now())) {
                        attendanceList.add(attendance);
                        count++;

                    }

                } catch (EOFException e) {

                    ois.close();
                    break;

                }

            }

            attendanceTV.setItems(attendanceList);

            todayAttendanceLabel.setText("Today's Total Attendance : " + count);
            todayAttendanceLabel.setVisible(true);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    @FXML
    public void backOA(ActionEvent actionEvent) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("U3G4_RecordGymAttendance.fxml"));
            Node node = loader.load();
            mainPane.getChildren().setAll(node);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();

    }
}