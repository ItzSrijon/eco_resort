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

public class UpdateProfileController {

    @FXML
    private TextField fullNameTF;

    @FXML
    private PasswordField passwordPF;

    @FXML
    private TextField phoneTF;

    @FXML
    private TextField emailTF;

    @FXML
    private Label messageLabel;

    private final GuestRepository guestRepository = new GuestRepository();

    private Guest currentGuest;

    @FXML
    public void initialize() {

        currentGuest = guestRepository.getCurrentGuest();

        if (currentGuest != null) {

            fullNameTF.setText(currentGuest.getFullName());
            phoneTF.setText(currentGuest.getPhoneNumber());
            emailTF.setText(currentGuest.getEmail());
            passwordPF.setText(currentGuest.getPassword());

        }

    }

    @FXML
    public void updateButtonOA(ActionEvent actionEvent) {

        if (fullNameTF.getText().isEmpty()
                || phoneTF.getText().isEmpty()
                || emailTF.getText().isEmpty()
                || passwordPF.getText().isEmpty()) {

            messageLabel.setText("Please fill all fields.");
            return;

        }

        currentGuest.setFullName(fullNameTF.getText());
        currentGuest.setPhoneNumber(phoneTF.getText());
        currentGuest.setEmail(emailTF.getText());
        currentGuest.setPassword(passwordPF.getText());

        currentGuest.updateProfile();

        messageLabel.setText("Profile Updated Successfully.");

    }

    @FXML
    public void backButtonOA(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/summer/section1/group7/eco_resort/Piya/guestDashboard.fxml"));

        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);

        stage.show();

    }

}