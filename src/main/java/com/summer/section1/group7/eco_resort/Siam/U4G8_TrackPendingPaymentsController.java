package com.summer.section1.group7.eco_resort.Siam;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class U4G8_TrackPendingPaymentsController {

    @FXML
    private TableColumn<Invoice, String> guestNameTC;
    @FXML
    private TableColumn<Invoice, Double> totalBillTC;
    @FXML
    private TableView<Invoice> pendingPaymentTV;
    @FXML
    private TableColumn<Invoice, Double> totalPaidTC;
    @FXML
    private TableColumn<Invoice, String> guestIdTC;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private TableColumn<Invoice, Double> duePaymentTC;

    @FXML
    public void initialize() {

        guestIdTC.setCellValueFactory(new PropertyValueFactory<>("guestId"));
        guestNameTC.setCellValueFactory(new PropertyValueFactory<>("guestName"));
        totalBillTC.setCellValueFactory(new PropertyValueFactory<>("totalBill"));
        totalPaidTC.setCellValueFactory(new PropertyValueFactory<>("totalPaid"));
        duePaymentTC.setCellValueFactory(new PropertyValueFactory<>("duePayment"));

    }

    @FXML
    public void loadPendingPaymentsOA(ActionEvent actionEvent) {

        ObservableList<Invoice> pendingList = FXCollections.observableArrayList();

        try {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("invoice.bin"));

            while (true) {

                try {
                    Invoice invoice = (Invoice) ois.readObject();

                    if (invoice.getDuePayment() > 0) {

                        pendingList.add(invoice);
                    }

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

        pendingPaymentTV.setItems(pendingList);

        if (pendingList.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Pending Payments");
            alert.setContentText("No pending payments found.");

            alert.showAndWait();

        }
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

}