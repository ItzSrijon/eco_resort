package Piya.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CheckOutRecordController
{
    @javafx.fxml.FXML
    private TableColumn guestNameTC;
    @javafx.fxml.FXML
    private TableColumn timeTC;
    @javafx.fxml.FXML
    private TableColumn recordIdTC;
    @javafx.fxml.FXML
    private TableView checkOutTableView;
    @javafx.fxml.FXML
    private TableColumn roomNumberTC;
    @javafx.fxml.FXML
    private TableColumn checkOutDateTC;
    @javafx.fxml.FXML
    private Label messageLabel;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void refreshButtonOA(ActionEvent actionEvent) {
    }
}