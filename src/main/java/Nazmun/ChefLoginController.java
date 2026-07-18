package Nazmun;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ChefLoginController
{
    @javafx.fxml.FXML
    private TextField userIdTF;
    @javafx.fxml.FXML
    private PasswordField passwordTF;
    @javafx.fxml.FXML
    private Label loginErrorLabel;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void loginButtonOA(ActionEvent actionEvent) throws IOException {

        String userId = userIdTF.getText();
        String password = passwordTF.getText();

        if (userId == null || userId.isEmpty()) {
            loginErrorLabel.setText("Please enter your User ID.");
            return;
        }
        if (!userId.matches("\\d+")) {
            loginErrorLabel.setText("User ID must be numeric.");
            return;
        }
        if (password == null || password.length() < 6) {
            loginErrorLabel.setText("Password must be at least 6 characters.");
            return;
        }
        if (!userId.equals("2001") || !password.equals("chef1234")) {
            loginErrorLabel.setText("Invalid User ID or Password.");
            return;
        }

        loginErrorLabel.setText("");

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/summer/section1/group7/eco_resort/Nazmun/ChefDashboard.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}