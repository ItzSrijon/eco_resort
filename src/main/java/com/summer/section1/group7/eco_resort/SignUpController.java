package com.summer.section1.group7.eco_resort;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class SignUpController {

    @FXML
    private ComboBox<String> userTypeComboBox;
    @FXML
    private TextField phoneTF;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField emailTF;
    @FXML
    private ComboBox<String> genderCB;
    @FXML
    private DatePicker dobDP;
    @FXML
    private PasswordField passwordPF;
    @FXML
    private TextField userNameTF;
    @FXML
    private PasswordField confirmPasswordPF;

    @FXML
    public void initialize() {

        userTypeComboBox.getItems().addAll(
                "Receptionist",
                "Maintenance Officer",
                "Gym Manager",
                "Accountant",
                "Guest",
                "Security Officer",
                "Manager",
                "Chef"
        );

        genderCB.getItems().addAll(
                "Male",
                "Female",
                "Other"
        );
    }

    @FXML
    public void createAccountOA(ActionEvent actionEvent) {

        String name = nameTF.getText().trim();
        String username = userNameTF.getText().trim();
        String phone = phoneTF.getText().trim();
        String email = emailTF.getText().trim();
        String gender = genderCB.getValue();
        LocalDate dob = dobDP.getValue();
        String password = passwordPF.getText();
        String confirmPassword = confirmPasswordPF.getText();
        String role = userTypeComboBox.getValue();

        // Validation

        if (name.isEmpty() || username.isEmpty() || phone.isEmpty() || email.isEmpty() || gender == null || dob == null || password.isEmpty() || confirmPassword.isEmpty() || role == null) {

            showAlert(Alert.AlertType.ERROR, "Missing Information", null, "Please fill all fields."
            );
            return;
        }

        if (!phone.matches("\\d{11}")) {
            showAlert(Alert.AlertType.ERROR, "Invalid Phone Number", null, "Phone number must be 11 digits.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Password Mismatch", null, "Passwords do not match.");
            return;
        }

        if (UserManager.usernameExists(username)) {
            showAlert(Alert.AlertType.WARNING, "Duplicate Username", null, "Username already exists.");
            return;
        }
        if (!email.contains("@")) {
            showAlert(Alert.AlertType.ERROR, "Invalid Email", null, "Please enter a valid email address.");
            return;
        }

        String userId = UserManager.generateUserId();

        User newUser = new User(
                userId,
                username,
                name,
                phone,
                email,
                gender,
                password,
                role,
                dob
        );

        UserManager.addUser(newUser);

        showAlert(
                Alert.AlertType.INFORMATION,
                "Registration Successful",
                "Account Created Successfully",
                "Your User ID: " + userId +
                        "\n\nPlease remember this User ID.");

        clearFields();
    }

    @FXML
    public void backToLoginOA(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/com/summer/section1/group7/eco_resort/Login.fxml"));

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }

    private void clearFields() {

        nameTF.clear();
        userNameTF.clear();
        phoneTF.clear();
        emailTF.clear();
        passwordPF.clear();
        confirmPasswordPF.clear();
        dobDP.setValue(null);
        genderCB.getSelectionModel().clearSelection();
        genderCB.setValue(null);
        userTypeComboBox.getSelectionModel().clearSelection();
        userTypeComboBox.setValue(null);

    }
    private void showAlert(Alert.AlertType type,
                           String title,
                           String header,
                           String message) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}