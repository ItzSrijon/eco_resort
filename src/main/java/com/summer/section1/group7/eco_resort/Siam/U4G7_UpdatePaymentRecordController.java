package com.summer.section1.group7.eco_resort.Siam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class U4G7_UpdatePaymentRecordController {

    @FXML
    private TextField guestNameTF;
    @FXML
    private TextField transactionIdTF;
    @FXML
    private TextField paymentTypeTF;
    @FXML
    private DatePicker paymentDateDP;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private TextField amountTF;

    private Payment selectedPayment;

    @FXML
    public void initialize() {

    }

    @FXML
    public void searchPaymentOA(ActionEvent actionEvent) {

        selectedPayment = null;

        if (transactionIdTF.getText().trim().isEmpty()) {

            showAlert(Alert.AlertType.ERROR, "Error", "Please enter Transaction ID.");
            return;

        }

        try {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("payment.bin"));

            while (true) {

                try {

                    Payment payment = (Payment) ois.readObject();

                    if (payment.getPaymentId().equalsIgnoreCase(transactionIdTF.getText().trim())) {

                        selectedPayment = payment;
                        break;

                    }
                }

                catch (EOFException e) {

                    break;

                }
            }

            ois.close();

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        if (selectedPayment == null) {

            showAlert(Alert.AlertType.ERROR, "Not Found", "Transaction not found.");
            return;

        }

        guestNameTF.setText(selectedPayment.getGuestName());
        paymentTypeTF.setText(selectedPayment.getPaymentType());
        amountTF.setText(String.valueOf(selectedPayment.getAmount()));
        paymentDateDP.setValue(selectedPayment.getPaymentDate());

        showAlert(Alert.AlertType.INFORMATION, "Success", "Payment loaded successfully.");

    }

    @FXML
    public void updatePaymentOA(ActionEvent actionEvent) {

        if (selectedPayment == null) {

            showAlert(Alert.AlertType.ERROR, "Error", "Search payment first.");
            return;

        }

        ArrayList<Payment> paymentList = new ArrayList<>();

        try {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("payment.bin"));

            while (true) {

                try {

                    Payment payment = (Payment) ois.readObject();
                    if (payment.getPaymentId().equals(selectedPayment.getPaymentId())) {

                        payment.setAmount(Double.parseDouble(amountTF.getText()));
                        payment.setPaymentDate(paymentDateDP.getValue());

                    }

                    paymentList.add(payment);

                }

                catch (EOFException e) {

                    break;

                }

            }

            ois.close();

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        try {

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("payment.bin"));

            for (Payment payment : paymentList) {
                oos.writeObject(payment);

            }

            oos.close();

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        showAlert(Alert.AlertType.INFORMATION, "Success", "Payment updated successfully.");

    }

    @FXML
    public void backToDashboardOA(ActionEvent actionEvent) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountantDashboard.fxml"));
            Node node = loader.load();
            mainPane.getChildren().setAll(node);
        }

        catch (Exception e) {

            e.printStackTrace();

        }

    }

    private void showAlert(Alert.AlertType type, String title, String message) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();

    }

}