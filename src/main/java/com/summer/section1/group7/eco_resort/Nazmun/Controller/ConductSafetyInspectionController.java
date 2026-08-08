package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import com.summer.section1.group7.eco_resort.Nazmun.Model.SafetyInspectionItem;
import com.summer.section1.group7.eco_resort.Nazmun.Model.SafetyInspectionManager;
import com.summer.section1.group7.eco_resort.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ConductSafetyInspectionController
{
    @javafx.fxml.FXML
    private ComboBox<String> areaCB;
    @javafx.fxml.FXML
    private TableView<SafetyInspectionItem> inspectionTableView;
    @javafx.fxml.FXML
    private TableColumn<SafetyInspectionItem, String> areaIdTC;
    @javafx.fxml.FXML
    private TableColumn<SafetyInspectionItem, String> areaNameTC;
    @javafx.fxml.FXML
    private TableColumn<SafetyInspectionItem, String> statusTC;
    @javafx.fxml.FXML
    private TableColumn<SafetyInspectionItem, String> deadlineTC;
    @javafx.fxml.FXML
    private DatePicker deadlineDP;
    @javafx.fxml.FXML
    private Label summaryLabel;

    @javafx.fxml.FXML
    public void initialize() {
        areaCB.getItems().addAll("Kitchen", "Lobby", "Pool", "Gym", "Spa", "Parking", "Guest Corridor");

        areaIdTC.setCellValueFactory(new PropertyValueFactory<>("areaId"));
        areaNameTC.setCellValueFactory(new PropertyValueFactory<>("areaName"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("status"));
        deadlineTC.setCellValueFactory(new PropertyValueFactory<>("deadline"));

        inspectionTableView.getItems().addAll(SafetyInspectionManager.getInspectionList());

        updateSummary();
    }

    @javafx.fxml.FXML
    public void addAreaButtonOA(ActionEvent actionEvent) {
        String area = areaCB.getValue();

        if (area == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select an area.");
            alert.showAndWait();
            return;
        }

        String areaId = SafetyInspectionManager.generateAreaId();
        SafetyInspectionItem item = new SafetyInspectionItem(areaId, area);

        inspectionTableView.getItems().add(item);
        SafetyInspectionManager.getInspectionList().add(item);
        SafetyInspectionManager.saveToFile();

        areaCB.setValue(null);
        updateSummary();
    }

    @javafx.fxml.FXML
    public void passButtonOA(ActionEvent actionEvent) {
        SafetyInspectionItem selected = inspectionTableView.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        selected.setStatus("Pass");
        selected.setDeadline(null);
        SafetyInspectionManager.saveToFile();

        inspectionTableView.refresh();
        updateSummary();
    }

    @javafx.fxml.FXML
    public void failButtonOA(ActionEvent actionEvent) {
        SafetyInspectionItem selected = inspectionTableView.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        if (deadlineDP.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please pick a deadline.");
            alert.showAndWait();
            return;
        }

        selected.setStatus("Fail");
        selected.setDeadline(deadlineDP.getValue());
        SafetyInspectionManager.saveToFile();

        inspectionTableView.refresh();
        deadlineDP.setValue(null);
        updateSummary();
    }

    private void updateSummary() {
        int passed = 0, failed = 0, pending = 0;

        for (SafetyInspectionItem item : inspectionTableView.getItems()) {
            if (item.getStatus().equals("Pass")) passed++;
            else if (item.getStatus().equals("Fail")) failed++;
            else pending++;
        }

        summaryLabel.setText("Passed: " + passed + "   Failed: " + failed + "   Pending: " + pending);
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Nazmun/ManagerDashboard.fxml");
    }
}