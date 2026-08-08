package com.summer.section1.group7.eco_resort.Piya.controller;

import com.summer.section1.group7.eco_resort.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;

public class UpdateProfileController {

    @FXML private TextField nameTF;
    @FXML private TextField usernameTF;
    @FXML private TextField emailTF;
    @FXML private TextField phoneTF;
    @FXML private PasswordField passwordPF;
    @FXML private Label messageLabel;

    private User currentUser;

    public void setCurrentUser(User user) {
        currentUser = user;
        loadUserData();
    }

    // Event-2 + Event-3: Retrieve and display current information
    private void loadUserData() {
        if (currentUser == null) return;

        nameTF.setText(currentUser.getName());
        usernameTF.setText(currentUser.getUsername());
        emailTF.setText(currentUser.getEmail());
        phoneTF.setText(currentUser.getPhoneNumber());
        passwordPF.setText(currentUser.getPassword());
    }

    // Event-4 + Event-5 + Event-6 + Event-8
    @FXML
    public void updateProfileButtonOA(ActionEvent event) {
        if (currentUser == null) {
            messageLabel.setText("User not found.");
            return;
        }

        String email = emailTF.getText().trim();
        String phone = phoneTF.getText().trim();
        String password = passwordPF.getText().trim();

        // Event-5: Validate updated information
        if (email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Please fill all fields.");
            return;
        }

        if (!phone.matches("\\d{11}")) {
            messageLabel.setText("Invalid phone number.");
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            messageLabel.setText("Invalid email.");
            return;
        }

        // Update user information
        currentUser.setPhoneNumber(phone);
        currentUser.setEmail(email);
        currentUser.setPassword(password);

        // Event-6: Save updated profile
        if (saveUser()) {
            // Event-8: Display confirmation
            messageLabel.setText("Profile updated successfully.");
        }
    }

    private boolean saveUser() {
        File file = new File("user.bin");

        try {
            if (!file.exists()) {
                messageLabel.setText("User file not found.");
                return false;
            }

            java.util.ArrayList<User> users = new java.util.ArrayList<>();

            try (ObjectInputStream ois =
                         new ObjectInputStream(new FileInputStream(file))) {

                while (true) {
                    try {
                        User user = (User) ois.readObject();

                        if (user.getUserId().equals(currentUser.getUserId())) {
                            users.add(currentUser);
                        } else {
                            users.add(user);
                        }

                    } catch (EOFException e) {
                        break;
                    }
                }
            }

            try (ObjectOutputStream oos =
                         new ObjectOutputStream(new FileOutputStream(file))) {

                for (User user : users) {
                    oos.writeObject(user);
                }
            }

            return true;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            messageLabel.setText("Failed to save profile.");
            return false;
        }
    }

    // Event-7: Refresh profile data
    @FXML
    public void refreshButtonOA(ActionEvent event) {
        loadUserData();
        messageLabel.setText("Profile refreshed.");
    }

    // Return to Guest Dashboard
    @FXML
    public void backButtonOA(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/summer/section1/group7/eco_resort/Piya/GuestDashboard.fxml"
                    )
            );

            Scene scene = new Scene(loader.load());

            GuestDashboardController controller = loader.getController();
            controller.setCurrentUser(currentUser);

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene()
                    .getWindow();

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            messageLabel.setText("Unable to return to dashboard.");
        }
    }
}

