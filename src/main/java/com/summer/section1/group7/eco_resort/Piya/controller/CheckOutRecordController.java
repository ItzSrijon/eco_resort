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


public class CheckOutRecordController {


    @FXML
    private TableView<CheckOutRecord> checkOutTableView;


    @FXML
    private TableColumn<CheckOutRecord,String> recordIdTC,guestNameTC,roomNumberTC,timeTC;


    @FXML
    private TableColumn<CheckOutRecord,LocalDate> checkOutDateTC;


    @FXML
    private ComboBox<User> guestCB;


    @FXML
    private TextField departureTimeTF;


    @FXML
    private Label messageLabel;



    private ObservableList<CheckOutRecord> recordList =
            FXCollections.observableArrayList();


    // Added variable
    private RoomReservation selectedReservation;



    @FXML
    public void initialize(){


        recordIdTC.setCellValueFactory(
                new PropertyValueFactory<>("recordId"));


        guestNameTC.setCellValueFactory(
                new PropertyValueFactory<>("guestName"));


        roomNumberTC.setCellValueFactory(
                new PropertyValueFactory<>("roomNumber"));


        checkOutDateTC.setCellValueFactory(
                new PropertyValueFactory<>("checkOutDate"));


        timeTC.setCellValueFactory(
                new PropertyValueFactory<>("departureTime"));



        checkOutTableView.setItems(recordList);


        loadGuests();

        loadCheckOutRecords();

    }





    private void loadGuests(){


        File file=new File("User.bin");


        if(!file.exists())
            return;


        try(ObjectInputStream ois =
                    new ObjectInputStream(
                            new FileInputStream(file))){


            while(true){

                try{

                    User user=(User)ois.readObject();


                    if(user.getRole().equals("Guest"))
                        guestCB.getItems().add(user);


                }
                catch(EOFException e){

                    break;

                }

            }


        }
        catch(Exception e){

            e.printStackTrace();

        }

    }





    private void loadCheckOutRecords(){


        File file=new File("CheckOutRecord.bin");


        if(!file.exists())
            return;



        try(ObjectInputStream ois =
                    new ObjectInputStream(
                            new FileInputStream(file))){


            while(true){

                try{


                    CheckOutRecord record =
                            (CheckOutRecord)ois.readObject();


                    recordList.add(record);


                }
                catch(EOFException e){

                    break;

                }

            }


        }
        catch(Exception e){

            e.printStackTrace();

        }

    }





    @FXML
    public void checkOutButtonOA(ActionEvent event){


        User guest = guestCB.getValue();


        String time =
                departureTimeTF.getText();



        if(guest==null || time.isEmpty()){

            messageLabel.setText(
                    "Select guest and enter departure time.");

            return;
        }




        Room room=getGuestRoom(guest);



        if(room==null){


            messageLabel.setText(
                    "Reservation not found.");

            return;

        }



        room.setAvailability("Available");



        CheckOutRecord record =
                new CheckOutRecord(

                        "CO"+System.currentTimeMillis(),

                        selectedReservation.getReservationId(),

                        guest,

                        room,

                        LocalDate.now(),

                        time,

                        "Completed"

                );



        saveRecord(record);



        recordList.add(record);



        messageLabel.setText(
                "Check-out completed successfully.");



        departureTimeTF.clear();

    }





    private Room getGuestRoom(User guest){


        File file=new File("RoomReservation.bin");



        if(!file.exists())
            return null;



        try(ObjectInputStream ois =
                    new ObjectInputStream(
                            new FileInputStream(file))){



            while(true){


                try{


                    RoomReservation reservation =
                            (RoomReservation)ois.readObject();



                    if(reservation.getUser()
                            .getUserId()
                            .equals(guest.getUserId())){


                        // Store full reservation
                        selectedReservation = reservation;


                        return reservation.getRoom();

                    }


                }
                catch(EOFException e){

                    break;

                }

            }


        }
        catch(Exception e){

            e.printStackTrace();

        }



        return null;

    }





    private void saveRecord(CheckOutRecord record){


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





    @FXML
    public void refreshButtonOA(ActionEvent event){


        recordList.clear();


        loadCheckOutRecords();


        messageLabel.setText(
                "Records refreshed.");

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