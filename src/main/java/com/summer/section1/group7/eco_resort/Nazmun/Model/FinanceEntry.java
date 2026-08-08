package com.summer.section1.group7.eco_resort.Nazmun.Model;

import java.io.Serializable;

public class FinanceEntry implements Serializable {
    private String type;
    private String category;
    private double amount;

    public FinanceEntry(String type, String category, double amount) {
        this.type = type;
        this.category = category;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "FinanceEntry{" +
                "type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                '}';
    }
}