 package com.summer.section1.group7.eco_resort.Piya.controller;

import com.summer.section1.group7.eco_resort.User;
import com.summer.section1.group7.eco_resort.UserManager;
import com.summer.section1.group7.eco_resort.Piya.model.Activity;
import com.summer.section1.group7.eco_resort.Piya.model.ActivityReservation;
import com.summer.section1.group7.eco_resort.Piya.model.AppendableObjectOutputStream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BrowseActivitiesController {

    @FXML private TableView<Activity> activityTV;
    @FXML private TableColumn<Activity, String> activityNameTC;
    @FXML private TableColumn<Activity, String> TimeTC;
    @FXML private TableColumn<Activity, Double> priceTC;
    @FXML private TableColumn<Activity, String> StatusTC;
    @FXML private TableColumn<Activity, Integer> limitTC;
    @FXML private TextField searchActivityTF;
    @FXML private TextArea descriptionTA;
    @FXML private Label messageLabel;

    private User currentUser;

    private final ObservableList<Activity> activityList =
            FXCollections.observableArrayList();

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    @FXML
    public void initialize() {

        activityNameTC.setCellValueFactory(
                new PropertyValueFactory<>("activityName"));

        TimeTC.setCellValueFactory(
                new PropertyValueFactory<>("schedule"));

        priceTC.setCellValueFactory(
                new PropertyValueFactory<>("price"));

        StatusTC.setCellValueFactory(
                new PropertyValueFactory<>("status"));

        limitTC.setCellValueFactory(
                new PropertyValueFactory<>("capacity"));

        if (currentUser == null) {
            currentUser = UserManager.getLoggedInUser();
        }

        // Event-3: Retrieve activities
        loadActivities();

        // Event-4, Event-5, Event-6: Display activities
        activityTV.setItems(activityList);

        // Event-7: Display selected activity details
        activityTV.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldValue, newValue) -> {

                    if (newValue != null) {
                        showActivityDetails(newValue);
                    }
                });
    }

    // Event-3: Retrieve activities from Activity.bin
    private void loadActivities() {

        File file = new File("Activity.bin");

        if (!file.exists()) {
            messageLabel.setText("No activity data found.");
            return;
        }

        try (ObjectInputStream ois =
                     new ObjectInputStream(
                             new FileInputStream(file))) {

            while (true) {

                try {

                    Activity activity =
                            (Activity) ois.readObject();

                    activityList.add(activity);

                } catch (EOFException e) {
                    break;
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
            messageLabel.setText(
                    "Failed to load activities."
            );
        }
    }

    // Event-1 + Event-2: Search requested activity
    @FXML
    public void searchButtonOA(ActionEvent event) {

        String searchText =
                searchActivityTF.getText()
                        .trim()
                        .toLowerCase();

        if (searchText.isEmpty()) {

            activityTV.setItems(activityList);

            messageLabel.setText(
                    "Enter an activity to search."
            );

            return;
        }

        ObservableList<Activity> searchedList =
                FXCollections.observableArrayList();

        for (Activity activity : activityList) {

            if (activity.getActivityName() != null &&
                    activity.getActivityName()
                            .toLowerCase()
                            .contains(searchText)) {

                searchedList.add(activity);
            }
        }

        if (searchedList.isEmpty()) {

            activityTV.setItems(activityList);

            messageLabel.setText(
                    "Activity not found."
            );

        } else {

            activityTV.setItems(searchedList);

            messageLabel.setText(
                    "Activity found."
            );
        }
    }

    // Event-7: Display selected activity details
    private void showActivityDetails(Activity activity) {

        descriptionTA.setText(
                "Activity Name: "
                        + activity.getActivityName()
                        + "\nCategory: "
                        + activity.getCategory()
                        + "\nSchedule: "
                        + activity.getSchedule()
                        + "\nParticipant Limit: "
                        + activity.getCapacity()
                        + "\nPrice: "
                        + activity.getPrice()
                        + "\nStatus: "
                        + activity.getStatus()
                        + "\n\nDescription:\n"
                        + activity.getDescription()
        );
    }

    // Event-8: Reserve selected activity
    @FXML
    public void reserveButtonOA(ActionEvent event) {

        Activity selectedActivity =
                activityTV.getSelectionModel()
                        .getSelectedItem();

        if (selectedActivity == null) {

            messageLabel.setText(
                    "Please select an activity first."
            );

            return;
        }

        if (currentUser == null) {
            currentUser = UserManager.getLoggedInUser();
        }

        if (currentUser == null) {

            messageLabel.setText(
                    "User not found. Please login again."
            );

            return;
        }

        if (selectedActivity.getStatus() != null &&
                selectedActivity.getStatus()
                        .equalsIgnoreCase("Full")) {

            messageLabel.setText(
                    "This activity is full."
            );

            return;
        }

        /*
         * Guest does not create reservationId.
         * Receptionist will assign the reservationId later.
         */
        ActivityReservation reservation =
                new ActivityReservation(
                        null,
                        currentUser,
                        selectedActivity.getActivityName(),
                        selectedActivity.getSchedule(),
                        "Pending"
                );

        if (saveReservation(reservation)) {

            messageLabel.setText(
                    "Activity reservation submitted successfully."
            );
        }
    }

    // Save activity reservation to ActivityReservation.bin
    private boolean saveReservation(
            ActivityReservation reservation) {

        File file =
                new File("ActivityReservation.bin");

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
                    "Failed to save activity reservation."
            );

            return false;
        }
    }

    // Return to Guest Dashboard
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

            controller.setCurrentUser(currentUser);

            Stage stage =
                    (Stage) ((Node) event.getSource())
                            .getScene()
                            .getWindow();

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {

            e.printStackTrace();

            messageLabel.setText(
                    "Unable to return to dashboard."
            );
        }
    }
}

