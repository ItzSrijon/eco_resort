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
import java.net.URL;

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
    private Button loginButton1;

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

    @Deprecated
    public void handleLogin(ActionEvent actionEvent) {
        String username = usernameField.getText() == null ? "" : usernameField.getText().trim();
        String password = passwordField.getText() == null ? "" : passwordField.getText();
        String selectedRole = userTypeComboBox.getSelectionModel().getSelectedItem();

        if (username.isEmpty() || password.isEmpty()) {
            showMessage("Username or Password cannot be empty");
            return;
        }

        if (selectedRole == null) {
            showMessage("Please select a role");
            return;
        }

        User user = UserManager.checkLogIn(username, password);

        if (user != null) {
            if (user.getRole().equals(selectedRole)) {
                UserManager.setLoggedInUser(user);
                showMessage("Login successful");
                try {
                    openDashboard(user.getRole(), actionEvent);
                } catch (IOException e) {
                    showMessage("Failed to open dashboard.");
                    e.printStackTrace();
                }
            } else {
                showMessage("Role mismatch for this user.");
            }
        } else {
            showMessage("Invalid username or password.");
        }
    }

    @javafx.fxml.FXML
    public void loginButtonOA(ActionEvent event) {
        handleLogin(event);
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
        } else if (role.equals("Gym Manager")) {
            fxmlFile = "/com/summer/section1/group7/eco_resort/Siam/GymManagerDashboard.fxml";
        } else if (role.equals("Accountant")) {
            fxmlFile = "/com/summer/section1/group7/eco_resort/Siam/AccountantDashboard.fxml";
        } else if (role.equals("Receptionist")) {
            fxmlFile = "/com/summer/section1/group7/eco_resort/Srijon/Receptionist/receptionistDashboard.fxml";
        } else if (role.equals("Maintenance Officer")) {
            fxmlFile = "/com/summer/section1/group7/eco_resort/Srijon/Maintenance/maintenanceDashboard.fxml";
        }

        if (fxmlFile != null) {
            URL url = LoginController.class.getResource(fxmlFile);
            System.out.println("Opening dashboard FXML URL = " + url);
            if (url == null) {
                showMessage("Dashboard resource not found: " + fxmlFile);
                throw new IOException("FXML not found: " + fxmlFile);
            }

            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            showMessage("Dashboard for this role is not yet implemented.");
        }
    }

    @javafx.fxml.FXML
    public void createNewAccountOA(ActionEvent actionEvent) {

        handleGoToSignup(actionEvent);
    }

    @Deprecated
    public void handleGoToSignup(ActionEvent actionEvent) {
        try {
            URL url = getClass().getResource("/com/summer/section1/group7/eco_resort/SignUp.fxml");
            System.out.println("SignUp.fxml URL = " + url);
            if (url == null) {
                showMessage("Sign-up resource not found.");
                return;
            }
            Parent root = FXMLLoader.load(url);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showMessage("Error loading the sign-up page.");
            e.printStackTrace();
        }
    }

    // Helper to safely show messages without risking NPE
    private void showMessage(String text) {
        if (messageLabel != null) {
            messageLabel.setText(text);
        } else {
            // fallback: print to console so you still see feedback during debugging
            System.out.println("MESSAGE: " + text);
        }
    }

    @javafx.fxml.FXML
    public void createGuestAccountButton(ActionEvent actionEvent) {
        handleGoToSignup(actionEvent);
    }
}
