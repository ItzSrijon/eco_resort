package com.summer.section1.group7.eco_resort;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private ComboBox<String> userTypeComboBox;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

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

    }

    @FXML
    public void loginButtonOA(ActionEvent actionEvent) {

        String input = usernameField.getText().trim();
        String password = passwordField.getText();
        String role = userTypeComboBox.getValue();

        if (input.isEmpty() || password.isEmpty() || role == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Missing Information",
                    null,
                    "Please fill all fields."
            );
            return;
        }

        User user = UserManager.checkLogIn(input, password);

        if (user == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Login Failed",
                    null,
                    "Invalid Username/User ID or Password."
            );
            return;
        }

        if (!user.getRole().equals(role)) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Role Mismatch",
                    null,
                    "Selected role does not match this account."
            );
            return;
        }

        UserManager.setLoggedInUser(user);

        try {
            openDashboard(user.getRole(), actionEvent);
        }
        catch (IOException e) {

            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Error",
                    null,
                    "Unable to open dashboard."
            );
        }

    }

    @FXML
    public void createNewAccountOA(ActionEvent actionEvent) {

        try {

            Parent root = FXMLLoader.load(
                    getClass().getResource(
                            "/com/summer/section1/group7/eco_resort/SignUp.fxml"
                    ));

            Stage stage = (Stage) ((Node) actionEvent.getSource())
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void openDashboard(String role, ActionEvent event) throws IOException {

        String fxmlFile = null;

        switch (role) {

            case "Chef":
                fxmlFile = "/com/summer/section1/group7/eco_resort/Nazmun/ChefDashboard.fxml";
                break;

            case "Manager":
                fxmlFile = "/com/summer/section1/group7/eco_resort/Nazmun/ManagerDashboard.fxml";
                break;

            case "Guest":
                fxmlFile = "/com/summer/section1/group7/eco_resort/Piya/guestDashboard.fxml";
                break;

            case "Security Officer":
                fxmlFile = "/com/summer/section1/group7/eco_resort/Piya/securityDashboard.fxml";
                break;

            case "Gym Manager":
                fxmlFile = "/com/summer/section1/group7/eco_resort/Siam/GymManagerDashboard.fxml";
                break;

            case "Accountant":
                fxmlFile = "/com/summer/section1/group7/eco_resort/Siam/AccountantDashboard.fxml";
                break;

            case "Receptionist":
                fxmlFile = "/com/summer/section1/group7/eco_resort/Srijon/ReceptionistDashboard.fxml";
                break;

            case "Maintenance Officer":
                fxmlFile = "/com/summer/section1/group7/eco_resort/Srijon/MaintenanceDashboard.fxml";
                break;

        }

        if (fxmlFile == null) {

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Dashboard",
                    null,
                    "Dashboard for this role is not available."
            );
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));

        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.setScene(new Scene(root));
        stage.show();

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