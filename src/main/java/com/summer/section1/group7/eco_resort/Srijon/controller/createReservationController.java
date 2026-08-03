package com.summer.section1.group7.eco_resort.Srijon.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class createReservationController
{
    @javafx.fxml.FXML
    private DatePicker checkInPicker;
    @javafx.fxml.FXML
    private TextField guestNameField;
    @javafx.fxml.FXML
    private DatePicker checkOutPicker;
    @javafx.fxml.FXML
    private Label messageLabel;
    @javafx.fxml.FXML
    private TextField phoneField;
    @javafx.fxml.FXML
    private ComboBox<String> roomTypeCombobox;

    @javafx.fxml.FXML
    public void initialize() {
        
    }

    @javafx.fxml.FXML
    public void saveReservation(ActionEvent actionEvent) {
    }
}