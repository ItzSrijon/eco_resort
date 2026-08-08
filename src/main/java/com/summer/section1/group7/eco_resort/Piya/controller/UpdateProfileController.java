package com.summer.section1.group7.eco_resort.Piya.controller;

import com.summer.section1.group7.eco_resort.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class UpdateProfileController {

    @FXML
    private TextField nameTF;

    @FXML
    private TextField phoneTF;

    @FXML
    private TextField emailTF;

    @FXML
    private PasswordField passwordTF;

    @FXML
    private Label messageLabel;


    private User currentUser;



    public void setCurrentUser(User user){

        currentUser = user;

        loadUserData();

    }



    @FXML
    public void initialize(){

    }



    // event-2 + event-3
    private void loadUserData(){

        if(currentUser == null)
            return;


        nameTF.setText(currentUser.getName());

        phoneTF.setText(currentUser.getPhoneNumber());

        emailTF.setText(currentUser.getEmail());

        passwordTF.setText(currentUser.getPassword());

    }





    @FXML
    public void updateButtonOA(ActionEvent event){


        if(currentUser == null){

            messageLabel.setText(
                    "User not found."
            );

            return;
        }
        String phone = phoneTF.getText();
        String email = emailTF.getText();
        String password = passwordTF.getText();

        if(phone.isEmpty() || email.isEmpty() || password.isEmpty()){
            messageLabel.setText(
                    "Please fill all fields.");

            return;

        }



        if(!phone.matches("\\d{11}")){

            messageLabel.setText("Invalid phone number.");
            return;

        }



        if(!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")){
            messageLabel.setText("Invalid email.");
            return;

        }



        currentUser.setPhoneNumber(phone);
        currentUser.setEmail(email);
        currentUser.setPassword(password);



        saveUser();



        messageLabel.setText("Profile updated successfully.");

    }





    private void saveUser(){


        ArrayList<User> users =
                new ArrayList<>();


        File file =
                new File("User.bin");



        try{


            if(file.exists()){


                ObjectInputStream ois =
                        new ObjectInputStream(
                                new FileInputStream(file));


                while(true){

                    try{

                        User user =
                                (User)ois.readObject();


                        if(user.getUserId()
                                .equals(currentUser.getUserId())){


                            users.add(currentUser);

                        }
                        else{


                            users.add(user);

                        }


                    }
                    catch(EOFException e){

                        break;

                    }

                }


                ois.close();


            }



            ObjectOutputStream oos =
                    new ObjectOutputStream(
                            new FileOutputStream(file));



            for(User user:users){

                oos.writeObject(user);

            }


            oos.close();



        }
        catch(Exception e){

            e.printStackTrace();

        }

    }




    // event-7
    @FXML
    public void refreshButtonOA(ActionEvent event){


        loadUserData();


        messageLabel.setText(
                "Profile refreshed."
        );

    }




    // event-8 + dashboard navigation
    @FXML
    public void backButtonOA(ActionEvent event){


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