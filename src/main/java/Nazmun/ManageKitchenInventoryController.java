package Nazmun;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ManageKitchenInventoryController
{
    @javafx.fxml.FXML
    private TextField requestQtyTF;
    @javafx.fxml.FXML
    private Label invalidQuantityLabel;
    @javafx.fxml.FXML
    private TableColumn reorderLevelTC;
    @javafx.fxml.FXML
    private Label stockSufficientLabel;
    @javafx.fxml.FXML
    private TableView inventoryTableView;
    @javafx.fxml.FXML
    private TableColumn quantityTC;
    @javafx.fxml.FXML
    private TableColumn ingredientNameTC;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void requestRestockButtonOA(ActionEvent actionEvent) {
    }
}