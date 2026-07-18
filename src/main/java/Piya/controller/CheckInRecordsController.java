package Piya.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CheckInRecordsController
{
    @javafx.fxml.FXML
    private TableColumn guestNameTC;
    @javafx.fxml.FXML
    private TableColumn timeTC;
    @javafx.fxml.FXML
    private TableColumn recordIdTC;
    @javafx.fxml.FXML
    private TableColumn checkInDateTC;
    @javafx.fxml.FXML
    private Label mssageLabel;
    @javafx.fxml.FXML
    private TableColumn roomNumberTC;
    @javafx.fxml.FXML
    private TableView checkInTableView;

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