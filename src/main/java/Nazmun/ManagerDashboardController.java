package Nazmun;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerDashboardController
{
    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void reservationsButtonOA(ActionEvent actionEvent) {

    }

    @javafx.fxml.FXML
    public void roomManagementButtonOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/summer/section1/group7/eco_resort/Nazmun/ManageRoomRate3.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void logoutButtonOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/summer/section1/group7/eco_resort/Nazmun/ManagerLogin1.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void budgetFinanceButtonOA(ActionEvent actionEvent) {

    }

    @javafx.fxml.FXML
    public void staffManagementButtonOA(ActionEvent actionEvent) {

    }

    @javafx.fxml.FXML
    public void safetyInspectionButtonOA(ActionEvent actionEvent) {

    }

    @javafx.fxml.FXML
    public void foodBeverageButtonOA(ActionEvent actionEvent) {

    }

    @javafx.fxml.FXML
    public void ecoMetricsButtonOA(ActionEvent actionEvent) {

    }
}