package com.summer.section1.group7.eco_resort.Piya.controller;

import com.summer.section1.group7.eco_resort.User;
import com.summer.section1.group7.eco_resort.Piya.model.AppendableObjectOutputStream;
import com.summer.section1.group7.eco_resort.Piya.model.Room;
import com.summer.section1.group7.eco_resort.Piya.model.RoomReservation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;

public class BookRoomController {

    @FXML
    private DatePicker checkInDP, checkOutDP;

    @FXML
    private ComboBox<Integer> guestCountCB;

    @FXML
    private ComboBox<String> roomTypeCB;

    @FXML
    private Label messageLabel;

    private Room selectedRoom;
    private User currentUser;


    public void setCurrentUser(User user){
        this.currentUser=user;
    }


    @FXML
    public void initialize(){

        roomTypeCB.getItems().addAll(
                "Single",
                "Deluxe",
                "Suite",
                "Family"
        );

        guestCountCB.getItems().addAll(
                1,2,3,4,5,6,7,8,9,10
        );
    }


    @FXML
    public void checkRoomAvailableButtonOA(ActionEvent event){

        selectedRoom=null;

        if(roomTypeCB.getValue()==null ||
                guestCountCB.getValue()==null){

            messageLabel.setText(
                    "Select room type and guest number."
            );
            return;
        }


        File file=new File("Room.bin");


        if(!file.exists()){

            messageLabel.setText(
                    "Room information not found."
            );
            return;
        }


        try(ObjectInputStream ois=
                    new ObjectInputStream(
                            new FileInputStream(file))){

            while(true){

                try{

                    Room room=(Room)ois.readObject();


                    if(room.getRoomType()
                            .equals(roomTypeCB.getValue())
                            &&
                            room.getAvailability()
                                    .equalsIgnoreCase("Available")
                            &&
                            room.getCapacity()
                                    >= guestCountCB.getValue()){

                        selectedRoom=room;
                        break;
                    }

                }
                catch(EOFException e){
                    break;
                }
            }


            if(selectedRoom==null){

                messageLabel.setText(
                        "No room available."
                );

            }
            else{

                messageLabel.setText(
                        "Room available."
                );

            }

        }
        catch(Exception e){

            e.printStackTrace();

            messageLabel.setText(
                    "Unable to check availability."
            );
        }
    }



    @FXML
    public void bookRoomButtonOA(ActionEvent event){

        if(checkInDP.getValue()==null ||
                checkOutDP.getValue()==null ||
                roomTypeCB.getValue()==null ||
                guestCountCB.getValue()==null){

            messageLabel.setText(
                    "Please fill all fields."
            );
            return;
        }


        if(!checkOutDP.getValue()
                .isAfter(checkInDP.getValue())){

            messageLabel.setText(
                    "Invalid booking dates."
            );
            return;
        }


        if(selectedRoom==null){

            messageLabel.setText(
                    "Check room availability first."
            );
            return;
        }


        if(currentUser==null){

            messageLabel.setText(
                    "User session not found."
            );
            return;
        }


        String reservationId=
                "RES"+System.currentTimeMillis();


        RoomReservation reservation=
                new RoomReservation(
                        reservationId,
                        checkInDP.getValue(),
                        checkOutDP.getValue(),
                        "Pending",
                        currentUser,
                        selectedRoom
                );


        saveReservation(reservation);


        messageLabel.setText(
                "Room booked successfully.\nID: "
                        +reservationId
        );


        checkInDP.setValue(null);
        checkOutDP.setValue(null);
        roomTypeCB.setValue(null);
        guestCountCB.setValue(null);

        selectedRoom=null;
    }



    private void saveReservation(RoomReservation reservation){

        File file=new File("RoomReservation.bin");


        try{

            FileOutputStream fos;
            ObjectOutputStream oos;


            if(file.exists()){

                fos=new FileOutputStream(file,true);
                oos=new AppendableObjectOutputStream(fos);

            }
            else{

                fos=new FileOutputStream(file);
                oos=new ObjectOutputStream(fos);

            }


            oos.writeObject(reservation);
            oos.close();

        }
        catch(Exception e){

            e.printStackTrace();

            messageLabel.setText(
                    "Failed to save reservation."
            );
        }
    }



    @FXML
    public void backButtonOA(ActionEvent event){

        try{

            FXMLLoader loader=
                    new FXMLLoader(
                            getClass().getResource(
                                    "/com/summer/section1/group7/eco_resort/Piya/GuestDashboard.fxml"
                            ));


            Scene scene=
                    new Scene(loader.load());


            GuestDashboardController controller=
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