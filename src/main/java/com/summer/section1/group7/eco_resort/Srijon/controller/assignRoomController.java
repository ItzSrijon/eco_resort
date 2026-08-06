package com.summer.section1.group7.eco_resort.Srijon.controller;

import com.summer.section1.group7.eco_resort.Srijon.model.Reservation;
import com.summer.section1.group7.eco_resort.Srijon.util.BinaryFileManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class assignRoomController {
    @FXML private TextField reservationIdField;
    @FXML private TextField roomNumberField;
    @FXML private Label infoLabel;

    private final String RES_FILE = "reservation.bin";

    @FXML
    public void onAssign() {
        String id = reservationIdField.getText();
        String room = roomNumberField.getText();
        ArrayList<Reservation> list = BinaryFileManager.loadList(RES_FILE);
        for (Reservation r : list) {
            if (r.getReservationId().equals(id)) {
                r.setRoomType(room); // এখানে roomType ফিল্ড ব্যবহার করছি, চাইলে আলাদা roomNumber ফিল্ড বানাতে পারেন
                try {
                    BinaryFileManager.saveList(RES_FILE, list);
                    infoLabel.setText("Room assigned: " + room);
                } catch (IOException e) {
                    infoLabel.setText("Error saving file");
                }
                return;
            }
        }
        infoLabel.setText("Reservation not found");
    }
}
