package com.summer.section1.group7.eco_resort.Piya.controller;

import com.summer.section1.group7.eco_resort.User;
import com.summer.section1.group7.eco_resort.UserManager;
import com.summer.section1.group7.eco_resort.Piya.model.ActivityReservation;
import com.summer.section1.group7.eco_resort.Piya.model.RoomReservation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReservationHistoryController {

    @FXML private TableView<RoomReservation> roomBookingTV;
    @FXML private TableColumn<RoomReservation, String> roomIdTC;
    @FXML private TableColumn<RoomReservation, String> roomTypeTC;
    @FXML private TableColumn<RoomReservation, String> checkInTC;
    @FXML private TableColumn<RoomReservation, String> checkOutTC;
    @FXML private TableColumn<RoomReservation, String> roomStatusTC;

    @FXML private TableView<ActivityReservation> activityBookingTV;
    @FXML private TableColumn<ActivityReservation, String> activityNameTC;
    @FXML private TableColumn<ActivityReservation, String> scheduleTC;
    @FXML private TableColumn<ActivityReservation, String> activityStatusTC;

    private User currentUser;

    private final ObservableList<RoomReservation> roomList =
            FXCollections.observableArrayList();

    private final ObservableList<ActivityReservation> activityList =
            FXCollections.observableArrayList();

    public void setCurrentUser(User user) {
        currentUser = user;

        if (currentUser == null) {
            currentUser = UserManager.getLoggedInUser();
        }

        roomList.clear();
        activityList.clear();

        loadRoomReservations();
        loadActivityReservations();

        roomBookingTV.setItems(roomList);
        activityBookingTV.setItems(activityList);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    @FXML
    public void initialize() {

        roomIdTC.setCellValueFactory(
                new PropertyValueFactory<>("roomId"));

        roomTypeTC.setCellValueFactory(
                new PropertyValueFactory<>("roomType"));

        checkInTC.setCellValueFactory(
                new PropertyValueFactory<>("checkInDate"));

        checkOutTC.setCellValueFactory(
                new PropertyValueFactory<>("checkOutDate"));

        roomStatusTC.setCellValueFactory(
                new PropertyValueFactory<>("bookingStatus"));

        activityNameTC.setCellValueFactory(
                new PropertyValueFactory<>("activityName"));

        scheduleTC.setCellValueFactory(
                new PropertyValueFactory<>("schedule"));

        activityStatusTC.setCellValueFactory(
                new PropertyValueFactory<>("bookingStatus"));

        roomBookingTV.setItems(roomList);
        activityBookingTV.setItems(activityList);
    }

    private void loadRoomReservations() {

        if (currentUser == null) {
            return;
        }

        File file = new File("RoomReservation.bin");

        if (!file.exists() || file.length() == 0) {
            return;
        }

        try (ObjectInputStream ois =
                     new ObjectInputStream(
                             new FileInputStream(file))) {

            while (true) {
                try {
                    RoomReservation reservation =
                            (RoomReservation) ois.readObject();

                    if (reservation.getUser() != null &&
                            reservation.getUser().getUserId() != null &&
                            reservation.getUser().getUserId()
                                    .equalsIgnoreCase(
                                            currentUser.getUserId())) {

                        roomList.add(reservation);
                    }

                } catch (EOFException e) {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadActivityReservations() {

        if (currentUser == null) {
            return;
        }

        File file = new File("ActivityReservation.bin");

        if (!file.exists() || file.length() == 0) {
            return;
        }

        try (ObjectInputStream ois =
                     new ObjectInputStream(
                             new FileInputStream(file))) {

            while (true) {
                try {
                    ActivityReservation reservation =
                            (ActivityReservation) ois.readObject();

                    if (reservation.getUser() != null &&
                            reservation.getUser().getUserId() != null &&
                            reservation.getUser().getUserId()
                                    .equalsIgnoreCase(
                                            currentUser.getUserId())) {

                        activityList.add(reservation);
                    }

                } catch (EOFException e) {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void backButtonOA(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/summer/section1/group7/eco_resort/Piya/GuestDashboard.fxml"
                    )
            );

            Scene scene = new Scene(loader.load());

            GuestDashboardController controller =
                    loader.getController();

            if (currentUser == null) {
                currentUser = UserManager.getLoggedInUser();
            }

            controller.setCurrentUser(currentUser);

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene()
                    .getWindow();

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}