package com.summer.section1.group7.eco_resort.Piya.controller;

import com.summer.section1.group7.eco_resort.User;
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

public class CheckOutManagementController {

    @FXML private TableView<RoomReservation> checkoutTV;
    @FXML private TableColumn<RoomReservation,String> guestNameTC;
    @FXML private TableColumn<RoomReservation,String> reservationIdTC;
    @FXML private TableColumn<RoomReservation,String> roomNumberTC;
    @FXML private TableColumn<RoomReservation,LocalDate> checkInDateTC;
    @FXML private TableColumn<RoomReservation,String> bookingStatusTC;
    @FXML private TableColumn<RoomReservation,String> guestStatusTC;
    @FXML private DatePicker checkOutDateDP;
    @FXML private TextField departureTimeTF;
    @FXML private Label messageLabel;
    @FXML private Label roomStatusLabel;

    private ObservableList<RoomReservation> reservationList =
            FXCollections.observableArrayList();

    private RoomReservation selectedReservation;

    @FXML
    public void initialize() {

        guestNameTC.setCellValueFactory(
                new PropertyValueFactory<>("guestName"));

        reservationIdTC.setCellValueFactory(
                new PropertyValueFactory<>("reservationId"));

        roomNumberTC.setCellValueFactory(
                new PropertyValueFactory<>("roomId"));

        checkInDateTC.setCellValueFactory(
                new PropertyValueFactory<>("checkInDate"));

        bookingStatusTC.setCellValueFactory(
                new PropertyValueFactory<>("bookingStatus"));

        guestStatusTC.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getUser() != null
                                ? data.getValue().getUser().getStatus()
                                : ""));

        checkoutTV.setItems(reservationList);

        checkoutTV.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldValue, newValue) ->
                        selectedReservation = newValue);

        loadConfirmedReservations();
    }

    // Event: Load confirmed guests
    private void loadConfirmedReservations() {

        reservationList.clear();

        File file = new File("RoomReservation.bin");

        if (!file.exists()) {
            messageLabel.setText("No booking record found.");
            return;
        }

        try (ObjectInputStream ois =
                     new ObjectInputStream(
                             new FileInputStream(file))) {

            while (true) {
                try {

                    RoomReservation reservation =
                            (RoomReservation) ois.readObject();

                    if ("Confirmed".equalsIgnoreCase(
                            reservation.getBookingStatus())) {

                        reservationList.add(reservation);
                    }

                } catch (EOFException e) {
                    break;
                }
            }

            messageLabel.setText("Guest records loaded.");

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Unable to load records.");
        }
    }

    // Event: View selected checkout information
    @FXML
    public void viewCheckOutInfoButtonOA(ActionEvent event) {

        if (selectedReservation == null) {
            messageLabel.setText("Select a guest record first.");
            return;
        }

        User user = selectedReservation.getUser();
        Room room = selectedReservation.getRoom();

        roomStatusLabel.setText(
                "Guest: " + user.getName() +
                        "\nRoom: " + room.getRoomId() +
                        "\nRoom Status: " + room.getAvailability()
        );
    }

    // Event: Complete checkout
    @FXML
    public void completeCheckoutButtonOA(ActionEvent event) {

        if (selectedReservation == null) {
            messageLabel.setText("Select a guest record first.");
            return;
        }

        if (checkOutDateDP.getValue() == null ||
                departureTimeTF.getText().trim().isEmpty()) {

            messageLabel.setText(
                    "Enter checkout date and departure time.");
            return;
        }

        LocalDate checkoutDate = checkOutDateDP.getValue();
        String departureTime = departureTimeTF.getText().trim();

        if (checkoutDate.isBefore(
                selectedReservation.getCheckInDate())) {

            messageLabel.setText(
                    "Checkout date cannot be before check-in date.");
            return;
        }

        User user = selectedReservation.getUser();
        Room room = selectedReservation.getRoom();

        CheckOutRecord record =
                new CheckOutRecord(
                        "CO" + System.currentTimeMillis(),
                        selectedReservation.getReservationId(),
                        user,
                        room,
                        checkoutDate,
                        departureTime,
                        "Completed"
                );

        if (saveCheckoutRecord(record)) {

            room.setAvailability("Available");
            user.setStatus("Checked Out");

            updateRoomFile(room);
            updateUserFile(user);

            selectedReservation.setBookingStatus("Checked Out");

            messageLabel.setText(
                    "Checkout completed successfully.");

            roomStatusLabel.setText(
                    "Room status : Available\n" +
                            "Guest status : Checked Out");

            checkoutTV.refresh();

            clearFields();

        } else {

            messageLabel.setText(
                    "Failed to save checkout record.");
        }
    }

    // Saves completed checkout information
    private boolean saveCheckoutRecord(CheckOutRecord record) {

        File file = new File("CheckOutRecord.bin");

        try {

            ObjectOutputStream oos;

            if (file.exists()) {

                oos = new AppendableObjectOutputStream(
                        new FileOutputStream(file, true));

            } else {

                oos = new ObjectOutputStream(
                        new FileOutputStream(file));
            }

            oos.writeObject(record);
            oos.close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Updates the changed room inside Room.bin
    private void updateRoomFile(Room updatedRoom) {

        File file = new File("Room.bin");
        File temp = new File("RoomTemp.bin");

        if (!file.exists()) return;

        try (ObjectInputStream ois =
                     new ObjectInputStream(
                             new FileInputStream(file));
             ObjectOutputStream oos =
                     new ObjectOutputStream(
                             new FileOutputStream(temp))) {

            while (true) {

                try {

                    Room room = (Room) ois.readObject();

                    if (room.getRoomId().equals(
                            updatedRoom.getRoomId())) {

                        room.setAvailability(
                                updatedRoom.getAvailability());
                    }

                    oos.writeObject(room);

                } catch (EOFException e) {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        if (file.delete()) {
            temp.renameTo(file);
        }
    }

    // Updates the changed guest inside User.bin
    private void updateUserFile(User updatedUser) {

        File file = new File("User.bin");
        File temp = new File("UserTemp.bin");

        if (!file.exists()) return;

        try (ObjectInputStream ois =
                     new ObjectInputStream(
                             new FileInputStream(file));
             ObjectOutputStream oos =
                     new ObjectOutputStream(
                             new FileOutputStream(temp))) {

            while (true) {

                try {

                    User user = (User) ois.readObject();

                    if (user.getUserId().equals(
                            updatedUser.getUserId())) {

                        user.setStatus(updatedUser.getStatus());
                    }

                    oos.writeObject(user);

                } catch (EOFException e) {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        if (file.delete()) {
            temp.renameTo(file);
        }
    }

    private void clearFields() {

        checkOutDateDP.setValue(null);
        departureTimeTF.clear();
        selectedReservation = null;
        checkoutTV.getSelectionModel().clearSelection();
    }

    // Event: Refresh
    @FXML
    public void refreshButtonOA(ActionEvent event) {

        clearFields();
        roomStatusLabel.setText("");
        loadConfirmedReservations();

        messageLabel.setText("Records refreshed.");
    }

    // Event: Back
    @FXML
    public void backButtonOA(ActionEvent event) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/com/summer/section1/group7/eco_resort/Piya/SecurityDashboard.fxml"));

            Scene scene = new Scene(loader.load());

            Stage stage =
                    (Stage) ((Node) event.getSource())
                            .getScene()
                            .getWindow();

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Save button - reserved for your future PDF feature
    @FXML
    public void saveButtonOA(ActionEvent event) {
        messageLabel.setText(
                "Save feature will be added later.");
    }
}