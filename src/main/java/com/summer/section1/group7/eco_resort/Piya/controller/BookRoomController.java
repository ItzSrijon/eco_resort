package com.summer.section1.group7.eco_resort.Piya.controller;

import com.summer.section1.group7.eco_resort.Piya.model.Reservation;
import com.summer.section1.group7.eco_resort.Piya.repository.ReservationRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class BookRoomController {

    @FXML
    private ComboBox<String> roomTypeCB;

    @FXML
    private ComboBox<Integer> guestCountCB;

    @FXML
    private DatePicker checkInDP;

    @FXML
    private DatePicker checkOutDP;

    @FXML
    private Label messageLabel;

    private ReservationRepository reservationRepository = new ReservationRepository();

    @FXML
    public void initialize() {

        roomTypeCB.getItems().addAll(
                "Single",
                "Double",
                "Deluxe",
                "Suite"
        );

        guestCountCB.getItems().addAll(
                1, 2, 3, 4
        );

    }

    @FXML
    public void bookButtonOA(ActionEvent actionEvent) {

        if (roomTypeCB.getValue() == null
                || guestCountCB.getValue() == null
                || checkInDP.getValue() == null
                || checkOutDP.getValue() == null) {

            messageLabel.setText("Please fill up all fields.");
            return;
        }

        Reservation reservation = new Reservation(
                reservationRepository.getReservationList().size() + 1,
                checkInDP.getValue(),
                checkOutDP.getValue(),
                "Booked"
        );

        reservationRepository.addReservation(reservation);

        messageLabel.setText("Room booked successfully!");

        roomTypeCB.setValue(null);
        guestCountCB.setValue(null);
        checkInDP.setValue(null);
        checkOutDP.setValue(null);
    }

    @FXML
    public void backButtonOA(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/summer/section1/group7/eco_resort/Piya/guestDashboard.fxml"));

        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);

        stage.show();

    }

}