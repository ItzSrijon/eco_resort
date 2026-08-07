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

public class U4G2_GenerateFinancialSummaryController {

    @FXML
    private TextField supplierPaymentTF;
    @FXML
    private TextField guestPaymentTF;
    @FXML
    private TextField profitTF;
    @FXML
    private TextField transactionTF;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private TextField employeeSalaryTF;
    @FXML
    private TextField incomeTF;
    @FXML
    private TextField expenseTF;

    private FinancialSummary summary;

    @FXML
    public void initialize() {

        incomeTF.setEditable(false);
        expenseTF.setEditable(false);
        profitTF.setEditable(false);

    }

    @FXML
    public void generateReportOA(ActionEvent actionEvent) {

        if (guestPaymentTF.getText().trim().isEmpty()
                || supplierPaymentTF.getText().trim().isEmpty()
                || employeeSalaryTF.getText().trim().isEmpty()
                || transactionTF.getText().trim().isEmpty()) {

            showAlert(Alert.AlertType.ERROR,
                    "Error",
                    "Please fill all fields.");

            return;

        }

        double guestPayment =
                Double.parseDouble(guestPaymentTF.getText());

        double supplierPayment =
                Double.parseDouble(supplierPaymentTF.getText());

        double employeeSalary =
                Double.parseDouble(employeeSalaryTF.getText());

        int totalTransaction =
                Integer.parseInt(transactionTF.getText());

        double income = guestPayment;

        double expense = supplierPayment + employeeSalary;

        double profit = income - expense;

        incomeTF.setText(String.valueOf(income));
        expenseTF.setText(String.valueOf(expense));
        profitTF.setText(String.valueOf(profit));

        summary = new FinancialSummary(
                guestPayment,
                supplierPayment,
                employeeSalary,
                income,
                expense,
                profit,
                totalTransaction
        );

        showAlert(Alert.AlertType.INFORMATION,
                "Success",
                "Financial Summary Generated.");

    }

    @FXML
    public void saveReportOA(ActionEvent actionEvent) {

        if (summary == null) {

            showAlert(Alert.AlertType.ERROR,
                    "Error",
                    "Generate report first.");

            return;

        }

        File file = new File("financialSummary.bin");

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

            oos.writeObject(summary);

            oos.close();

            showAlert(Alert.AlertType.INFORMATION,
                    "Success",
                    "Report saved successfully.");

        }

        catch (Exception e) {

            e.printStackTrace();

        }

    }

    @FXML
    public void viewReportOA(ActionEvent actionEvent) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("financialSummaryTableView.fxml"));

            Node node = loader.load();

            mainPane.getChildren().setAll(node);

        }

        catch (Exception e) {

            e.printStackTrace();

        }

    }

    @FXML
    public void backToDashboardOA(ActionEvent actionEvent) {

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

    private void showAlert(Alert.AlertType type,
                           String title,
                           String message) {

        Alert alert = new Alert(type);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();

    }

}