package com.summer.section1.group7.eco_resort.Piya.model;

import com.summer.section1.group7.eco_resort.User;

import java.io.Serializable;

public class Feedback implements Serializable {


    private int rating;
    private String comments;
    private User user;


    public Feedback(int rating, String comments, User user) {
        this.rating = rating;
        this.comments = comments;
        this.user = user;
    }


    public int getRating() {
        return rating;
    }


    public String getComments() {
        return comments;
    }


    public User getUser() {
        return user;
    }


    public void setRating(int rating) {
        this.rating = rating;
    }


    public void setComments(String comments) {
        this.comments = comments;
    }


    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Feedback{" +
                "rating=" + rating +
                ", comments='" + comments + '\'' +
                ", user=" + user +
                '}';
    }
}