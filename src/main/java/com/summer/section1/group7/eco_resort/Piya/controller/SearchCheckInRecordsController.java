package com.summer.section1.group7.eco_resort.Piya.controller;

import com.summer.section1.group7.eco_resort.Piya.model.CheckInRecord;

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


public class SearchCheckInRecordsController {


    @FXML
    private TableView<CheckInRecord> checkInRecordTV;


    @FXML
    private TableColumn<CheckInRecord,String> guestNameTC,
            reservationIdTC,
            roomNumberTC,
            arrivalTimeTC;


    @FXML
    private TableColumn<CheckInRecord,LocalDate> checkInDateTC;


    @FXML
    private TextField searchTF;


    private ObservableList<CheckInRecord> recordList =
            FXCollections.observableArrayList();
    @FXML
    private Label messageLabel;


    @FXML
    public void initialize(){

        guestNameTC.setCellValueFactory(
                new PropertyValueFactory<>("guestName"));


        reservationIdTC.setCellValueFactory(
                new PropertyValueFactory<>("reservationId"));


        roomNumberTC.setCellValueFactory(
                new PropertyValueFactory<>("roomNumber"));


        checkInDateTC.setCellValueFactory(
                new PropertyValueFactory<>("checkInDate"));


        arrivalTimeTC.setCellValueFactory(
                new PropertyValueFactory<>("arrivalTime"));


        checkInRecordTV.setItems(recordList);

    }



    @FXML
    public void searchButtonOA(ActionEvent event){


        String search =
                searchTF.getText().trim();


        if(search.isEmpty()){

            messageLabel.setText(
                    "Enter Guest Name or Reservation ID.");

            return;
        }


        recordList.clear();


        File file =
                new File("CheckInRecord.bin");


        if(!file.exists()){

            messageLabel.setText(
                    "No check-in records found.");

            return;
        }


        try(ObjectInputStream ois =
                    new ObjectInputStream(
                            new FileInputStream(file))){


            while(true){

                try{

                    CheckInRecord record =
                            (CheckInRecord)ois.readObject();


                    if(record.getGuestName()
                            .equalsIgnoreCase(search)
                            ||
                            record.getReservationId()
                                    .equalsIgnoreCase(search)){


                        recordList.add(record);

                    }


                }
                catch(EOFException e){

                    break;

                }

            }


            if(recordList.isEmpty())

                messageLabel.setText(
                        "No matching record found.");

            else

                messageLabel.setText(
                        "Record found.");



        }
        catch(Exception e){

            e.printStackTrace();

        }


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

    @FXML
    public void refreshButtonOA(ActionEvent actionEvent) {
        searchTF.clear();
        recordList.clear();

        File file = new File("CheckInRecord.bin");

        if (!file.exists()) {
            messageLabel.setText("No check-in records found.");
            return;
        }

        try (ObjectInputStream ois =
                     new ObjectInputStream(
                             new FileInputStream(file))) {

            while (true) {
                try {
                    CheckInRecord record =
                            (CheckInRecord) ois.readObject();

                    recordList.add(record);

                } catch (EOFException e) {
                    break;
                }
            }

            if (recordList.isEmpty()) {
                messageLabel.setText("No check-in records found.");
            } else {
                messageLabel.setText("Records refreshed successfully.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Error refreshing records.");
        }

    }
}