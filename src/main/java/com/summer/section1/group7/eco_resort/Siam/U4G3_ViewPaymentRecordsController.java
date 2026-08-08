package com.summer.section1.group7.eco_resort.Siam;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;

public class U4G3_ViewPaymentRecordsController {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private DatePicker fromDateDP;
    @FXML
    private DatePicker toDateDP;
    @FXML
    private TableView<Payment> paymentTV;
    @FXML
    private TableColumn<Payment,String> paymentIdTC;
    @FXML
    private TableColumn<Payment,String> guestIdTC;
    @FXML
    private TableColumn<Payment,String> guestNameTC;
    @FXML
    private TableColumn<Payment,Double> amountTC;
    @FXML
    private TableColumn<Payment,String> paymentMethodTC;
    @FXML
    private TableColumn<Payment,LocalDate> paymentDateTC;
    ObservableList<Payment> paymentList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        paymentIdTC.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        guestIdTC.setCellValueFactory(new PropertyValueFactory<>("guestId"));
        guestNameTC.setCellValueFactory(new PropertyValueFactory<>("guestName"));
        amountTC.setCellValueFactory(new PropertyValueFactory<>("amount"));
        paymentMethodTC.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        paymentDateTC.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));

    }
    @FXML
    public void loadPaymentsOA(ActionEvent actionEvent) {

        paymentList.clear();

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("payment.bin"));

            while (true) {

                try {

                    Payment p = (Payment) ois.readObject();

                    if (fromDateDP.getValue() != null && p.getPaymentDate().isBefore(fromDateDP.getValue())) {
                        continue;
                    }

                    if (toDateDP.getValue() != null && p.getPaymentDate().isAfter(toDateDP.getValue())) {
                        continue;
                    }
                    paymentList.add(p);

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

        paymentTV.setItems(paymentList);

    }

    @FXML
    public void backButtonOA(ActionEvent actionEvent) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountantDashboard.fxml"));
            Node node = loader.load();
            mainPane.getChildren().setAll(node);

        }

        catch (Exception e) {

            e.printStackTrace();

        }
    }
}