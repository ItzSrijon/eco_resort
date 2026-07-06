package Piya;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class guestRegistrationController {
    @javafx.fxml.FXML
    private TextField nameTF;
    @javafx.fxml.FXML
    private TextField emailTF;
    @javafx.fxml.FXML
    private TextField phoneNumberTF;
    @javafx.fxml.FXML
    private PasswordField passwordTF;
    @javafx.fxml.FXML
    private Label messageLabel;

    @javafx.fxml.FXML
    public void initialize() {

    }

    @javafx.fxml.FXML
    public void RegisterButtonOA(ActionEvent actionEvent) {
        String fullName = nameTF.getText();
        String password = passwordTF.getText();
        String phoneNumber = phoneNumberTF.getText();
        String email = emailTF.getText();

        if (fullName.isEmpty() || phoneNumber.isEmpty()
                || email.isEmpty() || password.isEmpty()) {

            messageLabel.setText("Please fill in all fields.");

        }
        else if (!phoneNumber.matches("\\d{11}")) {

            messageLabel.setText("Phone number must contain exactly 11 digits.");

        }
        else if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

            messageLabel.setText("Invalid email address.");

        }
        else if (password.length() < 6) {

            messageLabel.setText("Password must be at least 6 characters.");

        }
        else {

            messageLabel.setText("Registration Successful!");

        }
    }

}