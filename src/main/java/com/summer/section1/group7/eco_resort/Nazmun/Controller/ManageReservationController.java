package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import com.summer.section1.group7.eco_resort.Nazmun.Model.Reservation;
import com.summer.section1.group7.eco_resort.Nazmun.Model.ReservationManager;
import com.summer.section1.group7.eco_resort.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageReservationController
{
    @javafx.fxml.FXML
    private TableView<Reservation> reservationTableView;
    @javafx.fxml.FXML
    private TableColumn<Reservation, String> reservationIdTC;
    @javafx.fxml.FXML
    private TableColumn<Reservation, String> guestNameTC;
    @javafx.fxml.FXML
    private TableColumn<Reservation, String> roomTypeTC;
    @javafx.fxml.FXML
    private TableColumn<Reservation, String> checkInTC;
    @javafx.fxml.FXML
    private TableColumn<Reservation, String> checkOutTC;
    @javafx.fxml.FXML
    private TableColumn<Reservation, String> statusTC;
    @javafx.fxml.FXML
    private TextField guestNameTF;
    @javafx.fxml.FXML
    private ComboBox<String> roomTypeCB;
    @javafx.fxml.FXML
    private DatePicker checkInDP;
    @javafx.fxml.FXML
    private DatePicker checkOutDP;

    @javafx.fxml.FXML
    public void initialize() {
        reservationIdTC.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        guestNameTC.setCellValueFactory(new PropertyValueFactory<>("guestName"));
        roomTypeTC.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        checkInTC.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        checkOutTC.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("status"));

        roomTypeCB.getItems().addAll("Standard", "Superior", "Deluxe", "Junior Suite", "Presidential");

        reservationTableView.getItems().addAll(ReservationManager.getReservationList());
    }

    @javafx.fxml.FXML
    public void addReservationButtonOA(ActionEvent actionEvent) {
        String name = guestNameTF.getText();
        String roomType = roomTypeCB.getValue();

        if (roomType == null || checkInDP.getValue() == null || checkOutDP.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill room type, check-in and check-out dates.");
            alert.showAndWait();
            return;
        }

        String reservationId = ReservationManager.generateReservationId();
        Reservation r = new Reservation(reservationId, name, roomType, checkInDP.getValue(), checkOutDP.getValue());

        reservationTableView.getItems().add(r);
        ReservationManager.getReservationList().add(r);
        ReservationManager.saveToFile();

        guestNameTF.setText("");
        roomTypeCB.setValue(null);
        checkInDP.setValue(null);
        checkOutDP.setValue(null);
    }

    @javafx.fxml.FXML
    public void cancelReservationButtonOA(ActionEvent actionEvent) {
        Reservation selected = reservationTableView.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        selected.setStatus("Cancelled");
        ReservationManager.saveToFile();

        reservationTableView.refresh();
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Nazmun/ManagerDashboard.fxml");
    }
}