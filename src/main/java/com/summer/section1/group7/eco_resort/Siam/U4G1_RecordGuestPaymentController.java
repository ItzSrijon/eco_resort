package com.summer.section1.group7.eco_resort.Siam;

import com.summer.section1.group7.eco_resort.User;
import com.summer.section1.group7.eco_resort.UserManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.time.LocalDate;

public class U4G1_RecordGuestPaymentController {

    @FXML
    private TextField guestIdTF;
    @FXML
    private TextField guestNameTF;
    @FXML
    private TextField amountTF;
    @FXML
    private TextField paymentDateTF;

    @FXML
    private ComboBox<String> paymentTypeCB;
    @FXML
    private ComboBox<String> paymentMethodCB;

    private GymMember loadedGymMember;
    @FXML
    private AnchorPane mainPane;

    @FXML
    public void initialize() {

        paymentTypeCB.setItems(
                FXCollections.observableArrayList(
                        "Gym",
                        "Room",
                        "Restaurant",
                        "Spa",
                        "Laundry"
                )
        );

        paymentMethodCB.setItems(
                FXCollections.observableArrayList(
                        "Cash",
                        "Card",
                        "Bkash",
                        "Nagad",
                        "Bank Transfer"
                )
        );

        paymentDateTF.setText(LocalDate.now().toString());

    }

    @FXML
    public void searchGuestOA(ActionEvent actionEvent) {

        guestNameTF.clear();
        amountTF.clear();
        loadedGymMember = null;

        String guestId = guestIdTF.getText().trim();

        if (guestId.isEmpty()) {

            showAlert(Alert.AlertType.ERROR,
                    "Error",
                    "Please enter Guest ID.");

            return;
        }

        User loadedGuest = null;

        for (User user : UserManager.getUserList()) {

            if (user.getUserId().equalsIgnoreCase(guestId)
                    && user.getRole().equalsIgnoreCase("Guest")) {

                loadedGuest = user;
                break;
            }
        }

        if (loadedGuest == null) {

            showAlert(Alert.AlertType.ERROR,
                    "Error",
                    "Guest not found.");

            return;
        }

        guestNameTF.setText(loadedGuest.getName());
        paymentDateTF.setText(LocalDate.now().toString());

        // এখন Gym Member খুঁজবে
        try {

            ObjectInputStream ois =
                    new ObjectInputStream(
                            new FileInputStream("gymMember.bin"));

            while (true) {

                try {

                    GymMember gm = (GymMember) ois.readObject();

                    if (gm.getGuestId().equalsIgnoreCase(guestId)) {

                        loadedGymMember = gm;
                        break;
                    }

                } catch (EOFException e) {
                    break;
                }

            }

            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        showAlert(Alert.AlertType.INFORMATION,
                "Success",
                "Guest loaded successfully.");
    }
    @FXML
    public void recordPaymentOA(ActionEvent actionEvent) {

        if (guestNameTF.getText().isEmpty()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Error",
                    "Please search a guest first."
            );

            return;

        }

        if (paymentTypeCB.getValue() == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Error",
                    "Please select payment type."
            );

            return;

        }

        if (paymentMethodCB.getValue() == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Error",
                    "Please select payment method."
            );

            return;

        }

        double amount;

        if (paymentTypeCB.getValue().equals("Gym")) {

            if (loadedGymMember == null) {

                showAlert(
                        Alert.AlertType.ERROR,
                        "Error",
                        "This guest is not a gym member."
                );

                return;

            }

            amount = loadedGymMember.getTotalFee();

            amountTF.setText(String.valueOf(amount));
            amountTF.setEditable(false);

        }

        else {

            if (amountTF.getText().isEmpty()) {

                showAlert(
                        Alert.AlertType.ERROR,
                        "Error",
                        "Please enter amount."
                );

                return;

            }

            amount = Double.parseDouble(amountTF.getText());

        }

        Payment payment = new Payment(
                "P" + System.currentTimeMillis(),   // paymentId
                guestIdTF.getText(),
                guestNameTF.getText(),
                paymentTypeCB.getValue(),
                paymentMethodCB.getValue(),
                amount,
                LocalDate.now()
        );

        try {

            File file = new File("payment.bin");

            ObjectOutputStream oos;

            if (file.exists()) {

                oos = new AppendableObjectOutputStream(
                        new FileOutputStream(file, true));

            }

            else {

                oos = new ObjectOutputStream(
                        new FileOutputStream(file));

            }

            oos.writeObject(payment);

            oos.close();

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Success",
                    "Payment recorded successfully."
            );

            clearFields();

        }

        catch (Exception e) {

            e.printStackTrace();

        }

    }

    private void clearFields() {

        guestIdTF.clear();
        guestNameTF.clear();
        amountTF.clear();

        paymentTypeCB.getSelectionModel().clearSelection();
        paymentMethodCB.getSelectionModel().clearSelection();

        paymentDateTF.setText(LocalDate.now().toString());

        amountTF.setEditable(true);

        loadedGymMember = null;

    }
    private void showAlert(Alert.AlertType type,
                           String title,
                           String message) {

        Alert alert = new Alert(type);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();

    }
    @FXML
    public void backButtonOA(ActionEvent actionEvent) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("AccountantDashboard.fxml"));

            Node node = loader.load();

            mainPane.getChildren().setAll(node);

        }

        catch (Exception e) {

            e.printStackTrace();

        }

    }

    @FXML
    public void paymentTypeOA(ActionEvent actionEvent) {

        if (paymentTypeCB.getValue() == null) {
            return;
        }

        if (paymentTypeCB.getValue().equals("Gym")) {

            if (loadedGymMember != null) {

                amountTF.setText(
                        String.valueOf(
                                loadedGymMember.getTotalFee()
                        )
                );

                amountTF.setEditable(false);

            }

        }
        else {

            amountTF.clear();
            amountTF.setEditable(true);

        }

    }
}