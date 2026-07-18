package Nazmun;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChefDashboardController
{
    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void ecoMealPlanningButtonOA(ActionEvent actionEvent) {
        // Not implemented yet
    }

    @javafx.fxml.FXML
    public void foodWasteLogButtonOA(ActionEvent actionEvent) {
        // Not implemented yet
    }

    @javafx.fxml.FXML
    public void foodQualityButtonOA(ActionEvent actionEvent) {
        // Not implemented yet
    }

    @javafx.fxml.FXML
    public void logoutButtonOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/summer/section1/group7/eco_resort/Nazmun/ChefLogin1.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void kitchenStaffButtonOA(ActionEvent actionEvent) {
        // Not implemented yet
    }

    @javafx.fxml.FXML
    public void menuManagementButtonOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/summer/section1/group7/eco_resort/Nazmun/ManageMenu2.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void kitchenInventoryButtonOA(ActionEvent actionEvent) {
        // Not implemented yet
    }

    @javafx.fxml.FXML
    public void dailyPreparationButtonOA(ActionEvent actionEvent) {
        // Not implemented yet
    }
}