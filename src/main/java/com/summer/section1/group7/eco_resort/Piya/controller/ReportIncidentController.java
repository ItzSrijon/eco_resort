package com.summer.section1.group7.eco_resort.Piya.controller;

import com.summer.section1.group7.eco_resort.User;
import com.summer.section1.group7.eco_resort.Piya.model.AppendableObjectOutputStream;
import com.summer.section1.group7.eco_resort.Piya.model.Incident;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;

public class ReportIncidentController {

    @FXML
    private TextField titleTF, locationTF;

    @FXML
    private TextArea descriptionTA;

    @FXML
    private ComboBox<String> incidentTypeCB;

    @FXML
    private Label messageLabel;

    private User currentUser;


    public void setCurrentUser(User user){
        this.currentUser = user;
    }


    @FXML
    public void initialize(){

        incidentTypeCB.getItems().addAll(
                "Unauthorized Access",
                "Emergency",
                "Suspicious Activity",
                "Theft",
                "Other"
        );

    }



    @FXML
    public void submitButtonOA(ActionEvent event){

        String title = titleTF.getText();
        String location = locationTF.getText();
        String description = descriptionTA.getText();
        String type = incidentTypeCB.getValue();


        if(title.isEmpty() ||
                location.isEmpty() ||
                description.isEmpty() ||
                type == null){

            messageLabel.setText(
                    "Please fill all fields."
            );

            return;
        }



        String id = "IN" + System.currentTimeMillis();


        Incident incident = new Incident(

                id,
                title,
                description,
                type,
                location,
                LocalDate.now(),
                currentUser,
                "Pending"

        );



        saveIncident(incident);



        messageLabel.setText(
                "Incident reported successfully.\nID: " + id
        );



        clearFields();

    }




    private void saveIncident(Incident incident){


        try{

            File file = new File("Incident.bin");


            ObjectOutputStream oos;


            if(file.exists()){

                oos = new AppendableObjectOutputStream(
                        new FileOutputStream(file,true)
                );

            }
            else{

                oos = new ObjectOutputStream(
                        new FileOutputStream(file)
                );

            }



            oos.writeObject(incident);

            oos.close();


        }
        catch(Exception e){

            e.printStackTrace();

            messageLabel.setText(
                    "Unable to save incident."
            );

        }

    }





    private void clearFields(){

        titleTF.clear();

        locationTF.clear();

        descriptionTA.clear();

        incidentTypeCB.setValue(null);

    }





    @FXML
    public void backButtonOA(ActionEvent event){


        try{


            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/com/summer/section1/group7/eco_resort/Piya/SecurityDashboard.fxml"
                            )
                    );



            Scene scene =
                    new Scene(loader.load());



            SecurityDashboardController controller =
                    loader.getController();


            controller.setCurrentUser(currentUser);



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