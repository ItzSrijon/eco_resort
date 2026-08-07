package com.summer.section1.group7.eco_resort.Piya.model;

import com.summer.section1.group7.eco_resort.User;

import java.io.Serializable;
import java.time.LocalDate;


public class CheckOutRecord implements Serializable {


    private String recordId;
    private String reservationId;

    private User user;
    private Room room;

    private LocalDate checkOutDate;
    private String departureTime;

    private String status;



    public CheckOutRecord(String recordId,
                          String reservationId,
                          User user,
                          Room room,
                          LocalDate checkOutDate,
                          String departureTime,
                          String status){


        this.recordId=recordId;
        this.reservationId=reservationId;
        this.user=user;
        this.room=room;
        this.checkOutDate=checkOutDate;
        this.departureTime=departureTime;
        this.status=status;

    }



    public String getRecordId(){
        return recordId;
    }


    public String getReservationId(){
        return reservationId;
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
        return room.getRoomId();
    }


    public LocalDate getCheckOutDate(){
        return checkOutDate;
    }


    public String getDepartureTime(){
        return departureTime;
    }


    public String getStatus(){
        return status;
    }


    @Override
    public String toString(){

        return recordId+" "+user.getName()+" "+status;
    }

}