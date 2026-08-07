package com.summer.section1.group7.eco_resort.Piya.controller;

import com.summer.section1.group7.eco_resort.Piya.model.AppendableObjectOutputStream;
import com.summer.section1.group7.eco_resort.Piya.model.Guest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class GuestRegistrationController {

    @FXML private TextField guestIdTF;
    @FXML private TextField usernameTF;
    @FXML private TextField nameTF;
    @FXML private TextField phoneNumberTF;
    @FXML private TextField emailTF;
    @FXML private PasswordField passwordTF;
    @FXML private ComboBox<String> genderCB;
    @FXML private DatePicker dobDP;
    @FXML private Label messageLabel;

    @FXML
    public void initialize() {
        genderCB.getItems().addAll("Male","Female","Other");
    }

    @FXML
    public void registerButtonOA(ActionEvent event) {

        messageLabel.setText("");

        if(guestIdTF.getText().isEmpty() ||
                usernameTF.getText().isEmpty() ||
                nameTF.getText().isEmpty() ||
                phoneNumberTF.getText().isEmpty() ||
                emailTF.getText().isEmpty() ||
                passwordTF.getText().isEmpty() ||
                genderCB.getValue()==null ||
                dobDP.getValue()==null){

            messageLabel.setText("Please fill up all fields.");
            return;
        }

        if(!phoneNumberTF.getText().matches("\\d{11}")){
            messageLabel.setText("Phone number must contain 11 digits.");
            return;
        }

        if(!emailTF.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")){
            messageLabel.setText("Invalid email address.");
            return;
        }

        Guest guest = new Guest(
                guestIdTF.getText(),
                usernameTF.getText(),
                nameTF.getText(),
                phoneNumberTF.getText(),
                emailTF.getText(),
                genderCB.getValue(),
                passwordTF.getText(),
                dobDP.getValue()
        );

        File file = new File("User.bin");

        try{

            FileOutputStream fos;
            ObjectOutputStream oos;

            if(file.exists()){
                fos = new FileOutputStream(file,true);
                oos = new AppendableObjectOutputStream(fos);
            }
            else{
                fos = new FileOutputStream(file);
                oos = new ObjectOutputStream(fos);
            }

            oos.writeObject(guest);
            oos.close();

            messageLabel.setText("Guest registered successfully.");

            clearFields();

        }
        catch(Exception e){
            e.printStackTrace();
            messageLabel.setText("Registration failed.");
        }
    }

    @FXML
    public void clearButtonOA(ActionEvent event) {
        clearFields();
        messageLabel.setText("");
    }

    private void clearFields() {

        guestIdTF.clear();
        usernameTF.clear();
        nameTF.clear();
        phoneNumberTF.clear();
        emailTF.clear();
        passwordTF.clear();

        genderCB.setValue(null);
        dobDP.setValue(null);
    }
}