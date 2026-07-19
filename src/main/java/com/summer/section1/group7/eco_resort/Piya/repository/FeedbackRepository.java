package com.summer.section1.group7.eco_resort.Piya.repository;

import com.summer.section1.group7.eco_resort.Piya.model.Feedback;

import java.util.ArrayList;

public class FeedbackRepository {

    private static ArrayList<Feedback> feedbackList;

    public FeedbackRepository() {

        if (feedbackList == null) {

            feedbackList = new ArrayList<>();

        }

    }

    public void addFeedback(Feedback feedback) {

        feedbackList.add(feedback);

    }

    public ArrayList<Feedback> getFeedbackList() {

        return feedbackList;

    }

}