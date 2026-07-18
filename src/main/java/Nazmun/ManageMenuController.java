package Nazmun;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageMenuController
{
    @javafx.fxml.FXML
    private TableColumn<MenuItem, String> priceTC;
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

        categoryCB.setItems(FXCollections.observableArrayList(
                "Starter", "Main Course", "Dessert", "Beverage"
        ));

        menuList.add(new MenuItem("Grilled Chicken", "Main Course", "450"));
        menuList.add(new MenuItem("Mango Smoothie", "Beverage", "180"));
        menuList.add(new MenuItem("Chocolate Cake", "Dessert", "250"));

        menuItemsTableView.setItems(menuList);
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/summer/section1/group7/eco_resort/Nazmun/ChefDashboard.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void saveMenuItemButtonOA(ActionEvent actionEvent) {

        String itemName = itemNameTF.getText();
        String category = categoryCB.getValue();
        String price = priceTF.getText();

        if (itemName == null || itemName.isEmpty()) {
            ingredientWarningLabel.setText("Please enter the item name.");
            return;
        }
        if (category == null || category.isEmpty()) {
            ingredientWarningLabel.setText("Please select a category.");
            return;
        }
        if (price == null || price.isEmpty() || !price.matches("\\d+")) {
            ingredientWarningLabel.setText("Please enter a valid price.");
            return;
        }

        MenuItem existingItem = null;
        for (MenuItem item : menuList) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                existingItem = item;
                break;
            }
        }

        if (existingItem != null) {
            existingItem.setCategory(category);
            existingItem.setPrice(price);
            menuItemsTableView.refresh();
        } else {
            menuList.add(new MenuItem(itemName, category, price));
        }

        ingredientWarningLabel.setText("");
        itemNameTF.clear();
        categoryCB.setValue(null);
        priceTF.clear();
    }
}