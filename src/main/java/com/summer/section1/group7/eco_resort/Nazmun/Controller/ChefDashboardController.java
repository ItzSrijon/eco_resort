package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import com.summer.section1.group7.eco_resort.SceneSwitcher;
import com.summer.section1.group7.eco_resort.User;
import com.summer.section1.group7.eco_resort.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ChefDashboardController {

    @FXML
    private Label welcomeLabel;

    @FXML
    public void initialize() {
        User loggedInUser = UserManager.getLoggedInUser();
        if (loggedInUser != null) {
            welcomeLabel.setText("Welcome, " + loggedInUser.getName() + " !!!");
        }
    }

    @FXML
    public void menuManagementButtonOA(ActionEvent event) {
        SceneSwitcher.switchTo("Nazmun/ChefManageMenu2.fxml");
    }

    @FXML
    public void dailyPreparationButtonOA(ActionEvent event) {
        SceneSwitcher.switchTo("Nazmun/ChefOverseeDailyPreparation3.fxml");
    }

    @FXML
    public void kitchenStaffButtonOA(ActionEvent event) {
        SceneSwitcher.switchTo("Nazmun/ChefManageKitchenStaff4.fxml");
    }

    @FXML
    public void kitchenInventoryButtonOA(ActionEvent event) {
        SceneSwitcher.switchTo("Nazmun/ChefManageKitchenInventory5.fxml");
    }

    @FXML
    public void foodQualityButtonOA(ActionEvent event) {
        SceneSwitcher.switchTo("Nazmun/ChefInspectFoodQuality6.fxml");
    }

    @FXML
    public void profitAnalysisButtonOA(ActionEvent event) {
        SceneSwitcher.switchTo("Nazmun/ChefProfitAnalysis7.fxml");
    }

    @FXML
    public void foodWasteButtonOA(ActionEvent event) {
        SceneSwitcher.switchTo("Nazmun/ChefRecordFoodWaste8.fxml");
    }

    @FXML
    public void logoutButtonOA(ActionEvent event) {
        UserManager.setLoggedInUser(null);
        SceneSwitcher.switchTo("Login.fxml");
    }
}