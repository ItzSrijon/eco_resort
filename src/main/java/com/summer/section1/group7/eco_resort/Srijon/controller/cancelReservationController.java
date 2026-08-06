package com.summer.section1.group7.eco_resort.Srijon.controller;

import com.summer.section1.group7.eco_resort.Srijon.model.Reservation;
import com.summer.section1.group7.eco_resort.Srijon.util.BinaryFileManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class cancelReservationController {
    @FXML private TextField reservationIdField;
    @FXML private Label infoLabel;

    private final String RES_FILE = "reservation.bin";

    @FXML
    public void onCancel() {
        String id = reservationIdField.getText();
        if (id == null || id.isBlank()) {
            infoLabel.setText("Enter reservation ID");
            return;
        }
        ArrayList<Reservation> list = BinaryFileManager.loadList(RES_FILE);
        boolean found = false;
        for (Reservation r : list) {
            if (r.getReservationId().equals(id)) {
                r.setStatus("Canceled");
                found = true;
                break;
            }
        }
        if (found) {
            try {
                BinaryFileManager.saveList(RES_FILE, list);
                infoLabel.setText("Reservation canceled: " + id);
            } catch (IOException e) {
                infoLabel.setText("Error saving file");
            }
        } else {
            infoLabel.setText("Reservation not found");
        }
    }
}
