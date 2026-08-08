package com.summer.section1.group7.eco_resort.Nazmun.Model;

import java.io.Serializable;

public class Room implements Serializable {
    private String roomId, roomType, occupancyStatus = "Vacant";
    private double rate;

    public Room(String roomId, String roomType, double rate) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.rate = rate;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getOccupancyStatus() {
        return occupancyStatus;
    }

    public void setOccupancyStatus(String occupancyStatus) {
        this.occupancyStatus = occupancyStatus;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId='" + roomId + '\'' +
                ", roomType='" + roomType + '\'' +
                ", occupancyStatus='" + occupancyStatus + '\'' +
                '}';
    }
}