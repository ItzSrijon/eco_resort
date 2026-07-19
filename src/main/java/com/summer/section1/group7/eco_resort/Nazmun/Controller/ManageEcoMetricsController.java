package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class ManageEcoMetricsController
{
    @javafx.fxml.FXML
    private Label ecoAlertLabel;
    @javafx.fxml.FXML
    private TextArea correctiveNotesTF;
    @javafx.fxml.FXML
    private TableColumn targetTC;
    @javafx.fxml.FXML
    private TableColumn comparisonTC;
    @javafx.fxml.FXML
    private Label ecoStatusLabel;
    @javafx.fxml.FXML
    private TableView ecoMetricsTableView;
    @javafx.fxml.FXML
    private TableColumn metricNameTC;
    @javafx.fxml.FXML
    private TableColumn currentUsageTC;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void saveEcoReportButtonOA(ActionEvent actionEvent) {
    }
}