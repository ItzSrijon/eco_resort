package com.summer.section1.group7.eco_resort.Piya.controller;

import com.summer.section1.group7.eco_resort.Piya.model.*;

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


public class SecurityOfficerCheckIn {


    @FXML
    private TableView<RoomReservation> checkInTV;

    @FXML
    private TableColumn<RoomReservation,String> reservationIdTC;

    @FXML
    private TableColumn<RoomReservation,String> guestNameTC;

    @FXML
    private TableColumn<RoomReservation,String> roomNumberTC;

    @FXML
    private TableColumn<RoomReservation,LocalDate> bookingDateTC;

    @FXML
    private TableColumn<RoomReservation,String> bookingStatusTC;


    @FXML
    private TextField arrivalTimeTF;

    @FXML
    private Label messageLabel;


    private ObservableList<RoomReservation> reservationList =
            FXCollections.observableArrayList();


    private RoomReservation selectedReservation;



    @FXML
    public void initialize(){


        reservationIdTC.setCellValueFactory(
                new PropertyValueFactory<>("reservationId"));


        guestNameTC.setCellValueFactory(
                new PropertyValueFactory<>("guestName"));


        roomNumberTC.setCellValueFactory(
                new PropertyValueFactory<>("roomId"));


        bookingDateTC.setCellValueFactory(
                new PropertyValueFactory<>("bookingDate"));


        bookingStatusTC.setCellValueFactory(
                new PropertyValueFactory<>("bookingStatus"));


        checkInTV.setItems(reservationList);



        checkInTV.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs,oldValue,newValue)->{

                    selectedReservation=newValue;

                });



        loadVerifiedGuests();

    }



    private void loadVerifiedGuests(){


        File file =
                new File("RoomReservation.bin");


        if(!file.exists()){

            messageLabel.setText(
                    "No guest records found.");

            return;

        }



        try(ObjectInputStream ois =
                    new ObjectInputStream(
                            new FileInputStream(file))) {



            while(true){

                try{


                    RoomReservation reservation =
                            (RoomReservation)ois.readObject();



                    if(reservation.getBookingStatus()
                            .equals("Confirmed")){


                        reservationList.add(reservation);

                    }


                }
                catch(EOFException e){

                    break;

                }

            }


        }
        catch(Exception e){

            e.printStackTrace();

            messageLabel.setText(
                    "Unable to load records.");

        }

    }





    @FXML
    public void saveButtonOA(ActionEvent event){


        if(selectedReservation==null){

            messageLabel.setText(
                    "Select verified guest first.");

            return;

        }



        String arrivalTime =
                arrivalTimeTF.getText().trim();



        if(arrivalTime.isEmpty()){

            messageLabel.setText(
                    "Enter arrival time.");

            return;

        }



        Room room =
                selectedReservation.getRoom();



        room.setAvailability("Occupied");



        CheckInRecord record =
                new CheckInRecord(

                        "CI"+System.currentTimeMillis(),

                        selectedReservation.getReservationId(),

                        selectedReservation.getUser(),

                        room,

                        LocalDate.now(),

                        arrivalTime

                );



        saveCheckIn(record);



        messageLabel.setText(
                "Check-In successful.\nID: "
                        +record.getRecordId());



        arrivalTimeTF.clear();

    }





    private void saveCheckIn(CheckInRecord record){


        try{


            File file =
                    new File("CheckInRecord.bin");


            ObjectOutputStream oos;



            if(file.exists()){


                oos =
                        new AppendableObjectOutputStream(
                                new FileOutputStream(file,true));


            }
            else{


                oos =
                        new ObjectOutputStream(
                                new FileOutputStream(file));


            }



            oos.writeObject(record);

            oos.close();


        }
        catch(Exception e){

            e.printStackTrace();

        }

    }





    @FXML
    public void refreshButtonOA(ActionEvent event){


        reservationList.clear();

        loadVerifiedGuests();

        arrivalTimeTF.clear();

        selectedReservation=null;


        messageLabel.setText(
                "List refreshed.");

    }





    @FXML
    public void backButtonOA(ActionEvent event){


        try{


            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/com/summer/section1/group7/eco_resort/Piya/SecurityDashboard.fxml"));



            Scene scene =
                    new Scene(loader.load());



            Stage stage =
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