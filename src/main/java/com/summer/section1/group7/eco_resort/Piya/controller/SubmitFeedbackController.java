package com.summer.section1.group7.eco_resort.Piya.controller;

import com.summer.section1.group7.eco_resort.Piya.model.Feedback;
import com.summer.section1.group7.eco_resort.Piya.repository.FeedbackRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class SubmitFeedbackController {

    @FXML
    private ComboBox<Integer> ratingCB;

    @FXML
    private TextArea feedbackTA;

    @FXML
    private Label messageLabel;

    private FeedbackRepository feedbackRepository = new FeedbackRepository();

    @FXML
    public void initialize() {

        ratingCB.getItems().addAll(
                1, 2, 3, 4, 5
        );

    }

    @FXML
    public void submitButtonOA(ActionEvent actionEvent) {

        Integer rating = ratingCB.getValue();

        String comment = feedbackTA.getText();

        if (rating == null || comment.isEmpty()) {

            messageLabel.setText("Please fill up all fields.");

            return;

        }

        int feedbackId = feedbackRepository.getFeedbackList().size() + 1;

        Feedback feedback = new Feedback(

                feedbackId,
                rating,
                comment

        );

        feedbackRepository.addFeedback(feedback);

        messageLabel.setText("Feedback Submitted Successfully.");

        ratingCB.setValue(null);

        feedbackTA.clear();

    }

    @FXML
    public void backButtonOA(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/summer/section1/group7/eco_resort/Piya/guestDashboard.fxml"));

        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) messageLabel.getScene().getWindow();

        stage.setScene(scene);

        stage.show();

    }

}