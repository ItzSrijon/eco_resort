package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import com.summer.section1.group7.eco_resort.Nazmun.Model.MenuItem;
import com.summer.section1.group7.eco_resort.Nazmun.Model.MenuManager;
import com.summer.section1.group7.eco_resort.Nazmun.Model.ProfitRecord;
import com.summer.section1.group7.eco_resort.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ChefProfitAnalysisController
{
    @javafx.fxml.FXML
    private TableView<MenuItem> menuTableView;
    @javafx.fxml.FXML
    private TableColumn<MenuItem, String> itemNameTC;
    @javafx.fxml.FXML
    private TableColumn<MenuItem, Double> itemPriceTC;
    @javafx.fxml.FXML
    private TextField costTF;

    @javafx.fxml.FXML
    private TableView<ProfitRecord> profitTableView;
    @javafx.fxml.FXML
    private TableColumn<ProfitRecord, String> profitNameTC;
    @javafx.fxml.FXML
    private TableColumn<ProfitRecord, Double> profitPriceTC;
    @javafx.fxml.FXML
    private TableColumn<ProfitRecord, Double> profitCostTC;
    @javafx.fxml.FXML
    private TableColumn<ProfitRecord, Double> profitAmountTC;
    @javafx.fxml.FXML
    private TableColumn<ProfitRecord, Double> profitMarginTC;

    @javafx.fxml.FXML
    public void initialize() {
        itemNameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
        itemPriceTC.setCellValueFactory(new PropertyValueFactory<>("price"));

        menuTableView.getItems().addAll(MenuManager.getMenuList());

        profitNameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
        profitPriceTC.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        profitCostTC.setCellValueFactory(new PropertyValueFactory<>("cost"));
        profitAmountTC.setCellValueFactory(new PropertyValueFactory<>("profit"));
        profitMarginTC.setCellValueFactory(new PropertyValueFactory<>("marginPercent"));
    }

    @javafx.fxml.FXML
    public void calculateButtonOA(ActionEvent actionEvent) {
        MenuItem selected = menuTableView.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        double cost;
        try {
            cost = Double.parseDouble(costTF.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Cost must be a valid number.");
            alert.showAndWait();
            return;
        }

        ProfitRecord record = new ProfitRecord(selected.getName(), selected.getPrice(), cost);
        profitTableView.getItems().add(record);

        costTF.setText("");
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Nazmun/ChefDashboard.fxml");
    }
}