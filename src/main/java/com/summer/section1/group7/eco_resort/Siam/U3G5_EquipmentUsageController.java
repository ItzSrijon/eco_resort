package com.summer.section1.group7.eco_resort.Siam;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class U3G5_EquipmentUsageController
{
    @javafx.fxml.FXML
    private TextField memberNameTF;
    @javafx.fxml.FXML
    private TextField availableQtyTF;
    @javafx.fxml.FXML
    private ComboBox durationCB;
    @javafx.fxml.FXML
    private ComboBox equipmentCB;
    @javafx.fxml.FXML
    private TextField memberIdTF;
    @javafx.fxml.FXML
    private TextField startTimeTF;
    @javafx.fxml.FXML
    private AnchorPane mainPane;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @Deprecated
    public void assignEquipment(ActionEvent actionEvent) {
    }


    @Deprecated
    public void searchMember(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void showAvailability(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void searchButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void backToDashboardOA(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("GymManagerDashboard.fxml"));
            Node node=fxmlLoader.load();
            mainPane.getChildren().setAll(node);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void assignEquipmentOA(ActionEvent actionEvent) {
    }
}