package com.summer.section1.group7.eco_resort.Piya.model;

import com.summer.section1.group7.eco_resort.User;

import java.io.Serializable;

public class ActivityReservation implements Serializable {



    private String reservationId;
    private User user;

    private String activityName;
    private String schedule;
    private String bookingStatus;


    public ActivityReservation(String reservationId,
                               User user,
                               String activityName,
                               String schedule,
                               String bookingStatus) {

        this.reservationId = reservationId;
        this.user = user;
        this.activityName = activityName;
        this.schedule = schedule;
        this.bookingStatus = bookingStatus;
    }


    public String getReservationId() {
        return reservationId;
    }


    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }


    public User getUser() {
        return user;
    }


    public String getGuestName() {
        return user.getName();
    }


    public String getActivityName() {
        return activityName;
    }


    public String getSchedule() {
        return schedule;
    }


    public String getBookingStatus() {
        return bookingStatus;
    }


    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }


    public void reserveActivity() {

    }


    public void cancelActivity() {

    }


    @Override
    public String toString() {

        return "ActivityReservation{" +
                "reservationId='" + reservationId + '\'' +
                ", user=" + user +
                ", activityName='" + activityName + '\'' +
                ", schedule='" + schedule + '\'' +
                ", bookingStatus='" + bookingStatus + '\'' +
                '}';

    }
}