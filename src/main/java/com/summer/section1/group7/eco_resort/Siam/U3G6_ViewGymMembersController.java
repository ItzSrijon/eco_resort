package com.summer.section1.group7.eco_resort.Siam;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class U3G6_ViewGymMembersController
{
    @javafx.fxml.FXML
    private TableColumn guestNameTC;
    @javafx.fxml.FXML
    private TableView memberTV;
    @javafx.fxml.FXML
    private TableColumn phoneNumberTC;
    @javafx.fxml.FXML
    private ComboBox memberStatusCB;
    @javafx.fxml.FXML
    private TableColumn packageTC;
    @javafx.fxml.FXML
    private TableColumn memberIdTC;
    @javafx.fxml.FXML
    private TableColumn statusTC;
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
    public void loadMembersOA(ActionEvent actionEvent) {
    }
}