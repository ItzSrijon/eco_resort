package com.summer.section1.group7.eco_resort.Srijon.controller;

import com.summer.section1.group7.eco_resort.Srijon.model.Reservation;
import com.summer.section1.group7.eco_resort.Srijon.util.BinaryFileManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class viewReservationController {

    @FXML private TableView<Reservation> reservationTable;
    @FXML private TableColumn<Reservation, String> colReservationId;
    @FXML private TableColumn<Reservation, String> colGuestId;
    @FXML private TableColumn<Reservation, String> colPhone;
    @FXML private TableColumn<Reservation, String> colRoom;
    @FXML private TableColumn<Reservation, LocalDate> colCheckIn;
    @FXML private TableColumn<Reservation, LocalDate> colCheckOut;
    @FXML private TableColumn<Reservation, String> colStatus;
    @FXML private Label infoLabel;

    private final String RES_FILE = "reservation.bin";
    private final ObservableList<Reservation> reservations = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colReservationId.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        colGuestId.setCellValueFactory(new PropertyValueFactory<>("guestId"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("guestPhone"));
        colRoom.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colCheckIn.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        colCheckOut.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        reservationTable.setItems(reservations);
        loadReservations();
    }

    @FXML
    public void onRefresh() {
        loadReservations();
    }

    @FXML
    public void onDeleteSelected() {
        Reservation selected = reservationTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            infoLabel.setText("No reservation selected.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Delete Reservation");
        confirm.setHeaderText("Delete reservation " + selected.getReservationId() + "?");
        confirm.setContentText("This will remove the reservation from the file.");

        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            ArrayList<Reservation> list = BinaryFileManager.loadList(RES_FILE);
            boolean removed = list.removeIf(r -> {
                String id = r.getReservationId();
                return id != null && id.equals(selected.getReservationId());
            });
            if (removed) {
                try {
                    BinaryFileManager.saveList(RES_FILE, list);
                    loadReservations();
                    infoLabel.setText("Reservation deleted: " + selected.getReservationId());
                } catch (IOException e) {
                    e.printStackTrace();
                    infoLabel.setText("Failed to delete reservation.");
                }
            } else {
                infoLabel.setText("Reservation not found in file.");
            }
        }
    }

    private void loadReservations() {
        reservations.clear();
        ArrayList<Reservation> list = BinaryFileManager.loadList(RES_FILE);
        if (list != null) reservations.addAll(list);
        reservationTable.setItems(reservations);
        infoLabel.setText("Loaded " + reservations.size() + " reservations.");
    }
}
