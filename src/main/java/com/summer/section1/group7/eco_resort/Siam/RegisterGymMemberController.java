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
import java.io.*;
import java.time.LocalDate;

public class RegisterGymMemberController {
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

        String guestId = guestIdTF.getText().trim();
        if (guestId.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", null, "Please enter Guest ID.");
            return;
        }
        loadedGuest = null;

        for (User user : UserManager.getUserList()) {
            if (user.getUserId().equalsIgnoreCase(guestId)
                    && user.getRole().equalsIgnoreCase("Guest")) {

                loadedGuest = user;
                break;
            }
        }

        if (loadedGuest == null) {
            guestNameTF.clear();
            phoneTF.clear();
            emailTF.clear();
            feeTF.clear();

            showAlert(Alert.AlertType.ERROR, "Not Found", null, "Guest not found.");
            return;
        }
        guestNameTF.setText(loadedGuest.getName());
        phoneTF.setText(loadedGuest.getPhoneNumber());
        emailTF.setText(loadedGuest.getEmail());

        showAlert(Alert.AlertType.INFORMATION, "Success", null, "Guest loaded successfully.");

    }

    @FXML
    public void registerMemberOA(ActionEvent actionEvent) {

        if (loadedGuest == null) {
            showAlert(Alert.AlertType.ERROR, "Error", null, "Load guest first.");
            return;
        }

        if (packageCB.getValue() == null || durationCB.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Error", null, "Select package and duration.");
            return;
        }

        File file = new File("gymMember.bin");

        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                while (true) {
                    try {
                        GymMember gm = (GymMember) ois.readObject();

                        if (gm.getGuestId().equalsIgnoreCase(loadedGuest.getUserId())) {
                            ois.close();
                            showAlert(Alert.AlertType.ERROR, "Already Registered", null, "This guest is already registered."
                            );
                            return;
                        }
                    } catch (EOFException e) {
                        break;
                    }
                }
                ois.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        GymMember member = new GymMember(
                loadedGuest.getUserId(),
                loadedGuest.getName(),
                loadedGuest.getPhoneNumber(),
                loadedGuest.getEmail(),
                packageCB.getValue(),
                durationCB.getValue(),
                "Active",
                LocalDate.now()
        );
        feeTF.setText(String.valueOf(member.getTotalFee()));
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
            showAlert(Alert.AlertType.INFORMATION, "Success", null, "Gym member registered successfully.");
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

    private void showAlert(Alert.AlertType type, String title, String header, String message) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}