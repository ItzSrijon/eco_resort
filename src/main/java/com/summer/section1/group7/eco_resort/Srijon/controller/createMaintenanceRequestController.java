package com.summer.section1.group7.eco_resort.Srijon.controller;

import com.summer.section1.group7.eco_resort.Srijon.model.MaintenanceRequest;
import com.summer.section1.group7.eco_resort.Srijon.util.BinaryFileManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class createMaintenanceRequestController {
    @FXML private TextField roomNumberField;
    @FXML private TextField issueField;
    @FXML private Label infoLabel;

    @FXML
    public void onSubmit() {
        String room = roomNumberField.getText();
        String issue = issueField.getText();
        if (room == null || room.isBlank() || issue == null || issue.isBlank()) {
            infoLabel.setText("Fill all fields!");
            return;
        }
        String FILE = "maintenance.bin";
        ArrayList<MaintenanceRequest> list = BinaryFileManager.loadList(FILE);
        String id = UUID.randomUUID().toString();
        MaintenanceRequest req = new MaintenanceRequest(id, room.trim(), issue.trim(), "Pending");
        list.add(req);
        try {
            BinaryFileManager.saveList(FILE, list);
            infoLabel.setText("Request created with ID: " + req.getRequestId());


            System.out.println("New Maintenance Request ID: " + id);

            System.out.println("REQ|" + id + "|" + room.trim() + "|" + issue.trim());

            roomNumberField.clear();
            issueField.clear();
        } catch (IOException e) {
            e.printStackTrace();
            infoLabel.setText("Error saving request.");
        }
    }
}
