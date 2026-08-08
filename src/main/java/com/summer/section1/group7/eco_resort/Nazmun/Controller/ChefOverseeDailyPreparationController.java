package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import com.summer.section1.group7.eco_resort.Nazmun.Model.DailyPrepItem;
import com.summer.section1.group7.eco_resort.Nazmun.Model.DailyPrepManager;
import com.summer.section1.group7.eco_resort.Nazmun.Model.StaffManager;
import com.summer.section1.group7.eco_resort.Nazmun.Model.StaffMember;
import com.summer.section1.group7.eco_resort.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ChefOverseeDailyPreparationController
{
    @javafx.fxml.FXML
    private TableView<DailyPrepItem> prepTableView;
    @javafx.fxml.FXML
    private TableColumn<DailyPrepItem, String> dishIdTC;
    @javafx.fxml.FXML
    private TableColumn<DailyPrepItem, String> dishNameTC;
    @javafx.fxml.FXML
    private TableColumn<DailyPrepItem, Integer> quantityTC;
    @javafx.fxml.FXML
    private TableColumn<DailyPrepItem, String> stationTC;
    @javafx.fxml.FXML
    private TableColumn<DailyPrepItem, String> assignedStaffTC;
    @javafx.fxml.FXML
    private TextField dishNameTF;
    @javafx.fxml.FXML
    private TextField quantityTF;
    @javafx.fxml.FXML
    private ComboBox<String> stationCB;
    @javafx.fxml.FXML
    private ComboBox<String> staffCB;

    @javafx.fxml.FXML
    public void initialize() {
        dishIdTC.setCellValueFactory(new PropertyValueFactory<>("dishId"));
        dishNameTC.setCellValueFactory(new PropertyValueFactory<>("dishName"));
        quantityTC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        stationTC.setCellValueFactory(new PropertyValueFactory<>("station"));
        assignedStaffTC.setCellValueFactory(new PropertyValueFactory<>("assignedStaff"));

        stationCB.getItems().addAll("Grill", "Sauté", "Pastry", "Cold Prep", "Plating");

        for (StaffMember s : StaffManager.getStaffList()) {
            staffCB.getItems().add(s.getName());
        }

        prepTableView.getItems().addAll(DailyPrepManager.getPrepList());
    }

    @javafx.fxml.FXML
    public void addDishButtonOA(ActionEvent actionEvent) {
        String name = dishNameTF.getText();

        int quantity;
        try {
            quantity = Integer.parseInt(quantityTF.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Quantity must be a whole number.");
            alert.showAndWait();
            return;
        }

        String dishId = DailyPrepManager.generateDishId();
        DailyPrepItem item = new DailyPrepItem(dishId, name, quantity);

        prepTableView.getItems().add(item);
        DailyPrepManager.getPrepList().add(item);
        DailyPrepManager.saveToFile();

        dishNameTF.setText("");
        quantityTF.setText("");
    }

    @javafx.fxml.FXML
    public void assignButtonOA(ActionEvent actionEvent) {
        DailyPrepItem selected = prepTableView.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        selected.setStation(stationCB.getValue());
        selected.setAssignedStaff(staffCB.getValue());
        DailyPrepManager.saveToFile();

        prepTableView.refresh();
        stationCB.setValue(null);
        staffCB.setValue(null);
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Nazmun/ChefDashboard.fxml");
    }
}