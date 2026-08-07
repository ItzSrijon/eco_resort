package com.summer.section1.group7.eco_resort.Piya.controller;

import com.summer.section1.group7.eco_resort.User;
import com.summer.section1.group7.eco_resort.Piya.model.AppendableObjectOutputStream;
import com.summer.section1.group7.eco_resort.Piya.model.Feedback;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


public class SubmitFeedbackController {


    @FXML
    private ComboBox<Integer> ratingCB;


    @FXML
    private TextArea commentsTA;


    @FXML
    private Label messageLabel;



    private User currentUser;



    public void setCurrentUser(User user){

        this.currentUser = user;

    }





    @FXML
    public void initialize(){

        ratingCB.setItems(
                FXCollections.observableArrayList(
                        1,2,3,4,5
                )
        );

    }







    @FXML
    public void submitButtonOA(ActionEvent actionEvent){


        if(ratingCB.getValue()==null ||
                commentsTA.getText().isEmpty()){


            messageLabel.setText(
                    "Please complete all fields."
            );

            return;

        }



        if(currentUser == null){


            messageLabel.setText(
                    "User not found. Please login again."
            );

            return;

        }





        Feedback feedback =
                new Feedback(
                        ratingCB.getValue(),
                        commentsTA.getText(),
                        currentUser
                );



        saveFeedback(feedback);

    }








    public void saveFeedback(Feedback feedback){


        File file =
                new File("Feedback.bin");



        try{


            FileOutputStream fos;

            ObjectOutputStream oos;



            if(file.exists()){


                fos =
                        new FileOutputStream(
                                file,
                                true
                        );


                oos =
                        new AppendableObjectOutputStream(
                                fos
                        );


            }

            else{


                fos =
                        new FileOutputStream(
                                file
                        );


                oos =
                        new ObjectOutputStream(
                                fos
                        );

            }





            oos.writeObject(feedback);


            oos.close();



            messageLabel.setText(
                    "Feedback submitted successfully."
            );



            ratingCB.setValue(null);

            commentsTA.clear();



        }

        catch(Exception e){


            e.printStackTrace();


            messageLabel.setText(
                    "Feedback submission failed."
            );


        }


    }








    @FXML
    public void backButtonOA(ActionEvent actionEvent){


        try{


            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/com/summer/section1/group7/eco_resort/Piya/GuestDashboard.fxml"
                            )
                    );



            Scene scene =
                    new Scene(
                            loader.load()
                    );



            GuestDashboardController controller =
                    loader.getController();



            controller.setCurrentUser(
                    currentUser
            );




            Stage stage =
                    (Stage)((Node)actionEvent.getSource())
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