package com.summer.section1.group7.eco_resort.Piya.controller;

import com.summer.section1.group7.eco_resort.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;


public class GuestDashboardController {


    private User currentUser;



    public void setCurrentUser(User user){

        this.currentUser = user;

    }



    public User getCurrentUser(){

        return currentUser;

    }




    @FXML
    public void initialize(){

    }





    @FXML
    public void updateProfileButtonOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/summer/section1/group7/eco_resort/Piya/updateProfile.fxml"));


        Scene scene = new Scene(loader.load());


        UpdateProfileController controller = loader.getController();


        controller.setCurrentUser(currentUser);



        Stage stage =
                (Stage)((Node)actionEvent.getSource())
                        .getScene()
                        .getWindow();



        stage.setScene(scene);

        stage.show();

    }





    @FXML
    public void submitFeedbackButtonOA(ActionEvent actionEvent)
            throws IOException {


        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource(
                                "/com/summer/section1/group7/eco_resort/Piya/submitFeedback.fxml"
                        )
                );


        Scene scene =
                new Scene(loader.load());


        SubmitFeedbackController controller =
                loader.getController();


        controller.setCurrentUser(currentUser);



        Stage stage =
                (Stage)((Node)actionEvent.getSource())
                        .getScene()
                        .getWindow();



        stage.setScene(scene);

        stage.show();

    }






    @FXML
    public void browseActivitiesButtonOA(ActionEvent actionEvent)
            throws IOException {


        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource(
                                "/com/summer/section1/group7/eco_resort/Piya/browseActivities.fxml"
                        )
                );


        Scene scene =
                new Scene(loader.load());



        BrowseActivitiesController controller =
                loader.getController();


        controller.setCurrentUser(currentUser);




        Stage stage =
                (Stage)((Node)actionEvent.getSource())
                        .getScene()
                        .getWindow();



        stage.setScene(scene);

        stage.show();

    }







    @FXML
    public void reservationHistoryButtonOA(ActionEvent actionEvent)
            throws IOException {



        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource(
                                "/com/summer/section1/group7/eco_resort/Piya/reservationHistory.fxml"
                        )
                );


        Scene scene =
                new Scene(loader.load());



        ReservationHistoryController controller =
                loader.getController();



        controller.setCurrentUser(currentUser);




        Stage stage =
                (Stage)((Node)actionEvent.getSource())
                        .getScene()
                        .getWindow();



        stage.setScene(scene);

        stage.show();


    }







    @FXML
    public void bookRoomButtonOA(ActionEvent actionEvent)
            throws IOException {



        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource(
                                "/com/summer/section1/group7/eco_resort/Piya/RoomReservation.fxml"
                        )
                );



        Scene scene =
                new Scene(loader.load());



        RoomReservationController controller =
                loader.getController();



        controller.setCurrentUser(currentUser);




        Stage stage =
                (Stage)((Node)actionEvent.getSource())
                        .getScene()
                        .getWindow();



        stage.setScene(scene);

        stage.show();

    }







    @FXML
    public void logoutButtonOA(ActionEvent actionEvent)
            throws IOException {



        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource(
                                "/com/summer/section1/group7/eco_resort/Piya/Login.fxml"
                        )
                );



        Scene scene =
                new Scene(loader.load());



        Stage stage =
                (Stage)((Node)actionEvent.getSource())
                        .getScene()
                        .getWindow();



        stage.setScene(scene);

        stage.show();

    }

}