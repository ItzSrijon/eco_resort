package com.summer.section1.group7.eco_resort.Siam;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class AccountantDashboardController
{
    @javafx.fxml.FXML
    private AnchorPane mainPane;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void viewPaymentRecordsOA(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("U4G1_RecordGuestPayment.fxml"));
            Node node=fxmlLoader.load();
            mainPane.getChildren().setAll(node);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    @javafx.fxml.FXML
    public void generateReceiptOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void pendingPaymentsOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void paymentMethodsOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void manageRefundOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void backToLoginOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void financialSummaryOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void revenueReportOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void recordPaymentOA(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("U4G1_RecordGuestPayment.fxml"));
            Node node=fxmlLoader.load();
            mainPane.getChildren().setAll(node);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}