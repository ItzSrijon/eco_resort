package com.summer.section1.group7.eco_resort.Siam;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GymManagerDashboardController
{

    @javafx.fxml.FXML
    private AnchorPane mainPane;

    @javafx.fxml.FXML
    public void initialize() {
    }



    @javafx.fxml.FXML
    public void registerNewGymMemberOA(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("U3G1_RegisterGymMember.fxml"));
            Node node=fxmlLoader.load();
            mainPane.getChildren().setAll(node);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void recordGYmAttendanceOA(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("U3G4_RecordGymAttendance.fxml"));
            Node node=fxmlLoader.load();
            mainPane.getChildren().setAll(node);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void scheduleTrainingSessionOA(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("U3G3_ScheduleTrainingSession.fxml"));
            Node node=fxmlLoader.load();
            mainPane.getChildren().setAll(node);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void manageEquipmentUsageOA(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("U3G5_ManageEquipmentUsage.fxml"));
            Node node=fxmlLoader.load();
            mainPane.getChildren().setAll(node);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    @javafx.fxml.FXML
    public void backToLoginPageOA(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(
                    "/com/summer/section1/group7/eco_resort/Login.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void updateMembershipStatusOA(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("U3G2_updateMembershipStatus.fxml"));
            Node node=fxmlLoader.load();
            mainPane.getChildren().setAll(node);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}