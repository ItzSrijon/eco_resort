package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import com.summer.section1.group7.eco_resort.Nazmun.Model.InventoryItem;
import com.summer.section1.group7.eco_resort.Nazmun.Model.InventoryManager;
import com.summer.section1.group7.eco_resort.Nazmun.Model.WasteRecord;
import com.summer.section1.group7.eco_resort.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ChefRecordFoodWasteController
{
    @javafx.fxml.FXML
    private ComboBox<String> itemCB;
    @javafx.fxml.FXML
    private TextField quantityTF;
    @javafx.fxml.FXML
    private ComboBox<String> reasonCB;

    @javafx.fxml.FXML
    private TableView<WasteRecord> wasteTableView;
    @javafx.fxml.FXML
    private TableColumn<WasteRecord, String> wasteItemNameTC;
    @javafx.fxml.FXML
    private TableColumn<WasteRecord, Double> wasteQuantityTC;
    @javafx.fxml.FXML
    private TableColumn<WasteRecord, String> wasteReasonTC;

    @javafx.fxml.FXML
    private TableView<InventoryItem> inventoryTableView;
    @javafx.fxml.FXML
    private TableColumn<InventoryItem, String> invItemNameTC;
    @javafx.fxml.FXML
    private TableColumn<InventoryItem, Double> invQuantityTC;

    @javafx.fxml.FXML
    public void initialize() {
        for (InventoryItem item : InventoryManager.getInventoryList()) {
            itemCB.getItems().add(item.getName());
        }

        reasonCB.getItems().addAll("Spoiled", "Overcooked", "Expired", "Prep Error", "Other");

        wasteItemNameTC.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        wasteQuantityTC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        wasteReasonTC.setCellValueFactory(new PropertyValueFactory<>("reason"));

        invItemNameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
        invQuantityTC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        inventoryTableView.getItems().addAll(InventoryManager.getInventoryList());
    }

    @javafx.fxml.FXML
    public void logWasteButtonOA(ActionEvent actionEvent) {
        String itemName = itemCB.getValue();
        String reason = reasonCB.getValue();

        if (itemName == null || reason == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select an ingredient and a reason.");
            alert.showAndWait();
            return;
        }

        double quantity;
        try {
            quantity = Double.parseDouble(quantityTF.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Quantity must be a valid number.");
            alert.showAndWait();
            return;
        }

        InventoryItem matchedItem = null;
        for (InventoryItem item : InventoryManager.getInventoryList()) {
            if (item.getName().equals(itemName)) {
                matchedItem = item;
                break;
            }
        }

        if (matchedItem != null) {
            matchedItem.setQuantity(matchedItem.getQuantity() - quantity);
            InventoryManager.saveToFile();
            inventoryTableView.refresh();
        }

        wasteTableView.getItems().add(new WasteRecord(itemName, quantity, reason));

        quantityTF.setText("");
        itemCB.setValue(null);
        reasonCB.setValue(null);
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Nazmun/ChefDashboard.fxml");
    }
}