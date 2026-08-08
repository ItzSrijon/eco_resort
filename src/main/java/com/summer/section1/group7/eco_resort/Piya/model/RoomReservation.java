package com.summer.section1.group7.eco_resort.Piya.model;

import com.summer.section1.group7.eco_resort.User;

import java.io.Serializable;
import java.time.LocalDate;

public class RoomReservation implements Serializable {
    private static final long serialVersionUID = 1L;


    private String reservationId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String bookingStatus;

    private User user;
    private Room room;



    public RoomReservation(String reservationId,
                           LocalDate checkInDate,
                           LocalDate checkOutDate,
                           String bookingStatus,
                           User user,
                           Room room){

        this.reservationId = reservationId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingStatus = bookingStatus;
        this.user = user;
        this.room = room;

    }



    public String getReservationId(){

        return reservationId;

    }


    public void setReservationId(String reservationId){

        this.reservationId = reservationId;

    }



    public LocalDate getCheckInDate(){

        return checkInDate;

    }



    public LocalDate getCheckOutDate(){

        return checkOutDate;

    }



    public String getBookingStatus(){

        return bookingStatus;

    }



    public void setBookingStatus(String bookingStatus){

        this.bookingStatus = bookingStatus;

    }



    public User getUser(){

        return user;

    }



    public Room getRoom(){

        return room;

    }




    public String getGuestName(){

        if(user != null){

            return user.getName();

        }

        return "";

    }




    public String getRoomId(){

        if(room != null){

            return room.getRoomId();

        }

        return "";

    }



    public String getGuestId(){

        if(user != null){

            return user.getUserId();

        }

        return "";

    }



    public String getRoomType(){

        if(room != null){

            return room.getRoomType();

        }

        return "";

    }



    public void bookRoom(){

    }



    public void viewReservationHistory(){

    }



    public void cancelReservation(){

    }



    @Override
    public String toString(){

        return "RoomReservation{" +
                "reservationId='" + reservationId + '\'' +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", bookingStatus='" + bookingStatus + '\'' +
                ", user=" + user +
                ", room=" + room +
                '}';

    }

}