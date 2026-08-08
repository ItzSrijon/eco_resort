package com.summer.section1.group7.eco_resort.Piya.model;

import com.summer.section1.group7.eco_resort.User;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckInRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private String recordId;
    private String reservationId;

    private User user;
    private Room room;

    private LocalDate checkInDate;
    private String arrivalTime;


    public CheckInRecord(String recordId,
                         String reservationId,
                         User user,
                         Room room,
                         LocalDate checkInDate,
                         String arrivalTime){

        this.recordId = recordId;
        this.reservationId = reservationId;
        this.user = user;
        this.room = room;
        this.checkInDate = checkInDate;
        this.arrivalTime = arrivalTime;
    }


    public String getRecordId(){
        return recordId;
    }


    public String getReservationId(){
        return reservationId;
    }


    public void setReservationId(String reservationId){
        this.reservationId = reservationId;
    }


    public User getUser(){
        return user;
    }


    public Room getRoom(){
        return room;
    }


    public String getGuestName(){
        return user.getName();
    }


    public String getRoomNumber(){

        return room.getRoomType();

    }


    public LocalDate getCheckInDate(){
        return checkInDate;
    }


    public String getArrivalTime(){
        return arrivalTime;
    }


    @Override
    public String toString(){

        return "CheckInRecord{" +
                "recordId='" + recordId + '\'' +
                ", reservationId='" + reservationId + '\'' +
                ", user=" + user +
                ", room=" + room +
                ", checkInDate=" + checkInDate +
                ", arrivalTime='" + arrivalTime + '\'' +
                '}';

    }
}