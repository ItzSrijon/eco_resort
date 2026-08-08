package com.summer.section1.group7.eco_resort.Srijon.controller;

import com.summer.section1.group7.eco_resort.Srijon.model.Equipment;
import com.summer.section1.group7.eco_resort.Srijon.util.BinaryFileManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class viewEquipmentController {

    @FXML private TableView<Equipment> equipmentTable;
    @FXML private TableColumn<Equipment, String> colId;
    @FXML private TableColumn<Equipment, String> colName;
    @FXML private TableColumn<Equipment, String> colCondition;
    @FXML private Label infoLabel;

    private final String FILE = "equipment.bin";

    @FXML
    public void initialize() {
        colId.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getEquipmentId()));
        colName.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getName()));
        colCondition.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getCondition()));
        onRefresh();
    }

    @FXML
    public void onRefresh() {
        ArrayList<Equipment> list = BinaryFileManager.loadList(FILE);
        equipmentTable.setItems(FXCollections.observableArrayList(list));
        infoLabel.setText("Loaded " + (list == null ? 0 : list.size()) + " equipment items.");
    }

    @FXML
    public void onExportSummary() {
        ArrayList<Equipment> list = BinaryFileManager.loadList(FILE);
        if (list == null || list.isEmpty()) {
            infoLabel.setText("No equipment to summarize.");
            return;
        }
        Map<String, Long> counts = list.stream()
                .collect(Collectors.groupingBy(e -> e.getCondition() == null ? "Unknown" : e.getCondition(), Collectors.counting()));
        StringBuilder sb = new StringBuilder();
        sb.append("Equipment summary:\n");
        counts.forEach((k, v) -> sb.append(k).append(": ").append(v).append("\n"));
        // show in dialog
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Equipment Summary");
        a.setHeaderText("Condition counts");
        a.setContentText(sb.toString());
        a.getDialogPane().setPrefWidth(400);
        a.showAndWait();
    }
}
