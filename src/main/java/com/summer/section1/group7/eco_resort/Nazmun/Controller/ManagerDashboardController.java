package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import com.summer.section1.group7.eco_resort.SceneSwitcher;
import com.summer.section1.group7.eco_resort.User;
import com.summer.section1.group7.eco_resort.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ManagerDashboardController {

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
    public void reservationsButtonOA(ActionEvent event) {
        SceneSwitcher.switchTo("Nazmun/ManageReservation2.fxml");
    }

    @FXML
    public void roomManagementButtonOA(ActionEvent event) {
        SceneSwitcher.switchTo("Nazmun/ManageRoomRate3.fxml");
    }

    @FXML
    public void menuApprovalButtonOA(ActionEvent event) {
        SceneSwitcher.switchTo("Nazmun/ManageMenuApproval4.fxml");
    }

    @FXML
    public void staffManagementButtonOA(ActionEvent event) {
        SceneSwitcher.switchTo("Nazmun/ManageStaff5.fxml");
    }

    @FXML
    public void budgetFinanceButtonOA(ActionEvent event) {
        SceneSwitcher.switchTo("Nazmun/MonitorBudget6.fxml");
    }

    @FXML
    public void safetyInspectionButtonOA(ActionEvent event) {
        SceneSwitcher.switchTo("Nazmun/ConductSafetyInspection7.fxml");
    }

    @FXML
    public void ecoMetricsButtonOA(ActionEvent event) {
        SceneSwitcher.switchTo("Nazmun/MonitorEcoMetrics8.fxml");
    }

    @FXML
    public void logoutButtonOA(ActionEvent event) {
        UserManager.setLoggedInUser(null);
        SceneSwitcher.switchTo("Login.fxml");
    }
}