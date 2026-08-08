package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import com.summer.section1.group7.eco_resort.Nazmun.Model.StaffManager;
import com.summer.section1.group7.eco_resort.Nazmun.Model.StaffMember;
import com.summer.section1.group7.eco_resort.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ChefManageKitchenStaffController
{
    @javafx.fxml.FXML
    private TableView<StaffMember> kitchenStaffTableView;
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
    private TableColumn<StaffMember, String> staffTaskTC;
    @javafx.fxml.FXML
    private TableColumn<StaffMember, String> staffTimeSlotTC;
    @javafx.fxml.FXML
    private TextField staffNameTF;
    @javafx.fxml.FXML
    private ComboBox<String> staffRoleCB;
    @javafx.fxml.FXML
    private TextField staffShiftTF;
    @javafx.fxml.FXML
    private ComboBox<String> staffStatusCB;
    @javafx.fxml.FXML
    private TextField taskDescriptionTF;
    @javafx.fxml.FXML
    private TextField taskTimeSlotTF;

    @javafx.fxml.FXML
    public void initialize() {
        staffIdTC.setCellValueFactory(new PropertyValueFactory<>("staffId"));
        staffNameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
        staffRoleTC.setCellValueFactory(new PropertyValueFactory<>("role"));
        staffShiftTC.setCellValueFactory(new PropertyValueFactory<>("shiftTiming"));
        staffStatusTC.setCellValueFactory(new PropertyValueFactory<>("status"));
        staffTaskTC.setCellValueFactory(new PropertyValueFactory<>("currentTask"));
        staffTimeSlotTC.setCellValueFactory(new PropertyValueFactory<>("taskTimeSlot"));

        staffRoleCB.getItems().addAll("Cook", "Sous Chef", "Kitchen Helper", "Baker", "Dishwasher");
        staffStatusCB.getItems().addAll("Available", "On Shift", "Off Duty");

        kitchenStaffTableView.getItems().addAll(StaffManager.getStaffList());
    }

    @javafx.fxml.FXML
    public void addStaffButtonOA(ActionEvent actionEvent) {
        String name = staffNameTF.getText();
        String role = staffRoleCB.getValue();
        String shift = staffShiftTF.getText();
        String status = staffStatusCB.getValue();

        if (role == null || status == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select role and status.");
            alert.showAndWait();
            return;
        }

        String staffId = StaffManager.generateStaffId();
        StaffMember s = new StaffMember(staffId, name, role, shift, status);

        kitchenStaffTableView.getItems().add(s);
        StaffManager.getStaffList().add(s);
        StaffManager.saveToFile();

        staffNameTF.setText("");
        staffRoleCB.setValue(null);
        staffShiftTF.setText("");
        staffStatusCB.setValue(null);
    }

    @javafx.fxml.FXML
    public void assignTaskButtonOA(ActionEvent actionEvent) {
        StaffMember selected = kitchenStaffTableView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a staff member.");
            alert.showAndWait();
            return;
        }

        if (!StaffManager.isAvailable(selected)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(selected.getName() + " is Off Duty. Pick another staff member.");
            alert.showAndWait();
            return;
        }

        selected.setCurrentTask(taskDescriptionTF.getText());
        selected.setTaskTimeSlot(taskTimeSlotTF.getText());
        StaffManager.saveToFile();

        kitchenStaffTableView.refresh();
        taskDescriptionTF.setText("");
        taskTimeSlotTF.setText("");
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Nazmun/ChefDashboard.fxml");
    }
}