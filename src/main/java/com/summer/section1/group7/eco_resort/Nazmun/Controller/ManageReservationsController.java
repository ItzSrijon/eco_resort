package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import com.summer.section1.group7.eco_resort.Nazmun.Model.Reservation;
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
import java.time.temporal.ChronoUnit;

public class ManageReservationsController
{
    @javafx.fxml.FXML
    private TableColumn<Reservation, String> guestNameTC;
    @javafx.fxml.FXML
    private TableColumn<Reservation, LocalDate> checkInTC;
    @javafx.fxml.FXML
    private DatePicker checkInDP;
    @javafx.fxml.FXML
    private TextField guestNameTF;
    @javafx.fxml.FXML
    private DatePicker checkOutDP;
    @javafx.fxml.FXML
    private ComboBox<String> roomTypeCB;
    @javafx.fxml.FXML
    private TableView<Reservation> reservationsTableView;
    @javafx.fxml.FXML
    private Label reservationStatusLabel;
    @javafx.fxml.FXML
    private TableColumn<Reservation, LocalDate> checkOutTC;
    @javafx.fxml.FXML
    private TextField contactNumberTF;
    @javafx.fxml.FXML
    private TableColumn<Reservation, String> contactTC;
    @javafx.fxml.FXML
    private TableColumn<Reservation, Double> totalBillTC;
    @javafx.fxml.FXML
    private TableColumn<Reservation, String> statusTC;
    @javafx.fxml.FXML
    private TableColumn<Reservation, String> roomTypeTC;

    private final ObservableList<Reservation> reservationList = FXCollections.observableArrayList();

    // simple per-room-type nightly rates, used to calculate total bill
    private double getRateForRoomType(String type) {
        return switch (type) {
            case "Single" -> 80.0;
            case "Double" -> 120.0;
            case "Suite" -> 200.0;
            default -> 100.0;
        };
    }

    @javafx.fxml.FXML
    public void initialize() {
        guestNameTC.setCellValueFactory(new PropertyValueFactory<>("guestName"));
        contactTC.setCellValueFactory(new PropertyValueFactory<>("contact"));
        roomTypeTC.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        checkInTC.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
        checkOutTC.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("status"));
        totalBillTC.setCellValueFactory(new PropertyValueFactory<>("totalBill"));

        roomTypeCB.getItems().addAll("Single", "Double", "Suite");

        reservationsTableView.setItems(reservationList);
    }

    @javafx.fxml.FXML
    public void checkAvailabilityButtonOA(ActionEvent actionEvent) {
        LocalDate checkIn = checkInDP.getValue();
        LocalDate checkOut = checkOutDP.getValue();
        String roomType = roomTypeCB.getValue();

        if (checkIn == null || checkOut == null || roomType == null) {
            reservationStatusLabel.setTextFill(javafx.scene.paint.Color.RED);
            reservationStatusLabel.setText("Select room type and both dates first.");
            return;
        }

        if (!checkOut.isAfter(checkIn)) {
            reservationStatusLabel.setTextFill(javafx.scene.paint.Color.RED);
            reservationStatusLabel.setText("Check-out must be after check-in.");
            return;
        }

        // Simple overlap check against existing reservations of same room type
        boolean conflict = reservationList.stream().anyMatch(r ->
                r.getRoomType().equals(roomType)
                        && checkIn.isBefore(r.getCheckOut())
                        && checkOut.isAfter(r.getCheckIn())
        );

        if (conflict) {
            reservationStatusLabel.setTextFill(javafx.scene.paint.Color.RED);
            reservationStatusLabel.setText("Room type not available for these dates.");
        } else {
            reservationStatusLabel.setTextFill(javafx.scene.paint.Color.GREEN);
            reservationStatusLabel.setText("Available! You can save the reservation.");
        }
    }

    @javafx.fxml.FXML
    public void saveReservationButtonOA(ActionEvent actionEvent) {
        String name = guestNameTF.getText().trim();
        String contact = contactNumberTF.getText().trim();
        String roomType = roomTypeCB.getValue();
        LocalDate checkIn = checkInDP.getValue();
        LocalDate checkOut = checkOutDP.getValue();

        if (name.isEmpty() || contact.isEmpty() || roomType == null || checkIn == null || checkOut == null) {
            reservationStatusLabel.setTextFill(javafx.scene.paint.Color.RED);
            reservationStatusLabel.setText("Please fill all fields.");
            return;
        }

        if (!checkOut.isAfter(checkIn)) {
            reservationStatusLabel.setTextFill(javafx.scene.paint.Color.RED);
            reservationStatusLabel.setText("Check-out must be after check-in.");
            return;
        }

        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        double totalBill = nights * getRateForRoomType(roomType);

        Reservation reservation = new Reservation(name, contact, roomType, checkIn, checkOut, "Booked", totalBill);
        reservationList.add(reservation);
        reservationsTableView.refresh();

        reservationStatusLabel.setTextFill(javafx.scene.paint.Color.GREEN);
        reservationStatusLabel.setText("Reservation saved.");

        guestNameTF.clear();
        contactNumberTF.clear();
        roomTypeCB.getSelectionModel().clearSelection();
        checkInDP.setValue(null);
        checkOutDP.setValue(null);
    }

    @javafx.fxml.FXML
    public void checkInButtonOA(ActionEvent actionEvent) {
        Reservation selected = reservationsTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            reservationStatusLabel.setTextFill(javafx.scene.paint.Color.RED);
            reservationStatusLabel.setText("Select a reservation first.");
            return;
        }
        selected.setStatus("Checked-In");
        reservationsTableView.refresh();
        reservationStatusLabel.setTextFill(javafx.scene.paint.Color.GREEN);
        reservationStatusLabel.setText(selected.getGuestName() + " checked in.");
    }

    @javafx.fxml.FXML
    public void checkOutButtonOA(ActionEvent actionEvent) {
        Reservation selected = reservationsTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            reservationStatusLabel.setTextFill(javafx.scene.paint.Color.RED);
            reservationStatusLabel.setText("Select a reservation first.");
            return;
        }
        selected.setStatus("Checked-Out");
        reservationsTableView.refresh();
        reservationStatusLabel.setTextFill(javafx.scene.paint.Color.GREEN);
        reservationStatusLabel.setText(selected.getGuestName() + " checked out.");
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