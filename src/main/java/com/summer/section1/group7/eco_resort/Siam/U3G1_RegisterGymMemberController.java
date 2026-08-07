package com.summer.section1.group7.eco_resort.Siam;
import com.summer.section1.group7.eco_resort.User;
import com.summer.section1.group7.eco_resort.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class U3G1_RegisterGymMemberController {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private TextField guestIdTF;
    @FXML
    private TextField guestNameTF;
    @FXML
    private TextField phoneTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField feeTF;
    @FXML
    private ComboBox<String> packageCB;
    @FXML
    private ComboBox<Integer> durationCB;
    private User loadedGuest;
    @FXML
    public void initialize() {
        packageCB.getItems().addAll("Basic", "Premium", "VIP");
        durationCB.getItems().addAll(2, 3, 4, 5, 6, 7);
    }
    @FXML
    public void backToDashboardOA(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GymManagerDashboard.fxml"));
            Node node = loader.load();
            mainPane.getChildren().setAll(node);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void loadGuestOA(ActionEvent actionEvent) {
        loadedGuest = UserManager.findGuest(guestIdTF.getText().trim());

        if (loadedGuest == null) {
            clearGuestInfo();
            showAlert(Alert.AlertType.ERROR, "Not Found", "Guest not found.");
            return;
        }

        guestNameTF.setText(loadedGuest.getName());
        phoneTF.setText(loadedGuest.getPhoneNumber());
        emailTF.setText(loadedGuest.getEmail());
        showAlert(Alert.AlertType.INFORMATION, "Success", "Guest loaded successfully.");

    }

    @FXML
    public void registerMemberOA(ActionEvent actionEvent) {
        if (loadedGuest == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Load guest first.");
            return;
        }
        if (packageCB.getValue() == null || durationCB.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Select package and duration.");
            return;
        }
        if (GymManager.findGymMember(loadedGuest.getUserId()) != null) {
            showAlert(Alert.AlertType.ERROR, "Already Registered", "This guest is already registered.");
            return;
        }

        GymMember member = new GymMember(
                loadedGuest.getUserId(),
                loadedGuest.getName(),
                loadedGuest.getPhoneNumber(),
                loadedGuest.getEmail(),
                packageCB.getValue().toString(),
                (Integer) durationCB.getValue(),
                "Active",
                LocalDate.now());

        feeTF.setText(String.valueOf(member.getTotalFee()));
        File file = new File("gymMember.bin");
        try {
            FileOutputStream fos;
            ObjectOutputStream oos;
            if (file.exists()) {
                fos = new FileOutputStream(file, true);
                oos = new AppendableObjectOutputStream(fos);
            }
            else {
                fos = new FileOutputStream(file);
                oos = new ObjectOutputStream(fos);
            }
            oos.writeObject(member);
            oos.close();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Gym member registered successfully.");
            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void clearFields() {
        guestIdTF.clear();
        guestNameTF.clear();
        phoneTF.clear();
        emailTF.clear();
        feeTF.clear();
        packageCB.getSelectionModel().clearSelection();
        durationCB.getSelectionModel().clearSelection();
        loadedGuest = null;
    }
    private void clearGuestInfo() {
        guestNameTF.clear();
        phoneTF.clear();
        emailTF.clear();
        feeTF.clear();
    }
    private void showAlert(Alert.AlertType type, String title,String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}