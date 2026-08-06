package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import com.summer.section1.group7.eco_resort.Nazmun.Model.StaffManager;
import com.summer.section1.group7.eco_resort.Nazmun.Model.StaffMember;
import com.summer.section1.group7.eco_resort.SceneSwitcher;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ChefManageKitchenStaffController {

    @FXML
    private TableView<StaffMember> kitchenStaffTableView;
    @FXML
    private TableColumn<StaffMember, String> staffIdTC;
    @FXML
    private TableColumn<StaffMember, String> staffNameTC;
    @FXML
    private TableColumn<StaffMember, String> staffRoleTC;
    @FXML
    private TableColumn<StaffMember, String> staffShiftTC;
    @FXML
    private TableColumn<StaffMember, String> staffStatusTC;
    @FXML
    private TableColumn<StaffMember, String> staffTaskTC;
    @FXML
    private TableColumn<StaffMember, String> staffTimeSlotTC;

    @FXML
    private TextField staffNameTF;
    @FXML
    private ComboBox<String> staffRoleCB;
    @FXML
    private TextField staffShiftTF;
    @FXML
    private ComboBox<String> staffStatusCB;
    @FXML
    private Label addStaffMessageLabel;

    @FXML
    private TextField taskDescriptionTF;
    @FXML
    private TextField taskTimeSlotTF;
    @FXML
    private Label assignTaskMessageLabel;

    private final ObservableList<StaffMember> staffObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        // Wire columns to StaffMember getters
        staffIdTC.setCellValueFactory(new PropertyValueFactory<>("staffId"));
        staffNameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
        staffRoleTC.setCellValueFactory(new PropertyValueFactory<>("role"));
        staffShiftTC.setCellValueFactory(new PropertyValueFactory<>("shiftTiming"));
        staffStatusTC.setCellValueFactory(new PropertyValueFactory<>("status"));
        staffTaskTC.setCellValueFactory(new PropertyValueFactory<>("currentTask"));
        staffTimeSlotTC.setCellValueFactory(new PropertyValueFactory<>("taskTimeSlot"));

        // Populate role and status dropdowns
        staffRoleCB.getItems().addAll("Cook", "Sous Chef", "Kitchen Helper", "Baker", "Dishwasher");
        staffStatusCB.getItems().addAll("Available", "On Shift", "Off Duty");

        // Load existing staff from chefStaff.bin into TableView
        staffObservableList.setAll(StaffManager.getStaffList());
        kitchenStaffTableView.setItems(staffObservableList);
    }

    // event-4/5: Add a new staff member
    @FXML
    public void addStaffButtonOA(ActionEvent event) {

        String name = staffNameTF.getText() == null ? "" : staffNameTF.getText().trim();
        String role = staffRoleCB.getValue();
        String shift = staffShiftTF.getText() == null ? "" : staffShiftTF.getText().trim();
        String status = staffStatusCB.getValue();

        if (name.isEmpty() || role == null || shift.isEmpty() || status == null) {
            addStaffMessageLabel.setText("Please fill all fields before adding staff.");
            return;
        }

        String staffId = StaffManager.generateStaffId();
        StaffMember newStaff = new StaffMember(staffId, name, role, shift, status);

        StaffManager.addStaff(newStaff);
        staffObservableList.setAll(StaffManager.getStaffList());

        addStaffMessageLabel.setText("Staff added: " + staffId);
        clearAddStaffFields();
    }

    // event-4 through event-7: Assign a task to the selected staff member
    @FXML
    public void assignTaskButtonOA(ActionEvent event) {

        StaffMember selected = kitchenStaffTableView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            assignTaskMessageLabel.setText("Please select a staff member from the table.");
            return;
        }

        String taskDescription = taskDescriptionTF.getText() == null ? "" : taskDescriptionTF.getText().trim();
        String timeSlot = taskTimeSlotTF.getText() == null ? "" : taskTimeSlotTF.getText().trim();

        // event-5: validate task description and time slot are not empty
        if (taskDescription.isEmpty() || timeSlot.isEmpty()) {
            assignTaskMessageLabel.setText("Task description and time slot cannot be empty.");
            return;
        }

        // event-6: verify staff member is on shift and available
        if (!StaffManager.isAvailable(selected)) {
            assignTaskMessageLabel.setText("Shift conflict: " + selected.getName() + " is Off Duty. Please pick another staff member.");
            return;
        }

        // event-7: valid, save task to staff member
        selected.setCurrentTask(taskDescription);
        selected.setTaskTimeSlot(timeSlot);
        StaffManager.saveStaffToFile();

        kitchenStaffTableView.refresh();
        assignTaskMessageLabel.setStyle("-fx-text-fill: green;");
        assignTaskMessageLabel.setText("Task assigned to " + selected.getName() + " successfully.");

        clearTaskFields();
    }

    @FXML
    public void backButtonOA(ActionEvent event) {
        SceneSwitcher.switchTo("Nazmun/ChefDashboard.fxml");
    }

    private void clearAddStaffFields() {
        staffNameTF.clear();
        staffRoleCB.setValue(null);
        staffShiftTF.clear();
        staffStatusCB.setValue(null);
    }

    private void clearTaskFields() {
        taskDescriptionTF.clear();
        taskTimeSlotTF.clear();
    }
}