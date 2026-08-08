package com.summer.section1.group7.eco_resort.Srijon.controller;

import com.summer.section1.group7.eco_resort.Srijon.model.Room;
import com.summer.section1.group7.eco_resort.Srijon.util.BinaryFileManager;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class updateRoomStatusController {
    @FXML private TextField roomNumberField;
    @FXML private ComboBox<String> statusComboBox;
    @FXML private Label infoLabel;

    private final String ROOM_FILE = "room.bin";

    @FXML
    public void initialize() {
        statusComboBox.getItems().addAll("Available", "Occupied", "Dirty");
    }

    @FXML
    public void onUpdate() {
        String roomNo = roomNumberField.getText();
        String status = statusComboBox.getValue();
        ArrayList<Room> list = BinaryFileManager.loadList(ROOM_FILE);
        for (Room r : list) {
            if (r.getRoomNumber().equals(roomNo)) {
                r.setStatus(status);
                try {
                    BinaryFileManager.saveList(ROOM_FILE, list);
                    infoLabel.setText("Room status updated");
                } catch (IOException e) {
                    infoLabel.setText("Error saving file");
                }
                return;
            }
        }
        infoLabel.setText("Room not found");
    }
}
