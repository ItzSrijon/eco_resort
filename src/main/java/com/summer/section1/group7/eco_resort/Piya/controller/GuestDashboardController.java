package com.summer.section1.group7.eco_resort.Piya.controller;

import com.summer.section1.group7.eco_resort.User;
import com.summer.section1.group7.eco_resort.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class GuestDashboardController {

    private User currentUser;

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    @FXML
    public void initialize() {
        /*
         * initialize() runs before setCurrentUser().
         * Therefore, get the logged-in user only if available.
         */
        if (currentUser == null) {
            currentUser = UserManager.getLoggedInUser();
        }

        if (currentUser != null) {
            System.out.println(
                    "Guest Dashboard User: "
                            + currentUser.getUsername()
                            + " (" + currentUser.getUserId() + ")"
            );
        } else {
            System.out.println(
                    "Guest Dashboard: No logged-in user found."
            );
        }
    }

    @FXML
    public void updateProfileButtonOA(ActionEvent event)
            throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/com/summer/section1/group7/eco_resort/Piya/updateProfile.fxml"
                )
        );

        Scene scene = new Scene(loader.load());

        UpdateProfileController controller =
                loader.getController();

        controller.setCurrentUser(currentUser);

        changeScene(event, scene);
    }

    @FXML
    public void submitFeedbackButtonOA(ActionEvent event)
            throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/com/summer/section1/group7/eco_resort/Piya/submitFeedback.fxml"
                )
        );

        Scene scene = new Scene(loader.load());

        SubmitFeedbackController controller =
                loader.getController();

        controller.setCurrentUser(currentUser);

        changeScene(event, scene);
    }

    @FXML
    public void browseActivitiesButtonOA(ActionEvent event)
            throws IOException {

        URL url = getClass().getResource(
                "/com/summer/section1/group7/eco_resort/Piya/browseActivities.fxml"
        );

        if (url == null) {
            System.out.println("browseActivities.fxml not found.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(url);

        Scene scene = new Scene(loader.load());

        BrowseActivitiesController controller =
                loader.getController();

        if (controller == null) {
            System.out.println(
                    "BrowseActivitiesController was not created. "
                            + "Check fx:controller in browseActivities.fxml."
            );
            return;
        }

        /*
         * Pass the same logged-in guest to BrowseActivitiesController.
         */
        controller.setCurrentUser(currentUser);

        changeScene(event, scene);
    }

    @FXML
    public void reservationHistoryButtonOA(ActionEvent event)
            throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/com/summer/section1/group7/eco_resort/Piya/reservationHistory.fxml"
                )
        );

        Scene scene = new Scene(loader.load());

        ReservationHistoryController controller =
                loader.getController();

        controller.setCurrentUser(currentUser);

        changeScene(event, scene);
    }

    @FXML
    public void bookRoomButtonOA(ActionEvent event)
            throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/com/summer/section1/group7/eco_resort/Piya/RoomReservation.fxml"
                )
        );

        Scene scene = new Scene(loader.load());

        BookRoomController controller =
                loader.getController();

        controller.setCurrentUser(currentUser);

        changeScene(event, scene);
    }

    @FXML
    public void logoutButtonOA(ActionEvent event)
            throws IOException {

        UserManager.setLoggedInUser(null);
        currentUser = null;

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/com/summer/section1/group7/eco_resort/Login.fxml"
                )
        );

        Scene scene = new Scene(loader.load());

        changeScene(event, scene);
    }

    private void changeScene(ActionEvent event, Scene scene) {

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.setScene(scene);
        stage.show();
    }
}

