package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import com.summer.section1.group7.eco_resort.Nazmun.Model.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class ManageStaffManagementController
{
    @javafx.fxml.FXML
    private TableColumn<Staff, String> roleTC;
    @javafx.fxml.FXML
    private ComboBox<String> roleCB;
    @javafx.fxml.FXML
    private TableColumn<Staff, String> staffNameTC;
    @javafx.fxml.FXML
    private TextField staffNameTF;
    @javafx.fxml.FXML
    private TableColumn<Staff, String> staffIdTC;
    @javafx.fxml.FXML
    private DatePicker trainingDateDP;
    @javafx.fxml.FXML
    private TableColumn<Staff, String> staffStatusTC;
    @javafx.fxml.FXML
    private TableView<Staff> staffTableView;
    @javafx.fxml.FXML
    private TableColumn<Staff, String> shiftTC;
    @javafx.fxml.FXML
    private ComboBox<String> shiftCB;
    @javafx.fxml.FXML
    private Label staffConflictLabel;

    private final ObservableList<Staff> staffList = FXCollections.observableArrayList();
    private int nextStaffId = 1;

    @javafx.fxml.FXML
    public void initialize() {
        staffIdTC.setCellValueFactory(new PropertyValueFactory<>("staffId"));
        staffNameTC.setCellValueFactory(new PropertyValueFactory<>("staffName"));
        roleTC.setCellValueFactory(new PropertyValueFactory<>("role"));
        shiftTC.setCellValueFactory(new PropertyValueFactory<>("shift"));
        staffStatusTC.setCellValueFactory(new PropertyValueFactory<>("status"));

        roleCB.getItems().addAll("Receptionist", "Housekeeping", "Waiter", "Security", "Kitchen Staff");
        shiftCB.getItems().addAll("Morning", "Afternoon", "Night");

        staffTableView.setItems(staffList);
    }

    @javafx.fxml.FXML
    public void addStaffButtonOA(ActionEvent actionEvent) {
        String name = staffNameTF.getText().trim();
        String role = roleCB.getValue();
        String shift = shiftCB.getValue();

        if (name.isEmpty() || role == null || shift == null) {
            staffConflictLabel.setText("Please fill name, role, and shift.");
            return;
        }

        boolean duplicateShift = staffList.stream().anyMatch(s ->
                s.getStaffName().equalsIgnoreCase(name) && s.getShift().equals(shift));

        if (duplicateShift) {
            staffConflictLabel.setTextFill(javafx.scene.paint.Color.RED);
            staffConflictLabel.setText("Shift conflict: this staff member is already assigned that shift.");
            return;
        }

        String staffId = "S" + String.format("%03d", nextStaffId++);
        Staff staff = new Staff(staffId, name, role, shift, "Active");
        staffList.add(staff);
        staffTableView.refresh();

        staffConflictLabel.setTextFill(javafx.scene.paint.Color.GREEN);
        staffConflictLabel.setText("Staff added: " + staffId);

        staffNameTF.clear();
        roleCB.getSelectionModel().clearSelection();
        shiftCB.getSelectionModel().clearSelection();
    }

    @javafx.fxml.FXML
    public void assignTrainingButtonOA(ActionEvent actionEvent) {
        Staff selected = staffTableView.getSelectionModel().getSelectedItem();
        LocalDate trainingDate = trainingDateDP.getValue();

        if (selected == null) {
            staffConflictLabel.setText("Select a staff member first.");
            return;
        }

        if (trainingDate == null) {
            staffConflictLabel.setText("Select a training date.");
            return;
        }

        staffConflictLabel.setTextFill(javafx.scene.paint.Color.GREEN);
        staffConflictLabel.setText("Training scheduled for " + selected.getStaffName() + " on " + trainingDate + ".");

        trainingDateDP.setValue(null);
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(
                "/com/summer/section1/group7/eco_resort/Nazmun/ManagerDashboard.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}