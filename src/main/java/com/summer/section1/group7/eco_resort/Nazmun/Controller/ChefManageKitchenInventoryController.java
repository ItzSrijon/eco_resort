package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import com.summer.section1.group7.eco_resort.Nazmun.Model.InventoryItem;
import com.summer.section1.group7.eco_resort.Nazmun.Model.InventoryManager;
import com.summer.section1.group7.eco_resort.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class ChefManageKitchenInventoryController
{
    @javafx.fxml.FXML
    private TableView<InventoryItem> inventoryTableView;
    @javafx.fxml.FXML
    private TableColumn<InventoryItem, String> itemIdTC;
    @javafx.fxml.FXML
    private TableColumn<InventoryItem, String> itemNameTC;
    @javafx.fxml.FXML
    private TableColumn<InventoryItem, Double> itemQuantityTC;
    @javafx.fxml.FXML
    private TableColumn<InventoryItem, Double> itemReorderTC;
    @javafx.fxml.FXML
    private TextField itemNameTF;
    @javafx.fxml.FXML
    private TextField itemQuantityTF;
    @javafx.fxml.FXML
    private TextField itemReorderTF;

    @javafx.fxml.FXML
    public void initialize() {
        itemIdTC.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        itemNameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
        itemQuantityTC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        itemReorderTC.setCellValueFactory(new PropertyValueFactory<>("reorderLevel"));

        inventoryTableView.setRowFactory(new Callback<TableView<InventoryItem>, TableRow<InventoryItem>>() {
            @Override
            public TableRow<InventoryItem> call(TableView<InventoryItem> tableView) {
                return new TableRow<InventoryItem>() {
                    @Override
                    protected void updateItem(InventoryItem item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setStyle("");
                        } else if (item.isLowStock()) {
                            setStyle("-fx-background-color: #ffcccc;");
                        } else {
                            setStyle("");
                        }
                    }
                };
            }
        });

        inventoryTableView.getItems().addAll(InventoryManager.getInventoryList());
    }

    @javafx.fxml.FXML
    public void addItemButtonOA(ActionEvent actionEvent) {
        String name = itemNameTF.getText();

        double quantity, reorderLevel;
        try {
            quantity = Double.parseDouble(itemQuantityTF.getText());
            reorderLevel = Double.parseDouble(itemReorderTF.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Quantity and reorder level must be valid numbers.");
            alert.showAndWait();
            return;
        }

        String itemId = InventoryManager.generateItemId();
        InventoryItem item = new InventoryItem(itemId, name, quantity, reorderLevel);

        inventoryTableView.getItems().add(item);
        InventoryManager.getInventoryList().add(item);
        InventoryManager.saveToFile();

        itemNameTF.setText("");
        itemQuantityTF.setText("");
        itemReorderTF.setText("");
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Nazmun/ChefDashboard.fxml");
    }
}