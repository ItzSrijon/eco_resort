package com.summer.section1.group7.eco_resort.Nazmun.Model;

public class MenuItem {
    private final javafx.beans.property.SimpleStringProperty itemName;
    private final javafx.beans.property.SimpleStringProperty category;
    private final javafx.beans.property.SimpleDoubleProperty price;

    public MenuItem(String itemName, String category, double price) {
        this.itemName = new javafx.beans.property.SimpleStringProperty(itemName);
        this.category = new javafx.beans.property.SimpleStringProperty(category);
        this.price = new javafx.beans.property.SimpleDoubleProperty(price);
    }

    public String getItemName() { return itemName.get(); }
    public String getCategory() { return category.get(); }
    public double getPrice() { return price.get(); }

    public javafx.beans.property.SimpleStringProperty itemNameProperty() { return itemName; }
    public javafx.beans.property.SimpleStringProperty categoryProperty() { return category; }
    public javafx.beans.property.SimpleDoubleProperty priceProperty() { return price; }
}