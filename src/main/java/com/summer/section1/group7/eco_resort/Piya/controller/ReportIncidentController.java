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

    @FXML private TextField titleTF;
    @FXML private TextField locationTF;
    @FXML private TextArea descriptionTA;
    @FXML private ComboBox<String> incidentTypeCB;
    @FXML private Label messageLabel;

    private User currentUser;

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    @FXML
    public void initialize() {
        incidentTypeCB.getItems().addAll(
                "Unauthorized Access",
                "Emergency",
                "Suspicious Activity",
                "Theft",
                "Other"
        );
    }

    @FXML
    public void submitButtonOA(ActionEvent event) {

        String title = titleTF.getText().trim();
        String location = locationTF.getText().trim();
        String description = descriptionTA.getText().trim();
        String type = incidentTypeCB.getValue();

        // Event-5: Validate report fields
        if (title.isEmpty() || location.isEmpty()
                || description.isEmpty() || type == null) {

            messageLabel.setText("Please fill all fields.");
            return;
        }

        // Event-7: Generate incident ID
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

        // Event-6: Save incident record
        if (saveIncident(incident)) {

            // Event-8: Display report status
            messageLabel.setText(
                    "Incident reported successfully.\nID: " + id
            );

            clearFields();
        }
    }

    private boolean saveIncident(Incident incident) {

        File file = new File("Incident.bin");

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

            oos.writeObject(incident);
            oos.close();

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            messageLabel.setText("Unable to save incident.");
            return false;
        }
    }

    private void clearFields() {
        titleTF.clear();
        locationTF.clear();
        descriptionTA.clear();
        incidentTypeCB.setValue(null);
    }

    @FXML
    public void backButtonOA(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/summer/section1/group7/eco_resort/Piya/SecurityDashboard.fxml"
                    )
            );

            Scene scene = new Scene(loader.load());

            SecurityDashboardController controller =
                    loader.getController();

            controller.setCurrentUser(currentUser);

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Unable to return to dashboard.");
        }
    }
}