package Nazmun;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ManageKitchenStaffController
{
    @javafx.fxml.FXML
    private TableColumn roleTC;
    @javafx.fxml.FXML
    private TableColumn staffNameTC;
    @javafx.fxml.FXML
    private Label shiftConflictLabel;
    @javafx.fxml.FXML
    private TableColumn statusTC;
    @javafx.fxml.FXML
    private TextField timeSlotTF;
    @javafx.fxml.FXML
    private TextField taskDescriptionTF;
    @javafx.fxml.FXML
    private TableView kitchenStaffTableView;
    @javafx.fxml.FXML
    private TableColumn shiftTC;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void assignDailyTaskButtonOA(ActionEvent actionEvent) {
    }
}