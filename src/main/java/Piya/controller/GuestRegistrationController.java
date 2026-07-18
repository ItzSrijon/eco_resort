package Piya.controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import Piya.model.Guest;
import Piya.repository.GuestRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class GuestRegistrationController {

    @FXML
    private TextField userIdTF;

    @FXML
    private TextField fullNameTF;

    @FXML
    private TextField phoneTF;

    @FXML
    private TextField emailTF;

    @FXML
    private PasswordField passwordPF;

    @FXML
    private Label messageLabel;

    private final GuestRepository guestRepository = new GuestRepository();

    @FXML
    public void registerButtonOA(ActionEvent actionEvent) {

        String idText = userIdTF.getText();
        String fullName = fullNameTF.getText();
        String phone = phoneTF.getText();
        String email = emailTF.getText();
        String password = passwordPF.getText();

        if (idText.isEmpty() || fullName.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {

            messageLabel.setText("Please fill out all the fields.");
            return;
        }

        int userId;

        try {

            userId = Integer.parseInt(idText);

        }

        catch (NumberFormatException e) {

            messageLabel.setText("User ID must be a number.");
            return;

        }

        Guest guest = new Guest(
                userId,
                fullName,
                phone,
                email,
                password
        );

        guestRepository.addGuest(guest);

        messageLabel.setText("Guest Registered Successfully.");

        userIdTF.clear();
        fullNameTF.clear();
        phoneTF.clear();
        emailTF.clear();
        passwordPF.clear();

    }

    @FXML
    public void loginButtonOA(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/summer/section1/group7/eco_resort/Piya/guestLogin.fxml"));

        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);

        stage.show();

    }

}
