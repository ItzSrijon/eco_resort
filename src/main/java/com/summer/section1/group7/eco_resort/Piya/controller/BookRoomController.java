package com.summer.section1.group7.eco_resort.Piya.controller;

import com.summer.section1.group7.eco_resort.User;
import com.summer.section1.group7.eco_resort.UserManager;
import com.summer.section1.group7.eco_resort.Piya.model.AppendableObjectOutputStream;
import com.summer.section1.group7.eco_resort.Piya.model.Room;
import com.summer.section1.group7.eco_resort.Piya.model.RoomReservation;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BookRoomController {

    @FXML private DatePicker checkInDP;
    @FXML private DatePicker checkOutDP;
    @FXML private ComboBox<String> roomTypeCB;
    @FXML private ComboBox<Integer> guestCountCB;
    @FXML private Label messageLabel;

    private Room selectedRoom;
    private User currentUser;

    public void setCurrentUser(User user) {
        currentUser = user;

        if (currentUser == null) {
            currentUser = UserManager.getLoggedInUser();
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    @FXML
    public void initialize() {

        roomTypeCB.setItems(
                FXCollections.observableArrayList(
                        "Single",
                        "Deluxe",
                        "Suite",
                        "Family"
                )
        );

        guestCountCB.setItems(
                FXCollections.observableArrayList(
                        1, 2, 3, 4, 5, 6
                )
        );
    }

    // Event-6: Check room availability
    @FXML
    public void checkRoomAvailableButtonOA(ActionEvent event) {

        selectedRoom = null;

        if (roomTypeCB.getValue() == null ||
                guestCountCB.getValue() == null) {

            messageLabel.setText(
                    "Please select room type and guests."
            );
            return;
        }

        File file = new File("Room.bin");

        if (!file.exists() || file.length() == 0) {

            messageLabel.setText(
                    "Room information not found."
            );
            return;
        }

        try (ObjectInputStream ois =
                     new ObjectInputStream(
                             new FileInputStream(file))) {

            while (true) {

                try {

                    Room room =
                            (Room) ois.readObject();

                    if (room.getRoomType() != null &&
                            room.getAvailability() != null &&
                            room.getRoomType()
                                    .equalsIgnoreCase(
                                            roomTypeCB.getValue()) &&
                            room.getAvailability()
                                    .equalsIgnoreCase(
                                            "Available")) {

                        selectedRoom = room;
                        break;
                    }

                } catch (EOFException e) {
                    break;
                }
            }

            if (selectedRoom == null) {

                messageLabel.setText(
                        "No room available."
                );

            } else {

                messageLabel.setText(
                        "Room available."
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            messageLabel.setText(
                    "Unable to check room availability."
            );
        }
    }

    // Event-7: Save reservation
    @FXML
    public void bookRoomButtonOA(ActionEvent event) {

        if (roomTypeCB.getValue() == null ||
                guestCountCB.getValue() == null ||
                checkInDP.getValue() == null ||
                checkOutDP.getValue() == null) {

            messageLabel.setText(
                    "Please fill all fields."
            );
            return;
        }

        // Event-4: Validate booking dates
        if (!checkOutDP.getValue()
                .isAfter(checkInDP.getValue())) {

            messageLabel.setText(
                    "Invalid booking dates."
            );
            return;
        }

        if (currentUser == null) {
            currentUser =
                    UserManager.getLoggedInUser();
        }

        if (currentUser == null) {

            messageLabel.setText(
                    "User session not found."
            );
            return;
        }

        if (selectedRoom == null) {

            messageLabel.setText(
                    "Please check room availability first."
            );
            return;
        }

        /*
         * reservationId is null intentionally.
         * The receptionist creates the reservationId later.
         */
        RoomReservation reservation =
                new RoomReservation(
                        null,
                        checkInDP.getValue(),
                        checkOutDP.getValue(),
                        "Pending",
                        currentUser,
                        selectedRoom
                );

        if (saveReservation(reservation)) {

            messageLabel.setText(
                    "Room booking request submitted successfully."
            );

            checkInDP.setValue(null);
            checkOutDP.setValue(null);
            roomTypeCB.setValue(null);
            guestCountCB.setValue(null);

            selectedRoom = null;
        }
    }

    private boolean saveReservation(
            RoomReservation reservation) {

        File file =
                new File("RoomReservation.bin");

        try {

            ObjectOutputStream oos;

            if (file.exists() && file.length() > 0) {

                oos =
                        new AppendableObjectOutputStream(
                                new FileOutputStream(
                                        file,
                                        true
                                )
                        );

            } else {

                oos =
                        new ObjectOutputStream(
                                new FileOutputStream(file)
                        );
            }

            oos.writeObject(reservation);
            oos.close();

            return true;

        } catch (IOException e) {

            e.printStackTrace();

            messageLabel.setText(
                    "Failed to save reservation."
            );

            return false;
        }
    }

    // Event-9: Return to Dashboard
    @FXML
    public void backButtonOA(ActionEvent event) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/com/summer/section1/group7/eco_resort/Piya/GuestDashboard.fxml"
                            )
                    );

            Scene scene =
                    new Scene(loader.load());

            GuestDashboardController controller =
                    loader.getController();

            if (currentUser == null) {
                currentUser =
                        UserManager.getLoggedInUser();
            }

            controller.setCurrentUser(
                    currentUser
            );

            Stage stage =
                    (Stage) ((Node) event.getSource())
                            .getScene()
                            .getWindow();

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {

            e.printStackTrace();

            messageLabel.setText(
                    "Unable to return to dashboard."
            );
        }
    }
}