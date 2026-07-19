package com.summer.section1.group7.eco_resort.Nazmun.Model;

public class Room {
    private final javafx.beans.property.SimpleStringProperty roomNo;
    private final javafx.beans.property.SimpleStringProperty roomType;
    private final javafx.beans.property.SimpleDoubleProperty currentRate;
    private final javafx.beans.property.SimpleStringProperty occupancyStatus;

    public Room(String roomNo, String roomType, double currentRate, String occupancyStatus) {
        this.roomNo = new javafx.beans.property.SimpleStringProperty(roomNo);
        this.roomType = new javafx.beans.property.SimpleStringProperty(roomType);
        this.currentRate = new javafx.beans.property.SimpleDoubleProperty(currentRate);
        this.occupancyStatus = new javafx.beans.property.SimpleStringProperty(occupancyStatus);
    }

    public String getRoomNo() { return roomNo.get(); }
    public String getRoomType() { return roomType.get(); }
    public double getCurrentRate() { return currentRate.get(); }
    public String getOccupancyStatus() { return occupancyStatus.get(); }

    public void setCurrentRate(double rate) { this.currentRate.set(rate); }
    public void setOccupancyStatus(String status) { this.occupancyStatus.set(status); }

    public javafx.beans.property.SimpleStringProperty roomNoProperty() { return roomNo; }
    public javafx.beans.property.SimpleStringProperty roomTypeProperty() { return roomType; }
    public javafx.beans.property.SimpleDoubleProperty currentRateProperty() { return currentRate; }
    public javafx.beans.property.SimpleStringProperty occupancyStatusProperty() { return occupancyStatus; }
}