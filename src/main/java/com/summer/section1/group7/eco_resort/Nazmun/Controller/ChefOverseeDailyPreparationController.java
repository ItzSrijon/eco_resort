package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import com.summer.section1.group7.eco_resort.Nazmun.Model.PrepTask;
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

public class ChefOverseeDailyPreparationController
{
    @javafx.fxml.FXML
    private TableColumn<PrepTask, String> dishNameTC;
    @javafx.fxml.FXML
    private ComboBox<String> assignedStaffCB;
    @javafx.fxml.FXML
    private ComboBox<String> stationCB;
    @javafx.fxml.FXML
    private Label taskStatusLabel;
    @javafx.fxml.FXML
    private TableColumn<PrepTask, String> notesTC;
    @javafx.fxml.FXML
    private TableColumn<PrepTask, Integer> quantityTC;
    @javafx.fxml.FXML
    private TableView<PrepTask> todaysOrdersTableView;
    @javafx.fxml.FXML
    private Label unavailabilityIssueLabel;

    private final ObservableList<PrepTask> taskList = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {
        dishNameTC.setCellValueFactory(new PropertyValueFactory<>("dishName"));
        quantityTC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        notesTC.setCellValueFactory(new PropertyValueFactory<>("notes"));

        stationCB.getItems().addAll("Grill", "Salad", "Dessert", "Beverage");
        assignedStaffCB.getItems().addAll("Staff A", "Staff B", "Staff C");

        // Demo data — replace with real orders source later
        taskList.addAll(
                new PrepTask("Grilled Chicken", 12, "No spice"),
                new PrepTask("Caesar Salad", 8, "Extra dressing"),
                new PrepTask("Chocolate Cake", 5, "")
        );

        todaysOrdersTableView.setItems(taskList);
    }

    @javafx.fxml.FXML
    public void assignTaskButtonOA(ActionEvent actionEvent) {
        PrepTask selected = todaysOrdersTableView.getSelectionModel().getSelectedItem();
        String station = stationCB.getValue();
        String staff = assignedStaffCB.getValue();

        if (selected == null) {
            unavailabilityIssueLabel.setText("Select a dish/order first.");
            return;
        }

        if (station == null || staff == null) {
            unavailabilityIssueLabel.setText("Select both station and staff.");
            return;
        }

        unavailabilityIssueLabel.setText("");
        taskStatusLabel.setTextFill(javafx.scene.paint.Color.GREEN);
        taskStatusLabel.setText(selected.getDishName() + " assigned to " + staff + " at " + station + " station.");

        stationCB.getSelectionModel().clearSelection();
        assignedStaffCB.getSelectionModel().clearSelection();
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(
                "/com/summer/section1/group7/eco_resort/Nazmun/ChefDashboard.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}