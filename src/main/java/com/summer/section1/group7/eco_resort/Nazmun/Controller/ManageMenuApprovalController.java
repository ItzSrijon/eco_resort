package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import com.summer.section1.group7.eco_resort.Nazmun.Model.MenuItem;
import com.summer.section1.group7.eco_resort.Nazmun.Model.MenuManager;
import com.summer.section1.group7.eco_resort.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageMenuApprovalController
{
    @javafx.fxml.FXML
    private TableView<MenuItem> menuTableView;
    @javafx.fxml.FXML
    private TableColumn<MenuItem, String> itemNameTC;
    @javafx.fxml.FXML
    private TableColumn<MenuItem, String> itemCategoryTC;
    @javafx.fxml.FXML
    private TableColumn<MenuItem, Double> itemPriceTC;
    @javafx.fxml.FXML
    private TableColumn<MenuItem, String> itemStatusTC;
    @javafx.fxml.FXML
    private TableColumn<MenuItem, String> itemReasonTC;
    @javafx.fxml.FXML
    private TextField rejectionReasonTF;

    @javafx.fxml.FXML
    public void initialize() {
        itemNameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
        itemCategoryTC.setCellValueFactory(new PropertyValueFactory<>("category"));
        itemPriceTC.setCellValueFactory(new PropertyValueFactory<>("price"));
        itemStatusTC.setCellValueFactory(new PropertyValueFactory<>("approvalStatus"));
        itemReasonTC.setCellValueFactory(new PropertyValueFactory<>("rejectionReason"));

        menuTableView.getItems().addAll(MenuManager.getMenuList());
    }

    @javafx.fxml.FXML
    public void approveButtonOA(ActionEvent actionEvent) {
        MenuItem selected = menuTableView.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        selected.setApprovalStatus("Approved");
        selected.setRejectionReason("");
        MenuManager.saveToFile();

        menuTableView.refresh();
        rejectionReasonTF.setText("");
    }

    @javafx.fxml.FXML
    public void rejectButtonOA(ActionEvent actionEvent) {
        MenuItem selected = menuTableView.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        String reason = rejectionReasonTF.getText();

        if (reason == null || reason.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter a rejection reason.");
            alert.showAndWait();
            return;
        }

        selected.setApprovalStatus("Rejected");
        selected.setRejectionReason(reason);
        MenuManager.saveToFile();

        menuTableView.refresh();
        rejectionReasonTF.setText("");
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Nazmun/ManagerDashboard.fxml");
    }
}