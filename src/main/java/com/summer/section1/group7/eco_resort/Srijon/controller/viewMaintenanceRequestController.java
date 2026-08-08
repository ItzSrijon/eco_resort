package com.summer.section1.group7.eco_resort.Srijon.controller;

import com.summer.section1.group7.eco_resort.Srijon.model.MaintenanceRequest;
import com.summer.section1.group7.eco_resort.Srijon.util.BinaryFileManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.ArrayList;

public class viewMaintenanceRequestController {
    @FXML private TableView<MaintenanceRequest> requestTable;
    @FXML private TableColumn<MaintenanceRequest, String> colId;
    @FXML private TableColumn<MaintenanceRequest, String> colRoom;
    @FXML private TableColumn<MaintenanceRequest, String> colIssue;
    @FXML private TableColumn<MaintenanceRequest, String> colStatus;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getRequestId()));
        colRoom.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getRoomNumber()));
        colIssue.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getIssue()));
        colStatus.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getStatus()));
        onRefresh();
    }

    @FXML
    public void onRefresh() {
        String FILE = "maintenance.bin";
        ArrayList<MaintenanceRequest> list = BinaryFileManager.loadList(FILE);
        requestTable.setItems(FXCollections.observableArrayList(list));
    }
}
