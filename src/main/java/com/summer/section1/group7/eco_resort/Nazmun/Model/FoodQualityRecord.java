package com.summer.section1.group7.eco_resort.Nazmun.Model;

public class FoodQualityRecord {
    private String dishName, station, assignedStaff;
    private int taste, presentation, portionSize;

    public FoodQualityRecord(String dishName, String station, String assignedStaff) {
        this.dishName = dishName;
        this.station = station;
        this.assignedStaff = assignedStaff;
    }

    public String getDishName() {
        return dishName;
    }

    public String getStation() {
        return station;
    }

    public String getAssignedStaff() {
        return assignedStaff;
    }

    public int getTaste() {
        return taste;
    }

    public void setTaste(int taste) {
        this.taste = taste;
    }

    public int getPresentation() {
        return presentation;
    }

    public void setPresentation(int presentation) {
        this.presentation = presentation;
    }

    public int getPortionSize() {
        return portionSize;
    }

    public void setPortionSize(int portionSize) {
        this.portionSize = portionSize;
    }
}