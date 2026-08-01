package com.summer.section1.group7.eco_resort.Siam;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class U3G7_AssignFitnessPackageController
{
    @javafx.fxml.FXML
    private TextField phoneTF;
    @javafx.fxml.FXML
    private TextField startDateTF;
    @javafx.fxml.FXML
    private TextField currentPackageTF;
    @javafx.fxml.FXML
    private TextField guestNameTF;
    @javafx.fxml.FXML
    private TextField expiryDateTF;
    @javafx.fxml.FXML
    private ComboBox newFitnessPackageCB;
    @javafx.fxml.FXML
    private TextField guestIdTF;
    @javafx.fxml.FXML
    private AnchorPane mainPane;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
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
    public void assignPackageOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void searchGuestOA(ActionEvent actionEvent) {
    }
}