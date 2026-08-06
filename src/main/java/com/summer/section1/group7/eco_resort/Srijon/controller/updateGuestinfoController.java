package com.summer.section1.group7.eco_resort.Srijon.controller;

import com.summer.section1.group7.eco_resort.Srijon.model.Reservation;
import com.summer.section1.group7.eco_resort.Srijon.util.BinaryFileManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class updateGuestinfoController {
    @FXML private TextField reservationIdField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private Label infoLabel;

    private final String RES_FILE = "reservation.bin";

    @FXML
    public void onUpdate() {
        String id = reservationIdField.getText();
        ArrayList<Reservation> list = BinaryFileManager.loadList(RES_FILE);
        for (Reservation r : list) {
            if (r.getReservationId().equals(id)) {
                r.setGuestPhone(phoneField.getText());
                r.setGuestEmail(emailField.getText());
                try {
                    BinaryFileManager.saveList(RES_FILE, list);
                    infoLabel.setText("Guest info updated");
                } catch (IOException e) {
                    infoLabel.setText("Error saving file");
                }
                return;
            }
        }
        infoLabel.setText("Reservation not found");
    }
}
