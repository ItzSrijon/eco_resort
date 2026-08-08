package com.summer.section1.group7.eco_resort.Nazmun.Model;

public class WasteRecord {
    private String itemName, reason;
    private double quantity;

    public WasteRecord(String itemName, double quantity, String reason) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.reason = reason;
    }

    public String getItemName() {
        return itemName;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getReason() {
        return reason;
    }
}