package com.summer.section1.group7.eco_resort.Nazmun.Model;

public class ProfitRecord {
    private String name;
    private double sellingPrice, cost, profit, marginPercent;

    public ProfitRecord(String name, double sellingPrice, double cost) {
        this.name = name;
        this.sellingPrice = sellingPrice;
        this.cost = cost;
        this.profit = sellingPrice - cost;
        this.marginPercent = sellingPrice == 0 ? 0 : (profit / sellingPrice) * 100;
    }

    public String getName() {
        return name;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public double getCost() {
        return cost;
    }

    public double getProfit() {
        return profit;
    }

    public double getMarginPercent() {
        return marginPercent;
    }
}