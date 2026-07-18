package Nazmun;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class ManageFoodWasteLogController
{
    @javafx.fxml.FXML
    private ComboBox wasteReasonCB;
    @javafx.fxml.FXML
    private TableView wasteLogTableView;
    @javafx.fxml.FXML
    private TextField wasteQuantityTF;
    @javafx.fxml.FXML
    private Label thresholdWarningLabel;
    @javafx.fxml.FXML
    private TableColumn reasonTC;
    @javafx.fxml.FXML
    private TextField wastedItemTF;
    @javafx.fxml.FXML
    private TableColumn quantityTC;
    @javafx.fxml.FXML
    private TableColumn itemNameTC;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void saveWasteEntryButtonOA(ActionEvent actionEvent) {
    }
}