package com.summer.section1.group7.eco_resort.Siam;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ViewFinancialSummaryController {

    @FXML
    private TableColumn<FinancialSummary, Double> employeeSalaryTC;
    @FXML
    private TableColumn<FinancialSummary, Double> profitTC;
    @FXML
    private TableView<FinancialSummary> summaryTV;
    @FXML
    private TableColumn<FinancialSummary, Double> expenseTC;
    @FXML
    private TableColumn<FinancialSummary, Double> supplierPaymentTC;
    @FXML
    private TableColumn<FinancialSummary, Integer> transactionTC;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private TableColumn<FinancialSummary, Double> guestPaymentTC;
    @FXML
    private TableColumn<FinancialSummary, Double> incomeTC;

    @FXML
    public void initialize() {

        guestPaymentTC.setCellValueFactory(new PropertyValueFactory<>("guestPayment"));
        supplierPaymentTC.setCellValueFactory(new PropertyValueFactory<>("supplierPayment"));
        employeeSalaryTC.setCellValueFactory(new PropertyValueFactory<>("employeeSalary"));
        incomeTC.setCellValueFactory(new PropertyValueFactory<>("totalIncome"));
        expenseTC.setCellValueFactory(new PropertyValueFactory<>("totalExpense"));
        profitTC.setCellValueFactory(new PropertyValueFactory<>("profit"));
        transactionTC.setCellValueFactory(new PropertyValueFactory<>("totalTransaction"));

    }

    @FXML
    public void loadReportOA(ActionEvent actionEvent) {

        ObservableList<FinancialSummary> list = FXCollections.observableArrayList();

        try {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("financialSummary.bin"));

            while (true) {

                try {

                    FinancialSummary summary = (FinancialSummary) ois.readObject();
                    list.add(summary);

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

        summaryTV.setItems(list);

    }

    @FXML
    public void backOA(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("U4G2_FinancialSummary.fxml"));
            Node node = loader.load();
            mainPane.getChildren().setAll(node);
        }

        catch (Exception e) {
            e.printStackTrace();

        }
    }
}