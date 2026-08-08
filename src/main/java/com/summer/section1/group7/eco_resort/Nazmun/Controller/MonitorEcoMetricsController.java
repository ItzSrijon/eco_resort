package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import com.summer.section1.group7.eco_resort.Nazmun.Model.*;
import com.summer.section1.group7.eco_resort.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MonitorEcoMetricsController
{
    @javafx.fxml.FXML
    private ComboBox<String> metricTypeCB;
    @javafx.fxml.FXML
    private TextField usageTF;
    @javafx.fxml.FXML
    private TextField targetTF;
    @javafx.fxml.FXML
    private TableView<EcoMetric> metricTableView;
    @javafx.fxml.FXML
    private TableColumn<EcoMetric, String> metricIdTC;
    @javafx.fxml.FXML
    private TableColumn<EcoMetric, String> metricTypeTC;
    @javafx.fxml.FXML
    private TableColumn<EcoMetric, Double> usageTC;
    @javafx.fxml.FXML
    private TableColumn<EcoMetric, Double> targetTC;
    @javafx.fxml.FXML
    private TableColumn<EcoMetric, String> statusTC;

    @javafx.fxml.FXML
    public void initialize() {
        metricTypeCB.getItems().addAll("Electricity", "Water", "Waste");

        metricIdTC.setCellValueFactory(new PropertyValueFactory<>("metricId"));
        metricTypeTC.setCellValueFactory(new PropertyValueFactory<>("metricType"));
        usageTC.setCellValueFactory(new PropertyValueFactory<>("usage"));
        targetTC.setCellValueFactory(new PropertyValueFactory<>("target"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("status"));

        metricTableView.getItems().addAll(EcoMetricManager.getMetricList());
    }

    @javafx.fxml.FXML
    public void addRecordButtonOA(ActionEvent actionEvent) {
        String type = metricTypeCB.getValue();

        double usage, target;
        try {
            usage = Double.parseDouble(usageTF.getText());
            target = Double.parseDouble(targetTF.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Usage and target must be valid numbers.");
            alert.showAndWait();
            return;
        }

        String metricId = EcoMetricManager.generateMetricId();

        EcoMetric metric;
        if (type.equals("Electricity")) {
            metric = new ElectricityMetric(metricId, usage, target);
        } else if (type.equals("Water")) {
            metric = new WaterMetric(metricId, usage, target);
        } else {
            metric = new WasteMetric(metricId, usage, target);
        }

        metricTableView.getItems().add(metric);
        EcoMetricManager.getMetricList().add(metric);
        EcoMetricManager.saveToFile();

        metricTypeCB.setValue(null);
        usageTF.setText("");
        targetTF.setText("");
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Nazmun/ManagerDashboard.fxml");
    }
}