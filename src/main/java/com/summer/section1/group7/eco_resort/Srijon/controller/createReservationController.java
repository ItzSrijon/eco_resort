package com.summer.section1.group7.eco_resort.Srijon.controller;

import com.summer.section1.group7.eco_resort.User;
import com.summer.section1.group7.eco_resort.Srijon.model.Reservation;
import com.summer.section1.group7.eco_resort.Srijon.util.BinaryFileManager;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.ArrayList;

public class createReservationController {

    @FXML private TextField guestIdField;
    @FXML private ComboBox<String> roomTypeCombobox;
    @FXML private DatePicker checkInPicker;
    @FXML private DatePicker checkOutPicker;
    @FXML private Label messageLabel;

    private static final String RES_FILE = "reservation.bin";
    private static final String USER_FILE = "user.bin";

    @FXML
    public void initialize() {
        messageLabel.setText("");
        roomTypeCombobox.getItems().setAll("Single", "Double", "VIP");
    }

    @FXML
    public void saveReservation() {

        messageLabel.setText("");

        String guestId = (guestIdField.getText() == null) ? "" : guestIdField.getText().trim();
        String roomType = roomTypeCombobox.getValue();
        LocalDate checkIn = checkInPicker.getValue();
        LocalDate checkOut = checkOutPicker.getValue();

        // validation
        if (guestId.isEmpty() || roomType == null || checkIn == null || checkOut == null) {
            messageLabel.setText("All fields are required!");
            return;
        }

        if (!checkOut.isAfter(checkIn)) {
            messageLabel.setText("Check-out must be after check-in!");
            return;
        }

        // verify guest exists
        if (!guestExists(guestId)) {
            messageLabel.setText("Guest ID not found!");
            return;
        }

        // load reservations (defensive)
        ArrayList<Reservation> list = BinaryFileManager.loadList(RES_FILE);
        if (list == null) list = new ArrayList<>();

        // generate id
        String reservationId = generateReservationId(list);

        // create & save
        Reservation r = new Reservation(
                reservationId,
                guestId,
                roomType,
                checkIn,
                checkOut,
                "Active"
        );

        list.add(r);

        try {
            BinaryFileManager.saveList(RES_FILE, list);
            messageLabel.setText("Reservation Created! ID: " + reservationId);
            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Failed to save reservation.");
        }
    }

    // checks user.bin for a User with matching userId and role Guest
    private boolean guestExists(String guestId) {
        ArrayList<User> users = BinaryFileManager.loadList(USER_FILE);
        if (users == null) return false;

        for (User u : users) {
            if (u.getUserId() != null
                    && u.getUserId().equalsIgnoreCase(guestId)
                    && u.getRole() != null
                    && u.getRole().equalsIgnoreCase("Guest")) {
                return true;
            }
        }
        return false;
    }

    private String generateReservationId(ArrayList<Reservation> list) {
        int max = 0;

        for (Reservation r : list) {
            String id = r.getReservationId();
            if (id != null && id.startsWith("R")) {
                try {
                    int num = Integer.parseInt(id.substring(1));
                    if (num > max) max = num;
                } catch (Exception ignored) { }
            }
        }
        return String.format("R%03d", max + 1);
    }

    private void clearFields() {
        guestIdField.clear();
        roomTypeCombobox.setValue(null);
        checkInPicker.setValue(null);
        checkOutPicker.setValue(null);
    }
}
