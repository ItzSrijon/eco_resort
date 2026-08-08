package com.summer.section1.group7.eco_resort.Nazmun.Model;

import java.io.Serializable;

public class DailyPrepItem implements Serializable {
    private String dishId, dishName, station, assignedStaff;
    private int quantity;

    public DailyPrepItem(String dishId, String dishName, int quantity) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.quantity = quantity;
        this.station = "";
        this.assignedStaff = "";
    }

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getAssignedStaff() {
        return assignedStaff;
    }

    public void setAssignedStaff(String assignedStaff) {
        this.assignedStaff = assignedStaff;
    }

    @Override
    public String toString() {
        return "DailyPrepItem{" +
                "dishName='" + dishName + '\'' +
                ", station='" + station + '\'' +
                '}';
    }
}