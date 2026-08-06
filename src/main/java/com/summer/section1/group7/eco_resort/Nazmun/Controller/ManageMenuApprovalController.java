package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import com.summer.section1.group7.eco_resort.Nazmun.Model.FoodBeverageItem;
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

public class ManageMenuApprovalController
{
    @javafx.fxml.FXML
    private TableColumn<FoodBeverageItem, Double> priceTC;
    @javafx.fxml.FXML
    private TextField newPriceTF;
    @javafx.fxml.FXML
    private Label stockWarningLabel;
    @javafx.fxml.FXML
    private TableView<FoodBeverageItem> menuTableView;
    @javafx.fxml.FXML
    private Label dailyRevenueLabel;
    @javafx.fxml.FXML
    private TableColumn<FoodBeverageItem, String> categoryTC;
    @javafx.fxml.FXML
    private TextArea revisionCommentsTF;
    @javafx.fxml.FXML
    private Label dailyExpenseLabel;
    @javafx.fxml.FXML
    private TableColumn<FoodBeverageItem, String> itemNameTC;
    @javafx.fxml.FXML
    private TableColumn<FoodBeverageItem, String> stockStatusTC;

    private final ObservableList<FoodBeverageItem> menuList = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {
        itemNameTC.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        categoryTC.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceTC.setCellValueFactory(new PropertyValueFactory<>("price"));
        stockStatusTC.setCellValueFactory(new PropertyValueFactory<>("stockStatus"));

        // Demo data — replace with real shared menu source later
        menuList.addAll(
                new FoodBeverageItem("Grilled Chicken", "Main Course", 12.50, "In Stock"),
                new FoodBeverageItem("Caesar Salad", "Starter", 7.00, "In Stock"),
                new FoodBeverageItem("Chocolate Cake", "Dessert", 6.00, "Low Stock")
        );

        menuTableView.setItems(menuList);

        dailyRevenueLabel.setText("$540.00");
        dailyExpenseLabel.setText("$210.00");
    }

    @javafx.fxml.FXML
    public void updatePriceButtonOA(ActionEvent actionEvent) {
        FoodBeverageItem selected = menuTableView.getSelectionModel().getSelectedItem();
        String priceText = newPriceTF.getText().trim();

        if (selected == null) {
            stockWarningLabel.setText("Select a menu item first.");
            return;
        }

        if (priceText.isEmpty()) {
            stockWarningLabel.setText("Enter a new price.");
            return;
        }

        try {
            double newPrice = Double.parseDouble(priceText);
            selected.setPrice(newPrice);
            menuTableView.refresh();
            stockWarningLabel.setTextFill(javafx.scene.paint.Color.GREEN);
            stockWarningLabel.setText("Price updated.");
            newPriceTF.clear();
        } catch (NumberFormatException e) {
            stockWarningLabel.setTextFill(javafx.scene.paint.Color.RED);
            stockWarningLabel.setText("Price must be a number.");
        }
    }

    @javafx.fxml.FXML
    public void publishMenuButtonOA(ActionEvent actionEvent) {
        boolean allValid = menuList.stream().allMatch(item ->
                item.getPrice() > 0 && !item.getStockStatus().equalsIgnoreCase("Out of Stock"));

        if (allValid) {
            stockWarningLabel.setTextFill(javafx.scene.paint.Color.GREEN);
            stockWarningLabel.setText("Menu published successfully — now live for guest orders.");
        } else {
            stockWarningLabel.setTextFill(javafx.scene.paint.Color.RED);
            stockWarningLabel.setText("Cannot publish — some items are out of stock or missing a price.");
        }
    }

    @javafx.fxml.FXML
    public void sendBackToChefButtonOA(ActionEvent actionEvent) {
        String comments = revisionCommentsTF.getText().trim();

        if (comments.isEmpty()) {
            stockWarningLabel.setTextFill(javafx.scene.paint.Color.RED);
            stockWarningLabel.setText("Enter revision comments before sending back to Chef.");
            return;
        }

        stockWarningLabel.setTextFill(javafx.scene.paint.Color.GREEN);
        stockWarningLabel.setText("Menu sent back to Chef with comments.");
        revisionCommentsTF.clear();
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(
                "/com/summer/section1/group7/eco_resort/Nazmun/ManagerDashboard.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}