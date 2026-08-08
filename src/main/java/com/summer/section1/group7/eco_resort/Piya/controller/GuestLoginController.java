package com.summer.section1.group7.eco_resort.Piya.controller;

import com.summer.section1.group7.eco_resort.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;

public class GuestLoginController {

    @FXML private TextField emailPhoneTF;
    @FXML private PasswordField passwordPF;
    @FXML private Label messageLabel;

    @FXML
    public void loginButtonOA(ActionEvent event){

        messageLabel.setText("");

        if(emailPhoneTF.getText().isEmpty() ||
                passwordPF.getText().isEmpty()){

            messageLabel.setText("Please enter all information.");
            return;
        }

        File file = new File("User.bin");

        if(!file.exists()){
            messageLabel.setText("No registered guest found.");
            return;
        }

        boolean loginSuccessful = false;

        try(ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream(file))){

            while(true){

                try{

                    User user = (User) ois.readObject();

                    if((user.getEmail().equalsIgnoreCase(emailPhoneTF.getText())
                            || user.getPhoneNumber().equals(emailPhoneTF.getText()))
                            && user.getPassword().equals(passwordPF.getText())
                            && user.getRole().equals("Guest")){

                        loginSuccessful = true;

                        messageLabel.setText("Login successful.");

                        FXMLLoader loader =
                                new FXMLLoader(getClass().getResource(
                                        "/com/summer/section1/group7/eco_resort/Piya/GuestDashboard.fxml"));

                        Scene scene = new Scene(loader.load());

                        GuestDashboardController controller =
                                loader.getController();

                        controller.setCurrentUser(user);

                        Stage stage =
                                (Stage)((Node)event.getSource())
                                        .getScene()
                                        .getWindow();

                        stage.setScene(scene);
                        stage.show();

                        break;
                    }

                }
                catch(EOFException e){
                    break;
                }

            }

            if(!loginSuccessful){
                messageLabel.setText("Invalid Email/Phone or Password.");
            }

        }
        catch(Exception e){
            e.printStackTrace();
            messageLabel.setText("Unable to login.");
        }

    }

    @FXML
    public void clearButtonOA(ActionEvent event){

        emailPhoneTF.clear();
        passwordPF.clear();
        messageLabel.setText("");

    }

    @FXML
    public void backButtonOA(ActionEvent event){

        try{

            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource(
                            "/com/summer/section1/group7/eco_resort/Piya/Login.fxml"));

            Scene scene = new Scene(loader.load());

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