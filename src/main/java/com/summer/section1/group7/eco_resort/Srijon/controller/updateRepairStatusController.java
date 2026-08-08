package com.summer.section1.group7.eco_resort.Srijon.controller;

import com.summer.section1.group7.eco_resort.Srijon.model.MaintenanceRequest;
import com.summer.section1.group7.eco_resort.Srijon.util.BinaryFileManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.ArrayList;

public class updateRepairStatusController {
    @FXML private TextField requestIdField;
    @FXML private ComboBox<String> statusComboBox;
    @FXML private Label infoLabel;

    @FXML
    public void initialize() {
        statusComboBox.getItems().addAll("Pending", "In Progress", "Completed");
    }

    @FXML
    public void onUpdate() {
        String id = requestIdField.getText();
        String status = statusComboBox.getValue();
        String FILE = "maintenance.bin";
        ArrayList<MaintenanceRequest> list = BinaryFileManager.loadList(FILE);
        for (MaintenanceRequest r : list) {
            if (r.getRequestId().equals(id)) {
                r.setStatus(status);
                try {
                    BinaryFileManager.saveList(FILE, list);
                    infoLabel.setText("Status updated.");
                } catch (IOException e) {
                    infoLabel.setText("Error saving file.");
                }
                return;
            }
        }
        infoLabel.setText("Request not found.");
    }
}
