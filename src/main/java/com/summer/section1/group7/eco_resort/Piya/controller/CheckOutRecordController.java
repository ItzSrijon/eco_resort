package com.summer.section1.group7.eco_resort.Piya.controller;

import com.summer.section1.group7.eco_resort.Piya.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CheckOutRecordController {

    @FXML private TableView<CheckOutRecord> checkOutTableView;
    @FXML private TableColumn<CheckOutRecord,String> recordIdTC;
    @FXML private TableColumn<CheckOutRecord,String> guestNameTC;
    @FXML private TableColumn<CheckOutRecord,String> roomNumberTC;
    @FXML private TableColumn<CheckOutRecord,LocalDate> checkOutDateTC;
    @FXML private TableColumn<CheckOutRecord,String> timeTC;
    @FXML private Label messageLabel;
    @FXML private DatePicker departureDP;
    @FXML private TextField departureTF;

    private ObservableList<CheckOutRecord> recordList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        recordIdTC.setCellValueFactory(
                new PropertyValueFactory<>("recordId"));
        guestNameTC.setCellValueFactory(
                new PropertyValueFactory<>("guestName"));
        roomNumberTC.setCellValueFactory(
                new PropertyValueFactory<>("roomNumber"));
        checkOutDateTC.setCellValueFactory(
                new PropertyValueFactory<>("checkOutDate"));
        timeTC.setCellValueFactory(
                new PropertyValueFactory<>("departureTime"));

        checkOutTableView.setItems(recordList);
        loadCheckOutRecords();
    }

    @FXML
    public void loadCheckOutRecordButtonOA(ActionEvent event) {
        messageLabel.setText("");

        LocalDate departureDate = departureDP.getValue();
        String departureTime = departureTF.getText().trim();

        if (departureDate == null || departureTime.isEmpty()) {
            messageLabel.setText(
                    "Enter departure date and time.");
            return;
        }

        List<RoomReservation> reservations =
                loadReservations();

        if (reservations.isEmpty()) {
            messageLabel.setText(
                    "No reservation data found.");
            return;
        }

        int count = 0;

        for (RoomReservation reservation : reservations) {

            if (reservation.getCheckOutDate() == null)
                continue;

            if (!reservation.getCheckOutDate()
                    .equals(departureDate))
                continue;

            if (alreadyCheckedOut(
                    reservation.getReservationId()))
                continue;

            Room room = reservation.getRoom();

            if (room == null)
                continue;

            room.setAvailability("Available");
            reservation.setBookingStatus("Completed");

            CheckOutRecord record =
                    new CheckOutRecord(
                            "CO" + System.currentTimeMillis() + count,
                            reservation.getReservationId(),
                            reservation.getUser(),
                            room,
                            departureDate,
                            departureTime,
                            "Completed"
                    );

            saveRecord(record);
            recordList.add(record);
            count++;
        }

        if (count == 0) {
            messageLabel.setText(
                    "No guest found for this date.");
        }
        else {
            saveReservations(reservations);

            messageLabel.setText(
                    count + " guest(s) checked out.");

            departureTF.clear();
        }
    }

    private List<RoomReservation> loadReservations() {
        List<RoomReservation> reservations =
                new ArrayList<>();

        File file = new File("RoomReservation.bin");

        if (!file.exists())
            return reservations;

        try (ObjectInputStream ois =
                     new ObjectInputStream(
                             new FileInputStream(file))) {

            while (true) {
                try {
                    RoomReservation reservation =
                            (RoomReservation) ois.readObject();

                    reservations.add(reservation);

                } catch (EOFException e) {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return reservations;
    }

    private void saveReservations(
            List<RoomReservation> reservations) {

        File file = new File("RoomReservation.bin");

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(
                             new FileOutputStream(file))) {

            for (RoomReservation reservation : reservations) {
                oos.writeObject(reservation);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean alreadyCheckedOut(
            String reservationId) {

        for (CheckOutRecord record : recordList) {

            if (record.getReservationId() != null &&
                    record.getReservationId()
                            .equals(reservationId)) {

                return true;
            }
        }

        return false;
    }

    private void loadCheckOutRecords() {

        File file = new File("CheckOutRecord.bin");

        if (!file.exists())
            return;

        try (ObjectInputStream ois =
                     new ObjectInputStream(
                             new FileInputStream(file))) {

            while (true) {
                try {
                    CheckOutRecord record =
                            (CheckOutRecord) ois.readObject();

                    recordList.add(record);

                } catch (EOFException e) {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveRecord(CheckOutRecord record) {

        File file = new File("CheckOutRecord.bin");

        try {
            ObjectOutputStream oos;

            if (file.exists()) {
                oos = new AppendableObjectOutputStream(
                        new FileOutputStream(file, true));
            }
            else {
                oos = new ObjectOutputStream(
                        new FileOutputStream(file));
            }

            oos.writeObject(record);
            oos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void refreshButtonOA(ActionEvent event) {

        recordList.clear();
        loadCheckOutRecords();

        messageLabel.setText(
                "Records refreshed.");
    }

    @FXML
    public void backButtonOA(ActionEvent event) {

        try {
            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/com/summer/section1/group7/eco_resort/Piya/SecurityDashboard.fxml"));

            Scene scene = new Scene(loader.load());

            Stage stage =
                    (Stage)((Node)event.getSource())
                            .getScene()
                            .getWindow();

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
