package com.summer.section1.group7.eco_resort.Srijon.controller;

import com.summer.section1.group7.eco_resort.Srijon.model.MaintenanceRequest;
import com.summer.section1.group7.eco_resort.Srijon.util.BinaryFileManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.ArrayList;

public class closeRequestController {
    @FXML private TextField requestIdField;
    @FXML private Label infoLabel;

    @FXML
    public void onClose() {
        String id = requestIdField.getText();
        String FILE = "maintenance.bin";
        ArrayList<MaintenanceRequest> list = BinaryFileManager.loadList(FILE);
        for (MaintenanceRequest r : list) {
            if (r.getRequestId().equals(id)) {
                r.setStatus("Closed");
                try {
                    BinaryFileManager.saveList(FILE, list);
                    infoLabel.setText("Request closed.");
                } catch (IOException e) {
                    infoLabel.setText("Error saving file.");
                }
                return;
            }
        }
        infoLabel.setText("Request not found.");
    }
}
