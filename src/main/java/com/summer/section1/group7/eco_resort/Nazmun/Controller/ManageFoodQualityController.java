package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ManageFoodQualityController
{
    @javafx.fxml.FXML
    private TableColumn dishNameTC;
    @javafx.fxml.FXML
    private TableColumn stationTC;
    @javafx.fxml.FXML
    private TableView todaysDishesTableView;
    @javafx.fxml.FXML
    private TableColumn cookTC;
    @javafx.fxml.FXML
    private Label belowStandardAlertLabel;
    @javafx.fxml.FXML
    private Slider qualitySlider;
    @javafx.fxml.FXML
    private Slider presentationSlider;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void submitRatingButtonOA(ActionEvent actionEvent) {
    }
}