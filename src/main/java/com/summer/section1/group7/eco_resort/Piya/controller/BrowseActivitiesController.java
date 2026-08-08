package com.summer.section1.group7.eco_resort.Piya.controller;

import com.summer.section1.group7.eco_resort.User;
import com.summer.section1.group7.eco_resort.Piya.model.Activity;

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

public class BrowseActivitiesController {

    @FXML
    private TableView<Activity> activityTV;

    @FXML
    private TableColumn<Activity,String> activityNameTC;

    @FXML
    private TableColumn<Activity,String> TimeTC;

    @FXML
    private TableColumn<Activity,Double> priceTC;

    @FXML
    private TableColumn<Activity,String> StatusTC;

    @FXML
    private TableColumn<Activity,Integer> limitTC;


    @FXML
    private TextField searchActivityTF;

    @FXML
    private ComboBox<String> activityCB;

    @FXML
    private TextArea descriptionTA;

    @FXML
    private Label messageLabel;


    private User currentUser;


    private ObservableList<Activity> activityList =
            FXCollections.observableArrayList();

    private ObservableList<Activity> searchedList =
            FXCollections.observableArrayList();



    public void setCurrentUser(User user){
        currentUser = user;
    }



    @FXML
    public void initialize(){

        activityNameTC.setCellValueFactory(
                new PropertyValueFactory<>("activityName"));

        TimeTC.setCellValueFactory(
                new PropertyValueFactory<>("schedule"));

        priceTC.setCellValueFactory(
                new PropertyValueFactory<>("price"));

        StatusTC.setCellValueFactory(
                new PropertyValueFactory<>("status"));

        limitTC.setCellValueFactory(
                new PropertyValueFactory<>("capacity"));


        loadActivities();

        activityTV.setItems(activityList);


        activityTV.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs,oldValue,newValue)->{

                    if(newValue != null)
                        showActivityDetails(newValue);

                });


        activityCB.getItems().addAll(
                "All",
                "Available",
                "Full"
        );


        activityCB.setOnAction(e -> filterActivities());

    }



    // Retrieve activities from Activity.bin
    private void loadActivities(){

        File file = new File("Activity.bin");


        if(!file.exists()){

            messageLabel.setText(
                    "No activity data found.");

            return;
        }


        try(ObjectInputStream ois =
                    new ObjectInputStream(
                            new FileInputStream(file))) {


            while(true){

                try{

                    Activity activity =
                            (Activity) ois.readObject();

                    activityList.add(activity);

                }
                catch(EOFException e){

                    break;

                }

            }


        }
        catch(Exception e){

            e.printStackTrace();

            messageLabel.setText(
                    "Failed to load activities.");

        }

    }





    @FXML
    public void searchActivityButtonOA(ActionEvent event){

        searchedList.clear();


        String text =
                searchActivityTF.getText()
                        .toLowerCase();


        for(Activity activity : activityList){

            if(activity.getActivityName()
                    .toLowerCase()
                    .contains(text)){

                searchedList.add(activity);

            }

        }


        if(searchedList.isEmpty()){

            messageLabel.setText(
                    "No activities found.");

            activityTV.setItems(activityList);

        }
        else{

            activityTV.setItems(searchedList);

            messageLabel.setText(
                    "Search completed.");

        }

    }





    private void filterActivities(){

        String selected =
                activityCB.getValue();


        if(selected == null ||
                selected.equals("All")){

            activityTV.setItems(activityList);

            return;
        }


        ObservableList<Activity> filtered =
                FXCollections.observableArrayList();


        for(Activity activity : activityList){

            if(activity.getStatus()
                    .equalsIgnoreCase(selected)){

                filtered.add(activity);

            }

        }


        activityTV.setItems(filtered);

    }





    private void showActivityDetails(Activity activity){

        descriptionTA.setText(

                "Activity Name: "
                        + activity.getActivityName()

                        + "\nCategory: "
                        + activity.getCategory()

                        + "\nSchedule: "
                        + activity.getSchedule()

                        + "\nParticipant Limit: "
                        + activity.getCapacity()

                        + "\nPrice: "
                        + activity.getPrice()

                        + "\nStatus: "
                        + activity.getStatus()

                        + "\n\nDescription:\n"
                        + activity.getDescription()

        );

    }





    @FXML
    public void backButtonOA(ActionEvent event){

        try{

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/com/summer/section1/group7/eco_resort/Piya/GuestDashboard.fxml"
                            ));


            Scene scene =
                    new Scene(loader.load());


            GuestDashboardController controller =
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