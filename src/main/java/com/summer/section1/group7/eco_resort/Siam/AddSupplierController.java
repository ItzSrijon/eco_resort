package com.summer.section1.group7.eco_resort.Siam;

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

public class AddSupplierController {

    @FXML
    private TextField supplierNameTF;
    @FXML
    private TextField phoneTF;
    @FXML
    private TextField companyNameTF;
    @FXML
    private TextField emailTF;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private TextField supplierIdTF;

    @FXML
    public void initialize() {

        supplierIdTF.setText(SupplierManager.generateSupplierId());
        supplierIdTF.setEditable(false);

    }
    @FXML
    public void backToDashboardOA(ActionEvent actionEvent) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("U4G4_ProcessSupplierPayment.fxml"));
            Node node = loader.load();
            mainPane.getChildren().setAll(node);

        }

        catch (Exception e) {

            e.printStackTrace();

        }

    }
    @FXML
    public void addSupplierOA(ActionEvent actionEvent) {

        if (supplierNameTF.getText().trim().isEmpty() || companyNameTF.getText().trim().isEmpty() || phoneTF.getText().trim().isEmpty() || emailTF.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill all fields.");
            return;

        }

        Supplier supplier = new Supplier(
                supplierIdTF.getText(),
                supplierNameTF.getText().trim(),
                companyNameTF.getText().trim(),
                phoneTF.getText().trim(),
                emailTF.getText().trim());

        File file = new File("supplier.bin");

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

            oos.writeObject(supplier);
            oos.close();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Supplier added successfully.");

            clearFields();

        }

        catch (Exception e) {
            e.printStackTrace();

        }

    }
    private void clearFields() {

        supplierIdTF.setText(SupplierManager.generateSupplierId());
        supplierNameTF.clear();
        companyNameTF.clear();
        phoneTF.clear();
        emailTF.clear();

    }

    private void showAlert(Alert.AlertType type, String title, String message) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}