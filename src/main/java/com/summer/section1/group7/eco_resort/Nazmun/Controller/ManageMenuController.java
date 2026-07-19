package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import com.summer.section1.group7.eco_resort.Nazmun.Model.MenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageMenuController
{
    @javafx.fxml.FXML
    private TableColumn<MenuItem, Double> priceTC;
    @javafx.fxml.FXML
    private TextField itemNameTF;
    @javafx.fxml.FXML
    private TextField priceTF;
    @javafx.fxml.FXML
    private TableView<MenuItem> menuItemsTableView;
    @javafx.fxml.FXML
    private TableColumn<MenuItem, String> categoryTC;
    @javafx.fxml.FXML
    private ComboBox<String> categoryCB;
    @javafx.fxml.FXML
    private Label ingredientWarningLabel;
    @javafx.fxml.FXML
    private TableColumn<MenuItem, String> itemNameTC;

    private final ObservableList<MenuItem> menuList = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {
        itemNameTC.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        categoryTC.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceTC.setCellValueFactory(new PropertyValueFactory<>("price"));

        categoryCB.getItems().addAll("Starter", "Main Course", "Dessert", "Beverage");

        menuItemsTableView.setItems(menuList);
    }

    @javafx.fxml.FXML
    public void saveMenuItemButtonOA(ActionEvent actionEvent) {
        String name = itemNameTF.getText().trim();
        String category = categoryCB.getValue();
        String priceText = priceTF.getText().trim();

        if (name.isEmpty() || category == null || priceText.isEmpty()) {
            ingredientWarningLabel.setText("Please fill all fields.");
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            ingredientWarningLabel.setText("Price must be a number.");
            return;
        }

        MenuItem newItem = new MenuItem(name, category, price);
        menuList.add(newItem);
        menuItemsTableView.refresh();

        ingredientWarningLabel.setTextFill(javafx.scene.paint.Color.GREEN);
        ingredientWarningLabel.setText("Menu item saved.");

        itemNameTF.clear();
        priceTF.clear();
        categoryCB.getSelectionModel().clearSelection();
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(
                "/com/summer/section1/group7/eco_resort/Nazmun/ChefDashboard.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}