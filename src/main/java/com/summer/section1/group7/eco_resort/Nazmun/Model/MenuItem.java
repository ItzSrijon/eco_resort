package com.summer.section1.group7.eco_resort.Nazmun.Model;

import java.io.Serializable;

public class MenuItem implements Serializable {
    private String itemId, name, category;
    private double price;
    private String approvalStatus = "Pending";
    private String rejectionReason = "";

    public MenuItem(String itemId, String name, String category, double price) {
        this.itemId = itemId;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "itemId='" + itemId + '\'' +
                ", name='" + name + '\'' +
                ", approvalStatus='" + approvalStatus + '\'' +
                '}';
    }
}