package com.summer.section1.group7.eco_resort.Piya.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ViewLostAndFoundItemsController
{
    @javafx.fxml.FXML
    private TableColumn dateFoundTC;
    @javafx.fxml.FXML
    private TableColumn itemIdTC;
    @javafx.fxml.FXML
    private TableColumn statusTC;
    @javafx.fxml.FXML
    private TableView lostFoundTableView;
    @javafx.fxml.FXML
    private TableColumn foundLocationTC;
    @javafx.fxml.FXML
    private TableColumn itemNameTC;
    @javafx.fxml.FXML
    private Label messageLabel;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void updateStatusButtonOA(ActionEvent actionEvent) {
    }
}