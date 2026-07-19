package com.summer.section1.group7.eco_resort;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController
{
    @javafx.fxml.FXML
    private TextField usernameField;
    @javafx.fxml.FXML
    private ComboBox<String> userTypeComboBox;
    @javafx.fxml.FXML
    private Button signupButton;
    @javafx.fxml.FXML
    private Button goToLoginButton;
    @javafx.fxml.FXML
    private PasswordField passwordField;
    @javafx.fxml.FXML
    private Label messageLabel;
    @javafx.fxml.FXML
    private PasswordField confirmPasswordField;

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
    public void handleGoToLogin(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/summer/section1/group7/eco_resort/Login.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @javafx.fxml.FXML
    public void handleSignup(ActionEvent actionEvent) {
        String name = usernameField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String role = userTypeComboBox.getSelectionModel().getSelectedItem();

        if (name.isEmpty() || password.isEmpty() || role == null) {
            messageLabel.setText("Please fill all fields");
            return;
        }

        if (!password.equals(confirmPassword)) {
            messageLabel.setText("Passwords do not match");
            return;
        }

        User newUser = new User(name, password, role);
        UserManager.addUser(newUser);

        messageLabel.setText("Signup successful! Go to login.");

        usernameField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
        userTypeComboBox.getSelectionModel().clearSelection();
    }
}