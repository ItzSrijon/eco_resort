package com.summer.section1.group7.eco_resort.Siam;

import com.summer.section1.group7.eco_resort.User;
import com.summer.section1.group7.eco_resort.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class U4G5_GenerateGuestInvoiceController {

    @FXML
    private TextField totalBillTF;
    @FXML
    private TextField guestNameTF;
    @FXML
    private TextField totalPaidTF;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private TextField guestIdTF;
    @FXML
    private TextField duePaymentTF;

    @FXML
    public void initialize() {

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

    @FXML
    public void loadGuestOA(ActionEvent actionEvent) {
        if (guestIdTF.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter Guest ID.");
            return;
        }

        User guest = UserManager.findGuest(guestIdTF.getText().trim());

        if (guest == null) {
            guestNameTF.clear();
            showAlert(Alert.AlertType.ERROR, "Not Found", "Guest not found.");

            return;

        }
        guestNameTF.setText(guest.getName());
        showAlert(Alert.AlertType.INFORMATION, "Success", "Guest loaded successfully.");

    }

    @FXML
    public void generateInvoiceOA(ActionEvent actionEvent) {
        if (guestNameTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Load guest first.");

            return;
        }
        if (totalBillTF.getText().trim().isEmpty() || totalPaidTF.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter Total Bill and Total Paid.");
            return;
        }
        double totalBill = Double.parseDouble(totalBillTF.getText());
        double totalPaid = Double.parseDouble(totalPaidTF.getText());
        double due = totalBill - totalPaid;
        duePaymentTF.setText(String.valueOf(due));
        Invoice invoice = new Invoice(
                guestIdTF.getText().trim(),
                guestNameTF.getText().trim(),
                totalBill,
                totalPaid,
                due
        );
        File file = new File("invoice.bin");

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
            oos.writeObject(invoice);
            oos.close();
        }
        catch (Exception e) {
            e.printStackTrace();

        }
        showAlert(Alert.AlertType.INFORMATION, "Success", "Invoice generated successfully.");
    }

    @FXML

    public void viewInvoiceOA(ActionEvent actionEvent) {

        File file = new File("invoice.bin");

        if (!file.exists()) {
            showAlert(Alert.AlertType.ERROR, "Error", "No invoice found.");
            return;

        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewInvoice.fxml"));
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