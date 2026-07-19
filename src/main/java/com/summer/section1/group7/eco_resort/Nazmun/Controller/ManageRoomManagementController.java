package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import com.summer.section1.group7.eco_resort.Nazmun.Model.Room;
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

public class ManageRoomManagementController
{
    @javafx.fxml.FXML
    private Label pricingErrorLabel;
    @javafx.fxml.FXML
    private TableColumn<Room, String> roomNoTC;
    @javafx.fxml.FXML
    private ComboBox<String> newStatusCB;
    @javafx.fxml.FXML
    private Label occupancyRateLabel;
    @javafx.fxml.FXML
    private TableColumn<Room, String> roomTypeTC;
    @javafx.fxml.FXML
    private TextField newRateTF;
    @javafx.fxml.FXML
    private Label occupiedCountLabel;
    @javafx.fxml.FXML
    private TableColumn<Room, Double> currentRateTC;
    @javafx.fxml.FXML
    private Label vacantCountLabel;
    @javafx.fxml.FXML
    private TableView<Room> roomsTableView;
    @javafx.fxml.FXML
    private TableColumn<Room, String> occupancyStatusTC;

    private final ObservableList<Room> roomList = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {
        roomNoTC.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        roomTypeTC.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        currentRateTC.setCellValueFactory(new PropertyValueFactory<>("currentRate"));
        occupancyStatusTC.setCellValueFactory(new PropertyValueFactory<>("occupancyStatus"));

        newStatusCB.getItems().addAll("Occupied", "Vacant");

        loadData();
        roomsTableView.setItems(roomList);
        updateOccupancySummary();
    }

    private void loadData() {
        // Sample/demo data — replace with real data source (file/DB) as needed
        if (roomList.isEmpty()) {
            roomList.addAll(
                    new Room("101", "Single", 80.0, "Vacant"),
                    new Room("102", "Double", 120.0, "Occupied"),
                    new Room("103", "Suite", 200.0, "Vacant"),
                    new Room("104", "Double", 120.0, "Occupied"),
                    new Room("105", "Single", 80.0, "Vacant")
            );
        }
    }

    private void updateOccupancySummary() {
        long occupied = roomList.stream().filter(r -> r.getOccupancyStatus().equalsIgnoreCase("Occupied")).count();
        long vacant = roomList.size() - occupied;
        double rate = roomList.isEmpty() ? 0 : (occupied * 100.0 / roomList.size());

        occupiedCountLabel.setText(String.valueOf(occupied));
        vacantCountLabel.setText(String.valueOf(vacant));
        occupancyRateLabel.setText(String.format("%.1f%%", rate));
    }

    @javafx.fxml.FXML
    public void updateRoomButtonOA(ActionEvent actionEvent) {
        Room selected = roomsTableView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            pricingErrorLabel.setText("Please select a room to update.");
            return;
        }

        String newRateText = newRateTF.getText().trim();
        String newStatus = newStatusCB.getValue();

        if (newRateText.isEmpty() && newStatus == null) {
            pricingErrorLabel.setText("Enter a new rate or select a new status.");
            return;
        }

        if (!newRateText.isEmpty()) {
            try {
                double newRate = Double.parseDouble(newRateText);
                selected.setCurrentRate(newRate);
            } catch (NumberFormatException e) {
                pricingErrorLabel.setText("Rate must be a number.");
                return;
            }
        }

        if (newStatus != null) {
            selected.setOccupancyStatus(newStatus);
        }

        roomsTableView.refresh();
        updateOccupancySummary();
        pricingErrorLabel.setText("Room updated successfully.");
        pricingErrorLabel.setTextFill(javafx.scene.paint.Color.GREEN);

        newRateTF.clear();
        newStatusCB.getSelectionModel().clearSelection();
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