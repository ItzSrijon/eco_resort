package Nazmun;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
    private TableColumn<Room, String> currentRateTC;
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
        currentRateTC.setCellValueFactory(new PropertyValueFactory<>("rate"));
        occupancyStatusTC.setCellValueFactory(new PropertyValueFactory<>("occupancyStatus"));

        newStatusCB.setItems(FXCollections.observableArrayList(
                "Vacant", "Occupied", "Maintenance"
        ));

        roomList.add(new Room("101", "Standard", "3500", "Vacant"));
        roomList.add(new Room("102", "Deluxe", "5000", "Occupied"));
        roomList.add(new Room("103", "Suite", "8000", "Vacant"));
        roomList.add(new Room("104", "Standard", "3500", "Maintenance"));

        roomsTableView.setItems(roomList);

        updateOccupancySummary();
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/summer/section1/group7/eco_resort/Nazmun/ManagerDashboard.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void updateRoomButtonOA(ActionEvent actionEvent) {

        Room selectedRoom = roomsTableView.getSelectionModel().getSelectedItem();

        if (selectedRoom == null) {
            pricingErrorLabel.setText("Please select a room from the table first.");
            return;
        }

        String newRate = newRateTF.getText();
        String newStatus = newStatusCB.getValue();

        if (newRate == null || newRate.isEmpty()) {
            pricingErrorLabel.setText("Please enter a new rate.");
            return;
        }
        if (!newRate.matches("\\d+")) {
            pricingErrorLabel.setText("Rate must be a valid number.");
            return;
        }

        int rateValue = Integer.parseInt(newRate);
        if (rateValue < 1000 || rateValue > 20000) {
            pricingErrorLabel.setText("Rate must be within allowed range (1000 - 20000).");
            return;
        }

        if (newStatus == null || newStatus.isEmpty()) {
            pricingErrorLabel.setText("Please select a new status.");
            return;
        }

        selectedRoom.setRate(newRate);
        selectedRoom.setOccupancyStatus(newStatus);

        roomsTableView.refresh();
        pricingErrorLabel.setText("");

        newRateTF.clear();
        newStatusCB.setValue(null);

        updateOccupancySummary();
    }

    private void updateOccupancySummary() {
        long occupied = roomList.stream().filter(r -> r.getOccupancyStatus().equals("Occupied")).count();
        long vacant = roomList.stream().filter(r -> r.getOccupancyStatus().equals("Vacant")).count();

        int total = roomList.size();
        double occupancyRate = total == 0 ? 0 : (occupied * 100.0) / total;

        occupiedCountLabel.setText(String.valueOf(occupied));
        vacantCountLabel.setText(String.valueOf(vacant));
        occupancyRateLabel.setText(String.format("%.1f%%", occupancyRate));
    }
}