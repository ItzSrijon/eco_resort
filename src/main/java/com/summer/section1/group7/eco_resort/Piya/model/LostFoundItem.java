package com.summer.section1.group7.eco_resort.Piya.model;

public class LostFoundItem {
    private final int itemId;
    private String itemName;
    private String description;
    private String location;
    private String status;

    public LostFoundItem(int itemId, String itemName, String description, String location, String status) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.description = description;
        this.location = location;
        this.status = status;
    }

    public int getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public void saveItem() {

        System.out.println("Lost and Found item saved.");

    }

    public void updateStatus(String status) {

        this.status = status;

        System.out.println("Item status updated.");

    }
}
