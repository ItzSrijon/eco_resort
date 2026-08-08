package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import com.summer.section1.group7.eco_resort.Nazmun.Model.Room;
import com.summer.section1.group7.eco_resort.Nazmun.Model.RoomManager;
import com.summer.section1.group7.eco_resort.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageRoomRateController
{
    @javafx.fxml.FXML
    private TableView<Room> roomTableView;
    @javafx.fxml.FXML
    private TableColumn<Room, String> roomIdTC;
    @javafx.fxml.FXML
    private TableColumn<Room, String> roomTypeTC;
    @javafx.fxml.FXML
    private TableColumn<Room, Double> rateTC;
    @javafx.fxml.FXML
    private TableColumn<Room, String> occupancyTC;
    @javafx.fxml.FXML
    private TextField newRateTF;
    @javafx.fxml.FXML
    private ComboBox<String> newRoomTypeCB;
    @javafx.fxml.FXML
    private TextField newRoomRateTF;
    @javafx.fxml.FXML
    private ComboBox<String> occupancyCB;
    @javafx.fxml.FXML
    private Label summaryLabel;

    @javafx.fxml.FXML
    public void initialize() {
        roomIdTC.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        roomTypeTC.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        rateTC.setCellValueFactory(new PropertyValueFactory<>("rate"));
        occupancyTC.setCellValueFactory(new PropertyValueFactory<>("occupancyStatus"));

        newRoomTypeCB.getItems().addAll("Standard", "Superior", "Deluxe", "Junior Suite", "Presidential");
        occupancyCB.getItems().addAll("Vacant", "Occupied", "Maintenance");

        roomTableView.getItems().addAll(RoomManager.getRoomList());

        updateSummary();
    }

    @javafx.fxml.FXML
    public void addRoomButtonOA(ActionEvent actionEvent) {
        String roomType = newRoomTypeCB.getValue();

        double rate;
        try {
            rate = Double.parseDouble(newRoomRateTF.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Rate must be a valid number.");
            alert.showAndWait();
            return;
        }

        if (roomType == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a room type.");
            alert.showAndWait();
            return;
        }

        String roomId = RoomManager.generateRoomId();
        Room room = new Room(roomId, roomType, rate);

        roomTableView.getItems().add(room);
        RoomManager.getRoomList().add(room);
        RoomManager.saveToFile();

        newRoomTypeCB.setValue(null);
        newRoomRateTF.setText("");
        updateSummary();
    }

    @javafx.fxml.FXML
    public void saveRateButtonOA(ActionEvent actionEvent) {
        Room selected = roomTableView.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        double rate;
        try {
            rate = Double.parseDouble(newRateTF.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Rate must be a valid number.");
            alert.showAndWait();
            return;
        }

        selected.setRate(rate);
        RoomManager.saveToFile();

        roomTableView.refresh();
        newRateTF.setText("");
    }

    @javafx.fxml.FXML
    public void saveStatusButtonOA(ActionEvent actionEvent) {
        Room selected = roomTableView.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        String status = occupancyCB.getValue();
        if (status == null) return;

        selected.setOccupancyStatus(status);
        RoomManager.saveToFile();

        roomTableView.refresh();
        occupancyCB.setValue(null);
        updateSummary();
    }

    private void updateSummary() {
        int total = 0, vacant = 0, occupied = 0, maintenance = 0;

        for (Room r : roomTableView.getItems()) {
            total++;
            if (r.getOccupancyStatus().equals("Vacant")) vacant++;
            else if (r.getOccupancyStatus().equals("Occupied")) occupied++;
            else maintenance++;
        }

        summaryLabel.setText("Total: " + total + "   Vacant: " + vacant + "   Occupied: " + occupied + "   Maintenance: " + maintenance);
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Nazmun/ManagerDashboard.fxml");
    }
}