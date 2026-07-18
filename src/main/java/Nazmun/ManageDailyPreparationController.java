package Nazmun;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ManageDailyPreparationController
{
    @javafx.fxml.FXML
    private TableColumn dishNameTC;
    @javafx.fxml.FXML
    private ComboBox assignedStaffCB;
    @javafx.fxml.FXML
    private ComboBox stationCB;
    @javafx.fxml.FXML
    private Label taskStatusLabel;
    @javafx.fxml.FXML
    private TableColumn notesTC;
    @javafx.fxml.FXML
    private TableColumn quantityTC;
    @javafx.fxml.FXML
    private TableView todaysOrdersTableView;
    @javafx.fxml.FXML
    private Label unavailabilityIssueLabel;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void assignTaskButtonOA(ActionEvent actionEvent) {
    }
}