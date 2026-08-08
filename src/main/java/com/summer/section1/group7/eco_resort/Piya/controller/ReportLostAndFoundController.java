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

public class ReportLostAndFoundController {

    @FXML private TextField itemNameTF;
    @FXML private TextField locationTF;
    @FXML private TextArea descriptionTA;
    @FXML private ComboBox<String> categoryCB;
    @FXML private Label messageLabel;

    @FXML
    public void initialize() {
        categoryCB.getItems().addAll(
                "Lost",
                "Found"
        );
    }

    @FXML
    public void saveButtonOA(ActionEvent event) {

        String itemName = itemNameTF.getText().trim();
        String location = locationTF.getText().trim();
        String description = descriptionTA.getText().trim();
        String category = categoryCB.getValue();

        // Event-6: Validate item details
        if (itemName.isEmpty()
                || description.isEmpty()
                || location.isEmpty()
                || category == null) {

            messageLabel.setText("Please fill all details.");
            return;
        }

        LostAndFoundItem item = new LostAndFoundItem(
                category,
                itemName,
                description,
                location,
                "Reported",
                LocalDate.now()
        );

        // Event-7: Save record in system
        if (saveRecord(item)) {

            // Event-8: Display confirmation message
            messageLabel.setText(
                    "Lost & Found record saved successfully."
            );

            clearFields();
        }
    }

    private boolean saveRecord(LostAndFoundItem item) {

        File file = new File("LostAndFoundItem.bin");

        try {
            ObjectOutputStream oos;

            if (file.exists() && file.length() > 0) {

                oos = new AppendableObjectOutputStream(
                        new FileOutputStream(file, true)
                );

            } else {

                oos = new ObjectOutputStream(
                        new FileOutputStream(file)
                );
            }

            oos.writeObject(item);
            oos.close();

            return true;

        } catch (IOException e) {

            e.printStackTrace();
            messageLabel.setText("Unable to save record.");
            return false;
        }
    }

    private void clearFields() {
        itemNameTF.clear();
        descriptionTA.clear();
        locationTF.clear();
        categoryCB.setValue(null);
    }

    // Event-9: Go back to Security Dashboard
    @FXML
    public void backButtonOA(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/summer/section1/group7/eco_resort/Piya/SecurityDashboard.fxml"
                    )
            );

            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene()
                    .getWindow();

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {

            e.printStackTrace();
            messageLabel.setText(
                    "Unable to return to dashboard."
            );
        }
    }
}