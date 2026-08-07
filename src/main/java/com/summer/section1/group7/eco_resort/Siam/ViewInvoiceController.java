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

public class ViewInvoiceController {

    @FXML
    private TableColumn<Invoice, String> guestIdTC;
    @FXML
    private TableColumn<Invoice, String> guestNameTC;
    @FXML
    private TableColumn<Invoice, Double> totalBillTC;
    @FXML
    private TableColumn<Invoice, Double> totalPaidTC;
    @FXML
    private TableColumn<Invoice, Double> duePaymentTC;
    @FXML
    private TableView<Invoice> invoiceTV;
    @FXML
    private AnchorPane mainPane;

    @FXML
    public void initialize() {

        guestIdTC.setCellValueFactory(new PropertyValueFactory<>("guestId"));
        guestNameTC.setCellValueFactory(new PropertyValueFactory<>("guestName"));
        totalBillTC.setCellValueFactory(new PropertyValueFactory<>("totalBill"));
        totalPaidTC.setCellValueFactory(new PropertyValueFactory<>("totalPaid"));
        duePaymentTC.setCellValueFactory(new PropertyValueFactory<>("duePayment"));

    }

    @FXML
    public void loadButtonOA(ActionEvent actionEvent) {

        ObservableList<Invoice> list = FXCollections.observableArrayList();

        try {

            ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream("invoice.bin"));

            while (true) {

                try {

                    Invoice invoice = (Invoice) ois.readObject();

                    list.add(invoice);

                }

                catch (EOFException e) {

                    break;

                }

            }

            ois.close();

            invoiceTV.setItems(list);

        }

        catch (Exception e) {

            e.printStackTrace();

        }

    }

    @FXML
    public void backOA(ActionEvent actionEvent) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("U4G5_GenerateGuestInvoice.fxml"));

            Node node = loader.load();

            mainPane.getChildren().setAll(node);

        }

        catch (Exception e) {

            e.printStackTrace();

        }

    }

}