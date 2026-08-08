package com.summer.section1.group7.eco_resort.Srijon.controller;

import com.summer.section1.group7.eco_resort.Srijon.model.WorkLog;
import com.summer.section1.group7.eco_resort.Srijon.util.BinaryFileManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class logWorkController {

    @FXML private TextField requestIdField;
    @FXML private TextArea descriptionArea;
    @FXML private DatePicker datePicker;
    @FXML private Label infoLabel;

    @FXML
    public void initialize() {
        datePicker.setValue(LocalDate.now());
    }

    @FXML
    public void onAddLog() {
        String reqId = requestIdField.getText();
        String desc = descriptionArea.getText();
        LocalDate date = datePicker.getValue();

        if (reqId == null || reqId.isBlank()) {
            infoLabel.setText("Enter Request ID.");
            return;
        }
        if (desc == null || desc.isBlank()) {
            infoLabel.setText("Enter description of work.");
            return;
        }
        if (date == null) {
            infoLabel.setText("Select a date.");
            return;
        }

        String FILE = "worklog.bin";
        ArrayList<WorkLog> list = BinaryFileManager.loadList(FILE);
        WorkLog log = new WorkLog(UUID.randomUUID().toString(), reqId.trim(), desc.trim(), date);
        list.add(log);
        try {
            BinaryFileManager.saveList(FILE, list);
            infoLabel.setText("Work log added: " + log.getLogId());
            requestIdField.clear();
            descriptionArea.clear();
            datePicker.setValue(LocalDate.now());
        } catch (IOException e) {
            e.printStackTrace();
            infoLabel.setText("Failed to save work log.");
        }
    }
}
