package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import com.summer.section1.group7.eco_resort.Nazmun.Model.StaffManager;
import com.summer.section1.group7.eco_resort.Nazmun.Model.StaffMember;
import com.summer.section1.group7.eco_resort.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageStaffController
{
    @javafx.fxml.FXML
    private TableView<StaffMember> staffTableView;
    @javafx.fxml.FXML
    private TableColumn<StaffMember, String> staffIdTC;
    @javafx.fxml.FXML
    private TableColumn<StaffMember, String> staffNameTC;
    @javafx.fxml.FXML
    private TableColumn<StaffMember, String> staffRoleTC;
    @javafx.fxml.FXML
    private TableColumn<StaffMember, String> staffShiftTC;
    @javafx.fxml.FXML
    private TableColumn<StaffMember, String> staffStatusTC;
    @javafx.fxml.FXML
    private TextField staffNameTF;
    @javafx.fxml.FXML
    private ComboBox<String> staffRoleCB;
    @javafx.fxml.FXML
    private TextField staffShiftTF;
    @javafx.fxml.FXML
    private TextField newShiftTF;

    @javafx.fxml.FXML
    public void initialize() {
        staffIdTC.setCellValueFactory(new PropertyValueFactory<>("staffId"));
        staffNameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
        staffRoleTC.setCellValueFactory(new PropertyValueFactory<>("role"));
        staffShiftTC.setCellValueFactory(new PropertyValueFactory<>("shiftTiming"));
        staffStatusTC.setCellValueFactory(new PropertyValueFactory<>("status"));

        staffRoleCB.getItems().addAll("Manager", "Receptionist", "Housekeeping", "Security", "Maintenance");

        staffTableView.getItems().addAll(StaffManager.getStaffList());
    }

    @javafx.fxml.FXML
    public void addStaffButtonOA(ActionEvent actionEvent) {
        String name = staffNameTF.getText();
        String role = staffRoleCB.getValue();
        String shift = staffShiftTF.getText();

        if (role == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a role.");
            alert.showAndWait();
            return;
        }

        String staffId = StaffManager.generateStaffId();
        StaffMember s = new StaffMember(staffId, name, role, shift, "Available");

        staffTableView.getItems().add(s);
        StaffManager.getStaffList().add(s);
        StaffManager.saveToFile();

        staffNameTF.setText("");
        staffRoleCB.setValue(null);
        staffShiftTF.setText("");
    }

    @javafx.fxml.FXML
    public void updateShiftButtonOA(ActionEvent actionEvent) {
        StaffMember selected = staffTableView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a staff member first.");
            alert.showAndWait();
            return;
        }

        selected.setShiftTiming(newShiftTF.getText());
        StaffManager.saveToFile();

        staffTableView.refresh();
        newShiftTF.setText("");
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Nazmun/ManagerDashboard.fxml");
    }
}