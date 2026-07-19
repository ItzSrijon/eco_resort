package com.summer.section1.group7.eco_resort;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @javafx.fxml.FXML
    private ComboBox<String> userTypeComboBox;
    @javafx.fxml.FXML
    private TextField usernameField;
    @javafx.fxml.FXML
    private PasswordField passwordField;
    @javafx.fxml.FXML
    private Button loginButton;
    @javafx.fxml.FXML
    private Label messageLabel;
    @javafx.fxml.FXML
    private Button goToSignupButton;

    @javafx.fxml.FXML
    public void initialize() {
        userTypeComboBox.getItems().addAll(
                "Receptionist",
                "Maintenance Officer",
                "Gym Manager",
                "Accountant",
                "Guest",
                "Security Officer",
                "Manager",
                "Chef");
    }

    @javafx.fxml.FXML
    public void handleLogin(ActionEvent actionEvent) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        String selectedRole = userTypeComboBox.getSelectionModel().getSelectedItem();

        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Username or Password cannot be empty");
            return;
        }

        if (selectedRole == null) {
            messageLabel.setText("Please select a role");
            return;
        }

        User user = UserManager.checkLogIn(username, password);

        if (user != null) {
            if (user.getRole().equals(selectedRole)) {
                UserManager.setLoggedInUser(user);
                messageLabel.setText("Login successful");
                try {
                    openDashboard(user.getRole(), actionEvent);
                } catch (IOException e) {
                    messageLabel.setText("Failed to open dashboard.");
                    e.printStackTrace();
                }
            } else {
                messageLabel.setText("Role mismatch for this user.");
            }
        } else {
            messageLabel.setText("Invalid username or password.");
        }
    }

    private void openDashboard(String role, ActionEvent event) throws IOException {
        String fxmlFile = null;

        if (role.equals("Chef")) {
            fxmlFile = "/com/summer/section1/group7/eco_resort/Nazmun/ChefDashboard.fxml";
        } else if (role.equals("Manager")) {
            fxmlFile = "/com/summer/section1/group7/eco_resort/Nazmun/ManagerDashboard.fxml";
        } else if (role.equals("Guest")) {
            fxmlFile = "/com/summer/section1/group7/eco_resort/Piya/guestDashboard.fxml";
        } else if (role.equals("Security Officer")) {
            fxmlFile = "/com/summer/section1/group7/eco_resort/Piya/securityDashboard.fxml";
        }
        // Receptionist, Maintenance Officer, Gym Manager, Accountant:
        // dashboards not found yet — falls through to "not yet implemented" below

        if (fxmlFile != null) {
            FXMLLoader loader = new FXMLLoader(LoginController.class.getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            messageLabel.setText("Dashboard for this role is not yet implemented.");
        }
    }

    @javafx.fxml.FXML
    public void handleGoToSignup(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/summer/section1/group7/eco_resort/SignUp.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            messageLabel.setText("Error loading the sign-up page.");
            e.printStackTrace();
        }
    }
}