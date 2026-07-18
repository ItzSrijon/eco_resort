package Piya.repository;

import Piya.model.Feedback;

import java.util.ArrayList;

public class FeedbackRepository {

    private ArrayList<Feedback> feedbackList;

    public FeedbackRepository() {

        feedbackList = new ArrayList<>();

    }

    public void addFeedback(Feedback feedback) {

        feedbackList.add(feedback);

    }

    public ArrayList<Feedback> getFeedbackList() {

        return feedbackList;

    }

}