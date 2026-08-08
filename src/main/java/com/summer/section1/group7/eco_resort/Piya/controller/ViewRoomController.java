package com.summer.section1.group7.eco_resort.Piya.controller;

import com.summer.section1.group7.eco_resort.Piya.model.Room;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import javafx.stage.Stage;

import java.io.*;

public class ViewRoomController {

    @FXML
    private ComboBox<String> roomTypeCB;

    @FXML
    private Label priceLabel,capacityLabel,facilitiesLabel,availabilityLabel;

    @FXML
    private TextArea roomDescriptionTA;


    private ObservableList<Room> roomList =
            FXCollections.observableArrayList();



    @FXML
    public void initialize(){

        loadRooms();


        for(Room room:roomList)
            roomTypeCB.getItems()
                    .add(room.getRoomType());


        roomTypeCB.setOnAction(e->{

            for(Room room:roomList){

                if(room.getRoomType()
                        .equals(roomTypeCB.getValue())){

                    showRoomDetails(room);
                    break;
                }
            }
        });
    }



    private void loadRooms(){

        File file=new File("Room.bin");


        if(!file.exists())
            return;


        try(ObjectInputStream ois =
                    new ObjectInputStream(
                            new FileInputStream(file))) {


            while(true){

                try{

                    Room room =
                            (Room)ois.readObject();


                    roomList.add(room);

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





    private void showRoomDetails(Room room){

        priceLabel.setText(
                String.valueOf(room.getRoomPrice()));

        capacityLabel.setText(
                String.valueOf(room.getCapacity()));

        facilitiesLabel.setText(
                room.getFacilities());

        availabilityLabel.setText(
                room.getAvailability());


        roomDescriptionTA.setText(
                "Room Type: "
                        +room.getRoomType()
                        +"\nRoom Price: "
                        +room.getRoomPrice()
                        +"\nCapacity: "
                        +room.getCapacity()
                        +"\nFacilities: "
                        +room.getFacilities()
                        +"\nStatus: "
                        +room.getAvailability()
        );
    }





    @FXML
    public void refreshButtonOA(ActionEvent event){

        roomTypeCB.setValue(null);

        priceLabel.setText("");
        capacityLabel.setText("");
        facilitiesLabel.setText("");
        availabilityLabel.setText("");
        roomDescriptionTA.clear();

    }





    @FXML
    public void backButtonOA(ActionEvent event){

        try{

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/com/summer/section1/group7/eco_resort/Piya/GuestDashboard.fxml"));


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