package com.summer.section1.group7.eco_resort.Srijon.controller;

import com.summer.section1.group7.eco_resort.Srijon.model.Room;
import com.summer.section1.group7.eco_resort.Srijon.util.BinaryFileManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class occupancySummaryController {
    @FXML private Label totalRoomsLabel;
    @FXML private Label occupiedRoomsLabel;
    @FXML private Label percentageLabel;

    private final String ROOM_FILE = "room.bin";

    @FXML
    public void onGenerate() {
        ArrayList<Room> list = BinaryFileManager.loadList(ROOM_FILE);
        int total = list.size();
        long occupied = list.stream().filter(r -> "Occupied".equals(r.getStatus())).count();
        double percent = total == 0 ? 0 : (occupied * 100.0 / total);

        totalRoomsLabel.setText("Total Rooms: " + total);
        occupiedRoomsLabel.setText("Occupied Rooms: " + occupied);
        percentageLabel.setText("Occupancy %: " + String.format("%.2f", percent));
    }
}
