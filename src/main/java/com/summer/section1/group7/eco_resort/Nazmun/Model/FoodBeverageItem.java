package com.summer.section1.group7.eco_resort.Nazmun.Model;

public class FoodBeverageItem {
    private final javafx.beans.property.SimpleStringProperty itemName;
    private final javafx.beans.property.SimpleStringProperty category;
    private final javafx.beans.property.SimpleDoubleProperty price;
    private final javafx.beans.property.SimpleStringProperty stockStatus;

    public FoodBeverageItem(String itemName, String category, double price, String stockStatus) {
        this.itemName = new javafx.beans.property.SimpleStringProperty(itemName);
        this.category = new javafx.beans.property.SimpleStringProperty(category);
        this.price = new javafx.beans.property.SimpleDoubleProperty(price);
        this.stockStatus = new javafx.beans.property.SimpleStringProperty(stockStatus);
    }

    public String getItemName() { return itemName.get(); }
    public String getCategory() { return category.get(); }
    public double getPrice() { return price.get(); }
    public String getStockStatus() { return stockStatus.get(); }

    public void setPrice(double price) { this.price.set(price); }

    public javafx.beans.property.SimpleStringProperty itemNameProperty() { return itemName; }
    public javafx.beans.property.SimpleStringProperty categoryProperty() { return category; }
    public javafx.beans.property.SimpleDoubleProperty priceProperty() { return price; }
    public javafx.beans.property.SimpleStringProperty stockStatusProperty() { return stockStatus; }
}