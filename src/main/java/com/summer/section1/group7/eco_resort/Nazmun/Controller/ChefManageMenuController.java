package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import com.summer.section1.group7.eco_resort.Nazmun.Model.MenuItem;
import com.summer.section1.group7.eco_resort.Nazmun.Model.MenuManager;
import com.summer.section1.group7.eco_resort.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ChefManageMenuController
{
    @javafx.fxml.FXML
    private TableView<MenuItem> menuTableView;
    @javafx.fxml.FXML
    private TableColumn<MenuItem, String> itemIdTC;
    @javafx.fxml.FXML
    private TableColumn<MenuItem, String> itemNameTC;
    @javafx.fxml.FXML
    private TableColumn<MenuItem, String> itemCategoryTC;
    @javafx.fxml.FXML
    private TableColumn<MenuItem, Double> itemPriceTC;
    @javafx.fxml.FXML
    private TextField itemNameTF;
    @javafx.fxml.FXML
    private ComboBox<String> itemCategoryCB;
    @javafx.fxml.FXML
    private TextField itemPriceTF;
    @javafx.fxml.FXML
    private TextField editPriceTF;

    @javafx.fxml.FXML
    public void initialize() {
        itemIdTC.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        itemNameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
        itemCategoryTC.setCellValueFactory(new PropertyValueFactory<>("category"));
        itemPriceTC.setCellValueFactory(new PropertyValueFactory<>("price"));

        itemCategoryCB.getItems().addAll("Starter", "Main Course", "Dessert", "Beverage");

        menuTableView.getItems().addAll(MenuManager.getMenuList());
    }

    @javafx.fxml.FXML
    public void addMenuItemButtonOA(ActionEvent actionEvent) {
        String name = itemNameTF.getText();
        String category = itemCategoryCB.getValue();

        double price;
        try {
            price = Double.parseDouble(itemPriceTF.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Price must be a valid number.");
            alert.showAndWait();
            return;
        }

        if (category == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a category.");
            alert.showAndWait();
            return;
        }

        String itemId = MenuManager.generateItemId();
        MenuItem item = new MenuItem(itemId, name, category, price);

        menuTableView.getItems().add(item);
        MenuManager.getMenuList().add(item);
        MenuManager.saveToFile();

        itemNameTF.setText("");
        itemCategoryCB.setValue(null);
        itemPriceTF.setText("");
    }

    @javafx.fxml.FXML
    public void editPriceButtonOA(ActionEvent actionEvent) {
        MenuItem selected = menuTableView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a menu item.");
            alert.showAndWait();
            return;
        }

        double price;
        try {
            price = Double.parseDouble(editPriceTF.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Price must be a valid number.");
            alert.showAndWait();
            return;
        }

        selected.setPrice(price);
        MenuManager.saveToFile();

        menuTableView.refresh();
        editPriceTF.setText("");
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Nazmun/ChefDashboard.fxml");
    }
}