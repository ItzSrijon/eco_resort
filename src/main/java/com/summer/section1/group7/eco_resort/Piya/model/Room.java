package com.summer.section1.group7.eco_resort.Piya.model;

import java.io.Serializable;

public class Room implements Serializable {



    private String roomId;
    private String roomType;
    private double roomPrice;
    private int capacity;
    private String facilities;
    private String availability;


    public Room(String roomId,
                String roomType,
                double roomPrice,
                int capacity,
                String facilities,
                String availability){

        this.roomId = roomId;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.capacity = capacity;
        this.facilities = facilities;
        this.availability = availability;
    }


    public String getRoomId(){
        return roomId;
    }


    public String getRoomType(){
        return roomType;
    }


    public void setRoomType(String roomType){
        this.roomType = roomType;
    }


    public double getRoomPrice(){
        return roomPrice;
    }


    public void setRoomPrice(double roomPrice){
        this.roomPrice = roomPrice;
    }


    public int getCapacity(){
        return capacity;
    }


    public void setCapacity(int capacity){
        this.capacity = capacity;
    }


    public String getFacilities(){
        return facilities;
    }


    public void setFacilities(String facilities){
        this.facilities = facilities;
    }


    public String getAvailability(){
        return availability;
    }


    public void setAvailability(String availability){
        this.availability = availability;
    }


    @Override
    public String toString(){

        return "Room{" +
                "roomId='" + roomId + '\'' +
                ", roomType='" + roomType + '\'' +
                ", roomPrice=" + roomPrice +
                ", capacity=" + capacity +
                ", facilities='" + facilities + '\'' +
                ", availability='" + availability + '\'' +
                '}';
    }
}