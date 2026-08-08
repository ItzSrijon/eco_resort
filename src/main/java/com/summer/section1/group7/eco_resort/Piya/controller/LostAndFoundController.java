package com.summer.section1.group7.eco_resort.Piya.controller;

import com.summer.section1.group7.eco_resort.Piya.model.AppendableObjectOutputStream;
import com.summer.section1.group7.eco_resort.Piya.model.LostAndFoundItem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;

public class LostAndFoundController {

    @FXML
    private TextField itemIdTF;

    @FXML
    private TextField itemNameTF;

    @FXML
    private TextField locationTF;

    @FXML
    private TextArea descriptionTA;

    @FXML
    private ComboBox<String> categoryCB;

    @FXML
    private Label messageLabel;


    @FXML
    public void initialize(){

        categoryCB.getItems().addAll(
                "Lost",
                "Found"
        );

    }



    @FXML
    public void saveButtonOA(ActionEvent event){

        if(itemNameTF.getText().trim().isEmpty()
                || descriptionTA.getText().trim().isEmpty()
                || locationTF.getText().trim().isEmpty()
                || categoryCB.getValue()==null){

            messageLabel.setText(
                    "Please fill all details."
            );

            return;
        }


        String itemId=itemIdTF.getText().trim();


        if(itemId.isEmpty()){

            itemId="LF"+System.currentTimeMillis();

        }



        LostAndFoundItem item =
                new LostAndFoundItem(

                        itemId,

                        categoryCB.getValue(),

                        itemNameTF.getText(),

                        descriptionTA.getText(),

                        locationTF.getText(),

                        "Reported",

                        LocalDate.now()
                );


        saveRecord(item);



        messageLabel.setText(
                "Lost & Found record saved successfully."
        );


        clearFields();

    }





    private void saveRecord(LostAndFoundItem item){


        try{

            File file =
                    new File("LostAndFoundItem.bin");


            ObjectOutputStream oos;


            if(file.exists()){

                oos = new AppendableObjectOutputStream(
                                new FileOutputStream(file,true)
                        );

            }
            else{

                oos =
                        new ObjectOutputStream(
                                new FileOutputStream(file)
                        );

            }


            oos.writeObject(item);

            oos.close();


        }
        catch(Exception e){

            e.printStackTrace();

        }

    }





    private void clearFields(){

        itemIdTF.clear();

        itemNameTF.clear();

        descriptionTA.clear();

        locationTF.clear();

        categoryCB.setValue(null);

    }





    @FXML
    public void backButtonOA(ActionEvent event){


        try{


            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/com/summer/section1/group7/eco_resort/Piya/SecurityOfficerDashboard.fxml"
                            ));


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