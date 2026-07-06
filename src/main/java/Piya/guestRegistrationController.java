package Piya;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class guestRegistrationController
{
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

        if(!phoneNumber.matches("11")){
            messageLabel.setText("Phone Number must contain 11 digits");
        }
        else if(!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")){
            messageLabel.setText("Password must be at least 6 characters.");
            return;
        }
    }

    @javafx.fxml.FXML
    public void clearButtonOA(ActionEvent actionEvent) {
        nameTF.clear();
        passwordTF.clear();
        phoneNumberTF.clear();
        emailTF.clear();
    }
}