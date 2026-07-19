package com.summer.section1.group7.eco_resort.Piya.controller;

import com.summer.section1.group7.eco_resort.Piya.model.Guest;
import com.summer.section1.group7.eco_resort.Piya.repository.GuestRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class GuestLoginController {

    @FXML
    private TextField emailTF;

    @FXML
    private PasswordField passwordPF;

    @FXML
    private Label messageLabel;

    private GuestRepository guestRepository = new GuestRepository();

    @FXML
    public void initialize() {

    }

    @FXML
    public void loginButtonOA(ActionEvent actionEvent) throws IOException {

        String email = emailTF.getText();
        String password = passwordPF.getText();

        if (email.isEmpty() || password.isEmpty()) {

            messageLabel.setText("Please fill up all fields.");
            return;
        }

        Guest guest = guestRepository.login(email, password);

        if (guest == null) {

            messageLabel.setText("Invalid Email or Password.");

        }

        else {

            messageLabel.setText("Login Successful.");

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/summer/section1/group7/eco_resort/Piya/guestDashboard.fxml"));

            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    public void registerButtonOA(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/summer/section1/group7/eco_resort/Piya/guestRegistration.fxml"));

        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);

        stage.show();

    }

}