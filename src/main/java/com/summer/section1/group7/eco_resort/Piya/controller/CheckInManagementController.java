package com.summer.section1.group7.eco_resort.Piya.controller;

import com.summer.section1.group7.eco_resort.Piya.model.RoomReservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.Comparator;

public class CheckInManagementController {

    @FXML private DatePicker scheduleDP;
    @FXML private TableView<RoomReservation> checkInManagementTV;
    @FXML private TableColumn<RoomReservation,String> guestNameTC;
    @FXML private TableColumn<RoomReservation,String> roomNoTC;
    @FXML private TableColumn<RoomReservation,LocalDate> checkInDateTC;
    @FXML private TableColumn<RoomReservation,String> statusTC;
    @FXML private Label messageLabel;

    private ObservableList<RoomReservation> reservationList =
            FXCollections.observableArrayList();


    @FXML
    public void initialize(){

        guestNameTC.setCellValueFactory(
                new PropertyValueFactory<>("guestName"));

        roomNoTC.setCellValueFactory(
                new PropertyValueFactory<>("roomNo"));

        checkInDateTC.setCellValueFactory(
                new PropertyValueFactory<>("checkInDate"));

        statusTC.setCellValueFactory(
                new PropertyValueFactory<>("bookingStatus"));

        checkInManagementTV.setItems(reservationList);
    }


    @FXML
    public void viewScheduleButtonOA(ActionEvent event){

        reservationList.clear();

        if(scheduleDP.getValue()==null){
            messageLabel.setText("Please select a date.");
            return;
        }

        loadCheckInSchedule(scheduleDP.getValue());

        messageLabel.setText(
                reservationList.isEmpty()
                        ? "No check-in found."
                        : "Schedule loaded."
        );
    }


    private void loadCheckInSchedule(LocalDate date){

        File file=new File("RoomReservation.bin");

        if(!file.exists())
            return;


        try(ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream(file))){

            while(true){

                try{

                    RoomReservation reservation =
                            (RoomReservation)ois.readObject();


                    if(reservation.getCheckInDate()!=null &&
                            reservation.getCheckInDate().equals(date)){

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
        }
    }


    @FXML
    public void sortButtonOA(ActionEvent event){

        reservationList.sort(
                Comparator.comparing(
                        RoomReservation::getCheckInDate
                ));

        checkInManagementTV.refresh();

        messageLabel.setText("Sorted by date.");
    }


    @FXML
    public void refreshButtonOA(ActionEvent event){

        scheduleDP.setValue(null);
        reservationList.clear();

        messageLabel.setText("Refreshed.");
    }


    @FXML
    public void backButtonOA(ActionEvent event){

        try{

            FXMLLoader loader=
                    new FXMLLoader(getClass().getResource(
                            "/com/summer/section1/group7/eco_resort/Piya/securityDashboard.fxml"));

            Scene scene=new Scene(loader.load());

            Stage stage=(Stage)((Node)event.getSource())
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