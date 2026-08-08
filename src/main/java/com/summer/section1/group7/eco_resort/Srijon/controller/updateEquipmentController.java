package com.summer.section1.group7.eco_resort.Srijon.controller;

import com.summer.section1.group7.eco_resort.Srijon.model.Equipment;
import com.summer.section1.group7.eco_resort.Srijon.util.BinaryFileManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class updateEquipmentController {

    @FXML private TextField equipmentIdField;
    @FXML private TextField nameField;
    @FXML private ComboBox<String> conditionCombo;
    @FXML private TableView<Equipment> equipmentTable;
    @FXML private TableColumn<Equipment, String> colId;
    @FXML private TableColumn<Equipment, String> colName;
    @FXML private TableColumn<Equipment, String> colCondition;
    @FXML private Label infoLabel;

    private final String FILE = "equipment.bin";
    private final ObservableList<Equipment> equipmentList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // populate condition options
        conditionCombo.getItems().addAll("Good", "Needs Repair", "Out of Service");

        // configure table columns (property names must match Equipment getters)
        colId.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getEquipmentId()));
        colName.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getName()));
        colCondition.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getCondition()));

        equipmentTable.setItems(equipmentList);
        
        loadEquipment();

        equipmentTable.setOnMouseClicked(this::onTableClicked);
    }

    @FXML
    public void onAdd() {
        String name = nameField.getText();
        String condition = conditionCombo.getValue();

        if (name == null || name.isBlank()) {
            infoLabel.setText("Enter equipment name.");
            return;
        }
        if (condition == null || condition.isBlank()) {
            infoLabel.setText("Select equipment condition.");
            return;
        }

        ArrayList<Equipment> list = BinaryFileManager.loadList(FILE);
        String id = UUID.randomUUID().toString();
        Equipment eq = new Equipment(id, name.trim(), condition);
        list.add(eq);
        try {
            BinaryFileManager.saveList(FILE, list);
            loadEquipment();
            clearInputs();
            infoLabel.setText("Equipment added: " + id);
        } catch (IOException e) {
            e.printStackTrace();
            infoLabel.setText("Error saving equipment.");
        }
    }

    @FXML
    public void onUpdate() {
        String id = equipmentIdField.getText();
        String name = nameField.getText();
        String condition = conditionCombo.getValue();

        if (id == null || id.isBlank()) {
            infoLabel.setText("Select an equipment to update (or enter ID).");
            return;
        }
        if (name == null || name.isBlank()) {
            infoLabel.setText("Enter equipment name.");
            return;
        }
        if (condition == null || condition.isBlank()) {
            infoLabel.setText("Select equipment condition.");
            return;
        }

        ArrayList<Equipment> list = BinaryFileManager.loadList(FILE);
        boolean found = false;
        for (Equipment e : list) {
            if (id.equals(e.getEquipmentId())) {
                e.setName(name.trim());
                e.setCondition(condition);
                found = true;
                break;
            }
        }
        if (!found) {
            infoLabel.setText("Equipment ID not found.");
            return;
        }
        try {
            BinaryFileManager.saveList(FILE, list);
            loadEquipment();
            clearInputs();
            infoLabel.setText("Equipment updated: " + id);
        } catch (IOException e) {
            e.printStackTrace();
            infoLabel.setText("Error saving equipment.");
        }
    }

    @FXML
    public void onDelete() {
        String id = equipmentIdField.getText();
        if (id == null || id.isBlank()) {
            infoLabel.setText("Select an equipment to delete.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Delete Equipment");
        confirm.setHeaderText("Delete equipment " + id + "?");
        confirm.setContentText("This action cannot be undone.");

        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isEmpty() || result.get() != ButtonType.OK) {
            return;
        }

        ArrayList<Equipment> list = BinaryFileManager.loadList(FILE);
        boolean removed = list.removeIf(e -> id.equals(e.getEquipmentId()));
        if (!removed) {
            infoLabel.setText("Equipment ID not found.");
            return;
        }
        try {
            BinaryFileManager.saveList(FILE, list);
            loadEquipment();
            clearInputs();
            infoLabel.setText("Equipment deleted: " + id);
        } catch (IOException e) {
            e.printStackTrace();
            infoLabel.setText("Error saving equipment.");
        }
    }

    @FXML
    public void onRefresh() {
        loadEquipment();
        infoLabel.setText("Refreshed.");
    }

    private void loadEquipment() {
        equipmentList.clear();
        ArrayList<Equipment> list = BinaryFileManager.loadList(FILE);
        if (list != null) equipmentList.addAll(list);
    }

    private void onTableClicked(MouseEvent event) {
        Equipment selected = equipmentTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;
        equipmentIdField.setText(selected.getEquipmentId());
        nameField.setText(selected.getName());
        conditionCombo.setValue(selected.getCondition());
        infoLabel.setText("Selected: " + selected.getEquipmentId());
    }

    private void clearInputs() {
        equipmentIdField.clear();
        nameField.clear();
        conditionCombo.getSelectionModel().clearSelection();
    }
}
