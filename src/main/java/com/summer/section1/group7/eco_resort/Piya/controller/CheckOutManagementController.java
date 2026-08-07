package com.summer.section1.group7.eco_resort.Piya.controller;

import com.summer.section1.group7.eco_resort.User;
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


public class CheckOutManagementController {


    @FXML
    private TableView<RoomReservation> checkoutTV;

    @FXML
    private TableColumn<RoomReservation,String> guestNameTC;

    @FXML
    private TableColumn<RoomReservation,String> reservationIdTC;

    @FXML
    private TableColumn<RoomReservation,String> roomNumberTC;

    @FXML
    private TableColumn<RoomReservation,LocalDate> checkInDateTC;

    @FXML
    private TableColumn<RoomReservation,String> bookingStatusTC;

    @FXML
    private TableColumn<RoomReservation,String> guestStatusTC;


    @FXML
    private DatePicker checkOutDateDP;

    @FXML
    private TextField departureTimeTF;

    @FXML
    private Label messageLabel;

    @FXML
    private Label roomStatusLabel;



    private ObservableList<RoomReservation> reservationList =
            FXCollections.observableArrayList();


    private RoomReservation selectedReservation;



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


        bookingStatusTC.setCellValueFactory(
                new PropertyValueFactory<>("bookingStatus"));


        guestStatusTC.setCellValueFactory(
                new PropertyValueFactory<>("guestStatus"));


        checkoutTV.setItems(reservationList);



        checkoutTV.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs,oldValue,newValue)->{

                    selectedReservation=newValue;

                });

    }




    @FXML
    public void viewGuestButtonOA(ActionEvent event){


        reservationList.clear();


        File file =
                new File("RoomReservation.bin");


        if(!file.exists()){

            messageLabel.setText(
                    "No booking record found.");

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


            messageLabel.setText(
                    "Guest records loaded.");



        }
        catch(Exception e){

            e.printStackTrace();

            messageLabel.setText(
                    "Unable to load records.");

        }

    }





    @FXML
    public void checkoutButtonOA(ActionEvent event){


        if(selectedReservation==null){


            messageLabel.setText(
                    "Select guest record first.");

            return;

        }



        if(checkOutDateDP.getValue()==null ||
                departureTimeTF.getText().trim().isEmpty()){


            messageLabel.setText(
                    "Enter checkout date and departure time.");

            return;

        }




        LocalDate checkoutDate =
                checkOutDateDP.getValue();



        if(checkoutDate.isBefore(LocalDate.now())){


            messageLabel.setText(
                    "Invalid checkout date.");

            return;

        }



        User user =
                selectedReservation.getUser();



        Room room =
                selectedReservation.getRoom();




        CheckOutRecord record =
                new CheckOutRecord(

                        "CO"+System.currentTimeMillis(),

                        selectedReservation.getReservationId(),

                        user,

                        room,

                        checkoutDate,

                        departureTimeTF.getText(),

                        "Completed"

                );



        saveCheckout(record);




        // Event-6 : Update room availability
        room.setAvailability("Available");



        // Event-7 : Update guest status
        user.setStatus("Checked Out");




        roomStatusLabel.setText(
                "Room status : Available\n"+
                        "Guest status : Checked Out"
        );



        messageLabel.setText(
                "Checkout completed successfully.");



        clearFields();


    }





    private void saveCheckout(CheckOutRecord record){


        try{


            File file =
                    new File("CheckOutRecord.bin");



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





    private void clearFields(){


        checkOutDateDP.setValue(null);

        departureTimeTF.clear();

        selectedReservation=null;

    }





    @FXML
    public void refreshButtonOA(ActionEvent event){


        reservationList.clear();

        clearFields();


        messageLabel.setText(
                "Refreshed.");


        roomStatusLabel.setText("");

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