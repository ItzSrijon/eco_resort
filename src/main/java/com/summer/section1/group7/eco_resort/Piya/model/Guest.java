package com.summer.section1.group7.eco_resort.Piya.model;

import com.summer.section1.group7.eco_resort.User;

import java.io.Serializable;
import java.time.LocalDate;

public class Guest extends User implements Serializable {

    public Guest(String userId,
                 String username,
                 String name,
                 String phoneNumber,
                 String email,
                 String gender,
                 String password,
                 LocalDate dob) {

        super(userId,
                username,
                name,
                phoneNumber,
                email,
                gender,
                password,
                "Guest",
                dob,
                "Active");
    }

    public void register() {

    }

    public void bookRoom() {

    }

    public void browseActivities() {

    }

    public void viewRoomDetails() {

    }

    public void viewReservationHistory() {

    }

    public void updateProfile() {

    }

    public void submitFeedback() {

    }

    @Override
    public String toString() {
        return "Guest{" +
                "userId='" + getUserId() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", name='" + getName() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", gender='" + getGender() + '\'' +
                ", role='" + getRole() + '\'' +
                ", dob=" + getDob() +
                '}';
    }
}