package com.summer.section1.group7.eco_resort.Siam;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class UpdateMembershipStatusController
{
    @javafx.fxml.FXML
    private TextField phoneNumberTF;
    @javafx.fxml.FXML
    private TextField guestNameTF;
    @javafx.fxml.FXML
    private TextField guestIDTF;
    @javafx.fxml.FXML
    private TextField emailAddressTF;
    @javafx.fxml.FXML
    private ComboBox newStatusCB;
    @javafx.fxml.FXML
    private TextField currentStatusTF;
    @javafx.fxml.FXML
    private AnchorPane mainPane;

    @javafx.fxml.FXML
    public void initialize() {
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

    @Deprecated
    public void loadGuestOA(ActionEvent actionEvent) {
    }

    @Deprecated
    public void registerMemberOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void searchmembershipOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void updateStatusOA(ActionEvent actionEvent) {
    }
}