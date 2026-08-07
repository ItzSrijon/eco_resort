package com.summer.section1.group7.eco_resort.Siam;

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

public class U4G4_SupplierPaymentController {

    @FXML
    private TextField supplierIdTF;
    @FXML
    private TextField supplierNameTF;
    @FXML
    private TextField amountTF;
    @FXML
    private ComboBox<String> paymentMethodCB;
    @FXML
    private DatePicker paymentDateDP;
    @FXML
    private AnchorPane mainPane;

    private Supplier loadedSupplier;

    @FXML
    public void initialize() {

        paymentMethodCB.getItems().addAll(
                "Cash",
                "Card",
                "Bkash",
                "Nagad",
                "Bank Transfer"
        );

        paymentDateDP.setValue(LocalDate.now());

    }

    @FXML
    public void loadSupplierOA(ActionEvent actionEvent) {

        if (supplierIdTF.getText().trim().isEmpty()) {

            showAlert(Alert.AlertType.ERROR,
                    "Error",
                    "Please enter Supplier ID.");

            return;

        }

        loadedSupplier =
                SupplierManager.findSupplier(
                        supplierIdTF.getText().trim());

        if (loadedSupplier == null) {

            clearFields();

            showAlert(Alert.AlertType.ERROR,
                    "Not Found",
                    "Supplier not found.");

            return;

        }

        supplierNameTF.setText(
                loadedSupplier.getSupplierName());

        showAlert(Alert.AlertType.INFORMATION,
                "Success",
                "Supplier loaded successfully.");

    }

    @FXML
    public void recordPaymentOA(ActionEvent actionEvent) {

        if (loadedSupplier == null) {

            showAlert(Alert.AlertType.ERROR,
                    "Error",
                    "Load supplier first.");

            return;

        }

        if (amountTF.getText().trim().isEmpty()
                || paymentMethodCB.getValue() == null
                || paymentDateDP.getValue() == null) {

            showAlert(Alert.AlertType.ERROR,
                    "Error",
                    "Please fill all fields.");

            return;

        }

        Payment payment = new Payment(

                "P-" + System.currentTimeMillis(),

                loadedSupplier.getSupplierId(),

                loadedSupplier.getSupplierName(),

                "Supplier",

                paymentMethodCB.getValue(),

                Double.parseDouble(amountTF.getText()),

                paymentDateDP.getValue()

        );

        File file = new File("payment.bin");

        try {

            FileOutputStream fos;
            ObjectOutputStream oos;

            if (file.exists()) {

                fos = new FileOutputStream(file, true);
                oos = new AppendableObjectOutputStream(fos);

            } else {

                fos = new FileOutputStream(file);
                oos = new ObjectOutputStream(fos);

            }

            oos.writeObject(payment);
            oos.close();

            showAlert(Alert.AlertType.INFORMATION,
                    "Success",
                    "Supplier payment recorded successfully.");

            clearFields();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    private void clearFields() {

        supplierIdTF.clear();
        supplierNameTF.clear();
        amountTF.clear();

        paymentMethodCB.getSelectionModel().clearSelection();

        paymentDateDP.setValue(LocalDate.now());

        loadedSupplier = null;

    }
    @FXML
    public void addSupplierOA(ActionEvent actionEvent) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("AddSupplier.fxml"));

            Node node = loader.load();

            mainPane.getChildren().setAll(node);

        }

        catch (Exception e) {

            e.printStackTrace();

        }

    }

    private void showAlert(Alert.AlertType type,
                           String title,
                           String message) {

        Alert alert = new Alert(type);

        alert.setTitle(title);
        alert.setContentText(message);

        alert.showAndWait();

    }
    @FXML
    public void backToDashboardOA(ActionEvent actionEvent) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("AccountantDashboard.fxml"));

            Node node = loader.load();

            mainPane.getChildren().setAll(node);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}