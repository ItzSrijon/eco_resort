package com.summer.section1.group7.eco_resort.Srijon.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class viewRoomsController
{
    @javafx.fxml.FXML
    private TableColumn colRoomNumber;
    @javafx.fxml.FXML
    private TableColumn colRoomType;
    @javafx.fxml.FXML
    private TableView roomTable;
    @javafx.fxml.FXML
    private TableColumn colStatus;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void onRefresh(ActionEvent actionEvent) {
    }
}