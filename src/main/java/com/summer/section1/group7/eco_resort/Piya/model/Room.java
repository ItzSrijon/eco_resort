package com.summer.section1.group7.eco_resort.Piya.model;

public class Room {

    private final int roomId;
    private String roomType;
    private double price;
    private int capacity;
    private boolean availability;

    public Room(int roomId, String roomType, double price, int capacity, boolean availability) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.price = price;
        this.capacity = capacity;
        this.availability = availability;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPrice() {
        return price;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
    public void viewRoomDetails() {

        System.out.println("Displaying room details.");

    }

    public void updateAvailability(boolean availability) {

        this.availability = availability;

        System.out.println("Room availability updated.");

    }
}
