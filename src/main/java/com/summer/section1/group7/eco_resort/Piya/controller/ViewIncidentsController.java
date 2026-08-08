package com.summer.section1.group7.eco_resort.Piya.controller;

import com.summer.section1.group7.eco_resort.Piya.model.Incident;

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


public class ViewIncidentsController {


    @FXML
    private TableView<Incident> incidentTableView;

    @FXML
    private TableColumn<Incident,String> incidentIdTC;

    @FXML
    private TableColumn<Incident,String> incidentTitleTC;

    @FXML
    private TableColumn<Incident,String> incidentTypeTC;

    @FXML
    private TableColumn<Incident,LocalDate> reportingDateTC;

    @FXML
    private TableColumn<Incident,String> statusTC;

    @FXML
    private Label messageLabel;


    private ObservableList<Incident> incidentList =
            FXCollections.observableArrayList();



    @FXML
    public void initialize(){

        incidentIdTC.setCellValueFactory(
                new PropertyValueFactory<>("incidentId"));

        incidentTitleTC.setCellValueFactory(
                new PropertyValueFactory<>("title"));

        incidentTypeTC.setCellValueFactory(
                new PropertyValueFactory<>("category"));

        reportingDateTC.setCellValueFactory(
                new PropertyValueFactory<>("date"));

        statusTC.setCellValueFactory(
                new PropertyValueFactory<>("status"));


        incidentTableView.setItems(incidentList);

        loadIncidents();

    }



    public void loadIncidents(){

        File file=new File("Incident.bin");


        if(!file.exists()){

            messageLabel.setText(
                    "No incident history found.");

            return;
        }


        try(ObjectInputStream ois=
                    new ObjectInputStream(
                            new FileInputStream(file))){

            while(true){

                try{

                    Incident incident=
                            (Incident)ois.readObject();

                    incidentList.add(incident);

                }
                catch(EOFException e){

                    break;

                }

            }

            messageLabel.setText(
                    "Incident history loaded.");

        }
        catch(Exception e){

            e.printStackTrace();

            messageLabel.setText(
                    "Unable to load incidents.");

        }

    }



    @FXML
    public void refreshButtonOA(ActionEvent event){

        incidentList.clear();

        loadIncidents();

        messageLabel.setText(
                "Incident history refreshed.");

    }



    @FXML
    public void backButtonOA(ActionEvent event){

        try{

            FXMLLoader loader=
                    new FXMLLoader(
                            getClass().getResource(
                                    "/com/summer/section1/group7/eco_resort/Piya/SecurityDashboard.fxml"));


            Scene scene=
                    new Scene(loader.load());


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