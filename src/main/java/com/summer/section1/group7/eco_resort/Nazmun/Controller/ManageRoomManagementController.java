package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class ManageRoomManagementController
{
    @javafx.fxml.FXML
    private Label pricingErrorLabel;
    @javafx.fxml.FXML
    private TableColumn roomNoTC;
    @javafx.fxml.FXML
    private ComboBox newStatusCB;
    @javafx.fxml.FXML
    private Label occupancyRateLabel;
    @javafx.fxml.FXML
    private TableColumn roomTypeTC;
    @javafx.fxml.FXML
    private TextField newRateTF;
    @javafx.fxml.FXML
    private Label occupiedCountLabel;
    @javafx.fxml.FXML
    private TableColumn currentRateTC;
    @javafx.fxml.FXML
    private Label vacantCountLabel;
    @javafx.fxml.FXML
    private TableView roomsTableView;
    @javafx.fxml.FXML
    private TableColumn occupancyStatusTC;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void updateRoomButtonOA(ActionEvent actionEvent) {
    }
}