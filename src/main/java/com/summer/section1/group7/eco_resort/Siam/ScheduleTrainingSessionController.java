package com.summer.section1.group7.eco_resort.Siam;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ScheduleTrainingSessionController
{
    @javafx.fxml.FXML
    private DatePicker sessionDateDP;
    @javafx.fxml.FXML
    private TextField phoneNumberTF;
    @javafx.fxml.FXML
    private TextField guestNameTF;
    @javafx.fxml.FXML
    private Button loadGuestOA11;
    @javafx.fxml.FXML
    private ComboBox<String> trainerCB;
    @javafx.fxml.FXML
    private ComboBox<String> sessionTimeCB;
    @javafx.fxml.FXML
    private TextField guestIdTF;
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

    @javafx.fxml.FXML
    public void loadGuestOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void scheduleSessionOA(ActionEvent actionEvent) {
    }
}