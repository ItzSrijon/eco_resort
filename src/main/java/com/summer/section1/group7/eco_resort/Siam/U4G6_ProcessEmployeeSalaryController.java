package com.summer.section1.group7.eco_resort.Siam;

import com.summer.section1.group7.eco_resort.User;
import com.summer.section1.group7.eco_resort.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class U4G6_ProcessEmployeeSalaryController {

    @FXML
    private TextField userIdTF;
    @FXML
    private TextField employeeNameTF;
    @FXML
    private TextField salaryTF;
    @FXML
    private DatePicker paymentDateDP;
    @FXML
    private AnchorPane mainPane;

    private User loadedEmployee;

    @FXML
    public void initialize() {

        paymentDateDP.setValue(LocalDate.now());

    }

    @FXML
    public void searchEmployeeOA(ActionEvent actionEvent) {

        loadedEmployee = UserManager.findEmployee(userIdTF.getText().trim());

        if (loadedEmployee == null) {

            employeeNameTF.clear();

            showAlert(Alert.AlertType.ERROR,
                    "Not Found",
                    "Employee not found.");

            return;

        }

        employeeNameTF.setText(loadedEmployee.getName());

        showAlert(Alert.AlertType.INFORMATION,
                "Success",
                "Employee loaded successfully.");

    }

    @FXML
    public void paySalaryOA(ActionEvent actionEvent) {

        if (loadedEmployee == null) {

            showAlert(Alert.AlertType.ERROR,
                    "Error",
                    "Search employee first.");

            return;

        }

        if (salaryTF.getText().trim().isEmpty()) {

            showAlert(Alert.AlertType.ERROR,
                    "Error",
                    "Enter salary amount.");

            return;

        }

        SalaryPayment salaryPayment =
                new SalaryPayment(
                        loadedEmployee.getUserId(),
                        loadedEmployee.getName(),
                        Double.parseDouble(salaryTF.getText()),
                        paymentDateDP.getValue()
                );

        File file = new File("salary.bin");

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

            oos.writeObject(salaryPayment);

            oos.close();

            showAlert(Alert.AlertType.INFORMATION,
                    "Success",
                    "Salary paid successfully.");

            clearFields();

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

    private void clearFields() {

        userIdTF.clear();
        employeeNameTF.clear();
        salaryTF.clear();
        paymentDateDP.setValue(LocalDate.now());

        loadedEmployee = null;

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