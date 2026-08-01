package com.summer.section1.group7.eco_resort.Siam;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class U4G3_ViewPaymentRecordsController
{
    @javafx.fxml.FXML
    private TableColumn guestNameTC;
    @javafx.fxml.FXML
    private TableColumn amountTC;
    @javafx.fxml.FXML
    private TableColumn paymentMethodTC;
    @javafx.fxml.FXML
    private DatePicker toDateDP;
    @javafx.fxml.FXML
    private DatePicker fromDateDP;
    @javafx.fxml.FXML
    private TableColumn guestIdTC;
    @javafx.fxml.FXML
    private TableColumn paymentDateTC;
    @javafx.fxml.FXML
    private TableColumn paymentIdTC;
    @javafx.fxml.FXML
    private TableColumn invoiceIdTC;
    @javafx.fxml.FXML
    private TableView paymentTV;
    @javafx.fxml.FXML
    private AnchorPane mainPane;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("AccountantDashboard.fxml"));
            Node node=fxmlLoader.load();
            mainPane.getChildren().setAll(node);
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }

    @javafx.fxml.FXML
    public void loadPaymentsOA(ActionEvent actionEvent) {
    }
}