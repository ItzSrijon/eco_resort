package com.summer.section1.group7.eco_resort.Piya.controller;

import com.summer.section1.group7.eco_resort.User;
import com.summer.section1.group7.eco_resort.Piya.model.RoomReservation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;

public class SearchReservationController {

    @FXML
    private TableView<RoomReservation> searchReservationTV;

    @FXML
    private TableColumn<RoomReservation,String> guestNameTC,reservationIdTC,roomNumberTC,bookingStatusTC;

    @FXML
    private TableColumn<RoomReservation,LocalDate> checkInDateTC,checkInOutTC;

    @FXML
    private TextField searchTF;

    @FXML
    private Label messageLabel;


    private User currentUser;


    private ObservableList<RoomReservation> reservationList =
            FXCollections.observableArrayList();



    public void setCurrentUser(User user){
        this.currentUser=user;
    }


    public User getCurrentUser(){
        return currentUser;
    }




    @FXML
    public void initialize(){

        guestNameTC.setCellValueFactory(
                new PropertyValueFactory<>("guestName"));

        reservationIdTC.setCellValueFactory(
                new PropertyValueFactory<>("reservationId"));

        roomNumberTC.setCellValueFactory(
                new PropertyValueFactory<>("roomId"));

        checkInDateTC.setCellValueFactory(
                new PropertyValueFactory<>("checkInDate"));

        checkInOutTC.setCellValueFactory(
                new PropertyValueFactory<>("checkOutDate"));

        bookingStatusTC.setCellValueFactory(
                new PropertyValueFactory<>("bookingStatus"));


        searchReservationTV.setItems(reservationList);

    }




    @FXML
    public void searchButtonOA(ActionEvent event){

        String search=searchTF.getText().trim();


        if(search.isEmpty()){

            messageLabel.setText(
                    "Enter Reservation ID or Guest Name.");

            return;
        }


        reservationList.clear();


        File file=new File("RoomReservation.bin");


        if(!file.exists()){

            messageLabel.setText(
                    "No reservation records found.");

            return;
        }



        try(ObjectInputStream ois=
                    new ObjectInputStream(
                            new FileInputStream(file))) {


            while(true){

                try{

                    RoomReservation reservation=
                            (RoomReservation)ois.readObject();



                    if(reservation.getReservationId()
                            .equalsIgnoreCase(search)
                            ||
                            reservation.getGuestName()
                                    .equalsIgnoreCase(search)){


                        reservationList.add(reservation);

                    }

                }
                catch(EOFException e){

                    break;

                }

            }



            if(reservationList.isEmpty()){

                messageLabel.setText(
                        "No matching reservation found.");

            }
            else{

                messageLabel.setText(
                        "Reservation found.");

            }


        }
        catch(Exception e){

            e.printStackTrace();

            messageLabel.setText(
                    "Unable to load reservations.");

        }

    }





    @FXML
    public void backButtonOA(ActionEvent event){

        try{

            FXMLLoader loader=
                    new FXMLLoader(
                            getClass().getResource(
                                    "/com/summer/section1/group7/eco_resort/Piya/SecurityDashboard.fxml"
                            ));


            Scene scene=
                    new Scene(loader.load());


            SecurityDashboardController controller=
                    loader.getController();


            controller.setCurrentUser(currentUser);



            Stage stage=
                    (Stage)((Node)event.getSource())
                            .getScene()
                            .getWindow();


            stage.setScene(scene);

            stage.show();


        }
        catch(Exception e){

            e.printStackTrace();

        }

    }

}